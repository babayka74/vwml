package com.vw.lang.sink.java.interpreter.datastructure;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.gate.VWMLGate;
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
		private int refCounter = 0;
		
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
		
		protected int incRefCounter() {
			return refCounter++;
		}
		
		protected int decRefCounter() {
			if (refCounter != 0) {
				refCounter--;
			}
			return refCounter;
		}
		
		protected int getRefCounter() {
			return refCounter;
		}
	}
	
	public static String s_waitContext = "__waitContext__";
	// interpreter is in active state, so no operation OPCONFLICTSITUATIONSTART or OPCONFLICTSITUATIONEND is executed
	private Map<String, VWMLInterpreterObserverData> observed = new HashMap<String, VWMLInterpreterObserverData>();
	private VWMLGate blockedByGate = null;
	private String activeConflictContext = null;
	
	public static String getWaitContext() {
		return s_waitContext;
	}
	
	public VWMLConflictRingNodeAutomataInputs getConflictOperationalState(String context) {
		VWMLConflictRingNodeAutomataInputs input = null;	
		if (observed.get(getWaitContext()) != null) {
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
		if (context != getWaitContext()) {
			activeConflictContext = context;
		}
	}
	
	public String getActiveConflictContext() {
		return activeConflictContext;
	}

	public void setConflictOperationalState(String context, VWMLConflictRingNodeAutomataInputs conflictOperationalState) {
		boolean useRefCounter = false;
		if (context.equals(getWaitContext())) {
			useRefCounter = true;
		}
		if (conflictOperationalState == null) {
			if (useRefCounter) {
				VWMLInterpreterObserverData data = observed.get(context);
				if (data.decRefCounter() == 0) {
					observed.remove(context);
				}
			}
			else {
				observed.remove(context);
			}
		}
		else {
			if (useRefCounter) {
				VWMLInterpreterObserverData data = observed.get(context);
				if (data == null) {
					data = new VWMLInterpreterObserverData();
					observed.put(context, data);
				}
				data.incRefCounter();
				data.setAutomataInputs(conflictOperationalState);
			}
			else {
				VWMLInterpreterObserverData data = new VWMLInterpreterObserverData();
				data.setAutomataInputs(conflictOperationalState);
				observed.put(context, data);
			}
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

	public VWMLGate getBlockedByGate() {
		return blockedByGate;
	}

	public void setBlockedByGate(VWMLGate blockedByGate) {
		this.blockedByGate = blockedByGate;
	}
}
