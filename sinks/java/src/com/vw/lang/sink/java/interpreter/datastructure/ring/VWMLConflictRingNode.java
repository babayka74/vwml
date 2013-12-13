package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	// node's controlled interpreter
	private VWMLInterpreterImpl interpreter = null;
	// node's automata
	private VWMLConflictRingNodeAutomata nodeAutomata = VWMLConflictRingNodeAutomata.build();
	private boolean clone = false;
	// index of conflict fragment 
	private int sigma = 0;
	// true in case if node belongs to any group
	private boolean grouped = false;
	private int groupedCount = 0;
	private VWMLConflictRingExecutionGroup executionGroup = null;
	private VWMLConflictRingNode masterNode = null;
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

	public VWMLConflictRingNode clone(VWMLInterpreterImpl relatedInterpreter) {
		VWMLConflictRingNode n = build(this.getId(), this.getReadableId());
		VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode nc = (VWMLConflictRingNode)getLink().getConcreteLinkedEntity(it.getIt());
				if (this != nc) {
					n.getLink().link(nc);
				}
				else {
					n.getLink().link(n);
				}
			}
		}
		for(VWMLConflictRingNode ng : group) {
			VWMLConflictRingNode ngCloned = ng.clone(relatedInterpreter);
			n.addToGroup(ngCloned);
		}
		n.setExecutionGroup(getExecutionGroup());
		n.setInterpreter(relatedInterpreter);
		return n;
	}
	
	/**
	 * Resets internal structures, allowing us to 'resurrect' node
	 */
	public void reset() throws Exception {
		sigma = 0;
		if (interpreter != null && !interpreter.getConfig().isStepByStepInterpretation()) {
			interpreter.start();
		}
	}
	
	/**
	 * Executes node's operational logic
	 * @throws Exception
	 */
	public void operate() throws Exception {
		if (getInterpreter().getStatus() != VWMLInterpreterImpl.stopProcessing && getInterpreter().getStatus() != VWMLInterpreterImpl.stopped) {
			operateOnNode();
		}
		if (getInterpreter().getStatus() == VWMLInterpreterImpl.stopProcessing) {
			getInterpreter().setStatus(VWMLInterpreterImpl.stopped);
			if (getInterpreter().isCloned()) {
				for(VWMLEntity t : getInterpreter().getTerms()) {
					if (t.getClonedFrom() != null) {
						VWMLCloneFactory.releaseClonedContext(getInterpreter().getClonedFromEntity(), t.getContext());
					}
				}
			}
		}
	}
	
	public boolean isClone() {
		return clone;
	}

	public void markAsClone(boolean clone) {
		this.clone = clone;
	}

	/**
	 * Returns master (not cloned) interpreter
	 * @return
	 */
	public VWMLInterpreterImpl getInterpreter() {
		return interpreter;
	}
	
	/**
	 * Returns 'true' in case if main and all cloned interpreters finished interpretation process
	 * @return
	 */
	public boolean isStopped() {
		return interpreter.getStatus() == VWMLInterpreterImpl.stopProcessing || interpreter.getStatus() == VWMLInterpreterImpl.stopped;
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
		incSigmaImpl();
	}
	
	public void decSigma() {
		decSigmaImpl();
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
	
	public VWMLConflictRingNode getMasterNode() {
		return masterNode;
	}

	public void setMasterNode(VWMLConflictRingNode masterNode) {
		this.masterNode = masterNode;
	}

	/**
	 * Called by execution group when group on cloned nodes' 'sigmas' should be updated
	 * @param initiator
	 * @param inc
	 */
	public void updateSigmaOnGrouped(VWMLConflictRingNode initiator, boolean inc) {
		for(VWMLConflictRingNode grouped : group) {
			if (initiator.getId().equals(grouped.getId())) {
				if (inc) {
					grouped.incSigma();
				}
				else {
					grouped.decSigma();
				}
			}
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
	
	public VWMLConflictRingExecutionGroup getExecutionGroup() {
		return executionGroup;
	}

	public void setExecutionGroup(VWMLConflictRingExecutionGroup executionGroup) {
		this.executionGroup = executionGroup;
	}

	protected void operateOnNode() throws Exception {
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
			// System.out.println("Node of '" + operationalNode.getInterpreter().getContext().getContext() + "'; sigma '" + operationalNode.getSigma() + "'; state '" + state + "'; input '" + input + "'");
		}
		nodeAutomata.runAction(operationalNode, input, state);
	}
	
	protected void operateOnSigma(VWMLConflictRingNode exclude, boolean inc) {
		if (exclude == this) {
			// means that recurrent conflict detected; usually used when conflict situation occurred on cloned terms
		}
		else {
			if (inc) {
				incSigmaImpl();
			}
			else {
				decSigmaImpl();
			}
		}
	}
	
	protected void incSigmaImpl() {
		sigma++;
	}
	
	protected void decSigmaImpl() {
		if (sigma > 0) {
			sigma--;
		}
	}
}
