package com.vw.lang.beyond.java.fringe.gate.debug;

import com.vw.lang.debug.common.VWMLDebugCommandResponseData;

/**
 * Debugger's export functionality
 * @author Oleg
 *
 */
public interface IVWMLDebuggerCommandInterface {

	public static final String exceptionCaughtCommand = "__exception_on_procssor_caught__";
	public static final String exceptionCaughtContext = "__exception_on_root_context__";
	
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
	
	/**
	 * Returns 'true' in case if operations related breakpoint is active
	 * @param context
	 * @param command
	 * @param before
	 * @return
	 */
	public boolean checkIfOperationBreakPointIsActive(String context, String command, boolean before);

	/**
	 * Returns 'true' in case if exception related breakpoint is active
	 * @param context
	 * @return
	 */
	public boolean checkIfExceptionBreakPointIsActive(String context);
	
	/**
	 * Waits for continue command for given breakpoint
	 * @param context
	 * @param command
	 */
	public void waitOnBreakPoint(String context, String command);
	
	/**
	 * Resumes execution flow which was stopped by breakpoint
	 * @param context
	 */
	public void resumeExecutionFlow(String context);
	
	/**
	 * Delegates debugger's result
	 * @param notification
	 */
	public void delegate(VWMLDebugCommandResponseData notification);
}
