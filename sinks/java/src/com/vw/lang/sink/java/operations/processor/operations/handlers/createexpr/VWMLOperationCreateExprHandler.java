package com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPCREATEEXPR' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCreateExprHandler extends VWMLOperationHandler {
	
	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) { // specific case where only one entity on stack
			new VWMLOperationHandlerCreateExprFromEntity().handle(entities.get(0), linkage, context, operation);
		}
		else
		if (entities.size() == 2) { // specific case where only 2 entities on stack
			VWMLEntity interpretedEntity = (VWMLEntity)entities.get(1);
			VWMLEntity interpretingEntity = (VWMLEntity)entities.get(0);
			interpretedEntity.setInterpreting(interpretingEntity);
		}
		else {
			VWMLEntity entity = entities.get(entities.size() - 1);
			VWMLEntity newComplexEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																											entities.size() - 2,
																											(String)context.getContext(),
																											context.getEntityInterpretationHistorySize(),
																											context.getLinkOperationVisitor(),
																											VWMLOperationUtils.s_dontAddIfUnknown);
			entity.setInterpreting(newComplexEntity);
		}
		// clear stack
		stack.popUntilEmptyMark();
		entities.clear();
	}

}