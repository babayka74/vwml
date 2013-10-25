package com.vw.lang.beyond.java.fringe.gate.debug.commands;

import com.vw.lang.beyond.java.fringe.entity.EWComplexEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.debug.IVWMLDebuggerCommandInterface;
import com.vw.lang.beyond.java.fringe.gate.debug.VWML2DebuggerCommandHandler;
import com.vw.lang.debug.common.VWMLDebugCommandResponseData;

/**
 * Processes 'caughtExceptionOnOP' command which is sent by operation processor when exception is caught
 * @author Oleg
 *
 */
public class VWML2DebuggerReportExceptionOnOPCommand extends VWML2DebuggerCommandHandler {
	
	public VWML2DebuggerReportExceptionOnOPCommand() {
	}
	
	@Override
	public EWEntity handler(EWEntity commandArgs) {
		if (commandArgs.isMarkedAsComplexEntity()) {
			String context = (String)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(0).getId();
			String operation = (String)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(1).getId();
			Exception ex = (Exception)((EWComplexEntity)commandArgs).getLink().getConcreteLinkedEntity(2).getId();
			if (getDebuggerInterface().checkIfExceptionBreakPointIsActive(IVWMLDebuggerCommandInterface.exceptionCaughtContext)) {
				// notifies tool
				VWMLDebugCommandResponseData rd = new VWMLDebugCommandResponseData(context, operation, "exception", ex, null);
				getDebuggerInterface().delegate(rd);
				// wait
				getDebuggerInterface().waitOnBreakPoint(IVWMLDebuggerCommandInterface.exceptionCaughtContext,
														IVWMLDebuggerCommandInterface.exceptionCaughtCommand);
			}
		}
		return null;
	}
}
