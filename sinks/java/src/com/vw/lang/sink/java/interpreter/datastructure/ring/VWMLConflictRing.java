package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Conflict ring is used for resolving conflict situations during active interpretation phases
 * Can be considered as orchestration layer for parallel interpreting machines
 * @author Oleg
 *
 */
public class VWMLConflictRing {
	private int currentGroupIndex = 0;
	private boolean initialyEmptyRing = false;
	// actual conflict ring data structure
	private List<VWMLConflictRingExecutionGroup> groupsConflictRing = new LinkedList<VWMLConflictRingExecutionGroup>();
	private List<VWMLConflictRingNode> nodesConflictRing = new LinkedList<VWMLConflictRingNode>();
	private static int s_artificialId = 0;
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
	public static void init() {
		
	}
	
	/**
	 * Ring's un-initialization steps
	 */
	public static void done() {
		
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

	/**
	 * Normalizes conflict ring by grouping nodes
	 * @return
	 */
	public void normalize() {
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
					if (candidateNode != node && candidateNode.peekInterpreter() == null &&
						((String)candidateNode.getId()).startsWith(nodeCtx)) {
						candidateNode.pushInterpreter(node.peekInterpreter());
						toRemove.add(candidateNode);
						node.addToGroup(candidateNode);
					}
				}
			}
		}
		for(VWMLConflictRingNode r : toRemove) {
			nodesConflictRing.remove(r);
		}
		toRemove.clear();
		groupsConflictRing.clear();
		// build execution groups based on initial conflict ring
		for(VWMLConflictRingNode node : nodesConflictRing) {
			// builds new group
			VWMLConflictRingExecutionGroup g = VWMLConflictRingExecutionGroup.build(node.getId(), node.getReadableId());
			node.setExecutionGroup(g);
			// adds master node
			g.add(node);
			groupsConflictRing.add(g);
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
	public VWMLConflictRingExecutionGroup findGroupByEntityContext(String context) {
		VWMLConflictRingExecutionGroup g = null;
		if (!isRingOperational()) {
			initialyEmptyRing = true;
		}
		g = lookupGroupByContext(context);
		if (g == null) {
			g = buildArtificialGroupAndAssociatedNode(context);
		}
		return g;
	}
	
	/**
	 * Links conflict with group of related conflicts
	 * @param conflict
	 * @param linkedConflicts
	 */
	protected void link(String conflict, String[] linkedConflicts) {
		VWMLConflictRingNode node = VWMLConflictRingNode.build(conflict, conflict);
		node = addNode(null, node);
		for(String conflictName : linkedConflicts) {
			VWMLConflictRingNode rNode = VWMLConflictRingNode.build(conflictName, conflictName);
			rNode = addNode(null, rNode);
			if (!node.getLink().isLinked(rNode)) {
				node.getLink().link(rNode);
			}
		}
	}
	
	private VWMLConflictRingNode addNode(VWMLConflictRingExecutionGroup g, VWMLConflictRingNode node) {
		int i = nodesConflictRing.indexOf(node);
		if (i == -1) {
			if (g == null) {
				g = VWMLConflictRingExecutionGroup.build(node.getId(), node.getReadableId());
			}
			g.add(node);
			node.setExecutionGroup(g);
			groupsConflictRing.add(g);
			nodesConflictRing.add(node);
		}
		else {
			node = nodesConflictRing.get(i);
		}
		return node;
	}
	
	private VWMLConflictRingExecutionGroup lookupGroupByContext(String context) { 
		VWMLConflictRingExecutionGroup g = null;
		for (VWMLConflictRingExecutionGroup group : groupsConflictRing) {
			if (((String)group.getId()).startsWith(context + ".")) {
				g = group;
				break;
			}
		}
		return g;
	}
	
	private VWMLConflictRingExecutionGroup buildArtificialGroupAndAssociatedNode(String context) {
		VWMLConflictRingExecutionGroup g = null;
		String id = context + "." + "artificialId_" + s_artificialId;
		g = VWMLConflictRingExecutionGroup.build(id, id);
		VWMLConflictRingNode n = VWMLConflictRingNode.build(id, id);
		addNode(g, n);
		s_artificialId++;
		return g;
	}
}
