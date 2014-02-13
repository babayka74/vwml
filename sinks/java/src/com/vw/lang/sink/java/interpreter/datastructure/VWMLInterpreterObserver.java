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
	
	public static class VWMLInterpreterObserverData {
		private VWMLConflictRingNodeAutomataInputs automataInputs;
		private Object associatedTimer;
		
		protected VWMLConflictRingNodeAutomataInputs getAutomataInputs() {
			return automataInputs;
		}
		
		protected void setAutomataInputs(VWMLConflictRingNodeAutomataInputs automataInputs) {
			this.automataInputs = automataInputs;
		}
		
		protected Object getAssociatedTimer() {
			return associatedTimer;
		}

		protected void setAssociatedTimer(Object associatedTimer) {
			this.associatedTimer = associatedTimer;
		}
	}
	
	public static String s_waitContext = "__waitContext__";
	// interpreter is in active state, so no operation OPCONFLICTSITUATIONSTART or OPCONFLICTSITUATIONEND is executed
	private Map<String, VWMLInterpreterObserverData> observed = new HashMap<String, VWMLInterpreterObserverData>();
	private String activeConflictContext = null;
	
	public static String getWaitContext() {
		return s_waitContext;
	}
	
	public VWMLConflictRingNodeAutomataInputs getConflictOperationalState(String context) {
		VWMLConflictRingNodeAutomataInputs input = null;	
		if (observed.get(s_waitContext) != null) {
			return VWMLConflictRingNodeAutomataInputs.IN_W;
		}
		VWMLInterpreterObserverData data = observed.get(context);
		if (data == null) {
			data = new VWMLInterpreterObserverData();
			input = VWMLConflictRingNodeAutomataInputs.IN_N;
			data.setAutomataInputs(input);
			observed.put(context, data);
		}
		else {
			input = data.getAutomataInputs();
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
			VWMLInterpreterObserverData data = new VWMLInterpreterObserverData();
			data.setAutomataInputs(conflictOperationalState);
			observed.put(context, data);
		}
	}
	
	public void associateTimerWithContext(String context, Object timerId) {
		VWMLInterpreterObserverData data = observed.get(context);
		if (data != null) {
			data.setAssociatedTimer(timerId);
		}
	}
	
	public Object getAssociatedTimerWithContext(String context) {
		Object timerId = null;
		VWMLInterpreterObserverData data = observed.get(context);
		if (data != null) {
			timerId = data.getAssociatedTimer();
		}
		return timerId;
	}
}
