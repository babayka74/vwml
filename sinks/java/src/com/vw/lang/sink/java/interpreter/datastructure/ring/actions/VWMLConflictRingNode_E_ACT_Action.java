package com.vw.lang.sink.java.interpreter.datastructure.ring.actions;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataAction;

/**
 * Node automata E_ACT state
 * @author Oleg
 *
 */
public class VWMLConflictRingNode_E_ACT_Action extends VWMLConflictRingNodeAutomataAction {

	@Override
	public void action(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) throws Exception {
		node.setNodeInConflict(false);
		// increase 'index' on linked nodes
		decIndex(interpreter, node);
		// resets input
		resetInput(interpreter, node);
		// commands interpreter to perform next interpretation step
		nextStep(interpreter);
	}

}
