package com.vw.lang.sink.java.interpreter.datastructure;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;

/**
 * Observes interpreter's internal states
 * @author Oleg
 *
 */
public class VWMLInterpreterObserver {
	public static String s_waitContext = "__waitContext__";
	// interpreter is in active state, so no operation OPCONFLICTSITUATIONSTART or OPCONFLICTSITUATIONEND is executed
	private Map<String, VWMLConflictRingNodeAutomataInputs> observed = new HashMap<String, VWMLConflictRingNodeAutomataInputs>();
	private String activeConflictContext = null;
	
	public static String getWaitContext() {
		return s_waitContext;
	}
	
	public VWMLConflictRingNodeAutomataInputs getConflictOperationalState(String context) {
		if (observed.get(s_waitContext) != null) {
			return VWMLConflictRingNodeAutomataInputs.IN_W;
		}
		VWMLConflictRingNodeAutomataInputs input = observed.get(context);
		if (input == null) {
			input = VWMLConflictRingNodeAutomataInputs.IN_N;
			observed.put(context, input);
		}
		return input;
	}

	public void setActiveConflictContext(String context) {
		if (context != s_waitContext) {
			activeConflictContext = context;
		}
	}
	
	public String getActiveConflictContext() {
		return activeConflictContext;
	}

	public void setConflictOperationalState(String context, VWMLConflictRingNodeAutomataInputs conflictOperationalState) {
		if (conflictOperationalState == null) {
			observed.remove(context);
		}
		else {
			observed.put(context, conflictOperationalState);
		}
	}
}
