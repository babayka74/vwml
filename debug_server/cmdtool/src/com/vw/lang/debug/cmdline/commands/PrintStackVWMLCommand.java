package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Request stack of requested lifeterm and prints it
 * @author Oleg
 *
 */
@CLICommand(name="printStack", description="prints stack of requested lifeterm")
public class PrintStackVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-l", "--lifeterm"}, description="lifeterm's id in format - <context>.<id>")
	private String lifeTerm;
	
	public String getLifeTerm() {
		return lifeTerm;
	}

	public void setLifeTerm(String lifeTerm) {
		this.lifeTerm = lifeTerm;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext ctx) {
		return CommandResult.OK;
	}
}
