package com.vw.lang.sink.java.operations.processor.operations.handlers.random;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Handler of 'OPRANDOM' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRandomHandler extends VWMLOperationHandler {
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) { // specific case where only one entity on stack
			entity = getRandomEntityFromComplexEntity(entities.get(0));
		}		
		else {
			entity = getRandomEntityFromSetOfEntities(entities);
		}
		inspector.clear();
		entities.clear();
		stack.push(entity);
	}
	
	protected VWMLEntity getRandomEntityFromComplexEntity(VWMLEntity parentEntity) throws Exception {
		if (parentEntity == null) {
			throw new Exception("trying to execute 'RANDOM' operation on empty stack");
		}
		if (!parentEntity.isMarkedAsComplexEntity()) {
			throw new Exception("trying to execute 'RANDOM' on simple entity; execution is possible only on complex entity");
		}
		AbstractVWMLLinkVisitor visitor = parentEntity.getLink().getLinkOperationVisitor();
		// 'RANDOM' operation should return entity's number which is going to be extracted from complex entity
		int linkedObjs = parentEntity.getLink().getLinkedObjectsOnThisTime();
		// this entity is extracted and pushed to stack
		int entityNumber = GeneralUtils.getIntInRange(0, linkedObjs);
		// get requested entity
		VWMLEntity entity = (VWMLEntity)parentEntity.getLink().getConcreteLinkedEntity(entityNumber);
		if (entity == null) {
			// since no entity was extracted we are constructing empty complex entity
			String emptyCEName = ComplexEntityNameBuilder.generateEmptyComplexEntity();
			entity = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY,
															   emptyCEName,
															   parentEntity.getContext().getContext(),
															   parentEntity.getInterpretationHistorySize(),
															   VWMLObjectsRepository.notAsOriginal,
															   visitor);
		}
		return entity;
	}
	
	protected VWMLEntity getRandomEntityFromSetOfEntities(List<VWMLEntity> entities) {
		int entityNumber = GeneralUtils.getIntInRange(0, entities.size());
		entityNumber = (entityNumber >= entities.size()) ? entities.size() - 1 : entityNumber;
		return entities.get((entities.size() - 1) - entityNumber);
	}
}
