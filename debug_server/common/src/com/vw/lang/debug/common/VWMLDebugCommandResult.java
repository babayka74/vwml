package com.vw.lang.debug.common;

/**
 * Root class for debugger's commands
 * @author Oleg
 *
 */
public class VWMLDebugCommandResult {
	
	private int s_resOK = 1974;
	
	private int code = s_resOK;
	private Object data;
	// sets by transport
	private String detailedStatus;
	
	public VWMLDebugCommandResult() {
		super();
	}

	public VWMLDebugCommandResult(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public static VWMLDebugCommandResult defaultCommandResult() {
		return new VWMLDebugCommandResult();
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDetailedResponse() {
		return detailedStatus;
	}

	public void setDetailedResponse(String detailedResponse) {
		this.detailedStatus = detailedResponse;
	}
}
