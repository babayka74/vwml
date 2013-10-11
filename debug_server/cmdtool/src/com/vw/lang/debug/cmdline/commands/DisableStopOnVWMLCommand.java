package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Disables execution of 'stopOn' command; in order to enable user should send 'stopOn' command again
 * @author Oleg
 *
 */
@CLICommand(name="disableStopOn", description="disables stopOn's execution; the execution can be enabled by sending again 'stopOn' command")
public class DisableStopOnVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
