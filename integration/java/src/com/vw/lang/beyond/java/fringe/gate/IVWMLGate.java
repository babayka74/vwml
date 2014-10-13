package com.vw.lang.beyond.java.fringe.gate;

import java.util.Properties;

/**
 * Gate's root interface
 * @author Oleg
 *
 */
public interface IVWMLGate extends IEW2VWMLGate, IVWML2EWGate {
	
	public static final String builtInTimeCommandId = "getCurrentSystemTime";
	public static final String builtInDelayCommandId = "sleep";
	public static final String builtInCreateActivityCommandId = "create";
	public static final String builtInRemoveActivityCommandId = "remove";
	public static final String builtInGetStateActivityCommandId = "getstate";
	
	public void init() throws Exception;
	public void done() throws Exception;
	public void activateConfiguration(Properties props) throws Exception;
}
