package com.vw.lang.beyond.java.fringe.gate.debug.commands;

import com.vw.lang.beyond.java.fringe.entity.EWComplexEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.debug.VWML2DebuggerCommandHandler;
import com.vw.lang.debug.common.VWMLDebugCommandResponseData;

/**
 * Processes 'checkbp' command which is sent by VWML processor in order to check
 * whether processor should stop on breakpoint or no
 * @author Oleg
 *
 */
public class VWML2DebuggerCheckBpActionCommand extends VWML2DebuggerCommandHandler {
	
	public VWML2DebuggerCheckBpActionCommand() {
	}

	@Override
	public EWEntity handler(EWEntity commandArgs) {
		if (commandArgs.isMarkedAsComplexEntity()) {
			String context = (String)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(0).getId();
			String operation = (String)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(1).getId();
			boolean beforeOp = Boolean.parseBoolean((String)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(2).getId());
			if (getDebuggerInterface().checkIfOperationBreakPointIsActive(context, operation, beforeOp)) {
				// notifies tool
				VWMLDebugCommandResponseData rd = new VWMLDebugCommandResponseData(context, operation, "breakpoint", null, Boolean.valueOf(beforeOp));
				getDebuggerInterface().delegate(rd);
				// wait
				getDebuggerInterface().waitOnBreakPoint(context, operation);
			}
		}
		return null;
	}
}
