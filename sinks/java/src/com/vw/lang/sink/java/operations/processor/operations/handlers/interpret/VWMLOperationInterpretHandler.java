package com.vw.lang.sink.java.operations.processor.operations.handlers.interpret;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPINTERPRET' operation
 * @author ogibayev
 *
 */
public class VWMLOperationInterpretHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLEntity interpretingEntity = null;
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) {
			interpretingEntity = interpretSingleEntity(entities.get(0));
		}
		else {
			interpretingEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																								   entities.size() - 1,
																								   (String)stack.getContext(),
																								   stack.getEntityInterpretationHistorySize(),
																								   stack.getLinkOperationVisitor(),
																								   VWMLOperationUtils.s_addIfUnknown);
		}
		inspector.clear();
		stack.popUntilEmptyMark();
		stack.push(interpretingEntity);
	}
	
	protected VWMLEntity interpretSingleEntity(VWMLEntity entity) throws Exception {
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
		return interpretingEntity;
	}
}
