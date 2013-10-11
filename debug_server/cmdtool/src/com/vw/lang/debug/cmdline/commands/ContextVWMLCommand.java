package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Prints set of entities which are belong to requested context
 * @author Oleg
 *
 */
@CLICommand(name="printContext", description="prints entities which belong to requested context")
public class ContextVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-c", "--context"}, description="context id")
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
