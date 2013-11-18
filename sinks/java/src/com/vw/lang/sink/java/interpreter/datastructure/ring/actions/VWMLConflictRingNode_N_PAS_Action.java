package com.vw.lang.sink.java.interpreter.datastructure.ring.actions;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataAction;

/**
 * Node automata N_PAS state
 * @author Oleg
 *
 */
public class VWMLConflictRingNode_N_PAS_Action extends VWMLConflictRingNodeAutomataAction {

	@Override
	public void action(VWMLConflictRingNode node) throws Exception {
		// commands interpreter to perform next interpretation step
		nextStep(node);
	}

}
