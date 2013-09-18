package com.vw.lang.sink.java.operations.processor.operations.handlers.exe;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPEXECUTE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationExeHandler extends VWMLOperationHandler {

	@Override
	public VWMLEntity handle(VWMLEntity entity, VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		entity = (VWMLEntity)stack.pop();
		if (!entity.isTerm()) {
			interpreter.startOnExistedStack(linkage, stack, entity);
		}
		else {
			interpreter.activateComplexInterpretationProcess(linkage, stack, entity);
		}
		return entity;
	}
}
