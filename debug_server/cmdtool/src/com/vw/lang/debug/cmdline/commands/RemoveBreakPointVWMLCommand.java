package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Disables execution of 'setbp' command
 * @author Oleg
 *
 */
@CLICommand(name="removebp", description="removes break point previsouly set by command 'setbp'")
public class RemoveBreakPointVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}
}
