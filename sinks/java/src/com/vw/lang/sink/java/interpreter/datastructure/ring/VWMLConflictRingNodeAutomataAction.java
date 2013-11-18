package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
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
				n.incSigma();
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
				n.decSigma();
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
		if (node.getInterpreter() != null && node.getInterpreter().getStatus() != VWMLIterpreterImpl.stopProcessing) {
			r = node.getInterpreter().step();
		}		
		return r;
	}
	
	/**
	 * Resets input signal to IN_N	
	 * @param node
	 */
	public void resetInput(VWMLConflictRingNode node) {
		if (node.getInterpreter().getObserver() != null) {
			node.getInterpreter().getObserver().setConflictOperationalState(VWMLConflictRingNodeAutomataInputs.IN_N);
		}
	}
}
