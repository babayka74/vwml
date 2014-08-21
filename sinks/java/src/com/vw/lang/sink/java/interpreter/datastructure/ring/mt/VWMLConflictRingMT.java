package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vw.lang.sink.java.entity.VWMLEntity;
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

	protected static abstract class VWMLRingEvent {
		
		enum REVENT {
			LOCK,
			UNLOCK,
			CONTEXTFIND,
			ACTIVATENODE
		}
		
		private REVENT id;
		private AtomicBoolean processed = new AtomicBoolean(false);
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
			processed.getAndSet(true);
		}
		
		public boolean isProcessed() {
			return processed.get();
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
		
		protected void waitFor() throws Exception {
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
				if (stallCounter > 10000000) {
					throw new Exception("Stalled ring '" + getRing() + "' on thread '" + Thread.currentThread().getId() + "'");
				}
			}
			interpreter.removeBlockedOnInterpretation(getRtNode());
		}
		
		protected void handle(VWMLConflictRingMT ring) throws Exception {
			setProcessed();
		}
	}
	
	protected static class VWMLRingLockEvent extends VWMLRingEvent {

		private Object nodeId;
		
		public VWMLRingLockEvent() {
			super(VWMLRingEvent.REVENT.LOCK);
		}

		public VWMLRingLockEvent(REVENT id) {
			super(id);
		}
		
		public Object getNodeId() {
			return nodeId;
		}

		public void setNodeId(Object nodeId) {
			this.nodeId = nodeId;
		}
		
		protected void handle(VWMLConflictRingMT ring) throws Exception {
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

	protected static class VWMLRingUnlockEvent extends VWMLRingEvent {

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
		
		protected void handle(VWMLConflictRingMT ring) throws Exception {
			VWMLConflictRingNode n = ring.findConflictNode(getNodeId());
			if (n != null) {
				n.decSigma();
				//System.out.println("- " + n.getSigma());
			}
			super.handle(ring);
		}
	}
	
	protected static class VWMLRingActivateNodeEvent extends VWMLRingEvent {
		private VWMLInterpreterImpl interpreter;
		private VWMLEntity cloned;
		private VWMLEntity clonedSourceLft;
		
		public VWMLRingActivateNodeEvent(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) {
			super(VWMLRingEvent.REVENT.ACTIVATENODE);
			this.interpreter = interpreter;
			this.cloned = cloned;
			this.clonedSourceLft = clonedSourceLft;
		}
		
		protected void handle(VWMLConflictRingMT ring) throws Exception {
			try {
				VWMLOperationUtils.activateClonedTerm(ring, interpreter, cloned, clonedSourceLft);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.handle(ring);
		}
	}

	private volatile boolean stopped = false;
	private ConcurrentLinkedQueue<VWMLRingEvent> eventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	private ConcurrentLinkedQueue<VWMLRingEvent> deferredEventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	
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
		VWMLConflictRingNode n = super.next();
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
	 * Posts lock request to ring for processing
	 * @param fromRTNode
	 * @param from
	 * @param nodeId
	 */
	@Override
	public void sendLockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
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
	public void sendUnlockRequestFor(VWMLConflictRingNode fromRTNode, VWMLConflictRingNode from, Object nodeId) throws Exception {
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
	public void sendActivateNode(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		VWMLRingActivateNodeEvent event = new VWMLRingActivateNodeEvent(interpreter, cloned, clonedSourceLft);
		event.setRtNode(interpreter.getRtNode());
		event.setRing(event.getRtNode().getExecutionGroup().getRing());
		sendEvent(event);
	}

	/**
	 * Posts 'context find' request to ring
	 * @param id
	 */
	@Override
	public VWMLContext sendContextFindRequest(String id) throws Exception {
		throw new Exception("Not implemented yet for MT strategy");
	}
	
	/**
	 * Processes incoming requests (called from ring's thread)
	 */
	@Override
	public void processRequests() throws Exception {
		VWMLRingEvent event = null;
		while ((event = eventQueue.poll()) != null) {
			event.handle(this);
			//System.out.println("Read event '" + event + "' from '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
			if (event.isHandleAgain()) {
				deferredEventQueue.offer(event);
				//System.out.println("event '" + event + "' from '" + this + "' should be handled again; thread '" + Thread.currentThread().getId() + "'");
				// initiates thread switch
				Thread.sleep(0);
			}
		}
		while ((event = deferredEventQueue.poll()) != null) {
			eventQueue.offer(event);
		}
	}
	
	protected VWMLRingEvent sendEvent(VWMLRingEvent event) throws Exception {
		//System.out.println("Sent event '" + event + "' to '" + this + "'; thread '" + Thread.currentThread().getId() + "'");
		eventQueue.offer(event);
		waitForResult(event);
		return event;
	}
	
	protected void waitForResult(VWMLRingEvent event) throws Exception {
		event.waitFor();
	}
}
