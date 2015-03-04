package com.vw.lang.sink.java.entity;

/**
 * Implemented by user in order to get notification about static interpretation process
 * @author Oleg
 *
 */
public abstract class InterpretationObserver {
	private static final String ambiguousInterpretationFlag = "ambiguousInterpretation";
	
	private boolean ambiguosOn = false;
	private String[] flags = null;
	
	public boolean isAmbiguosOn() {
		return ambiguosOn;
	}

	public void setAmbiguosOn(boolean ambiguosOn) {
		this.ambiguosOn = ambiguosOn;
	}

	public void reset() {
		setAmbiguosOn(false);
	}
	
	public String[] getFlags() {
		return flags;
	}

	public void setFlags(String[] flags) {
		this.flags = flags;
	}

	public void setup() {
		if (flags != null) {
			for(String flag : flags) {
				if (flag.equals(ambiguousInterpretationFlag)) {
					setAmbiguosOn(true);
				}
			}
		}
	}
	
	/**
	 * Called when ambiguous interpretation detected
	 * @param interpreted
	 * @param interpreting
	 */
	public abstract void ambiguous(VWMLEntity interpreted, VWMLEntity interpreting);
	
	/**
	 * Called to report for unresolved ambiguous associations
	 * @param interpreted
	 * @param interpreting
	 */
	public abstract void unresolvedAmbiguous(VWMLEntity interpreted, VWMLEntity interpreting);
	
	/**
	 * Called to report about all resolved ambiguous associations
	 */
	public abstract void resolvedAmbiguous();
}
