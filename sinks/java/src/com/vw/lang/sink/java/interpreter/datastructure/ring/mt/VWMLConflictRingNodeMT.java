package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Multithreaded version of conflict ring node
 * @author Oleg
 *
 */
public class VWMLConflictRingNodeMT extends VWMLConflictRingNode {

	// true in case if node sends lock event in order to notify other rings about entering into conflict situation
	private boolean nodeOnSendingLockEvent = false;
	
	private Collection<VWMLConflictRingNode> nodesForDeferredUnlock = Collections.synchronizedCollection(new ArrayList<VWMLConflictRingNode>());
	
	public VWMLConflictRingNodeMT(Object hashId) {
		super(hashId);
	}

	public VWMLConflictRingNodeMT(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNodeMT(id, readableId);
	}
	
	@Override
	public boolean isNodeOnSendingLockEvent() {
		return nodeOnSendingLockEvent;
	}

	@Override
	public void markNodeAsSendingLockEvent(boolean nodeOnSendingLockEvent) {
		this.nodeOnSendingLockEvent = nodeOnSendingLockEvent;
	}

	/**
	 * Add deferred unlock operation; used when forced lock is required and need to wakeup node which was slept by force; the forced lock 
	 * is activated during circular conflict resolution (see VWMLConflictRingMT -> lock)
	 * @param node
	 */
	@Override
	public void addDeferredWakeupOnUnlock(VWMLConflictRingNode node) throws Exception {
		nodesForDeferredUnlock.add(node);
	}
	
	/**
	 * Processes deferred unlock for nodes added by 'addDeferredWakeupOnUnlock'
	 * @throws Exception
	 */
	@Override
	public void processDeferredWakeupsOnUnlock() throws Exception {
		for(VWMLConflictRingNode n : nodesForDeferredUnlock) {
			VWMLConflictRingMT.wakeupNode(n);
		}
		nodesForDeferredUnlock.clear();
	}
}
