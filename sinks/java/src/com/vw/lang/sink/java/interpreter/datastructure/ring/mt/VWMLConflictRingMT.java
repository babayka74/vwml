package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.operations.handlers.recall.VWMLOperationRecallTimerCallback;
import com.vw.lang.sink.java.operations.processor.operations.handlers.relax.VWMLOperationRelaxTimerCallback;

/**
 * Conflict ring for MT strategy
 * @author Oleg
 *
 */
public class VWMLConflictRingMT extends VWMLConflictRing {

	protected static class VWMLRingEventMT extends VWMLRingEvent {

		private AtomicBoolean processed = new AtomicBoolean(false);
		
		public VWMLRingEventMT() {
			super();
		}

		public VWMLRingEventMT(VWMLRingEvent.REVENT id) {
			super(id);
		}

		@Override
		public void setProcessed() {
			processed.getAndSet(true);
		}
		
		@Override
		public boolean isProcessed() {
			return processed.get();
		}
		
		@Override
		public void waitFor() throws Exception {
			int stallCounter = 0;
			if (getRtNode().peekInterpreter() == null) {
				throw new Exception("There are no any active interpretes on node '" + getRtNode().getId() + "'");
			}
			VWMLInterpreterImpl interpreter = getRtNode().peekInterpreter().getMasterInterpreter();
			if (interpreter == null) {
				throw new Exception("Master interpreter is null on node '" + getRtNode().getId() + "'");
			}
			//System.out.println("Blocked node '" + getRtNode().getId() + "'");
			interpreter.addBlockedOnInterpretation(getRtNode());
			while (!isProcessed()) {
				boolean nextStep = interpreter.oneStep();
				if (!nextStep || getRing().isStopped()) {
					break;
				}
				stallCounter++;
				if (stallCounter > 100000) {
					throw new Exception("Stalled event '" + getId() + "'; from node '" + getFrom().getId() + "' ring '" + getRing() + "' on thread '" + Thread.currentThread().getId() + "'");
				}
				Thread.sleep(0);
			}
			interpreter.removeBlockedOnInterpretation(getRtNode());
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			setProcessed();
		}
	}
	
	protected static class VWMLRingLockEvent extends VWMLRingEventMT {

		private Object nodeId;
		
		public VWMLRingLockEvent() {
			super(VWMLRingEvent.REVENT.LOCK);
		}

		public VWMLRingLockEvent(VWMLRingEvent.REVENT id) {
			super(id);
		}
		
		public Object getNodeId() {
			return nodeId;
		}

		public void setNodeId(Object nodeId) {
			this.nodeId = nodeId;
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			VWMLConflictRingNode n = ring.findConflictNode(getNodeId());
			if (n != null) {
				// means n sent lock event to getFrom() also, so we are detecting technical dead lock
				if (n.isInConflictNowWith((String)(getFrom().getId())) || isHandleAgain()) {
					//System.out.println("cross conflict '" + n.getId() + "' and '" + getFrom().getId() + "'");
					boolean r = VWMLConflictRingMTArbiter.instance().handleLockConflict(ring,
																						getRing(), // opposite
																						n,
																						getFrom(), // opposite
																						getRtNode() // opposite
																						);
					if (!r) { // not resolved
						setHandleAgain(true);
						if (getAgainCounter() > 100) {
							System.out.println("Suspicious long time for resolving cross conflict '" + n.getId() + "' and '" + getFrom().getId() + "'");
						}
						return;
					}
				}
				else
				if (n.isNodeInConflict()) {
					System.out.println("~~~");
					VWMLConflictRingMTArbiter.instance().sleepOppositeRTNodeStrategy(n, getRtNode());
				}
				else {
					n.incSigma();
					//System.out.println("+ " + n.getSigma());
				}
			}
			setHandleAgain(false);
			super.handle(ring);
		}
	}

	protected static class VWMLRingUnlockEvent extends VWMLRingEventMT {

		private Object nodeId;

		public VWMLRingUnlockEvent() {
			super(VWMLRingEvent.REVENT.UNLOCK);
		}

		public Object getNodeId() {
			return nodeId;
		}

