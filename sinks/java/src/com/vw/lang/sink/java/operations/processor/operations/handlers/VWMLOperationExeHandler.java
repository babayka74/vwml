package com.vw.lang.sink.java.operations.processor.operations.handlers;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.slft.VWMLSingleTermInterpreter;
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
	public void handle(VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLEntity entity = (VWMLEntity)stack.peek();
		if (entity == null) {
			throw new Exception("trying to execute 'EXECUTE' operation on empty stack");
		}
		// pops entity from stack
		stack.pop();
		// creates and activates new interpreter using existing parameters
		VWMLSingleTermInterpreter.instance(linkage, entity).start();
	}
}
