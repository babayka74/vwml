package com.vw.lang.sink.java.operations.processor.operations.handlers.interpret;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreter;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
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
	public VWMLEntity handle(VWMLEntity entity, VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		entity = (VWMLEntity)stack.pop();
		if (entity == null) {
			throw new Exception("trying to execute 'INTERPRET' operation on empty stack");
		}
		VWMLEntity interpretingEntity = null;
		if (entity.isTerm()) {
			interpretingEntity = ((VWMLTerm)entity).getAssociatedEntity();
			if (interpretingEntity == null) {
				throw new Exception("inconsistency found for term '" + entity + "'; please check initial state initialization");
			}
			interpretingEntity = interpretingEntity.getInterpreting();
		}
		else {
			interpretingEntity = entity.getInterpreting();
		}
		if (interpretingEntity == null) {
			throw new Exception("interpreting entity wasn't found for entity '" + entity + "'");
		}
		stack.push(interpretingEntity);
		return interpretingEntity;
	}
}
