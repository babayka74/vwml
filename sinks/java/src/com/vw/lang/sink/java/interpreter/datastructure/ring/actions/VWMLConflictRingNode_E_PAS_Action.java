package com.vw.lang.sink.java.interpreter.datastructure.ring.actions;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataAction;

/**
 * Node automata E_PAS state
 * @author Oleg
 *
 */
public class VWMLConflictRingNode_E_PAS_Action extends VWMLConflictRingNodeAutomataAction {

	@Override
	public void action(VWMLConflictRingNode node) throws Exception {
		throw new Exception("unreachable state reached; node '" + node.getId() + "'");
	}

}
