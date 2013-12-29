package com.vw.lang.sink.java.operations.processor.operations.handlers.substruct;

import java.util.List;

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
 * Handler of 'OPSUBSTRUCT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationSubstructHandler extends VWMLOperationHandler {

	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Substruct' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleSubstructOnComplexEntity(entities.get(0), context);
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
			result = handleSubstructOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
		stack.push(result);
	}

	protected VWMLEntity handleSubstructOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		VWMLEntity result = null;
		if (!entity.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() == 1) {
			VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
			if (!e.isMarkedAsComplexEntity()) {
				return nilEntity;
			}
			return e;
		}
		VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)entity).getLink().acquireLinkedObjectsIterator();
		if (it == null) {
			return emptyEntity;
		}
		for(; it.isCorrect(); it.next()) {
			VWMLEntity e1 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
			if (result == null) {
				it.next();
				if (it.isCorrect()) {
					VWMLEntity e2 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(it.getIt());
					result = substruct(e1, e2, entity);
				}
			}
			else {
				VWMLEntity oldResult = result;
				result = substruct(result, e1, entity);
				if (oldResult != null) {
					oldResult.getLink().unlinkFromAll();
					oldResult = null;
				}
			}
			if (result == nilEntity) {
				break;
			}
		}	
		return result;
	}
	
	protected VWMLEntity substruct(VWMLEntity e1, VWMLEntity e2, VWMLEntity ownerEntity) throws Exception {
		if (!e1.isMarkedAsComplexEntity() || !e2.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		VWMLEntity result = (VWMLEntity)VWMLObjectsRepository.acquireWithoutCheckingOnExistence(VWMLObjectType.COMPLEX_ENTITY,
																	  ComplexEntityNameBuilder.generateRandomName(),
																	  ownerEntity.getContext(),
																	  ownerEntity.getInterpretationHistorySize(),
																	  VWMLObjectsRepository.notAsOriginal,
																	  ownerEntity.getLink().getLinkOperationVisitor());
		VWMLLinkIncrementalIterator itE1 = ((VWMLComplexEntity)e1).getLink().acquireLinkedObjectsIterator();
		VWMLLinkIncrementalIterator itE2 = ((VWMLComplexEntity)e2).getLink().acquireLinkedObjectsIterator();
		if (itE1 == null && itE2 != null || (itE1 == null && itE2 == null)) {
			return emptyEntity;
		}
		else
		if (itE1 != null && itE2 == null) {
			return e1;
		}
		boolean[] visitedOnE2 = new boolean[((VWMLComplexEntity)e2).getLink().getLinkedObjectsOnThisTime()];
		for(; itE1.isCorrect(); itE1.next()) {
			VWMLEntity pe1 = (VWMLEntity)((VWMLComplexEntity)e1).getLink().getConcreteLinkedEntity(itE1.getIt());
			boolean add = true;
			for(; itE2.isCorrect(); itE2.next()) {
				VWMLEntity pe2 = (VWMLEntity)((VWMLComplexEntity)e2).getLink().getConcreteLinkedEntity(itE2.getIt());
				if (pe1.equals(pe2) && !visitedOnE2[itE2.getIt()]) {
					visitedOnE2[itE2.getIt()] = true;
					add = false;
					break;
				}
			}
			if (add) {
				result.getLink().link(pe1);
			}
			itE2.setIt(0);
		}
		visitedOnE2 = null;
		return result;
	}	
}
