package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_B_ACT_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_B_PAS_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_E_ACT_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_E_PAS_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_N_ACT_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_N_PAS_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_W_ACT_Action;
import com.vw.lang.sink.java.interpreter.datastructure.ring.actions.VWMLConflictRingNode_W_PAS_Action;

/**
 * Node's automata which activates next action on interpreter 
 * @author Oleg
 *
 */
public class VWMLConflictRingNodeAutomata {
	// inputs of automata
	private static int IN_MAX = VWMLConflictRingNodeAutomataInputs.IN_MAX.ordinal();
	// states of automata
	private final static int STATE_MAX = VWMLConflictRingNodeAutomataStates.STATE_MAX.ordinal();
	// automata's cells
	private VWMLConflictRingNodeAutomataCell[][] cells = new VWMLConflictRingNodeAutomataCell[IN_MAX][STATE_MAX];
	
	private static VWMLConflictRingNodeAutomata s_automata = null;
	
	private VWMLConflictRingNodeAutomata() {
		
	}
	
	public static synchronized VWMLConflictRingNodeAutomata build() {
		if (s_automata != null) {
			return s_automata;
		}
		s_automata = new VWMLConflictRingNodeAutomata();
		s_automata.init();
		return s_automata;
	}
	
	/**
	 * Executes automata's action depending on input parameters
	 * @param node
	 * @param input
	 * @param activityState
	 */
	public void runAction(VWMLConflictRingNode node, VWMLConflictRingNodeAutomataInputs input, VWMLConflictRingNodeAutomataStates activityState) throws Exception {
		cells[input.ordinal()][activityState.ordinal()].getAction().action(node);
	}
	
	/**
	 * initializes automata's data structures (not expendable)
	 */
	protected void init() {
		for(int i = 0; i < IN_MAX; i++) {
			for(int j = 0; j < STATE_MAX; j++) {
				cells[i][j] = new VWMLConflictRingNodeAutomataCell();
			}
		}
		// initializes automata's actions (automata has fixed set of inputs and states)
		cells[VWMLConflictRingNodeAutomataInputs.IN_N.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_ACT.ordinal()].setAction(new VWMLConflictRingNode_N_ACT_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_N.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_PAS.ordinal()].setAction(new VWMLConflictRingNode_N_PAS_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_B.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_ACT.ordinal()].setAction(new VWMLConflictRingNode_B_ACT_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_B.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_PAS.ordinal()].setAction(new VWMLConflictRingNode_B_PAS_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_E.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_ACT.ordinal()].setAction(new VWMLConflictRingNode_E_ACT_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_E.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_PAS.ordinal()].setAction(new VWMLConflictRingNode_E_PAS_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_W.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_ACT.ordinal()].setAction(new VWMLConflictRingNode_W_ACT_Action());
		cells[VWMLConflictRingNodeAutomataInputs.IN_W.ordinal()][VWMLConflictRingNodeAutomataStates.STATE_PAS.ordinal()].setAction(new VWMLConflictRingNode_W_PAS_Action());
	}
}