		public void setNodeId(Object nodeId) {
			this.nodeId = nodeId;
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			VWMLConflictRingNode n = ring.findConflictNode(getNodeId());
			if (n != null) {
				n.decSigma();
				//System.out.println("- " + n.getSigma());
			}
			super.handle(ring);
		}
	}
	
	protected static class VWMLRingActivateNodeEvent extends VWMLRingEventMT {
		private VWMLInterpreterImpl interpreter;
		private VWMLEntity cloned;
		private VWMLEntity clonedSourceLft;
		
		public VWMLRingActivateNodeEvent(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) {
			super(VWMLRingEvent.REVENT.ACTIVATENODE);
			this.interpreter = interpreter;
			this.cloned = cloned;
			this.clonedSourceLft = clonedSourceLft;
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			try {
				synchronized(VWMLConflictRingMT.class) {
					VWMLOperationUtils.activateClonedTerm(ring, interpreter, cloned, clonedSourceLft);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.handle(ring);
		}
	}
	
	protected static class VWMLRingBlockEvent extends VWMLRingEventMT {

		public VWMLRingBlockEvent() {
			super(VWMLRingEvent.REVENT.BLOCK);
		}
		
		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			((VWMLConflictRingMT)ring).blockRing();
		}
	}
	
	protected static class VWMLRingProcessRelaxTimerCbkEvent extends VWMLRingEventMT {

		private VWMLInterpreterInterruptTimerDeferredTask task = null;
		
		public VWMLRingProcessRelaxTimerCbkEvent(VWMLInterpreterInterruptTimerDeferredTask task) {
			super(VWMLRingEvent.REVENT.RELAXTIMER);
			this.task = task;
		}

		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			if (task.getActiveInterpreter().getStatus() != VWMLInterpreterImpl.stopProcessing && task.getActiveInterpreter().getStatus() != VWMLInterpreterImpl.stopped) {
				VWMLOperationRelaxTimerCallback cbk = new VWMLOperationRelaxTimerCallback();
				cbk.unblockActivity(task.getActiveInterpreter());
				task.getActiveInterpreter().setDeferredTask(task);
			}			
		}		
	}

	protected static class VWMLRingProcessRecallTimerCbkEvent extends VWMLRingEventMT {

		private VWMLInterpreterInterruptTimerDeferredTask task = null;
		
		public VWMLRingProcessRecallTimerCbkEvent(VWMLInterpreterInterruptTimerDeferredTask task) {
			super(VWMLRingEvent.REVENT.RECALLTIMER);
			this.task = task;
		}

		@Override
		public void handle(VWMLConflictRing ring) throws Exception {
			if (task.getActiveInterpreter().getStatus() != VWMLInterpreterImpl.stopProcessing && task.getActiveInterpreter().getStatus() != VWMLInterpreterImpl.stopped) {
				VWMLOperationRecallTimerCallback cbk = new VWMLOperationRecallTimerCallback();
				cbk.unblockActivity(task.getActiveInterpreter());
				task.getActiveInterpreter().setDeferredTask(task);
			}			
		}		
	}
	
	private volatile boolean stopped = false;
	private ConcurrentLinkedQueue<VWMLRingEvent> eventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	private ConcurrentLinkedQueue<VWMLRingEvent> deferredEventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<VWMLRingEvent>> nonAckGateEventQueue = new ConcurrentHashMap<String, ConcurrentLinkedQueue<VWMLRingEvent>>();
	private ConcurrentHashMap<String, VWMLGate> gates = new ConcurrentHashMap<String, VWMLGate>();
	private AtomicInteger blockedNodes = new AtomicInteger(0);
	private AtomicBoolean actuallyBlocked = new AtomicBoolean(false);
	
	@Override
	public boolean isStopped() {
		return stopped;
	}
	
