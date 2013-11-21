package com.vw.lang.sink.java.operations.processor.operations.handlers.implicit.assemble;

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

/**
 * Special types of operations; used by interpreter for internal purposes only
 * @author ogibayev
 *
 */
public class VWMLOperationImplicitAssembleHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																							  entities.size() - 1,
																							  (String)originalContext.getContext(),
																							  (String)originalContext.getContext(),
																							  context.getEntityInterpretationHistorySize(),
																							  context.getLinkOperationVisitor(),
																							  VWMLOperationUtils.s_addIfUnknown);
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
		stack.push(entity);
	}
}
