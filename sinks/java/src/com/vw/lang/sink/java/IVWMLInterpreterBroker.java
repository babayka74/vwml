package com.vw.lang.sink.java;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Interpterer's export interface
 * @author ogibayev
 *
 */
public interface IVWMLInterpreterBroker {

	/**
	 * Sets debugger's gate; used by concrete interpreter
	 * @param debuggerGate
	 */
	public void setDebuggerGate(IVWMLGate debuggerGate);

	/**
	 * Actually starts interpretation process
	 * @throws Exception
	 */
	public void start() throws Exception;
	/**
	 * Builds modules' initial state
	 * @throws Exception
	 */
	public void build() throws Exception;	
}
