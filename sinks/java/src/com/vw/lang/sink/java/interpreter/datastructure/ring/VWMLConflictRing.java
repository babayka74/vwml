package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Conflict ring is used for resolving conflict situations during active interpretation phases
 * Can be considered as orchestration layer for parallel interpreting machines
 * @author Oleg
 *
 */
public class VWMLConflictRing {
	
	protected static abstract class VWMLRingEvent {
		
		public static enum REVENT {
			LOCK,
			UNLOCK,
			CONTEXTFIND,
			ACTIVATENODE,
			BLOCK,
			RELAXTIMER,
			RECALLTIMER
		}
		
		private REVENT id;
		private boolean processed = false;
		private VWMLConflictRing ring = null;
		private VWMLConflictRingNode from = null;
		private VWMLConflictRingNode rtNode = null;
		private boolean handleAgain = false;
		private int againCounter = 0;


		public VWMLRingEvent() {
			super();
		}

		public VWMLRingEvent(REVENT id) {
			super();
			this.id = id;
		}

		public REVENT getId() {
			return id;
		}

		public void setId(REVENT id) {
			this.id = id;
		}
		
		public void setProcessed() {
			processed = true;
		}
		
		public boolean isProcessed() {
			return processed;
		}
		
		public VWMLConflictRing getRing() {
			return ring;
		}

		public void setRing(VWMLConflictRing ring) {
			this.ring = ring;
		}

		public VWMLConflictRingNode getFrom() {
			return from;
		}

		public void setFrom(VWMLConflictRingNode from) {
			this.from = from;
		}
		
		public VWMLConflictRingNode getRtNode() {
			return rtNode;
		}

		public void setRtNode(VWMLConflictRingNode rtNode) {
			this.rtNode = rtNode;
		}
		
		public boolean isHandleAgain() {
			return handleAgain;
		}

		public void setHandleAgain(boolean handleAgain) {
			this.handleAgain = handleAgain;
			againCounter++;
		}

		public int getAgainCounter() {
			return againCounter;
		}
	
		public abstract void waitFor() throws Exception;
		
		public void handle(VWMLConflictRing ring) throws Exception {
			setProcessed();
		}
	}
	
	public static class VWMLRingActivateGateEvent extends VWMLRingEvent {
		private VWMLEntity ringDestTerm;
		private VWMLEntity transportedEntity;
		private VWMLEntity handlerDestTerm;
		
		public VWMLRingActivateGateEvent(VWMLEntity ringDestTerm, VWMLEntity transportedEntity, VWMLEntity handlerDestTerm) {
			super();
			this.ringDestTerm = ringDestTerm;
			this.transportedEntity = transportedEntity;
			this.handlerDestTerm = handlerDestTerm;
		}

		public VWMLEntity getRingDestTerm() {
			return ringDestTerm;
		}
		
		public VWMLEntity getTransportedEntity() {
			return transportedEntity;
		}
		
		public VWMLEntity getHandlerDestTerm() {
			return handlerDestTerm;
		}

		@Override
		public void waitFor() throws Exception {
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			VWMLConflictRingNode node = ring.findNodeExecutingTerm(getRingDestTerm());
			if (node == null) {
				throw new Exception("Couldn't find node associated with term '" + getRingDestTerm() + "'");
			}
			VWMLOperationUtils.activateTerm(node.peekInterpreter(),
											getTransportedEntity(),
											false,
											getHandlerDestTerm(),
											"Gate_",
											"Gate",
											null);
			super.handle(ring);
		}
	}
	
