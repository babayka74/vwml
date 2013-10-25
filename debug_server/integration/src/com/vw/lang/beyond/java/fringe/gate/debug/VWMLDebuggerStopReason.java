package com.vw.lang.beyond.java.fringe.gate.debug;

/**
 * Reason of stopping execution
 * @author Oleg
 *
 */
public class VWMLDebuggerStopReason {
	
	public static enum Reason {
		BREAKPOINT,
		EXCEPTION
	}

	// reason's identificator (operation name for breakpoint for example)
	private Object identificator;
	// stopped context
	private Reason reason;
	
	private VWMLDebuggerStopReason(Reason reason, Object identificator) {
		this.identificator = identificator;
		this.reason = reason;
	}
	
	public static VWMLDebuggerStopReason build(Reason reason, Object identificator) {
		return new VWMLDebuggerStopReason(reason, identificator);
	}

	public Object getIdentificator() {
		return identificator;
	}

	public void setIdentificator(Object identificator) {
		this.identificator = identificator;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}
}
