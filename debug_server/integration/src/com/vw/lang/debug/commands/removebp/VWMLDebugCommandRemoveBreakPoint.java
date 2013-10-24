package com.vw.lang.debug.commands.removebp;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Disables concrete breakpoint which was set by previous call of 'stopOn' command
 * @author Oleg
 *
 */
public class VWMLDebugCommandRemoveBreakPoint extends VWMLDebugCommand {
	private String command;
	private String context;

	public VWMLDebugCommandRemoveBreakPoint() {
		super("removebp", null);
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

	@Override
	public VWMLDebugCommandResult handle(Object context) {
		// TODO Auto-generated method stub
		return null;
	}
}
