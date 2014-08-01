package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;

/**
 * Conflict ring for MT strategy
 * @author Oleg
 *
 */
public class VWMLConflictRingMT extends VWMLConflictRing {

	protected static class VWMLRingEvent {
		
		enum REVENT {
			LOCK,
			UNLOCK
		}
		
		private REVENT id;

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
	}

	protected static class VWMLRingUnlockEvent extends VWMLRingLockEvent {

		public VWMLRingUnlockEvent() {
			super(VWMLRingEvent.REVENT.UNLOCK);
		}
	}
	
	private ConcurrentLinkedQueue<VWMLRingEvent> eventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	
	/**
	 * Posts lock request to ring for processing
	 * @param nodeId
	 */
	@Override
	public void postLockRequestFor(Object nodeId) throws Exception {
		VWMLRingLockEvent event = new VWMLRingLockEvent();
		event.setNodeId(nodeId);
		eventQueue.offer(event);
	}

	/**
	 * Posts unlock request to ring for processing
	 * @param nodeId
	 */
	@Override
	public void postUnlockRequestFor(Object nodeId) throws Exception {
		VWMLRingUnlockEvent event = new VWMLRingUnlockEvent();
		event.setNodeId(nodeId);
		eventQueue.offer(event);
	}
}
