package com.vw.lang.sink.java.operations.processor.operations.handlers.conflictstart;

import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPCONFLICTSITUATIONSTART' operation
 * @author ogibayev
 *
 */
public class VWMLOperationConflictSituationStartHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		stack.popUntilEmptyMark();
	}

	/**
	 * Reports to observer interpreter's internal state
	 * @param interpreter
	 */
	@Override
	public void reportInterpreterInternalState(VWMLIterpreterImpl interpreter) {
		if (interpreter.getObserver() !=  null) {
			interpreter.getObserver().setConflictOperationalState(VWMLConflictRingNodeAutomataInputs.IN_B);
		}
	}
}
