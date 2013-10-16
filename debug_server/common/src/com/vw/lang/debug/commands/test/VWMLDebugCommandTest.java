package com.vw.lang.debug.commands.test;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Simple test command; used for testing communication between VWML debug client and server
 * @author Oleg
 *
 */
public class VWMLDebugCommandTest extends VWMLDebugCommand {
	public VWMLDebugCommandTest() {
		super("test", "test data");
	}

	@Override
	public VWMLDebugCommandResult handle() {
		VWMLDebugCommandResult r = VWMLDebugCommandResult.defaultCommandResult();
		r.setData("test response data");
		return r;
	}
}
