package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Starts execution of loaded project
 * @author Oleg
 *
 */
@CLICommand(name="start", description="starts execution of loaded project")
public class StartVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-l", "--lifeterm"}, description="starts execution from requested lifeterm")
	private String lifeTerm;
	
	public String getLifeTerm() {
		return lifeTerm;
	}

	public void setLifeTerm(String lifeTerm) {
		this.lifeTerm = lifeTerm;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
