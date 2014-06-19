package com.vw.lang.sink.java.operations.processor.operations.handlers.size;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
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
 * Handler of 'OPSIZE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationSizeHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Size' requires at least 1 argument; check code");
		}
		if (entities.size() == 1) {
			result = handleSizeOnComplexEntity(entities.get(0), context);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
																							   entities,
																							   entities.size() - 1,
																							   context,
																							   context,
																							   context.getEntityInterpretationHistorySize(),
																							   context.getLinkOperationVisitor(),
																							   VWMLOperationUtils.s_dontAddIfUnknown);
			result = handleSizeOnComplexEntity(entity, context);
			entity = null;
		}
		
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	protected VWMLEntity handleSizeOnComplexEntity(VWMLEntity entity, VWMLContext context) {
		String size = String.valueOf(((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime());
		return (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, context.getContext(), size, context, 0, null);
	}
}
