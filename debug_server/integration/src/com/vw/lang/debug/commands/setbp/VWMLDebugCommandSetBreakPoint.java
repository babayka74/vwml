package com.vw.lang.debug.commands.setbp;

import com.vw.lang.beyond.java.fringe.gate.debug.IVWMLDebuggerCommandInterface;
import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Stops interpretation process of given lifeterm on given context during executing given command, aka breakpoint
 * @author Oleg
 *
 */
public class VWMLDebugCommandSetBreakPoint extends VWMLDebugCommand {
	private String command;
	private String context;
	private Boolean after = true;
	private Boolean before = true;

	public VWMLDebugCommandSetBreakPoint() {
		super("setbp", null);
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Boolean getAfter() {
		return after;
	}

	public void setAfter(Boolean after) {
		this.after = after;
	}

	public Boolean getBefore() {
		return before;
	}

	public void setBefore(Boolean before) {
		this.before = before;
	}

	@Override
	public VWMLDebugCommandResult handle(Object context) {
		IVWMLDebuggerCommandInterface vwmlDebugger = (IVWMLDebuggerCommandInterface)context;
		vwmlDebugger.setBreakPoint(getContext(), getCommand(), getAfter(), getBefore());
		return VWMLDebugCommandResult.defaultCommandResult();
	}
}
