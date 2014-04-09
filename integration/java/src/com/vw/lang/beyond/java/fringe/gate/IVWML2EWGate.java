package com.vw.lang.beyond.java.fringe.gate;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;

/**
 * VW implements this interface for connectivity with external world
 * @author Oleg
 *
 */
public interface IVWML2EWGate {
	/**
	 * Invokes action on external world side
	 * @param commandId
	 * @param commandArgs
	 */
	public EWEntity invokeEW(String commandId, EWEntity commandArgs);
}
