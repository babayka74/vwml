package com.vw.lang.sink.java.interpreter;

/**
 * Used by interpreter in order to notify user about changes in its internal states
 * @author Oleg
 *
 */
public abstract class VWMLInterpreterListener {
	
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
}
