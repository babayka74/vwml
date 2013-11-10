package com.vw.lang.sink.java.operations.processor.operations.handlers.join;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
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
 * Handler of 'OPJOIN' operation
 * @author ogibayev
 *
 */
public class VWMLOperationJoinHandler extends VWMLOperationHandler {

	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	
	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Join' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleJoinOnComplexEntity(entities.get(0), context);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																								   entities.size() - 1,
																								   context.getContext(),
																								   context.getContext(),
																								   context.getEntityInterpretationHistorySize(),
																								   context.getLinkOperationVisitor(),
																								   VWMLOperationUtils.s_dontAddIfUnknown);
			result = handleJoinOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
		stack.push(result);
	}
	
	protected VWMLEntity handleJoinOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)entity).getLink().acquireLinkedObjectsIterator();
		if (it == null) {
			return emptyEntity;
		}
		VWMLEntity result = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.COMPLEX_ENTITY,
												 ComplexEntityNameBuilder.generateRandomName(),
												 context.getContext(),
												 entity.getInterpretationHistorySize(),
												 entity.getLink().getLinkOperationVisitor());
		for(; it.isCorrect(); it.next()) {
			VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
			if (join(result, e) == nilEntity) {
				break;
			}
		}
		return result;
	}
	
	private VWMLEntity join(VWMLEntity result, VWMLEntity e) {
		if (!e.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		for(VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)e).getLink().acquireLinkedObjectsIterator(); it.isCorrect(); it.next()) {
			VWMLEntity r = (VWMLEntity)((VWMLComplexEntity)e).getLink().getConcreteLinkedEntity(it.getIt());
			result.getLink().link(r);
		}
		return result;
	}
}