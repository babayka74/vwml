package com.vw.lang.sink.java.operations.processor.operations.handlers;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPINTERPRET' operation
 * @author ogibayev
 *
 */
public class VWMLOperationInterpretHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLEntity entity = (VWMLEntity)stack.peek();
		if (entity == null) {
			throw new Exception("trying to execute 'INTERPRET' operation on empty stack");
		}
		VWMLEntity interpretingEntity = entity.getInterpreting();
		if (interpretingEntity == null) {
			throw new Exception("interpreting entity wasn't found for entity '" + entity + "'");
		}
		// pops current entity from stack
		stack.pop();
		//... and pushes interpreting entity to stack
		stack.push(interpretingEntity);
	}

}