	/**
	 * Returns current node on the ring and goes to next
	 * @return
	 */
	@Override
	public VWMLConflictRingNode next() {
		VWMLConflictRingNode n = super.scheduleNextNode();
		if (n == null) {
			stopped = true;
		}
		// processes incoming requests which are sent from another ring
		try {
			processRequests();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	/**
	 * Increments number of blocked nodes (called when gate blocks node - waits for data)
	 */
	public void incrementNumOfBlockedNodes() {
		if (blockedNodes.get() >= calculateNumberOfNodes()) {
			return;
		}
		blockedNodes.incrementAndGet();
		System.out.println("Inc b nodes '" + blockedNodes.get() + "'; nodes '" + calculateNumberOfNodes() + "'");
		if (blockedNodes.intValue() == calculateNumberOfNodes()) {
			try {
				askToBlockRing();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Decrements number of blocked nodes (called when gate blocks node - waits for data)
	 */
	public void decrementNumOfBlockedNodes() {
		if (blockedNodes.intValue() > 0) {
			blockedNodes.decrementAndGet();
			System.out.println("Dec b nodes '" + blockedNodes.get() + "'; nodes '" + calculateNumberOfNodes() + "'");
			if (blockedNodes.intValue() < calculateNumberOfNodes()) {
				try {
					unblockRing();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Blocks ring's activity if allowed
	 */
	public boolean blockRing() throws Exception {
		boolean blocked = false;
		// do not block node in case if at least one gate is in ready state
		if (!isBlockingAllowedByGates() || doesRingHaveNonProcessedInternalMessages()) {
			System.out.println("Ring '" + this + "'; thread '" + Thread.currentThread().getId() + "' has at least one active gate; reject blocking request");
			setActuallyBlocked(false);
			return blocked;
		}
		if (isActuallyBlocked()) {
			// case when node is added during ring's expansion
			if (getInstantNumberOfBlockedNodes() >= calculateNumberOfNodes()) {
				System.out.println("Ring '" + this + "'; thread '" + Thread.currentThread().getId() + "' blocked");
				synchronized(this) {
					if (isActuallyBlocked()) {
						wait();
						// post result
						blocked = true;
					}
					else {
						System.out.println("Ring '" + this + "'; thread '" + Thread.currentThread().getId() + "' reject blocking request");
					}
				}
			}
			else {
				System.out.println("Ring '" + this + "'; thread '" + Thread.currentThread().getId() + "' reject blocking request");
				setActuallyBlocked(false);
			}
		}
		else {
			System.out.println("Ring '" + this + "'; thread '" + Thread.currentThread().getId() + "' reject blocking request");
		}
		return blocked;
	}
	
	/**
	 * Unblocks ring's thread (actual for MT strategy only)
	 */
	public boolean unblockRing() throws Exception {
		boolean unblocked = false;
		if (isActuallyBlocked()) {
			synchronized(this) {
				setActuallyBlocked(false);
				this.notifyAll();
			}
			System.out.println("Ring '" + this + "' unblocked");
			unblocked = true;
		}
		return unblocked;
	}
	
	/**
	 * Associates gate with ring
	 * @param gate
	 */
	@Override
	public void associateGate(VWMLGate gate) {
		gates.put(gate.getRegistrationKey(), gate);
	}

	/**
	 * Associates gate with ring
	 * @param gate
	 */
	@Override
	public void unAssociateGate(VWMLGate gate) {
		super.unAssociateGate(gate);
		gates.remove(gate.getRegistrationKey());
	}
	
	/**
	 * Returns 'false' in case if at least one gate is in ready state
	 * @return
	 */
	public boolean isBlockingAllowedByGates() throws Exception {
		boolean allowed = true;
		for(VWMLGate gate : gates.values()) {
			if (gate.getRing() == this) {
				if (gate.getRing().isGateOpenedByTId(gate.getRegistrationKey())) {
					allowed = false;
					break;
				}
			}
			else {
				throw new Exception("The gate '" + gate + " ' doesn't belong to ring '" + this + "'; belongs to '" + gate.getRing() + "'");
			}
		}
		return allowed;
	}
	
	/**
	 * Returns 'true' in case if ring has non-processed events
	 * @return
	 */
	public boolean doesRingHaveNonProcessedInternalMessages() {
		return (eventQueue.size() != 0 || deferredEventQueue.size() != 0);
	}
	
	/**
	 * Returns number of operational nodes
	 * @return
	 */
	@Override
	public int calculateNumberOfNodes() {
		int nodes = 0;
		synchronized(this) {
			nodes = super.calculateNumberOfNodes();
		}
		return nodes;
	}

	
	/**
	 * Returns instant number of ring's blocked nodes
	 * @return
	 */
	public int getInstantNumberOfBlockedNodes() {
		return blockedNodes.intValue();
	}
	
	/**
	 * Lookups for node which executes given term
	 * @param term
	 * @return
	 */
	@Override
	public VWMLConflictRingNode findNodeExecutingTerm(VWMLEntity term) {
		VWMLConflictRingNode node = null;
		synchronized(VWMLConflictRingMT.class) {
			node = super.findNodeExecutingTerm(term);
		}
		return node;
	}
	
	/**
	 * Lookups requested conflict node by id
	 * @param id
	 * @return
	 */
	@Override
	public VWMLConflictRingNode findConflictNode(Object id) {
		VWMLConflictRingNode n = null;
		synchronized(VWMLConflictRingMT.class) {
			n = super.findConflictNode(id);
		}
		return n;
	}
	
	/**
	 * Fetches transmitted data, if exists, from ring's gate; the data identified by destination term (see askActivateGate)
	 * @param ringDestTerm
	 * @return
	 */
	@Override
	public VWMLRingEvent fromGate(VWMLEntity ringDestTerm) {
		VWMLRingEvent event = null;
		ConcurrentLinkedQueue<VWMLRingEvent> q = nonAckGateEventQueue.get(ringDestTerm.getId());
		if (q != null) {
			event = q.poll();
		}
		return event;
	}

	/**
	 * Returns 'true' in case if gate has transmitted data for destination term
	 * @param ringDestTerm
	 * @return
	 */
	@Override
	public boolean isGateOpened(VWMLEntity ringDestTerm) {
		boolean opened = false;
		ConcurrentLinkedQueue<VWMLRingEvent> q = nonAckGateEventQueue.get(ringDestTerm.getId());
		if (q != null) {
			opened = (q.size() != 0);
		}
		return opened;
	}

	/**
	 * Posts request to blocks ring's thread (actual for MT strategy only)
	 */
	public boolean askToBlockRing() throws Exception {
		VWMLInterpreterConfiguration.RESOURCE_STRATEGY st = VWMLResourceHostManagerFactory.getResourceStrategy();
		if (st == VWMLInterpreterConfiguration.RESOURCE_STRATEGY.ST && getRingInitialInterpreter().getTimerManager().timers() != 0) {
			System.out.println("The ring '" + this + "' can't be blocked due to active timers");
		}
		else {
			setActuallyBlocked(true);
			postEvent(new VWMLRingBlockEvent());
		}
		return true;
	}
	
	/**
	 * Activates gate on given ring; the gate is used in order to pass data between rings
	 * @param ringDestTerm
	 * @param transportedEntity
	 * @param handlerDestTerm
	 * @throws Exception
	 */
	@Override
	public void askActivateGate(VWMLGate gate, VWMLEntity ringDestTerm, VWMLEntity transportedEntity, VWMLEntity handlerDestTerm) throws Exception {
		VWMLRingActivateGateEvent event = new VWMLRingActivateGateEvent(ringDestTerm, transportedEntity, handlerDestTerm);
		if (handlerDestTerm != null) {
			postEvent(event);
		}
		else {
			toGate(event);
		}
		if (gate != null) {
			gate.unblockActivity();
		}
		else {
			throw new Exception("Couldn't find gate by term '" + ringDestTerm.getId() + "'");
		}
	}
	
	/**
	 * Posts lock request to ring for processing
	 * @param fromRTNode
	 * @param from
	 * @param nodeId
	 */
	@Override
	public void askLockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		// System.out.println("Sent lock '" + from.getId() + "' to '" + nodeId + "'; thread '" + Thread.currentThread().getId() + "'");
		VWMLRingLockEvent event = new VWMLRingLockEvent();
		event.setRing(fromRTNode.getExecutionGroup().getRing());
		event.setRtNode(fromRTNode);
		event.setFrom(from);
		event.setNodeId(nodeId);
		from.inConflictNow((String)nodeId);
		sendEvent(event);
		from.deactivateConflictWith((String)nodeId);
	}

	/**
	 * Posts unlock request to ring for processing
	 * @param fromRTNode
	 * @param from
	 * @param nodeId
	 */
	@Override
	public void askUnlockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
		// System.out.println("Sent unlock '" + from.getId() + "' to '" + nodeId + "'; thread '" + Thread.currentThread().getId() + "'");
		VWMLRingUnlockEvent event = new VWMLRingUnlockEvent();
		event.setRing(fromRTNode.getExecutionGroup().getRing());
		event.setRtNode(fromRTNode);
		event.setFrom(from);
		event.setNodeId(nodeId);
		sendEvent(event);
		from.processDeferredWakeupsOnUnlock();
	}
	
	/**
	 * Activates node
	 * @param from
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 * @throws Exception
	 */
	@Override
	public void askActivateNode(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		VWMLRingActivateNodeEvent event = new VWMLRingActivateNodeEvent(interpreter, cloned, clonedSourceLft);
		event.setRtNode(interpreter.getRtNode());
		event.setRing(event.getRtNode().getExecutionGroup().getRing());
		postEvent(event);
		unblockRing();
	}

	/**
	 * Posts request to process relax timer's callback on specified ring
	 * @param task
	 * @throws Exception
	 */
	public void askProcessingForRelaxTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) throws Exception {
		VWMLRingProcessRelaxTimerCbkEvent event = new VWMLRingProcessRelaxTimerCbkEvent(task);
		event.setRtNode(task.getActiveInterpreter().getRtNode());
		event.setRing(event.getRtNode().getExecutionGroup().getRing());
		postEvent(event);
	}

	/**
	 * Posts request to process recall timer's callback on specified ring
	 * @param task
	 * @throws Exception
	 */
	public void askProcessingForRecallTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) throws Exception {
		VWMLRingProcessRecallTimerCbkEvent event = new VWMLRingProcessRecallTimerCbkEvent(task);
		event.setRtNode(task.getActiveInterpreter().getRtNode());
		event.setRing(event.getRtNode().getExecutionGroup().getRing());
		postEvent(event);
	}
	
	/**
	 * Posts 'context find' request to ring
	 * @param id
	 */
	@Override
	public VWMLContext askContextFindRequest(String id) throws Exception {
		throw new Exception("Not implemented yet for MT strategy");
	}

	public void removeTxQ(VWMLGate gate) {
		if (this.nonAckGateEventQueue.remove(gate.getRegistrationKey()) != null) {
			System.out.println("removed TxQ of gate '" + gate.getRegistrationKey() + "'");
		}
	}
	/**
	 * Processes incoming requests (called from ring's thread)
	 */
	@Override
	protected void processRequests() throws Exception {
		VWMLRingEvent event = null;
		while ((event = eventQueue.poll()) != null) {
			System.out.println("Read event '" + event + "' from '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
			event.handle(this);
			if (event.isHandleAgain()) {
				deferredEventQueue.offer(event);
				//System.out.println("event '" + event + "' from '" + this + "' should be handled again; thread '" + Thread.currentThread().getId() + "'");
			}
			// initiates thread switch
			Thread.sleep(0);
		}
		while ((event = deferredEventQueue.poll()) != null) {
			eventQueue.offer(event);
		}
	}

	protected VWMLRingEvent postEvent(VWMLRingEvent event) throws Exception {
		System.out.println("Post event '" + event + "' to '" + this + "'; thread '" + Thread.currentThread().getId() + "'; size " + eventQueue.size());
		eventQueue.offer(event);
		return event;
	}
	
	protected VWMLRingEvent sendEvent(VWMLRingEvent event) throws Exception {
		//System.out.println("Sent event '" + event + "' to '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
		eventQueue.offer(event);
		unblockRing();
		waitForResult(event);
		return event;
	}
	
	protected void waitForResult(VWMLRingEvent event) throws Exception {
		event.waitFor();
	}
	
	protected void toGate(VWMLRingActivateGateEvent event) {
		ConcurrentLinkedQueue<VWMLRingEvent> q = nonAckGateEventQueue.get(event.getRingDestTerm().getId());
		if (q == null) {
			q = new ConcurrentLinkedQueue<VWMLRingEvent>();
			nonAckGateEventQueue.put((String)event.getRingDestTerm().getId(), q);
			System.out.println("created TxQ of gate '" + event.getRingDestTerm().getId() + "'");
		}
		q.offer(event);
	}
	
	protected void setActuallyBlocked(boolean blocked) {
		actuallyBlocked.set(blocked);
	}
	
	protected boolean isActuallyBlocked() {
		return actuallyBlocked.get();
	}
	
}
