package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

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
	// set when node without interpreter and marked as candidate for clone is removed from group by scheduler
	// but this node has all conflicts' links which can be used during clone operation
	private VWMLConflictRingNode implicitMaster = null;
	private VWMLConflictRing ring = null;
	
	public VWMLConflictRingExecutionGroup(Object hashId) {
		super(hashId);
	}
	
	public VWMLConflictRingExecutionGroup(Object id, String readableId) {
		super(id, id, readableId);
	}
	
	public static VWMLConflictRingExecutionGroup build(VWMLConflictRing ring, Object id, String readableId) {
		VWMLConflictRingExecutionGroup g = new VWMLConflictRingExecutionGroup(id, readableId);
		g.setRing(ring);
		return g;
	}

	public void add(VWMLConflictRingNode n) {
		group.add(n);
	}
	
	public void remove(VWMLConflictRingNode n) {
		group.remove(n);
	}
	
	public VWMLConflictRingNode find(Object id) {
		for(VWMLConflictRingNode n : group) {
			if (n.getId().equals(id)) {
				return n;
			}
			// looking inside grouped ('aux' nodes)
			for(VWMLConflictRingNode ng : n.getGroup()) {
				if (ng.getId().equals(id)) {
					return ng;
				}
			}
		}
		return null;
	}
	
	public void balance() {
		VWMLConflictRingVisitor v = VWMLConflictRing.instance().getRingVisitor();
		if (v != null) {
			v.print("-> group [ " + getId() + " ]");
		}
		if (group.size() > 1) {
			VWMLConflictRingNode master = group.get(0);
			if (v != null) {
				v.print("\t-> group master [ " + master.getId() + " ]");
			}
			for(int i = 1; i < group.size();) {
				// has the same term as master
				master.addToGroup(group.get(i));
				if (v != null) {
					v.print("\t\t-> grouped by master [ " + group.get(i).getId() + " ]");
				}
				group.remove(i);
			}
		}
		if (v != null) {
			v.print("");
		}
	}
	
	public VWMLInterpreterImpl findInterpreterByContext(String context) {
		VWMLInterpreterImpl interpreter = null;
		for(VWMLConflictRingNode n : group) {
			interpreter = n.peekInterpreter();
			if (interpreter != null && interpreter.getContext() != null) {
				if (interpreter.getContext().getContext().startsWith(context)) {
					break;
				}
			}
		}
		return interpreter;
	}
	
	public void clear() {
		group.clear();
		removedNodes.clear();
		implicitMaster = null;
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

	public VWMLConflictRingNode findMasterInAnyCase() {
		VWMLConflictRingNode master = null;
		if (group.size() != 0) {
			VWMLConflictRingNode n = group.get(0);
			n.markAsClone(false);
			master = n;
		}
		else {
			if (implicitMaster != null) {
				master = implicitMaster;
			}
			else {
				VWMLConflictRingNode n = VWMLConflictRingNode.build(getId(), getReadableId());
				add(n);
				master = n;
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
				if (implicitMaster == null && n.isMarkAsCandidatOnClone()) {
					implicitMaster = n;
				}
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

	public VWMLConflictRing getRing() {
		return ring;
	}

	public void setRing(VWMLConflictRing ring) {
		this.ring = ring;
	}
}
