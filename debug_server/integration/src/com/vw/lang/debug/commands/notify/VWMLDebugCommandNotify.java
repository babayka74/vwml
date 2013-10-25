package com.vw.lang.debug.commands.notify;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Notifies command tool about some event, like exception or breakpoint activation
 * @author Oleg
 *
 */
public class VWMLDebugCommandNotify extends VWMLDebugCommand {

	public VWMLDebugCommandNotify() {
		super("notify", null);
	}
	
	@Override
	public VWMLDebugCommandResult handle(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

}
