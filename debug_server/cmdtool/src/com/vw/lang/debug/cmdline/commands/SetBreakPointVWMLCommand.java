package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Stops execution on requested command when command is executed on given context
 * @author Oleg
 *
 */
@CLICommand(name="setbp", description="stops execution on specified command when it is executed on given context")
public class SetBreakPointVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-c", "--command"}, description="stops on command; in case if command is empty the execution is stopped on every command executed on given context")
	private String command;
	@Parameter(names={"-C", "--context"}, description="context id")
	private String context;
	@Parameter(names={"-a", "--after"}, description="stops after command was executed")
	private Boolean after = true;
	@Parameter(names={"-b", "--before"}, description="stops before command is executed")
	private Boolean before = true;
	
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
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
