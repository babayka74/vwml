package com.vw.lang.sink.java.interpreter.datastructure.ring;

/**
 * Node's automata which returns next action which should be run on interpreter 
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
	
	private VWMLConflictRingNodeAutomata() {
		
	}
	
	public static VWMLConflictRingNodeAutomata build() {
		VWMLConflictRingNodeAutomata a = new VWMLConflictRingNodeAutomata();
		a.init();
		return a;
	}
	
	/**
	 * Executes automata's action depending on input parameters
	 * @param input
	 * @param activityState
	 */
	public void runAction(VWMLConflictRingNodeAutomataInputs input, VWMLConflictRingNodeAutomataStates activityState) throws Exception {
		
	}
	
	/**
	 * initializes automata's data structures
	 */
	protected void init() {
		for(int i = 0; i < IN_MAX; i++) {
			for(int j = 0; j < STATE_MAX; j++) {
				cells[i][j] = new VWMLConflictRingNodeAutomataCell();
			}
		}
	}
}
