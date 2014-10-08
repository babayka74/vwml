package com.vw.lang.sink.java.operations.processor.operations.handlers.cartesian;

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
 * Handler of 'OPCARTESIAN' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCartesianHandler extends VWMLOperationHandler {
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
			throw new Exception("operation 'Cartesian' requires at least 2 arguments; check code");
		}
		else
		if (entities.size() == 1) {
			result = handleCartesianOnComplexEntity(entities.get(0), context);
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
			result = handleCartesianOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	protected VWMLEntity handleCartesianOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		VWMLEntity result = emptyEntity;
		if (!entity.isMarkedAsComplexEntity() || entity.getLink().getLinkedObjectsOnThisTime() < s_num_args) {
			return result;
		}
		result = generateComplexEntityBasedOn(entity);
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		VWMLEntity e1 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
		it.next();
		while(true) {
			VWMLEntity e2 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
			cartesian(entity, e1, e2, result);
			if (result.equals(emptyEntity)) {
				break;
			}
			e1 = result;
			it.next();
			if (it.isCorrect()) {
				result = generateComplexEntityBasedOn(entity);
			}
			else {
				break;
			}
		}
		return result;
	}
	
	private void cartesian(VWMLEntity owner, VWMLEntity e1, VWMLEntity e2, VWMLEntity result) {
		VWMLLinkIncrementalIterator it1 = e1.getLink().acquireLinkedObjectsIterator();
		VWMLLinkIncrementalIterator it2 = e2.getLink().acquireLinkedObjectsIterator();
		if (it1 != null && it2 != null) {
			for(; it1.isCorrect(); it1.next()) {
				VWMLEntity e1p = (VWMLEntity)e1.getLink().getConcreteLinkedEntity(it1.getIt());
				it2.setIt(0);
				for(; it2.isCorrect(); it2.next()) {
					VWMLEntity e2p = (VWMLEntity)e2.getLink().getConcreteLinkedEntity(it2.getIt());
					result.getLink().link(joinEntities(owner, e1p, e2p));
				}
			}
		}
	}
	
	private VWMLEntity joinEntities(VWMLEntity owner, VWMLEntity e1, VWMLEntity e2) {
		VWMLEntity r = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
													       owner.getContext().getContext(),
														   ComplexEntityNameBuilder.generateRandomName(),
														   owner.getContext(),
														   owner.getInterpretationHistorySize(),
														   owner.getLink().getLinkOperationVisitor());
		r.getLink().link(e1);
		r.getLink().link(e2);
		return r;
	}
	
	private VWMLEntity generateComplexEntityBasedOn(VWMLEntity entity) {
		return (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
			      entity.getContext().getContext(),
				  ComplexEntityNameBuilder.generateRandomName(),
				  entity.getContext(),
				  entity.getInterpretationHistorySize(),
				  entity.getLink().getLinkOperationVisitor());
	}
}
