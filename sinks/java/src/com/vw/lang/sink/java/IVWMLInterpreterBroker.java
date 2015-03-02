package com.vw.lang.sink.java;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.entity.InterpretationObserver;

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
	 * Sets interpretation observer
	 * @param interpretationObserver
	 */
	public void setInterpretationObserver(InterpretationObserver interpretationObserver);
	
	/**
	 * Actually starts interpretation process
	 * @throws Exception
	 */
	public void start() throws Exception;
	
	/**
	 * Clears all resources which were allocated during interpreter's session
	 * @throws Exception
	 */
	public void clear() throws Exception;
	/**
	 * Builds modules' initial state
	 * @throws Exception
	 */
	public void build() throws Exception;	
}
