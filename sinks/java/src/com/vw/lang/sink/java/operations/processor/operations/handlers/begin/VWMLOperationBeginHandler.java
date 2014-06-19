package com.vw.lang.sink.java.operations.processor.operations.handlers.begin;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
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

public class VWMLOperationBeginHandler extends VWMLOperationHandler {
	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	
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
			result = handleBeginOnComplexEntity(entities.get(0), context);
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
			result = handleBeginOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	protected VWMLEntity handleBeginOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_num_args) {
			return emptyEntity;
		}
		VWMLEntity result = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
																entity.getContext().getContext(),  
																ComplexEntityNameBuilder.generateRandomName(),
																entity.getContext(),
																entity.getInterpretationHistorySize(),
																entity.getLink().getLinkOperationVisitor());
		VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)entity).getLink().acquireLinkedObjectsIterator();
		for(; it.getIt() < ((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() - 1; it.next()) {
			VWMLEntity linked = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
			result.getLink().link(linked);
		}
		return result;
	}
	
}
