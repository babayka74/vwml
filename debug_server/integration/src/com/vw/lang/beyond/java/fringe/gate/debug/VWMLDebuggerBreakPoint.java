package com.vw.lang.beyond.java.fringe.gate.debug;

/**
 * Data structure which decribe VWML's breakpoint
 * @author Oleg
 *
 */
public class VWMLDebuggerBreakPoint {
	private String context;
	private String command;
	private boolean after;
	private boolean before;
	
	private VWMLDebuggerBreakPoint() {
		
	}

	private VWMLDebuggerBreakPoint(String context, String command, boolean after, boolean before) {
		super();
		this.context = context;
		this.command = command;
		this.after = after;
		this.before = before;
	}

	public static VWMLDebuggerBreakPoint build(String context, String command, boolean after, boolean before) {
		return new VWMLDebuggerBreakPoint(context, command, after, before);
	}
	
	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public boolean isAfter() {
		return after;
	}
	
	public void setAfter(boolean after) {
		this.after = after;
	}
	
	public boolean isBefore() {
		return before;
	}

	public void setBefore(boolean before) {
		this.before = before;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VWMLDebuggerBreakPoint other = (VWMLDebuggerBreakPoint) obj;
		if (command == null) {
			if (other.command != null) {
				return false;
			}
		} else if (!command.equals(other.command)) {
			return false;
		}
		if (context == null) {
			if (other.context != null) {
				return false;
			}
		} else if (!context.equals(other.context)) {
			return false;
		}
		return true;
	}
}
