package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

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
	// index of conflict fragment 
	private int sigma = 0;
	// true in case if node belongs to any group
	private boolean grouped = false;
	private int groupedCount = 0;
	// index of interpreter which is going to be run on current iteration
	private int currentInterpreterIdx = 0;
	// the node is considered as stopped when all interpreters (master and cloned) stopped
	private int stoppedInterpreters = 0;
	// group of nodes (each node is considered as conflict fragment) which belong to the same source lifeterm
	private List<VWMLConflictRingNode> group = new ArrayList<VWMLConflictRingNode>();
	// cloned source lifeterms' interpreters which correspond to given node
	// all cloned terms have the same properties and associated with master node
	private List<VWMLInterpreterImpl> cloned = new ArrayList<VWMLInterpreterImpl>();
	
	public VWMLConflictRingNode() {
		
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}

	/**
	 * Resets internal structures, allowing us to 'resurrect' node
	 */
	public void reset() throws Exception {
		sigma = 0;
		currentInterpreterIdx = 0;
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
		VWMLInterpreterImpl ii = null;
		ii = getInterpreterByInternalRRIndex();
		if (ii.getStatus() != VWMLInterpreterImpl.stopProcessing &&  ii.getStatus() != VWMLInterpreterImpl.stopped) {
			operateOnInterpreter(ii);
		}
		if (ii.getStatus() == VWMLInterpreterImpl.stopProcessing) {
			ii.setStatus(VWMLInterpreterImpl.stopped);
			if (ii.isCloned()) {
				for(VWMLEntity t : ii.getTerms()) {
					if (t.getClonedFrom() != null) {
						VWMLCloneFactory.releaseClonedContext(ii.getClonedFromEntity(), t.getContext());
					}
				}
			}
			stoppedInterpreters++;
		}
		currentInterpreterIdx++;
		currentInterpreterIdx = currentInterpreterIdx % (1 + cloned.size());
	}
	
	/**
	 * Adds cloned interpreter to node; the interpreter should be initialized and ready for interpretation
	 * @param ii
	 */
	public void addCloned(VWMLInterpreterImpl ii) {
		cloned.add(ii);
	}
	
	/**
	 * Removes cloned interpreter from interpretation ring
	 * @param ii
	 */
	public void removeCloned(VWMLInterpreterImpl ii) {
		cloned.remove(ii);
	}
	
	/**
	 * Returns master (not cloned) interpreter
	 * @return
	 */
	public VWMLInterpreterImpl getInterpreter() {
		return getInterpreterByInternalRRIndex();
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
	
	protected void operateOnInterpreter(VWMLInterpreterImpl operationalInterpreter) throws Exception {
		VWMLConflictRingNode operationalNode = null;
		VWMLConflictRingNodeAutomataInputs input = null;
		VWMLConflictRingNodeAutomataStates state = null;
		if (!isGroup()) {
			operationalNode = this;
		}
		else {
			String activeConflictContext = operationalInterpreter.getObserver().getActiveConflictContext();
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
			input = operationalInterpreter.getObserver().getConflictOperationalState((String)operationalNode.getId());
			state = VWMLConflictRingNodeAutomataStates.STATE_PAS;
			if (operationalNode.getSigma() == 0) {
				state = VWMLConflictRingNodeAutomataStates.STATE_ACT;
			}
		}
		nodeAutomata.runAction(operationalNode, input, state);
	}
	
	protected VWMLInterpreterImpl getInterpreterByInternalRRIndex() {
		VWMLInterpreterImpl ii = interpreter;
		if (currentInterpreterIdx != 0) {
			ii = cloned.get(currentInterpreterIdx - 1);
		}
		return ii;
	}
}
