package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

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
			synchronized(ring) {
				try {
					if (((VWMLConflictRingMT)ring).isActuallyBlocked()) {
						System.out.println("Ring '" + ring + "'; thread '" + Thread.currentThread().getId() + "' blocked");
						ring.wait();
					}
					else {
						System.out.println("Ring '" + ring + "'; thread '" + Thread.currentThread().getId() + "' reject blocking request");
					}
				} catch (InterruptedException e) {
				}
			}
		}
	}
	

	private volatile boolean stopped = false;
	private ConcurrentLinkedQueue<VWMLRingEvent> eventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	private ConcurrentLinkedQueue<VWMLRingEvent> deferredEventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<VWMLRingEvent>> nonAckGateEventQueue = new ConcurrentHashMap<String, ConcurrentLinkedQueue<VWMLRingEvent>>();
	private AtomicInteger blockedNodes = new AtomicInteger(0);
	private AtomicBoolean actuallyBlocked = new AtomicBoolean(false);
	private VWMLGate blockedByGate = null;
	
	/**
	 * Puts node on wait state
	 * @param node
	 */
	public static void sleepNode(VWMLConflictRingNode node) {
		if (node != null && node.peekInterpreter() != null) {
			if (node.peekInterpreter().getObserver() != null) {
				node.peekInterpreter().getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), VWMLConflictRingNodeAutomataInputs.IN_W);
				System.out.println("rt node '" + node.getId() + "' for ring '" + node.getExecutionGroup().getRing() + "' goes to sleep");
			}
		}
	}

	/**
	 * Wakeups node which previously was put to sleep by sleepNode
	 * @param node
	 */
	public static void wakeupNode(VWMLConflictRingNode node) {
		if (node != null && node.peekInterpreter() != null) {
			if (node.peekInterpreter().getObserver() != null) {
				node.peekInterpreter().getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), null);
				System.out.println("rt node '" + node.getId() + "' for ring '" + node.getExecutionGroup().getRing() + "' waken");
			}
		}
	}
	
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
	public void incrementNumOfBlockedNodes(VWMLGate gate) throws Exception {
		if (blockedNodes.get() >= calculateNumberOfNodes()) {
			return;
		}
		blockedNodes.incrementAndGet();
		if (blockedNodes.intValue() == calculateNumberOfNodes()) {
			blockRing(gate);
		}
	}

	/**
	 * Decrements number of blocked nodes (called when gate blocks node - waits for data)
	 */
	public void decrementNumOfBlockedNodes() throws Exception {
		if (blockedNodes.intValue() > 0) {
			blockedNodes.decrementAndGet();
			if (blockedNodes.intValue() < calculateNumberOfNodes()) {
				unblockRing();
			}
		}
	}
	
	/**
	 * Blocks ring's thread (actual for MT strategy only)
	 */
	public boolean blockRing(VWMLGate gate) throws Exception {
		if (getRingInitialInterpreter().getTimerManager().timers() != 0) {
			System.out.println("The ring '" + this + "' can't be blocked due to active timers");
		}
		else {
			if (blockedByGate == null) {
				blockedByGate = gate;
				setActuallyBlocked(true);
				postEvent(new VWMLRingBlockEvent());
			}
		}
		return true;
	}

	/**
	 * Unblocks ring's thread (actual for MT strategy only)
	 */
	public boolean unblockRing() throws Exception {
		if (blockedByGate != null) {
			setActuallyBlocked(false);
			synchronized(this) {
				this.notifyAll();
			}
			blockedByGate = null;
			System.out.println("Ring '" + this + "' unblocked");
		}
		return true;
	}
	
	/**
	 * Returns number of operational nodes
	 * @return
	 */
	@Override
	public int calculateNumberOfNodes() {
		int nodes = 0;
		synchronized(VWMLConflictRingMT.class) {
			nodes = super.calculateNumberOfNodes();
		}
		return nodes;
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
	 * Activates gate on given ring; the gate is used in order to pass data between rings
	 * @param ringDestTerm
	 * @param transportedEntity
	 * @param handlerDestTerm
	 * @throws Exception
	 */
	@Override
	public void askActivateGate(VWMLEntity ringDestTerm, VWMLEntity transportedEntity, VWMLEntity handlerDestTerm) throws Exception {
		VWMLRingActivateGateEvent event = new VWMLRingActivateGateEvent(ringDestTerm, transportedEntity, handlerDestTerm);
		if (handlerDestTerm != null) {
			postEvent(event);
		}
		else {
			toGate(event);
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
		if (blockedByGate != null) {
			blockedByGate.unblockActivity();
		}
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
	
	/**
	 * Processes incoming requests (called from ring's thread)
	 */
	@Override
	protected void processRequests() throws Exception {
		VWMLRingEvent event = null;
		while ((event = eventQueue.poll()) != null) {
			event.handle(this);
			System.out.println("Read event '" + event + "' from '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
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
		if (blockedByGate != null) {
			blockedByGate.unblockActivity();
		}
		//System.out.println("Sent event '" + event + "' to '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
		eventQueue.offer(event);
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
