package com.vw.lang.debug.commands.start;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;

/**
 * Starts interpretation on given lifeterm
 * @author Oleg
 *
 */
public class VWMLDebugCommandStart extends VWMLDebugCommand {

	private String lifeTerm;
	
	public String getLifeTerm() {
		return lifeTerm;
	}

	public void setLifeTerm(String lifeTerm) {
		this.lifeTerm = lifeTerm;
	}

	@Override
	public VWMLDebugCommandResult handle() {
		return null;
	}
}
