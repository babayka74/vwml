package com.vw.lang.sink.java.interpreter;

/**
 * Used by interpreter in order to notify user about changes in its internal states
 * @author Oleg
 *
 */
public abstract class VWMLInterpreterListener {
	
	private boolean forcedStop = false;
	private boolean oneStepInterpretationMode = false;
	
	/**
	 * Handles interpreter's changes (implemented by long-term operations which require interpretation of terms in run-time)
	 * @param interpreter
	 */
	public abstract void hanldeStatus(VWMLInterpreterImpl interpreter);
	
	/**
	 * Returns interpreter's status according to listener's information
	 * @return
	 */
	public int getInterpreterStatus() {
		return VWMLInterpreterImpl.stopped;
	}

	/**
	 * Forces to stop interpretation process, usually used by interpreted term on 'ForEach' operation
	 * when iteration process should be stopped
	 */
	public void stopInterpretation() {
		forcedStop = true;
	}

	/**
	 * Returns 'true' in case if method 'stopInterpretation' was called before
	 * @return
	 */
	public boolean isForcedStop() {
		return forcedStop;
	}

	/**
	 * One step interpretation mode means that listener will stop interpretation after each step
	 * @return
	 */
	public boolean isOneStepInterpretationMode() {
		return oneStepInterpretationMode;
	}

	public void setOneStepInterpretationMode(boolean oneStepInterpretationMode) {
		this.oneStepInterpretationMode = oneStepInterpretationMode;
	}
}
