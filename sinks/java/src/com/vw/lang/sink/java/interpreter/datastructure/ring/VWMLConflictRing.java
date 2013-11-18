package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.LinkedList;
import java.util.List;

import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;

/**
 * Conflict ring is used for resolving conflict situations during active interpretation phases
 * Can be considered as orchestration layer for parallel interpreting machines
 * @author Oleg
 *
 */
public class VWMLConflictRing {
	private int currentNodeIndex = 0;
	// actual conflict ring data structure
	private List<VWMLConflictRingNode> conflictRing = new LinkedList<VWMLConflictRingNode>();
	// singleton implementation
	private static VWMLConflictRing s_conflictRing = null;
	
	private VWMLConflictRing() {
	}
	
	/**
	 * Simple singlton implementation
	 * @return
	 */
	public static synchronized VWMLConflictRing instance() {
		if (s_conflictRing != null) {
			return s_conflictRing;
		}
		s_conflictRing = new VWMLConflictRing();
		return s_conflictRing;
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
	public static void init() {
		
	}
	
	/**
	 * Ring's un-initialization steps
	 */
	public static void done() {
		
	}
	
	/**
	 * Returns current node on the ring and goes to next
	 * @return
	 */
	public VWMLConflictRingNode next() {
		if (conflictRing.size() == 0) {
			return null;
		}
		VWMLConflictRingNode n = null;
		int stoppedInterpreters = 0;
		while(stoppedInterpreters != conflictRing.size()) {
			n = conflictRing.get(currentNodeIndex);
			currentNodeIndex++;
			currentNodeIndex = currentNodeIndex % conflictRing.size();
			if (n.getInterpreter().getStatus() == VWMLIterpreterImpl.stopProcessing) {
				stoppedInterpreters++;
			}
			else {
				break;
			}
		}
		if (stoppedInterpreters == conflictRing.size()) {
			n = null;
		}
		return n;
	}
	
	/**
	 * Finds ring node by entity's context (O(N) lookup); called during initialization phase
	 * of {parallel | reactive} interpreter
	 * @param context
	 * @return
	 */
	public VWMLConflictRingNode findNodeByEntityContext(String context) {
		VWMLConflictRingNode f = null;
		for (VWMLConflictRingNode n : conflictRing) {
			if (((String)n.getId()).startsWith(context)) {
				f = n;
				break;
			}
		}
		return f;
	}
	
	/**
	 * Links conflict with group of related conflicts
	 * @param conflict
	 * @param linkedConflicts
	 */
	protected void link(String conflict, String[] linkedConflicts) {
		VWMLConflictRingNode node = VWMLConflictRingNode.build(conflict, conflict);
		node = addNode(node);
		for(String conflictName : linkedConflicts) {
			VWMLConflictRingNode rNode = VWMLConflictRingNode.build(conflictName, conflictName);
			rNode = addNode(rNode);
			if (!node.getLink().isLinked(rNode)) {
				node.getLink().link(rNode);
			}
		}
	}
	
	private VWMLConflictRingNode addNode(VWMLConflictRingNode node) {
		int i = conflictRing.indexOf(node);
		if (i == -1) {
			conflictRing.add(node);
		}
		else {
			node = conflictRing.get(i);
		}
		return node;
	}
}
