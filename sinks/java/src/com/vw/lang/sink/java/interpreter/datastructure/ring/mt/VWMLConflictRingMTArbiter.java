package com.vw.lang.sink.java.interpreter.datastructure.ring.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Is used for resolving conflict situation for simultaneous operations which require arbitration process
 * @author Oleg
 *
 */
public class VWMLConflictRingMTArbiter {
	
	/**
	 * Situation root class
	 * @author Oleg
	 *
	 */
	public static abstract class VWMLArbitrationSituation {
		
		public abstract void resolve() throws Exception;
	}
	
	/**
	 * Lock situation which requires arbitration
	 * @author Oleg
	 *
	 */
	public static class VWMLArbitrationLockSituation extends VWMLArbitrationSituation {
		private VWMLConflictRingNode from;
		private VWMLConflictRingNode to;

		public VWMLConflictRingNode getFrom() {
			return from;
		}

		public void setFrom(VWMLConflictRingNode from) {
			this.from = from;
		}

		public VWMLConflictRingNode getTo() {
			return to;
		}

		public void setTo(VWMLConflictRingNode to) {
			this.to = to;
		}
		
		public void resolve() throws Exception {
			VWMLConflictRingMT.wakeupNode(getTo());
		}
	}
	
	private static volatile VWMLConflictRingMTArbiter s_arbiter = null;
	
	private Map<VWMLConflictRingMT.VWMLRingEvent.REVENT, Collection<VWMLArbitrationSituation>> situations = new ConcurrentHashMap<VWMLConflictRingMT.VWMLRingEvent.REVENT, Collection<VWMLArbitrationSituation>>();
	
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
	 * Adds lock arbitration situation
	 * @param eventId
	 * @param node1
	 * @param node2
	 */
	public void addLockSituation(VWMLConflictRingMT.VWMLRingEvent.REVENT eventId, VWMLConflictRingNode from, VWMLConflictRingNode to) {
		Collection<VWMLArbitrationSituation> situationsPerEvent = situations.get(eventId);
		if (situationsPerEvent == null) {
			situationsPerEvent = Collections.synchronizedCollection(new ArrayList<VWMLArbitrationSituation>());
			situations.put(eventId, situationsPerEvent);
		}
		VWMLArbitrationLockSituation situation = new VWMLArbitrationLockSituation();
		VWMLConflictRingMT.sleepNode(from);
		VWMLConflictRingMT.sleepNode(to);
		situation.setFrom(from);
		situation.setTo(to);
		situationsPerEvent.add(situation);
	}
}
