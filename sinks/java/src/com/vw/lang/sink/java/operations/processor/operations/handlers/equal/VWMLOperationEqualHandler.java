package com.vw.lang.sink.java.operations.processor.operations.handlers.equal;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
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
import com.vw.lang.sink.java.operations.processor.operations.handlers.include.VWMLOperationIncludeHandler;

/**
 * Handler of 'OPEQ' operation
 * @author ogibayev
 *
 */
public class VWMLOperationEqualHandler extends VWMLOperationHandler {
	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity trueEntity = (VWMLEntity)VWMLObjectsRepository.instance().getTrueEntity();
	private VWMLEntity falseEntity = (VWMLEntity)VWMLObjectsRepository.instance().getFalseEntity();
	
	private static final int s_num_args = 2;

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Include' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleEqualOnComplexEntity(entities.get(0), context);
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
			result = handleEqualOnComplexEntity(entity, context);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	protected VWMLEntity handleEqualOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		VWMLEntity result = nilEntity;
		if (!entity.isMarkedAsComplexEntity()) {
			return result;
		}
		if (entity.getLink().getLinkedObjectsOnThisTime() < s_num_args) {
			return result;
		}
		VWMLEntity e1 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity e2 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		result = equal(e1, e2);
		return result;
	}
	
	protected VWMLEntity equal(VWMLEntity e1, VWMLEntity e2) throws Exception {
		VWMLEntity r = falseEntity;
		VWMLOperationIncludeHandler h = new VWMLOperationIncludeHandler();
		if (h.include(e1, e2) == trueEntity && h.include(e2, e1) == trueEntity) {
			r = trueEntity;
		}
		return r;
	}
}
