package com.vw.lang.sink.java.operations.processor.operations.handlers;

import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Handler of 'OPCREATEEXPR' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCreateExprHandler extends VWMLOperationHandler {

	// check 'createExprStrategyForLimitedNumberOfLinkedObjects's' size before changes
	private static int s_min_objects_for_complex_interpretation = 2;
	
	protected static abstract class VWMLOperationCreateExprStrategy {
		public abstract void run(VWMLLinkage linkage, Stack stack, VWMLEntity entity) throws Exception;
	}

	/**
	 * When only one entity in link - means that behaviour should be the same as for undefined entity
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategy1EntityInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, Stack stack, VWMLEntity entity) throws Exception {
			linkage.interpretUndefinedEntity(entity.getId());
			stack.push(entity);
		}
		
	}
	
	/**
	 * When two entities in link - means that entity should be interpreted as second entity, no need to create complex entity
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategy2EntitiesInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, Stack stack, VWMLEntity entity) throws Exception {
			VWMLEntity e = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(1);
			if (e == null) {
				throw new Exception("inconsistence detected; expected at least 2 linked entities but found only '" + entity.getLink().getLinkedObjectsOnThisTime() + "'");
			}
			entity.setInterpreting(e);
			stack.push(entity);
		}
		
	}
	
	/**
	 * Used when more than two entities in link
	 * @author ogibayev
	 *
	 */
	protected static class VWMLOperationCreateExprStrategyForManyEntitiesInLink extends VWMLOperationCreateExprStrategy {

		@Override
		public void run(VWMLLinkage linkage, Stack stack, VWMLEntity entity) throws Exception {
			// create complex entity
			String cen = ComplexEntityNameBuilder.generateRandomName();
			VWMLEntity newComplexEntity = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.COMPLEX_ENTITY, cen, entity.getLink().getLinkOperationVisitor());
			// go through the list in order to create interpreting expression
			VWMLEntity e = null;
			VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (e == null) {
					e = le;
					continue;
				}
				newComplexEntity.link(le);
			}
			e.setInterpreting(newComplexEntity);
			stack.push(e);
		}
	}
	
	private VWMLOperationCreateExprStrategy createExprStrategyForLimitedNumberOfLinkedObjects[] = {
			new VWMLOperationCreateExprStrategy1EntityInLink(),
			new VWMLOperationCreateExprStrategy2EntitiesInLink()
	};
	
	private VWMLOperationCreateExprStrategyForManyEntitiesInLink createExprStrategyForManyEntitiesInLink = new VWMLOperationCreateExprStrategyForManyEntitiesInLink();
	
	@Override
	public void handle(VWMLLinkage linkage, Stack stack, VWMLOperation operation) throws Exception {
		VWMLEntity entity = (VWMLEntity)stack.peek();
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
		stack.pop();
		// runs separated strategies if number of linked objects less than 's_min_objects_for_complex_interpretation'
		if (objects <= s_min_objects_for_complex_interpretation) {
			createExprStrategyForLimitedNumberOfLinkedObjects[objects - 1].run(linkage, stack, entity);
		}
		else {
			createExprStrategyForManyEntitiesInLink.run(linkage, stack, entity);
		}
	}
}
