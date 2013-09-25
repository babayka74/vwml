package com.vw.lang.sink.java.operations.processor.operations.handlers.exe;

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
 * Handler of 'OPEXECUTE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationExeHandler extends VWMLOperationHandler {
	
	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		VWMLEntity entity = null;
		if (entities.size() == 1) {
			entity = entities.get(0);
		}
		else {
			entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																					   entities.size() - 1,
																					   (String)context.getContext(),
																					   context.getEntityInterpretationHistorySize(),
																					   context.getLinkOperationVisitor(),
																					   VWMLOperationUtils.s_addIfUnknown);
		}
		entities.clear();
		stack.popUntilEmptyMark();
		if (entity.isTerm() || entity.isMarkedAsComplexEntity()) {
			interpreter.activateComplexInterpretationProcess(linkage, context, entity);
		}
		else {
			interpreter.startOnExistedContext(linkage, context, entity);
		}
	}
}
