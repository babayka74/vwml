package com.vw.lang.debug.commands.ccontinue;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Continues execution of term interpretation which previously was stopped by command 'stopOn'
 * @author Oleg
 *
 */
public class VWMLDebugCommandContinue extends VWMLDebugCommand {
	private String context;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public VWMLDebugCommandResult handle() {
		// TODO Auto-generated method stub
		return null;
	}
}
