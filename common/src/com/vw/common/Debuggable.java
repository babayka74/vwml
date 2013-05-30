package com.vw.common;

/**
 * Defines builder's interface which is used during by parser in order to generate sink's code
 * @author ogibayev
 *
 */
public abstract class Debuggable {
	
	/**
	 * Should be set to 'true' in order to turn on tracing functionality
	 */
	private boolean traceable = false;
	/**
	 * Ususally sets to 'true' during grammar's debugging process from ANTLR
	 */
	private boolean debug = false;
	
	/**
	 * Used in debug purposes when need to know name of rule which is going to be processed
	 * @param to
	 */
	public abstract void debugEnter(Object to);
	
	/**
	 * Used in debug purposes when need to know name of rule which has been processed
	 * @param from
	 */
	public abstract void debugExit(Object from);
	
	/**
	 * Traces independent string
	 * @param trace
	 */
	public abstract void trace(String trace);

	/**
	 * Getters/setters for class's fields
	 */
	public boolean isTraceable() {
		return traceable;
	}

	public void setTraceable(boolean traceable) {
		this.traceable = traceable;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
		setTraceable(debug);
	}
}