	private Long uniqId;
	private int currentGroupIndex = 0;
	private int artificialId = 0;
	private boolean initialyEmptyRing = false;
	private boolean master = false;
	private boolean stopped = false;
	// not participates in search operation (for LTT only)
	private boolean hidden = false;
	// actual conflict ring data structure
	private List<VWMLConflictRingExecutionGroup> groupsConflictRing = new LinkedList<VWMLConflictRingExecutionGroup>();
	private List<VWMLConflictRingNode> nodesConflictRing = new LinkedList<VWMLConflictRingNode>();
	private Map<String, String> conflictDef2TermAssociation = new HashMap<String, String>();
	private VWMLConflictRingVisitor ringVisitor = null;
	private VWMLInterpreterImpl ringInitialInterpreter = null;
	// for ST strategy; no need queue since each node performs only one operation which can send only one event
	private List<VWMLRingEvent> deferredEventQueue = new ArrayList<VWMLRingEvent>();
	private Map<String, List<VWMLRingEvent>> nonAckGateEventQueue = new HashMap<String, List<VWMLRingEvent>>();
	
	/**
	 * Simple singlton implementation
	 * @return
	 */
	public static synchronized VWMLConflictRing instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestRing();
	}

	/**
	 * Invalidates ring's instance
	 * @return
	 */
	public static synchronized void invalidate() {
		VWMLResourceHostManagerFactory.hostManagerInstance().markRingAsInvalid();
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
		uniqId = VWMLResourceHostManagerFactory.hostManagerInstance().getUniqId();
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
	 * Ring's id
	 * @return
	 */
	public Long getId() {
		return uniqId;
	}
	
	/**
	 * Copies initial data structure from another ring
	 * @param from
	 */
	public void copyFrom(VWMLConflictRing from) {
		List<VWMLConflictRingNode> cache = new ArrayList<VWMLConflictRingNode>();
		// conflicts
		for(VWMLConflictRingNode n : from.getNodesConflictRing()) {
			VWMLConflictRingNode n1 = n.deepCloneConflictModelCached(null, cache);
			nodesConflictRing.add(n1);
		}
		// groups (on the same cache)
		for(VWMLConflictRingExecutionGroup g : from.getGroupsConflictRing()) {
			groupsConflictRing.add(g.cloneConflictModelCached(this, cache));
		}
		for(String k : from.getConflictDef2TermAssociation().keySet()) {
			conflictDef2TermAssociation.put(k, from.getConflictDef2TermAssociation().get(k));
		}
		initialyEmptyRing = from.isInitialyEmptyRing();
		cache.clear();
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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * For MT strategy only
	 * Increments number of blocked nodes (called when gate blocks node - waits for data)
	 */
	public void incrementNumOfBlockedNodes(VWMLGate gate) throws Exception  {
	}

	/**
	 * For MT strategy only
	 * Decrements number of blocked nodes (called when gate blocks node - waits for data)
	 */
	public void decrementNumOfBlockedNodes() throws Exception  {
	}
	
	/**
	 * Blocks ring's thread (actual for MT strategy only)
	 */
	public boolean blockRing(VWMLGate gate) throws Exception  {
		return false;
	}

	/**
	 * Unblocks ring's thread (actual for MT strategy only)
	 */
	public boolean unblockRing() throws Exception  {
		return false;
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
	 * Returns active node on the ring and goes to next
	 * @return
	 */
	public VWMLConflictRingNode next() {
		VWMLConflictRingNode n = scheduleNextNode();
		try {
			processRequests();
		} catch (Exception e) {
			e.printStackTrace();
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
	 * Lookups for node which executes given term
	 * @param term
	 * @return
	 */
	public VWMLConflictRingNode findNodeExecutingTerm(VWMLEntity term) {
		VWMLConflictRingNode node = null;
		for(VWMLConflictRingExecutionGroup g : groupsConflictRing) {
			node = g.findNodeExecutingTerm(term);
			if (node != null) {
				break;
			}
		}
		return node;
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
	 * Fetches transmitted data, if exists, from ring's gate; the data identified by destination term (see askActivateGate)
	 * @param ringDestTerm
	 * @return
	 */
	public VWMLRingEvent fromGate(VWMLEntity ringDestTerm) {
		VWMLRingEvent event = null;
		List<VWMLRingEvent> q = nonAckGateEventQueue.get(ringDestTerm.getId());
		if (q != null && q.size() != 0) {
			event = q.get(q.size() - 1);
			q.remove(q.size() - 1);
		}
		return event;
	}
	
	/**
	 * Returns 'true' in case if gate has transmitted data for destination term
	 * @param ringDestTerm
	 * @return
	 */
	public boolean isGateOpened(VWMLEntity ringDestTerm) {
		boolean opened = false;
		List<VWMLRingEvent> q = nonAckGateEventQueue.get(ringDestTerm.getId());
		if (q != null) {
			opened = (q.size() != 0);
		}
		return opened;
	}
	
	/**
	 * Activates gate on given ring; the gate is used in order to pass data between rings
	 * @param ringDestTerm
	 * @param transportedEntity
	 * @param handlerDestTerm
	 * @throws Exception
	 */
	public void askActivateGate(VWMLEntity ringDestTerm, VWMLEntity transportedEntity, VWMLEntity handlerDestTerm) throws Exception {
		VWMLRingActivateGateEvent event = new VWMLRingActivateGateEvent(ringDestTerm, transportedEntity, handlerDestTerm);
		if (handlerDestTerm != null) {
			addDeferredEvent(event);
		}
		else {
			toGate(event);
		}
	}
	
	/**
	 * Posts request to process relax timer's callback on specified ring
	 * @param task
	 * @throws Exception
	 */
	public void askProcessingForRelaxTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) throws Exception {
		throw new Exception("for MT strategy only");
	}

	/**
	 * Posts request to process recall timer's callback on specified ring
	 * @param task
	 * @throws Exception
	 */
	public void askProcessingForRecallTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) throws Exception {
		throw new Exception("for MT strategy only");
	}
	/**
	 * Activates node
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 * @throws Exception
	 */
	public void askActivateNode(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		throw new Exception("for MT strategy only");
	}
	
	/**
	 * Posts lock request to ring for processing
	 * @param fromRTNode
	 * @param from
	 * @param nodeId
	 */
	public void askLockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		throw new Exception("for MT strategy only");
	}

	/**
	 * Posts unlock request to ring for processing
	 * @param from
	 * @param nodeId
	 */
	public void askUnlockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		throw new Exception("for MT strategy only");
	}
	
	/**
	 * Posts 'context find' request to ring
	 * @param id
	 */
	public VWMLContext askContextFindRequest(String id) throws Exception {
		throw new Exception("for MT strategy only");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqId == null) ? 0 : uniqId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VWMLConflictRing other = (VWMLConflictRing) obj;
		if (uniqId == null) {
			if (other.uniqId != null) {
				return false;
			}
		} else if (!uniqId.equals(other.uniqId)) {
			return false;
		}
		return true;
	}

	/**
	 * Processes incoming requests
	 */
	protected void processRequests() throws Exception {
		VWMLRingEvent event = fetchDeferredEvent();
		if (event != null) {
			event.handle(this);
		}
	}

	/**
	 * Schedules next node for execution
	 * @return
	 */
	protected VWMLConflictRingNode scheduleNextNode() {
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
	
	protected List<VWMLConflictRingNode> getNodesConflictRing() {
		return nodesConflictRing;
	}

	protected void toGate(VWMLRingActivateGateEvent event) {
		List<VWMLRingEvent> q = nonAckGateEventQueue.get(event.getRingDestTerm().getId());
		if (q == null) {
			q = new ArrayList<VWMLRingEvent>();
			nonAckGateEventQueue.put((String)event.getRingDestTerm().getId(), q);
		}
		q.add(event);
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
		String id = context; // + "." + "artificialId_" + artificialId;
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
	
	private void addDeferredEvent(VWMLRingEvent event) {
		deferredEventQueue.add(event);
	}
	
	private VWMLRingEvent fetchDeferredEvent() {
		VWMLRingEvent event = null;
		if (deferredEventQueue.size() != 0) {
			event = deferredEventQueue.get(deferredEventQueue.size() - 1);
			deferredEventQueue.remove(deferredEventQueue.size() - 1);
		}
		return event;
	}
}

