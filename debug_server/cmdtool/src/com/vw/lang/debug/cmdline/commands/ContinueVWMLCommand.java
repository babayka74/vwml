package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Continues execution of stopped lifeterm
 * @author Oleg
 *
 */
@CLICommand(name="continue", description="continue execution of stopped lifeterm, after 'stopOn' command execution")
public class ContinueVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
