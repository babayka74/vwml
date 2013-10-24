package com.vw.lang.debug.common;

/**
 * Command sent by client
 * @author Oleg
 *
 */
public abstract class VWMLDebugCommand {
	private String name;
	private Object data;
	
	private static final String s_commandTag = "command";
	
	public VWMLDebugCommand() {
		super();
	}

	public VWMLDebugCommand(String name, Object data) {
		super();
		this.name = name;
		this.data = data;
	}

	public static String getCommandTag() {
		return s_commandTag;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public abstract VWMLDebugCommandResult handle(Object context);
}
