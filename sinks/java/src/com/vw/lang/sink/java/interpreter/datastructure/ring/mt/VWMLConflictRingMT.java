package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
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
		
		protected void waitFor() throws Exception {
			synchronized(this) {
				while (!isProcessed()) {
					wait();
				}
			}
		}
		
		protected void handle(VWMLConflictRingMT ring) {
			setProcessed();
			synchronized(this) {
				notifyAll();
			}
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
		
		protected void handle(VWMLConflictRingMT ring) {
			VWMLConflictRingNode n = ring.lookupNodeById(getNodeId());
			if (n != null) {
				n.incSigma();
			}
			super.handle(ring);
		}
	}

	protected static class VWMLRingUnlockEvent extends VWMLRingLockEvent {

		public VWMLRingUnlockEvent() {
			super(VWMLRingEvent.REVENT.UNLOCK);
		}
		
		protected void handle(VWMLConflictRingMT ring) {
			VWMLConflictRingNode n = ring.lookupNodeById(getNodeId());
			if (n != null) {
				n.decSigma();
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
		
		protected void handle(VWMLConflictRingMT ring) {
			try {
				VWMLOperationUtils.activateClonedTerm(ring, interpreter, cloned, clonedSourceLft);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.handle(ring);
		}
	}

	protected static class VWMLRingContextFindEvent extends VWMLRingEvent {

		private String contextId;
		private VWMLContext foundContext;
		
		public VWMLRingContextFindEvent() {
			super(VWMLRingEvent.REVENT.CONTEXTFIND);
		}

		public VWMLRingContextFindEvent(REVENT id) {
			super(id);
		}

		public String getContextId() {
			return contextId;
		}

		public void setContextId(String contextId) {
			this.contextId = contextId;
		}
		
		public VWMLContext getFoundContext() {
			return foundContext;
		}

		@Override
		protected void handle(VWMLConflictRingMT ring) {
			VWMLContextsRepository repo = VWMLResourceHostManagerFactory.hostManagerInstance().requestContextsRepo();
			foundContext = repo.get(contextId);
			super.handle(ring);
		}
	}
	
	private ConcurrentLinkedQueue<VWMLRingEvent> eventQueue = new ConcurrentLinkedQueue<VWMLRingEvent>();
	
	/**
	 * Returns current node on the ring and goes to next
	 * @return
	 */
	@Override
	public VWMLConflictRingNode next() {
		VWMLConflictRingNode n = super.next();
		if (n != null) {
			// processes incoming requests which are sent from another ring
			processRequests();
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
	 * @param nodeId
	 */
	@Override
	public void sendLockRequestFor(Object nodeId) throws Exception {
		VWMLRingLockEvent event = new VWMLRingLockEvent();
		event.setNodeId(nodeId);
		sendEvent(event);
	}

	/**
	 * Posts unlock request to ring for processing
	 * @param nodeId
	 */
	@Override
	public void sendUnlockRequestFor(Object nodeId) throws Exception {
		VWMLRingUnlockEvent event = new VWMLRingUnlockEvent();
		event.setNodeId(nodeId);
		sendEvent(event);
	}
	
	/**
	 * Posts 'context find' request to ring
	 * @param id
	 */
	@Override
	public VWMLContext sendContextFindRequest(String id) throws Exception {
		VWMLRingContextFindEvent event = new VWMLRingContextFindEvent();
		event.setContextId(id);
		sendEvent(event);
		return event.getFoundContext();
	}
	
	/**
	 * Activates node
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 * @throws Exception
	 */
	public void sendActivateNode(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		VWMLRingActivateNodeEvent event = new VWMLRingActivateNodeEvent(interpreter, cloned, clonedSourceLft);
		sendEvent(event);
	}
	
	/**
	 * Processes incoming requests (called from ring's thread)
	 */
	@Override
	public void processRequests() {
		VWMLRingEvent event = null;
		while ((event = eventQueue.poll()) != null) {
			event.handle(this);
		}
	}
	
	protected VWMLRingEvent sendEvent(VWMLRingEvent event) throws Exception {
		eventQueue.offer(event);
		waitForResult(event);
		return event;
	}
	
	protected void waitForResult(VWMLRingEvent event) throws Exception {
		event.waitFor();
	}
}
