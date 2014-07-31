package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Conflict ring is used for resolving conflict situations during active interpretation phases
 * Can be considered as orchestration layer for parallel interpreting machines
 * @author Oleg
 *
 */
public class VWMLConflictRing {
	private int currentGroupIndex = 0;
	private int artificialId = 0;
	private boolean initialyEmptyRing = false;
	// actual conflict ring data structure
	private List<VWMLConflictRingExecutionGroup> groupsConflictRing = new LinkedList<VWMLConflictRingExecutionGroup>();
	private List<VWMLConflictRingNode> nodesConflictRing = new LinkedList<VWMLConflictRingNode>();
	private Map<String, String> conflictDef2TermAssociation = new HashMap<String, String>();
	private VWMLConflictRingVisitor ringVisitor = null;
	// singleton implementation
	private static VWMLConflictRing s_conflictRing = null;
	
	private VWMLConflictRing() {
	}
	
	/**
	 * Simple singlton implementation
	 * @return
	 */
	public static synchronized VWMLConflictRing instance() {
		if (s_conflictRing != null) {
			return s_conflictRing;
		}
		s_conflictRing = new VWMLConflictRing();
		s_conflictRing.init();
		return s_conflictRing;
	}

	/**
	 * Links conflict with group of related conflicts
	 * @param conflict
	 * @param linkedConflicts
	 */
	public static void register(String conflict, String[] linkedConflicts) {
		VWMLConflictRing.instance().link(conflict, linkedConflicts);
	}
	
	/**
	 * Ring's initialization steps
	 */
	public void init() {
		artificialId = 0;
		currentGroupIndex = 0;
		initialyEmptyRing = false;
		// actual conflict ring data structure
		groupsConflictRing.clear();
		nodesConflictRing.clear();
		conflictDef2TermAssociation.clear();
	}
	
	/**
	 * Ring's un-initialization steps
	 */
	public void done() {
		
	}

	/**
	 * Clears all associated resources (repository, fringes, etc)
	 * @throws Exception
	 */
	public void clear() throws Exception {
		VWMLContextsRepository.instance().removeAll();
		VWMLObjectsRepository.instance().removeAll();
		removeAll();
		markAsInvalid();
	}
	
