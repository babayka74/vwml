package com.vw.lang.sink.java;

/**
 * Interpterer's export interface
 * @author ogibayev
 *
 */
public interface IVWMLInterpreter {

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
