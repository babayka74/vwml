package com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr.VWMLOperationHandlerCreateExprFromEntity.VWMLOperationCreateExprStrategy;

/**
 * Handler of 'OPCREATEEXPR' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCreateExprHandler extends VWMLOperationHandler {
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) { // specific case where only one entity on stack
			new VWMLOperationHandlerCreateExprFromEntity().handle(interpreter, entities.get(0), linkage, entities.get(0).getContext(), operation);
		}
		else
		if (entities.size() == 2) { // specific case where only 2 entities on stack
			VWMLEntity interpretedEntity = (VWMLEntity)entities.get(1);
			VWMLEntity interpretingEntity = (VWMLEntity)entities.get(0);
			if (VWMLOperationCreateExprStrategy.isInterpretationShouldBeApplied(interpretedEntity, interpretingEntity)) {
				VWMLEntity argRef = VWMLOperationUtils.getRelatedEntityByArgument(interpreter, interpretedEntity);
				if (argRef != null) {
					interpretedEntity = argRef;
				}
				interpretedEntity.setInterpreting(interpretingEntity);
			}
			else {
				VWMLOperationCreateExprStrategy.stopInterpretation(interpreter);
			}
		}
		else {
			VWMLEntity entity = entities.get(entities.size() - 1);
			VWMLEntity newComplexEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																											entities.size() - 2,
																											entity.getContext(),
																											entity.getContext(),
																											context.getEntityInterpretationHistorySize(),
																											context.getLinkOperationVisitor(),
																											VWMLOperationUtils.s_dontAddIfUnknown);
			if (VWMLOperationCreateExprStrategy.isInterpretationShouldBeApplied(entity, newComplexEntity)) {
				VWMLEntity argRef = VWMLOperationUtils.getRelatedEntityByArgument(interpreter, entity);
				if (argRef != null) {
					entity = argRef;
				}
				entity.setInterpreting(newComplexEntity);
			}
			else {
				VWMLOperationCreateExprStrategy.stopInterpretation(interpreter);
			}
		}
		// clear stack
		entities.clear();
		inspector.clear();
	}

}