	public void removeAll() throws Exception {
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			g.clear();
		}
	}
	
	/**
	 * Resets all internal data structure allowing to start all interpreters from beginning
	 */
	public void reset() throws Exception {
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			g.reset();
		}
	}
	
	public boolean isInitialyEmptyRing() {
		return initialyEmptyRing;
	}

	public void setInitialyEmptyRing(boolean initialyEmptyRing) {
		this.initialyEmptyRing = initialyEmptyRing;
	}

	public VWMLConflictRingVisitor getRingVisitor() {
		return ringVisitor;
	}

	public void setRingVisitor(VWMLConflictRingVisitor ringVisitor) {
		this.ringVisitor = ringVisitor;
	}

	/**
	 * Normalizes conflict ring by grouping nodes
	 * @return
	 */
	public void normalize() {
		if (!isRingOperational()) {
			initialyEmptyRing = true;
		}
		if (nodesConflictRing.size() == 0 || initialyEmptyRing) {
			return;
		}
		List<VWMLConflictRingNode> toRemove = new ArrayList<VWMLConflictRingNode>();
		for(VWMLConflictRingNode node : nodesConflictRing) {
			if (node.peekInterpreter() != null && !node.isGrouped()) {
				// node is grouping node
				String nodeCtx = node.peekInterpreter().getContext().getContext();				
				// looking for candidate for group
				for(VWMLConflictRingNode candidateNode : nodesConflictRing) {
					if (!candidateNode.isMarkAsCandidatOnClone() && candidateNode != node && candidateNode.peekInterpreter() == null &&
						((String)candidateNode.getId()).startsWith(nodeCtx)) {
						candidateNode.pushInterpreter(node.peekInterpreter());
						toRemove.add(candidateNode);
						node.addToGroup(candidateNode);
					}
				}
			}
		}
		if (toRemove.size() != 0) {
			for(VWMLConflictRingNode r : toRemove) {
				nodesConflictRing.remove(r);
			}
			toRemove.clear();
		}
		// re-balancing nodes inside groups
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			g.balance();
		}
	}
	
	/**
	 * Returns 'true' in case if ring contains at least one node
	 * @return
	 */
	public boolean isRingOperational() {
		return (groupsConflictRing.size() != 0);
	}
	
	/**
	 * Returns current node on the ring and goes to next
	 * @return
	 */
	public VWMLConflictRingNode next() {
		if (groupsConflictRing.size() == 0) {
			return null;
		}
		VWMLConflictRingNode n = null;
		int stopped = 0;
		while(stopped != groupsConflictRing.size()) {
			VWMLConflictRingExecutionGroup g = groupsConflictRing.get(currentGroupIndex);
			currentGroupIndex++;
			currentGroupIndex = currentGroupIndex % groupsConflictRing.size();
			n = g.schedule();
			if (n == null) {
				stopped++;
			}
			else {
				break;
			}
		}
		if (stopped == groupsConflictRing.size()) {
			n = null;
		}
		return n;
	}
	
	/**
	 * Finds ring group by entity's context (O(N) lookup); called during initialization phase
	 * of {parallel | reactive} interpreter
	 * @param context
	 * @return
	 */
	public VWMLConflictRingExecutionGroup findGroupByEntityContext(String context, boolean instantiate) {
		VWMLConflictRingExecutionGroup g = null;
		g = lookupGroupByContext(context);
		if (g == null && instantiate) {
			g = buildArtificialGroupAndAssociatedNode(context);
		}
		return g;
	}
	
	/**
	 * Returns name of term which is associated with given conflict
	 * @param conflict
	 * @return
	 */
	public String getBoundTermByConflict(String conflict) {
		return conflictDef2TermAssociation.get(conflict);
	}
	
	/**
	 * Links conflict with group of related conflicts
	 * @param conflict
	 * @param linkedConflicts
	 */
	protected void link(String conflict, String[] linkedConflicts) {
		String c = associateBoundTermAndConflictDefinition(conflict);
		VWMLConflictRingNode node = VWMLConflictRingNode.build(c, c);
		node = addNode(null, node);
		for(String conflictName : linkedConflicts) {
			c = associateBoundTermAndConflictDefinition(conflictName);
			VWMLConflictRingNode rNode = VWMLConflictRingNode.build(c, c);
			rNode = addNode(null, rNode);
			if (!node.getLink().isLinked(rNode)) {
				node.getLink().link(rNode);
			}
		}
	}

	protected void markAsInvalid() {
		s_conflictRing = null;
	}
	
	private String associateBoundTermAndConflictDefinition(String conflict) {
		String parsedAssociation = conflict;
		String boundTermCtx = GeneralUtils.getConflictBoundTerm(conflict);
		if (boundTermCtx != null) {
			String suffix = GeneralUtils.getConflictDefinitionSuffix(conflict);
			parsedAssociation = boundTermCtx + suffix;
			conflictDef2TermAssociation.put(parsedAssociation, boundTermCtx);
		}
		return parsedAssociation;
	}
	
	private VWMLConflictRingNode addNode(VWMLConflictRingExecutionGroup g, VWMLConflictRingNode node) {
		int i = nodesConflictRing.indexOf(node);
		if (i == -1) {
			if (g == null) {
				g = attachNodeToConflictRingExecutionGroup(node, false);
			}
			else {
				g.add(node);
				groupsConflictRing.add(g);
			}
			node.setExecutionGroup(g);
			nodesConflictRing.add(node);
		}
		else {
			node = nodesConflictRing.get(i);
		}
		node.setMasterNode(node);
		return node;
	}
	
	private VWMLConflictRingExecutionGroup lookupGroupByContext(String context) { 
		VWMLConflictRingExecutionGroup g = null;
		for (VWMLConflictRingExecutionGroup group : groupsConflictRing) {
			if (group.getId().equals(context)) {
				g = group;
				break;
			}
		}
		return g;
	}
	
	private VWMLConflictRingExecutionGroup buildArtificialGroupAndAssociatedNode(String context) {
		VWMLConflictRingExecutionGroup g = null;
		String id = context + "." + "artificialId_" + artificialId;
		conflictDef2TermAssociation.put(id, context);
		g = VWMLConflictRingExecutionGroup.build(id, id);
		VWMLConflictRingNode n = VWMLConflictRingNode.build(id, id);
		addNode(g, n);
		artificialId++;
		return g;
	}
	
	/**
	 * Attaches node to execution group; the group is created in case if not found
	 * @param node
	 * @return
	 */
	private VWMLConflictRingExecutionGroup attachNodeToConflictRingExecutionGroup(VWMLConflictRingNode node, boolean instantiateArtifical) {
		VWMLConflictRingExecutionGroup g = null;
		String groupName = getBoundTermByConflict((String)node.getId());
		if (groupName == null) {
			groupName = (String)node.getId();
		}
		g = findGroupByEntityContext(groupName, instantiateArtifical);
		if (g == null) {
			g = VWMLConflictRingExecutionGroup.build(groupName, groupName);
			groupsConflictRing.add(g);
		}
		// adds master node
		g.add(node);
		return g;
	}
	
	
}
