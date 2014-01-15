package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Action is run by node's automata (root class; must be inherited)
 * @author Oleg
 *
 */
public abstract class VWMLConflictRingNodeAutomataAction {
	
	public abstract void action(VWMLConflictRingNode node) throws Exception;
	
	/**
	 * Increases node's index according to automata's specification
	 * @param node
	 */
	public void incIndex(VWMLConflictRingNode node) {
		// increase 'index' on linked nodes
		VWMLLinkIncrementalIterator it = node.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode n = (VWMLConflictRingNode)node.getLink().getConcreteLinkedEntity(it.getIt());
				if (n != node) {
					incrementOnExecutionGroup(n, true);
				}
				else {
					incrementOnExecutionGroup(node, false);
				}
			}
		}
	}

	/**
	 * Decreases node's index according to automata's specification
	 * @param node
	 */
	public void decIndex(VWMLConflictRingNode node) {
		// increase 'index' on linked nodes
		VWMLLinkIncrementalIterator it = node.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode n = (VWMLConflictRingNode)node.getLink().getConcreteLinkedEntity(it.getIt());
				if (n != node) {
					decrementOnExecutionGroup(n, true);
				}
				else {
					decrementOnExecutionGroup(node, false);
				}
			}
		}
	}
	
	/**
	 * Activates next interpreter's step
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public boolean nextStep(VWMLConflictRingNode node) throws Exception {
		boolean r = false;
		VWMLInterpreterImpl i = node.peekInterpreter();
		if (i != null && i.getStatus() != VWMLInterpreterImpl.stopProcessing) {
			r = i.step();
		}		
		return r;
	}
	
	/**
	 * Resets input signal to IN_N	
	 * @param node
	 */
	public void resetInput(VWMLConflictRingNode node) {
		VWMLInterpreterImpl i = node.peekInterpreter();
		if (i.getObserver() != null) {
			i.getObserver().setConflictOperationalState((String)node.getId(), VWMLConflictRingNodeAutomataInputs.IN_N);
			i.getObserver().setActiveConflictContext(null);
		}
	}
	
	/**
	 * Resets observer's active conflict context
	 * @param node
	 */
	public void resetActiveConflictContext(VWMLConflictRingNode node) {
		VWMLInterpreterImpl i = node.peekInterpreter();
		if (i.getObserver() != null) {
			i.getObserver().setActiveConflictContext(null);
		}
	}
	
	protected void incrementOnExecutionGroup(VWMLConflictRingNode node, boolean forAllNodes) {
		node.getExecutionGroup().updateSigma(node, true, forAllNodes);
	}
	
	protected void decrementOnExecutionGroup(VWMLConflictRingNode node, boolean forAllNodes) {
		node.getExecutionGroup().updateSigma(node, false, forAllNodes);
	}
}
