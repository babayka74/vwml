package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
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
	private boolean master = false;
	private boolean stopped = false;
	// actual conflict ring data structure
	private List<VWMLConflictRingExecutionGroup> groupsConflictRing = new LinkedList<VWMLConflictRingExecutionGroup>();
	private List<VWMLConflictRingNode> nodesConflictRing = new LinkedList<VWMLConflictRingNode>();
	private Map<String, String> conflictDef2TermAssociation = new HashMap<String, String>();
	private VWMLConflictRingVisitor ringVisitor = null;
	private VWMLInterpreterImpl ringInitialInterpreter = null;
	
	/**
	 * Simple singlton implementation
	 * @return
	 */
	public static synchronized VWMLConflictRing instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestRing();
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
		stopped = false;
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
	 * Copies initial data structure from another ring
	 * @param from
	 */
	public void copyFrom(VWMLConflictRing from) {
		 for(VWMLConflictRingExecutionGroup g : from.getGroupsConflictRing()) {
			 groupsConflictRing.add(g.clone(this));
		 }
		 for(String k : from.getConflictDef2TermAssociation().keySet()) {
			 conflictDef2TermAssociation.put(k, from.getConflictDef2TermAssociation().get(k));
		 }
		 initialyEmptyRing = from.isInitialyEmptyRing();
	}
	
	/**
	 * Makes ring as secondary
	 * the secondary means that all masters are removed from scheduler and marked as implicit,
	 * so they are used as model for conflict resolution only
	 */
	public void ringAsSecondaryCopy() {
		 for(VWMLConflictRingExecutionGroup g : getGroupsConflictRing()) {
			 g.convertGroupToGroupWithImplicitMaster();
		 }
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

	public boolean isStopped() {
		return stopped;
	}

	public VWMLConflictRingVisitor getRingVisitor() {
		return ringVisitor;
	}

	public void setRingVisitor(VWMLConflictRingVisitor ringVisitor) {
		this.ringVisitor = ringVisitor;
	}

	public List<VWMLConflictRingExecutionGroup> getGroupsConflictRing() {
		return groupsConflictRing;
	}

	public Map<String, String> getConflictDef2TermAssociation() {
		return conflictDef2TermAssociation;
	}

	public VWMLInterpreterImpl getRingInitialInterpreter() {
		return ringInitialInterpreter;
	}

	public void setRingInitialInterpreter(VWMLInterpreterImpl ringInitialInterpreter) {
		this.ringInitialInterpreter = ringInitialInterpreter;
	}

	/**
	 * Returns number of operational nodes
	 * @return
	 */
	public int calculateNumberOfNodes() {
		int nodes = 0;
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			nodes += g.nodes();
		}
		return nodes;
	}
	
	/**
	 * Normalizes conflict ring by grouping nodes
	 * @return
	 */
	public void normalize() {
		if (!isRingOperational()) {
			initialyEmptyRing = true;
			return;
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
			setStopped(true);
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
	 * Looks for node identified by id
	 * @param id
	 * @return
	 */
	public VWMLConflictRingNode lookupNodeById(Object id) {
		VWMLConflictRingNode n = null;
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			n = g.find(id);
			if (n != null) {
				break;
			}
		}
		return n;
	}

	/**
	 * Lookups requested conflict node by id
	 * @param id
	 * @return
	 */
	public VWMLConflictRingNode findConflictNode(Object id) {
		VWMLConflictRingNode n = null;
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			n = g.findConflictNode(id);
			if (n != null) {
				break;
			}
		}
		return n;
	}
	
	/**
	 * Returns true in case if node identified by id belongs to the ring
	 * @param id
	 * @return
	 */
	public boolean belong(Object id) {
		boolean belong = false;
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			if (g.belong((String)id)) {
				belong = true;
			}
		}
		return belong;
	}

	/**
	 * Master property is set by 'parallel' interpreter in order to mark its own ring (created upon initialization phase)
	 * @return
	 */
	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	/**
	 * Activates node
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 * @throws Exception
	 */
	public void sendActivateNode(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		
	}
	
	/**
	 * Posts lock request to ring for processing
	 * @param fromRTNode
	 * @param from
	 * @param nodeId
	 */
	public void sendLockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		
	}

	/**
	 * Posts unlock request to ring for processing
	 * @param from
	 * @param nodeId
	 */
	public void sendUnlockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		
	}
	
	/**
	 * Posts 'context find' request to ring
	 * @param id
	 */
	public VWMLContext sendContextFindRequest(String id) throws Exception {
		return null;
	}

	/**
	 * Processes incoming requests
	 */
	public void processRequests() {
		
	}

	protected void setStopped(boolean stopped) {
		this.stopped = stopped;
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
		VWMLResourceHostManagerFactory.hostManagerInstance().markRingAsInvalid();
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
		g = VWMLConflictRingExecutionGroup.build(this, id, id);
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
			g = VWMLConflictRingExecutionGroup.build(this, groupName, groupName);
			groupsConflictRing.add(g);
		}
		// adds master node
		g.add(node);
		return g;
	}
	
	
}
