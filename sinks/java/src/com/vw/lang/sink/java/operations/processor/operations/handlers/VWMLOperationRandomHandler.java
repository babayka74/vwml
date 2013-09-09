package com.vw.lang.sink.java.operations.processor.operations.handlers;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Handler of 'OPRANDOM' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRandomHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLLinkage linkage, Stack stack, VWMLOperation operation) throws Exception {
		VWMLEntity entity = (VWMLEntity)stack.peek();
		if (entity == null) {
			throw new Exception("trying to execute 'RANDOM' operation on empty stack");
		}
		if (!entity.isMarkedAsComplexEntity()) {
			throw new Exception("trying to execute 'RANDOM' on simple entity; execution is possible only on complex entity");
		}
		IVWMLLinkVisitor visitor = entity.getLink().getLinkOperationVisitor();
		// pops current entity from top of stack
		stack.pop();
		// 'RANDOM' operation should return entity's number which is going to be extracted from complex entity
		int linkedObjs = entity.getLink().getLinkedObjectsOnThisTime();
		// this entity is extracted and pushed to stack
		int entityNumber = GeneralUtils.getIntInRange(0, linkedObjs);
		// get requested entity
		entity = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(entityNumber);
		if (entity == null) {
			// since no entity was extracted we are constructing empty complex entity
			String emptyCEName = ComplexEntityNameBuilder.generateEmptyComplexEntity();
			entity = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, emptyCEName, visitor);
		}
		// push result entity to stack - finishing 'RANDOM' operation
		stack.push(entity);
	}
}
