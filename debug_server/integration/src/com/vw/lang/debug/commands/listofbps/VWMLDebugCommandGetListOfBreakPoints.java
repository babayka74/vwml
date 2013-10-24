package com.vw.lang.debug.commands.listofbps;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Returns list of breakpoints which were previously set by 'setbp' command
 * @author Oleg
 *
 */
public class VWMLDebugCommandGetListOfBreakPoints extends VWMLDebugCommand {

	public VWMLDebugCommandGetListOfBreakPoints() {
		super("listofbps", null);
	}
	
	@Override
	public VWMLDebugCommandResult handle(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

}
