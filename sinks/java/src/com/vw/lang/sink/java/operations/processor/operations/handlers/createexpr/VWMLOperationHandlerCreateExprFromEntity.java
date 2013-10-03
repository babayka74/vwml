package com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr;

import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Creates expression by parsing entity
 * @author ogibayev
 *
 */
public class VWMLOperationHandlerCreateExprFromEntity {
	// check 'createExprStrategyForLimitedNumberOfLinkedObjects's' size before changes
	private static int s_min_objects_for_complex_interpretation = 2;

	protected static abstract class VWMLOperationCreateExprStrategy {
		public abstract void run(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception;
	}

	/**
	 * When only one entity in link - means that behaviour should be the same as for undefined entity
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategy1EntityInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
			linkage.interpretUndefinedEntity(entity.getId());
		}

	}

	/**
	 * When two entities in link - means that entity should be interpreted as second entity, no need to create complex entity
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategy2EntitiesInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
			VWMLEntity e = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
			if (e == null) {
				throw new Exception("inconsistence detected; expected at least 2 linked entities but found only '" + entity.getLink().getLinkedObjectsOnThisTime() + "'");
			}
			VWMLEntity e1 = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(1);
			if (e1 == null) {
				throw new Exception("inconsistence detected; expected at least 2 linked entities but found only '" + entity.getLink().getLinkedObjectsOnThisTime() + "'");
			}
			e.setInterpreting(e1);
		}

	}

	/**
	 * Used when more than two entities in link
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategyForManyEntitiesInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
			VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
			VWMLEntity le = null;
			entity = (VWMLEntity)entity.getLink().peek(it);
			le = (VWMLEntity)entity.getLink().peek(it);
			// create complex entity
			String cen = ComplexEntityNameBuilder.generateRandomName();
			VWMLEntity newComplexEntity = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.COMPLEX_ENTITY,
																					cen,
																					(String)entity.getContext().getContext(),
																					context.getEntityInterpretationHistorySize(),
																					context.getLinkOperationVisitor());
			// go through the list in order to create interpreting expression
			for(; le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				newComplexEntity.link(le);
			}
			entity.setInterpreting(newComplexEntity);
		}
	}

	private VWMLOperationCreateExprStrategy createExprStrategyForLimitedNumberOfLinkedObjects[] = {
			new VWMLOperationCreateExprStrategy1EntityInLink(),
			new VWMLOperationCreateExprStrategy2EntitiesInLink()
	};

	private VWMLOperationCreateExprStrategyForManyEntitiesInLink createExprStrategyForManyEntitiesInLink = new VWMLOperationCreateExprStrategyForManyEntitiesInLink();


	public void handle(VWMLEntity entity, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		if (entity == null) {
			throw new Exception("trying to execute 'CREATEEXPR' operation on empty stack");
		}
		if (!entity.isMarkedAsComplexEntity()) {
			throw new Exception("can't create interpreting expression from simple entity '" + entity + "'");
		}
		// check what kind of entity we should create
		int objects = entity.getLink().getLinkedObjectsOnThisTime();
		if (objects == 0) {
			throw new Exception("can't create interpreting expression from empty complex entity '" + entity + "'");
		}
		// pops entity from stack
		// runs separated strategies if number of linked objects less than 's_min_objects_for_complex_interpretation'
		if (objects <= s_min_objects_for_complex_interpretation) {
			createExprStrategyForLimitedNumberOfLinkedObjects[objects - 1].run(linkage, context, entity);
		}
		else {
			createExprStrategyForManyEntitiesInLink.run(linkage, context, entity);
		}
	}
}
