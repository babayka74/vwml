package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Disables all breakpoints which were previously set by stopOn command
 * @author Oleg
 *
 */
@CLICommand(name="removeallbp", description="removes all breakpoints which were previously set by 'setbp' command")
public class RemoveAllBreakPointsVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}

}
