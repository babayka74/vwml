package com.vw.lang.beyond.java.fringe.gate.debug;

import com.vw.lang.beyond.java.fringe.gate.GateCommandHandler;

/**
 * Root class for internal VWML to debugger command processing
 * @author Oleg
 *
 */
public abstract class VWML2DebuggerCommandHandler extends GateCommandHandler {

	private IVWMLDebuggerCommandInterface debuggerInterface;

	public IVWMLDebuggerCommandInterface getDebuggerInterface() {
		return debuggerInterface;
	}

	public void setDebuggerInterface(IVWMLDebuggerCommandInterface debuggerInterface) {
		this.debuggerInterface = debuggerInterface;
	}
}
