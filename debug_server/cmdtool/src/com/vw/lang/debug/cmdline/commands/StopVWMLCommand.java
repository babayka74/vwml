package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Stops execution of loaded and started project
 * @author Oleg
 *
 */
@CLICommand(name="stop", description="stops execution of loaded project")
public class StopVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {
	
	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
