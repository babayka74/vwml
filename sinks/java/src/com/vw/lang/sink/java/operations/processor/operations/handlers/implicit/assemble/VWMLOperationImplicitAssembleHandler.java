package com.vw.lang.sink.java.operations.processor.operations.handlers.implicit.assemble;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
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
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																							  entities.size() - 1,
																							  "",
																							  0,
																							  null,
																							  VWMLOperationUtils.s_addIfUnknown);
		inspector.clear();
		stack.popUntilEmptyMark();
		stack.push(entity);
	}
}
