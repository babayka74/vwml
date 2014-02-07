package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObject;

/**
 * Ring's execution group
 * @author Oleg
 *
 */
public class VWMLConflictRingExecutionGroup extends VWMLObject {
	private int rIndex = 0;
	// cloned source lifeterms' nodes which correspond to given node
	// all cloned terms have the same properties and associated with master node
	private List<VWMLConflictRingNode> group = new ArrayList<VWMLConflictRingNode>();
	private List<VWMLConflictRingNode> removedNodes = new ArrayList<VWMLConflictRingNode>();
	
	public VWMLConflictRingExecutionGroup(Object hashId) {
		super(hashId);
	}
	
	public VWMLConflictRingExecutionGroup(Object id, String readableId) {
		super(id, id, readableId);
	}
	
	public static VWMLConflictRingExecutionGroup build(Object id, String readableId) {
		return new VWMLConflictRingExecutionGroup(id, readableId);
	}

	public void add(VWMLConflictRingNode n) {
		group.add(n);
	}
	
	public void remove(VWMLConflictRingNode n) {
		group.remove(n);
	}
	
	public void reset() throws Exception {
		VWMLConflictRingNode master = null;
		for(VWMLConflictRingNode n : group) {
			if (!n.isClone()) {
				if (n.peekInterpreter() != null && !n.isGrouped()) {
					n.reset();
				}
				master = n;
				break;
			}
		}
		group.clear();
		group.add(master);
	}
	
	public boolean isStopped() {
		boolean stopped = true;
		for(VWMLConflictRingNode n : group) {
			if (!n.isStopped()) {
				stopped = false;
				break;
			}
		}
		return stopped;
	}
	
	public VWMLConflictRingNode findMasterNode() {
		VWMLConflictRingNode master = null;
		for(VWMLConflictRingNode n : group) {
			if (!n.isClone()) {
				master = n;
				break;
			}
		}
		return master;
	}
	
	public VWMLConflictRingNode schedule() {
		if (group.size() == 0) {
			return null;
		}
		VWMLConflictRingNode r = null;
		for(int i = 0; i < group.size() && r == null; i++) {
			VWMLConflictRingNode n = group.get(rIndex);
			if (!n.isStopped()) {
				r = n;
			}
			else {
				removedNodes.add(n);
			}
			rIndex++;
			rIndex = rIndex % group.size();
		}
		for(VWMLConflictRingNode n : removedNodes) {
			for(int i = 0; i < group.size(); i++) {
				if (n == group.get(i)) {
					if (rIndex >= i) {
						if (rIndex > 0) rIndex--;
					}
					group.remove(i);
					break;
				}
			}
		}
		removedNodes.clear();
		return r;
	}
	
	public void updateSigma(VWMLConflictRingNode initiator, boolean inc, boolean forAllNodes) {
		VWMLConflictRingNode nodeToExclude = initiator;
		boolean initiatorWasGrouped = initiator.isGrouped();
		if (initiatorWasGrouped) {
			nodeToExclude = initiator.getGroupOwner();
		}
		for(VWMLConflictRingNode n : group) {
			if (n != nodeToExclude || forAllNodes) {
				if (initiatorWasGrouped) {
					n.updateSigmaOnGrouped(initiator, inc);
				}
				else {
					if (inc) {
						n.incSigma();
						//System.out.println("Node '" + initiator.getId() + "'; context '" + initiator.getInterpreter().getContext().getContext() + "' inc on node '" + n.getId() + "'; context '" + n.getInterpreter().getContext().getContext());
					}
					else {
						n.decSigma();
						//System.out.println("Node '" + initiator.getId() + "'; context '" + initiator.getInterpreter().getContext().getContext() + "' dec on node '" + n.getId() + "'; context '" + n.getInterpreter().getContext().getContext());						
					}
				}
			}
		}
	}
}
