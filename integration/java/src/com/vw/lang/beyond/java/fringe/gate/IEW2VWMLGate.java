package com.vw.lang.beyond.java.fringe.gate;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;

/**
 * External world (EW) implements this interface for connectivity with Virtual World
 * @author Oleg
 *
 */
public interface IEW2VWMLGate  {
	/**
	 * Invokes action on virtual world side
	 * @param commandId
	 * @param commandArgs
	 */
	public EWEntity invokeVW(String commandId, EWEntity commandArgs);
	
	/**
	 * Activates VWML's gate allowing to EW to call VWML world in callback's manner
	 * @param gate
	 */
	public void activateVWCallback(IEW2VWMLGate gate);
}
