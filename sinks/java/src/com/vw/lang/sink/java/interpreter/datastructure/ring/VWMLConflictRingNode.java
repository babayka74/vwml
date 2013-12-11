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
	// index of interpreter which is going to be run on current iteration
	private int currentRRIdx = 0;
	// the node is considered as stopped when all interpreters (master and cloned) stopped
	private int stoppedInterpreters = 0;
	private VWMLConflictRingNode masterNode = null;
	// group of nodes (each node is considered as conflict fragment) which belong to the same source lifeterm
	private List<VWMLConflictRingNode> group = new ArrayList<VWMLConflictRingNode>();
	// cloned source lifeterms' nodes which correspond to given node
	// all cloned terms have the same properties and associated with master node
	private List<VWMLConflictRingNode> cloned = new ArrayList<VWMLConflictRingNode>();
	
	public VWMLConflictRingNode() {
		
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}

	public VWMLConflictRingNode clone() {
		VWMLConflictRingNode n = build(this.getId(), this.getReadableId());
		VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				n.getLink().link(getLink().getConcreteLinkedEntity(it.getIt()));
			}
		}		
		n.markAsClone(true);
		n.setGroup(group);
		return n;
	}
	
	/**
	 * Resets internal structures, allowing us to 'resurrect' node
	 */
	public void reset() throws Exception {
		sigma = 0;
		currentRRIdx = 0;
		stoppedInterpreters = 0;
		if (interpreter != null && !interpreter.getConfig().isStepByStepInterpretation()) {
			interpreter.start();
		}
		cloned.clear();
	}
	
	/**
	 * Executes node's operational logic
	 * @throws Exception
	 */
	public void operate() throws Exception {
		if (isClone()) {
			throw new Exception("operate is possible on master node only");
		}
		VWMLConflictRingNode rn = null;
		rn = getNodeByInternalRRIndex();
		if (rn.getInterpreter().getStatus() != VWMLInterpreterImpl.stopProcessing && rn.getInterpreter().getStatus() != VWMLInterpreterImpl.stopped) {
			rn.operateOnNode();
		}
		if (rn.getInterpreter().getStatus() == VWMLInterpreterImpl.stopProcessing) {
			rn.getInterpreter().setStatus(VWMLInterpreterImpl.stopped);
			if (rn.getInterpreter().isCloned()) {
				for(VWMLEntity t : rn.getInterpreter().getTerms()) {
					if (t.getClonedFrom() != null) {
						VWMLCloneFactory.releaseClonedContext(rn.getInterpreter().getClonedFromEntity(), t.getContext());
					}
				}
			}
			stoppedInterpreters++;
		}
		currentRRIdx++;
		currentRRIdx = currentRRIdx % (1 + cloned.size());
	}
	
	public boolean isClone() {
		return clone;
	}

	public void markAsClone(boolean clone) {
		this.clone = clone;
	}

	public List<VWMLConflictRingNode> getGroup() {
		return group;
	}

	public void setGroup(List<VWMLConflictRingNode> group) {
		this.group = group;
	}

	/**
	 * Adds cloned node to master node
	 * @param rn
	 */
	public void addCloned(VWMLConflictRingNode rn) {
		cloned.add(rn);
	}
	
	/**
	 * Removes cloned node from master node
	 * @param ii
	 */
	public void removeCloned(VWMLConflictRingNode rn) {
		cloned.remove(rn);
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
		return (stoppedInterpreters == cloned.size() + 1);
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
	
	public void incSigma(VWMLConflictRingNode exclude) {
		operateOnSigma(exclude, true);
	}
	
	public void decSigma(VWMLConflictRingNode exclude) {
		operateOnSigma(exclude, false);
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
	
	protected VWMLConflictRingNode getNodeByInternalRRIndex() {
		VWMLConflictRingNode rn = this;
		if (currentRRIdx != 0) {
			rn = cloned.get(currentRRIdx - 1);
		}
		return rn;
	}
	
	protected void operateOnSigma(VWMLConflictRingNode exclude, boolean inc) {
		VWMLConflictRingNode n = null;
		if (isClone()) {
			n = getMasterNode();
		}
		else {
			n = this;
		}
		if (exclude != this) {
			if (inc) {
				n.incSigmaImpl();
			}
			else {
				n.decSigmaImpl();
			}
		}
		for(VWMLConflictRingNode c : n.getCloned()) {
			if (exclude != c) {
				if (inc) {
					c.incSigmaImpl();
				}
				else {
					c.decSigmaImpl();
				}
			}
		}
	}
	
	protected List<VWMLConflictRingNode> getCloned() {
		return cloned;
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
