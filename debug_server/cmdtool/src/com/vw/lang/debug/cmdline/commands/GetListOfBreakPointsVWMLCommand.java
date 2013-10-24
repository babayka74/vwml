package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Returns list of active break points which were previously set by 'setbp' command
 * @author Oleg
 *
 */
@CLICommand(name="listofbps", description="returns list of break points which were previously set by 'setbp' command")
public class GetListOfBreakPointsVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg0) {
		return CommandResult.OK;
	}

}
