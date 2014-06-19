package com.vw.lang.sink.java.interpreter.datastructure.ring.actions;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataAction;

/**
 * Node automata W_PAS state
 * @author Oleg
 *
 */
public class VWMLConflictRingNode_W_PAS_Action extends VWMLConflictRingNodeAutomataAction {

	private static final String s_delay = "0";
	private static final EWEntity s_eAsDelay = new EWEntity(s_delay, s_delay);
	
	@Override
	public void action(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) throws Exception {
		IVWMLGate fringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName());
		fringeGate.invokeEW(IVWMLGate.builtInDelayCommandId, s_eAsDelay);
	}
}
