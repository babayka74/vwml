package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Multithreaded version of conflict ring node
 * @author Oleg
 *
 */
public class VWMLConflictRingNodeMT extends VWMLConflictRingNode {

	private Collection<VWMLConflictRingNode> nodesForDeferredUnlock = Collections.synchronizedCollection(new ArrayList<VWMLConflictRingNode>());
	private Collection<String> nodesIdInConflictNow = Collections.synchronizedCollection(new ArrayList<String>());
	
	public VWMLConflictRingNodeMT(Object hashId) {
		super(hashId);
	}

	public VWMLConflictRingNodeMT(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNodeMT(id, readableId);
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
		if (nodesForDeferredUnlock.size() != 0) {
			incSigma(); // deferred sigma; waken node will decrease sigma
			for(VWMLConflictRingNode n : nodesForDeferredUnlock) {
				VWMLConflictRing.wakeupNode(n);
				System.out.println("Awoke from model node '" + this.getId() + "(" + this.getSigma() + ")'; rt node '" + n.getId() + "'");
			}
			nodesForDeferredUnlock.clear();
		}
	}

	/**
	 * Used in order to tell that current node is in conflict with nodeId 
	 * @param nodeId
	 * @throws Exception
	 */
	@Override
	public void inConflictNow(String nodeId) throws Exception {
		nodesIdInConflictNow.add(nodeId);
	}

	/**
	 * Returns true in case if current node is in conflict with node which was setup by 'inConflictNow'
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isInConflictNowWith(String nodeId) throws Exception {
		return nodesIdInConflictNow.contains(nodeId);
	}
	
	/**
	 * Deactivates conflict previously activated by 'inConflictNow'
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deactivateConflictWith(String nodeId) throws Exception {
		nodesIdInConflictNow.remove(nodeId);
		return true;
	}
}
