package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;

/**
 * Observes interpreter's internal states
 * @author Oleg
 *
 */
public class VWMLInterpreterObserver {
	// interpreter is in active state, so no operation OPCONFLICTSITUATIONSTART or OPCONFLICTSITUATIONEND is executed
	private VWMLConflictRingNodeAutomataInputs conflictOperationalState = VWMLConflictRingNodeAutomataInputs.IN_N;

	public VWMLConflictRingNodeAutomataInputs getConflictOperationalState() {
		return conflictOperationalState;
	}

	public void setConflictOperationalState(VWMLConflictRingNodeAutomataInputs conflictOperationalState) {
		this.conflictOperationalState = conflictOperationalState;
	}
}
