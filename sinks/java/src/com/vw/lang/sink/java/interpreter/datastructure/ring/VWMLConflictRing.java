package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.LinkedList;
import java.util.List;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

/**
 * Conflict ring is used for resolving conflict situations during active interpretation phases
 * Can be considered as orchestration layer for parallel interpreting machines
 * @author Oleg
 *
 */
public class VWMLConflictRing {
	private int currentNodeIndex = 0;
	private boolean initialyEmptyRing = false;
	// actual conflict ring data structure
	private List<VWMLConflictRingNode> conflictRing = new LinkedList<VWMLConflictRingNode>();

	private static int s_artificialNodeIdx = 0;
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
	
	public boolean isInitialyEmptyRing() {
		return initialyEmptyRing;
	}

	public void setInitialyEmptyRing(boolean initialyEmptyRing) {
		this.initialyEmptyRing = initialyEmptyRing;
	}

	/**
	 * Returns true in case if ring in consistency state
	 * @return
	 */
	public boolean checkConsistency() {
		boolean corrected = true;
		if (conflictRing.size() == 0 || initialyEmptyRing) {
			return corrected;
		}
		for(VWMLConflictRingNode node : conflictRing) {
			if (node.getInterpreter() == null) {
				corrected = false;
				for(VWMLConflictRingNode n : conflictRing) {
					if (n != node && n.getInterpreter() != null) {
						if (((String)n.getId()).startsWith((String)node.getId()) ||
							((String)node.getId()).startsWith((String)n.getId())) {
							node.setInterpreter(n.getInterpreter());
							corrected = true;
							break;
						}
					}
				}
				if (!corrected) {
					break;
				}
			}
		}
		return corrected;
	}
	
	/**
	 * Returns 'true' in case if ring contains at least one node
	 * @return
	 */
	public boolean isRingOperational() {
		return (conflictRing.size() != 0);
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
			if (n.getInterpreter().getStatus() == VWMLInterpreterImpl.stopProcessing) {
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
		if (!isRingOperational()) {
			initialyEmptyRing = true;
		}
		if (initialyEmptyRing) {
			String id = "artificialNode_" + s_artificialNodeIdx;
			f = addNode(VWMLConflictRingNode.build(id, id));
			s_artificialNodeIdx++;
		}
		else {
			for (VWMLConflictRingNode n : conflictRing) {
				if (((String)n.getId()).startsWith(context)) {
					f = n;
					break;
				}
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
