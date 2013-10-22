package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Continues execution of stopped lifeterm
 * @author Oleg
 *
 */
@CLICommand(name="continue", description="continue execution of stopped lifeterm, after 'stopOn' command execution")
public class ContinueVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-C", "--context"}, description="context id")
	private String context;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
