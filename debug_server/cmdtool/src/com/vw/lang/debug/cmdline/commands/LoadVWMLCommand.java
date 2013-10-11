package com.vw.lang.debug.cmdline.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;

/**
 * Loads compiled project's jar in separate process
 * @author Oleg
 *
 */
@CLICommand(name="load", description="loads requested project")
public class LoadVWMLCommand extends Command<VWMLDebugServerCmdLineContext> {

	@Parameter(names={"-p", "--path"}, description="full path, in URL format, to project's jar")
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext arg) {
		return CommandResult.OK;
	}
}
