package com.vw.lang.sink.java.operations.processor.operations.handlers.join;

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

/**
 * Handler of 'OPJOIN' operation
 * @author ogibayev
 *
 */
public class VWMLOperationJoinHandler extends VWMLOperationHandler {

	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Join' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleJoinOnComplexEntity(entities.get(0), context);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																								   entities.size() - 1,
																								   context,
																								   context,
																								   context.getEntityInterpretationHistorySize(),
																								   context.getLinkOperationVisitor(),
																								   VWMLOperationUtils.s_dontAddIfUnknown);
			result = handleJoinOnComplexEntity(entity, context);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
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
		VWMLEntity result = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
															      entity.getContext().getContext(),
																  ComplexEntityNameBuilder.generateRandomName(),
																  entity.getContext(),
																  entity.getInterpretationHistorySize(),
																  entity.getLink().getLinkOperationVisitor());
		VWMLEntity r = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
		if (!r.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		// copy prev result to new result entity
		VWMLLinkIncrementalIterator itR = ((VWMLComplexEntity)r).getLink().acquireLinkedObjectsIterator();
		if (itR != null) {
			joinWith(result, r, itR);
		}
		it.next();
		// next joined component should be complex entity which contains set of joined entities
		joinNew(result, entity, it);
		return result;
	}
	
	private VWMLEntity joinWith(VWMLEntity result, VWMLEntity r, VWMLLinkIncrementalIterator it) {
		for(; it.isCorrect(); it.next()) {
			VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)r).getLink().getConcreteLinkedEntity(it.getIt());
			if (join(result, e) == nilEntity) {
				break;
			}
		}
		return result;
	}
	
	private VWMLEntity joinNew(VWMLEntity result, VWMLEntity r, VWMLLinkIncrementalIterator it) {
		VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)r).getLink().getConcreteLinkedEntity(it.getIt());
		if (e == null || !e.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		it = e.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLEntity joined = (VWMLEntity)((VWMLComplexEntity)e).getLink().getConcreteLinkedEntity(it.getIt());
				if (join(result, joined) == nilEntity) {
					break;
				}
			}
		}
		return result;
	}
	
	private VWMLEntity join(VWMLEntity result, VWMLEntity e) {
		result.getLink().link(e);
		return result;
	}
}
