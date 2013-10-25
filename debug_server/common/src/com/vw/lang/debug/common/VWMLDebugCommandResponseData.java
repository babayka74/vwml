package com.vw.lang.debug.common;

/**
 * Standard response data that is sent back to debugging tool
 * @author Oleg
 *
 */
public class VWMLDebugCommandResponseData {
	private String context;
	private String command;
	private String cause;
	private Exception ex;
	private Object serializedData;

	public VWMLDebugCommandResponseData() {
		super();
	}

	public VWMLDebugCommandResponseData(String context, String command, String cause, Exception ex, Object serializedData) {
		super();
		this.context = context;
		this.command = command;
		this.cause = cause;
		this.ex = ex;
		this.serializedData = serializedData;
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

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public Object getSerializedData() {
		return serializedData;
	}

	public void setSerializedData(Object serializedData) {
		this.serializedData = serializedData;
	}
}
