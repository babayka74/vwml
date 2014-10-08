package com.vw.lang.sink.java.operations.processor.operations.handlers.squeeze;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Handler of 'OPSQU' operation
 * @author ogibayev
 *
 */
public class VWMLOperationSqueezeHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Squeeze' requires at least 1 argument; check code");
		}
		else
		if (entities.size() == 1) {
			result = handleSqueezeOnComplexEntity(entities.get(0), context);
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
			result = handleSqueezeOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}
	
	protected VWMLEntity handleSqueezeOnComplexEntity(VWMLEntity entity, VWMLContext context) {
		VWMLEntity result = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
															    entity.getContext().getContext(),
																ComplexEntityNameBuilder.generateRandomName(),
																entity.getContext(),
																entity.getInterpretationHistorySize(),
																entity.getLink().getLinkOperationVisitor());
		if (!entity.isMarkedAsComplexEntity()) {
			return entity;
		}
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		if (it == null) {
			return entity;
		}
		for(; it.isCorrect(); it.next()) {
			VWMLEntity e = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(it.getIt());
			if (!result.getLink().isLinked(e)) {
				result.getLink().link(e);
			}
		}
		return result;
	}
}
