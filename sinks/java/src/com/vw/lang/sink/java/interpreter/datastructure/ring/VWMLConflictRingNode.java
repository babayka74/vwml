package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	// node's controlled interpreter
	private VWMLInterpreterImpl interpreter;
	// node's automata
	private VWMLConflictRingNodeAutomata nodeAutomata = VWMLConflictRingNodeAutomata.build();
	// index of conflict fragment 
	private int sigma;
	// true in case if node belongs to any group
	private boolean grouped = false;
	private int groupedCount = 0;
	// group of nodes (each node is considered as conflict fragment) which belong to the same source lifeterm
	private List<VWMLConflictRingNode> group = new ArrayList<VWMLConflictRingNode>();
	
	public VWMLConflictRingNode() {
		
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}

	/**
	 * Executes node's operational logic
	 * @throws Exception
	 */
	public void operate() throws Exception {
		VWMLConflictRingNode operationalNode = null;
		VWMLConflictRingNodeAutomataInputs input = null;
		VWMLConflictRingNodeAutomataStates state = null;
		if (!isGroup()) {
			operationalNode = this;
		}
		else {
			String activeConflictContext = interpreter.getObserver().getActiveConflictContext();
			if (activeConflictContext == null) {
				operationalNode = this;
			}
			else {
				// looking for a node inside the group
				if (activeConflictContext.equals(getId())) { // lead node
					operationalNode = this;
				}
				else {
					for(VWMLConflictRingNode n : group) {
						if (((String)n.getId()).equals(activeConflictContext)) {
							operationalNode = n;
							break;
						}
					}
				}
				if (operationalNode == null) {
					throw new Exception("couldn't find conflic fragment (node) by active conflict context '" + activeConflictContext + "'");
				}
			}
		}
		if (operationalNode != null) {
			input = interpreter.getObserver().getConflictOperationalState((String)operationalNode.getId());
			state = VWMLConflictRingNodeAutomataStates.STATE_PAS;
			if (operationalNode.getSigma() == 0) {
				state = VWMLConflictRingNodeAutomataStates.STATE_ACT;
			}
		}
		nodeAutomata.runAction(operationalNode, input, state);
	}
	
	public VWMLInterpreterImpl getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(VWMLInterpreterImpl interpreter) {
		this.interpreter = interpreter;
	}

	public int getSigma() {
		return sigma;
	}

	public void setSigma(int sigma) {
		this.sigma = sigma;
	}
	
	public void incSigma() {
		sigma++;
	}
	
	public void decSigma() {
		if (sigma > 0) {
			sigma--;
		}
	}
	
	/**
	 * Adds node to group
	 * @param node
	 */
	public void addToGroup(VWMLConflictRingNode node) {
		group.add(node);
		node.setGrouped(true);
		node.incGroupedCounter();
	}
	
	/**
	 * Removes node from group
	 * @param node
	 */
	public void removeFromGroup(VWMLConflictRingNode node) {
		if (group.contains(node)) {
			group.remove(node);
			node.decGroupedCounter();
		}
	}
	
	/**
	 * Returns true in case if node represents group
	 * @return
	 */
	public boolean isGroup() {
		return (group.size() != 0);
	}

	public boolean isGrouped() {
		return grouped;
	}

	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	public void incGroupedCounter() {
		groupedCount++;
	}
	
	public void decGroupedCounter() {
		if (groupedCount > 0) {
			groupedCount--;
		}
		else {
			setGrouped(false);
		}
	}
}
