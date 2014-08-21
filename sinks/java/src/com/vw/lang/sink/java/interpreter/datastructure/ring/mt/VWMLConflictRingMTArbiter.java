package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Is used for resolving conflict situation for simultaneous operations which require arbitration process
 * @author Oleg
 *
 */
public class VWMLConflictRingMTArbiter {
	
	protected static class VWMLConflictPair {
		private VWMLConflictRing ring;
		private VWMLConflictRingNode node;
		private boolean accepted = false;
		private boolean resolved = false;
		
		public VWMLConflictPair(VWMLConflictRing ring, VWMLConflictRingNode node) {
			super();
			this.ring = ring;
			this.node = node;
		}
		
		public boolean isAccepted() {
			return accepted;
		}

		public void accept() {
			this.accepted = true;
		}

		public boolean isResolved() {
			return resolved;
		}

		public void setResolved(boolean resolved) {
			this.resolved = resolved;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((node == null) ? 0 : node.hashCode());
			result = prime * result + ((ring == null) ? 0 : ring.hashCode());
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
			VWMLConflictPair other = (VWMLConflictPair) obj;
			if (node == null) {
				if (other.node != null) {
					return false;
				}
			} else if (!node.equals(other.node)) {
				return false;
			}
			if (ring == null) {
				if (other.ring != null) {
					return false;
				}
			} else if (!ring.equals(other.ring)) {
				return false;
			}
			return true;
		}
	}
	
	protected static class VWMLConflictTuple {
		private VWMLConflictPair pair1;
		private VWMLConflictPair pair2;
		
		public VWMLConflictTuple(VWMLConflictPair pair1, VWMLConflictPair pair2) {
			super();
			this.pair1 = pair1;
			this.pair2 = pair2;
		}

		public VWMLConflictPair getPair1() {
			return pair1;
		}
		
		public void setPair1(VWMLConflictPair pair1) {
			this.pair1 = pair1;
		}
		
		public VWMLConflictPair getPair2() {
			return pair2;
		}
		
		public void setPair2(VWMLConflictPair pair2) {
			this.pair2 = pair2;
		}

		public VWMLConflictPair findPair(VWMLConflictPair pairAsKey) {
			if (pairAsKey.equals(pair1)) {
				return pair1;
			}
			else
			if (pairAsKey.equals(pair2)) {
				return pair2;
			}
			return null;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((pair1 == null) ? 0 : pair1.hashCode());
			result = prime * result + ((pair2 == null) ? 0 : pair2.hashCode());
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
			VWMLConflictTuple other = (VWMLConflictTuple) obj;
			if (pair1 == null) {
				if (other.pair1 != null) {
					return false;
				}
			} else if (!pair1.equals(other.pair1)) {
				return false;
			}
			if (pair2 == null) {
				if (other.pair2 != null) {
					return false;
				}
			} else if (!pair2.equals(other.pair2)) {
				return false;
			}
			return true;
		}
	}
	
	private static volatile VWMLConflictRingMTArbiter s_arbiter = null;
	
	private List<VWMLConflictTuple> conflicts = new ArrayList<VWMLConflictTuple>();
	
	private VWMLConflictRingMTArbiter() {
		
	}
	
	public static VWMLConflictRingMTArbiter instance() {
		if (s_arbiter == null) {
			synchronized(VWMLConflictRingMTArbiter.class) {
				if (s_arbiter == null) {
					VWMLConflictRingMTArbiter t = new VWMLConflictRingMTArbiter();
					t.init();
					s_arbiter = t;
				}
			}
		}
		return s_arbiter;
	}
	
	public void init() {
		
	}
	
	/**
	 * Handle lock conflict; avoids technical deadlock; checks conflict by testing acceptance of conflict's cross-reference
	 * @param ring
	 * @param ringOpposite
	 * @param node
	 * @param nodeOpposite
	 * @return
	 */
	public synchronized boolean handleLockConflict(	VWMLConflictRing ring, VWMLConflictRing ringOpposite,
													VWMLConflictRingNode node, VWMLConflictRingNode nodeOpposite,
													VWMLConflictRingNode oppositeRTNode) throws Exception {
		VWMLConflictPair pair1 = new VWMLConflictPair(ring, node);
		VWMLConflictPair pair2 = new VWMLConflictPair(ringOpposite, nodeOpposite);
		System.out.println("<" + Thread.currentThread().getId() + ">[" + ring + ":" + node.getId() + "] -> [" + ringOpposite + ":" + nodeOpposite.getId() + "]");
		VWMLConflictTuple tupleAsKey = new VWMLConflictTuple(pair1, pair2);
		VWMLConflictTuple tuple = getTupleByKey(tupleAsKey);
		if (tuple != null && tuple.getPair1().isResolved() && tuple.getPair2().isResolved()) {
			// remove resolved conflict
			tuple.setPair1(null);
			tuple.setPair2(null);
			node.deactivateConflictWith((String)nodeOpposite.getId());
			nodeOpposite.deactivateConflictWith((String)node.getId());
			conflicts.remove(tuple);
			System.out.println("<" + Thread.currentThread().getId() + "> removed conflict");
			return true;
		}
		if (tuple == null) {
			conflicts.add(tupleAsKey);
			tuple = tupleAsKey;
		}
		// each side accepts opposite one
		VWMLConflictPair p = tuple.findPair(pair2);
		if (p == null) {
			throw new Exception("Inconsistence found; invalid pair '" + pair2 + "'");
		}
		p.accept();
		// conflict accepted by two sides and should be resolved
		if (tuple.getPair1().isAccepted() && tuple.getPair2().isAccepted()) {
			// resolve
			sleepOppositeRTNodeStrategy(node, oppositeRTNode);
			tuple.getPair1().setResolved(true);
			tuple.getPair2().setResolved(true);
			System.out.println("<" + Thread.currentThread().getId() + "> resolved conflict");
			return true;
		}
		return false;
	}
	
	/**
	 * Model node sleeps opposite RT node - strategy
	 * @param modelNode
	 * @param rtNode
	 */
	public void sleepOppositeRTNodeStrategy(VWMLConflictRingNode modelNode, VWMLConflictRingNode rtNode) throws Exception {
		// conflict node to sleep
		VWMLConflictRingMT.sleepNode(rtNode);
		// conflict node will be waken up after n is waken
		System.out.println("Deferred wakeup for model node '" + modelNode.getId() + "(" + modelNode.getSigma() + ")'; rt node '" + rtNode.getId() + "'");
		modelNode.addDeferredWakeupOnUnlock(rtNode);
	}
	
	
	protected VWMLConflictTuple getTupleByKey(VWMLConflictTuple key) {
		VWMLConflictTuple tuple = null;
		for(int i = 0; i < 2; i++) {
			int tupleIndex = conflicts.indexOf(key);
			if (tupleIndex != -1) {
				tuple = conflicts.get(tupleIndex);
			}
			if (tuple == null) {
				VWMLConflictPair p1 = key.getPair1();
				VWMLConflictPair p2 = key.getPair2();
				key.setPair1(p2);
				key.setPair2(p1);
				continue;
			}
			break;
		}
		return tuple;

	}
}
