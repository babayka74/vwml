package com.vw.lang.sink.java.entity;

/**
 * Implemented by user in order to get notification about static interpretation process
 * @author Oleg
 *
 */
public abstract class InterpretationObserver {
	
	private boolean ambiguosOn = false;
	
	public boolean isAmbiguosOn() {
		return ambiguosOn;
	}

	public void setAmbiguosOn(boolean ambiguosOn) {
		this.ambiguosOn = ambiguosOn;
	}

	/**
	 * Called when ambiguous interpretation detected
	 * @param interpreted
	 * @param interpreting
	 */
	public abstract void ambiguous(VWMLEntity interpreted, VWMLEntity interpreting);
}
