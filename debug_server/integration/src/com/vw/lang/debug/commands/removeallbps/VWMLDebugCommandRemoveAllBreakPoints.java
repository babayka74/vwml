package com.vw.lang.debug.commands.removeallbps;

import com.vw.lang.beyond.java.fringe.gate.debug.IVWMLDebuggerCommandInterface;
import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Removes all breakpoints which were previously set by 'setbp' command
 * @author Oleg
 *
 */
public class VWMLDebugCommandRemoveAllBreakPoints extends VWMLDebugCommand {

	public VWMLDebugCommandRemoveAllBreakPoints() {
		super("removeallbps", null);
	}
	
	@Override
	public VWMLDebugCommandResult handle(Object context) {
		IVWMLDebuggerCommandInterface vwmlDebugger = (IVWMLDebuggerCommandInterface)context;
		vwmlDebugger.removeAllBreakPoints();
		return VWMLDebugCommandResult.defaultCommandResult();
	}

}
