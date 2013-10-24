package com.vw.lang.beyond.java.fringe.gate.debug;

/**
 * Debugger's export functionality
 * @author Oleg
 *
 */
public interface IVWMLDebuggerCommandInterface {

	/**
	 * Sests breakpoint on command execution for given command
	 * @param context
	 * @param command
	 * @param after
	 * @param before
	 */
	public void setBreakPoint(String context, String command, boolean after, boolean before);
	
	/**
	 * Removes concrete breakpoint
	 * @param context
	 * @param command
	 */
	public void removeBreakPoint(String context, String command);
	
	/**
	 * Removes all breakpoints which were previously set
	 */
	public void removeAllBreakPoints();
}
