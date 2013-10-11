package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Requests to receive any changes proceeded in requested contexts
 * @author Oleg
 *
 */
@CLICommand(name="observe", description="any changes in requested contexts will be notified")
public class ObserveVWMLCommand extends Command<VWMLDebugServerCmdLineContext>{

	// context ids in coma separated format
	@Parameter(names={"-c", "--contexts"}, description="contexts in coma separated format")
	private String contexts;
	
	public String getContexts() {
		return contexts;
	}

	public void setContexts(String contexts) {
		this.contexts = contexts;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg) {
		return CommandResult.OK;
	}
}
