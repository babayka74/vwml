package com.vw.lang.beyond.java.fringe.gate;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;

/**
 * General command handler which is called when command is executed
 * @author Oleg
 *
 */
public abstract class GateCommandHandler {
	public abstract EWEntity handler(EWEntity commandArgs);
}
