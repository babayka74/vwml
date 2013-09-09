package com.vw.lang.sink.java.operations.processor.operations.handlers;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPCREATEEXPR' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCreateExprHandler extends VWMLOperationHandler {

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
			
		}
		
	}
	
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
		
		// go through the list in order to create interpreting expression
		VWMLEntity interpretingEntity = null;
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
			if (interpretingEntity == null) {
				interpretingEntity = le;
				continue;
			}
		}
	}
}
