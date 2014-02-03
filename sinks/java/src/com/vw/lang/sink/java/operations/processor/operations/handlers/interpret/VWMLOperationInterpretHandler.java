package com.vw.lang.sink.java.operations.processor.operations.handlers.interpret;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPINTERPRET' operation
 * @author ogibayev
 *
 */
public class VWMLOperationInterpretHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLEntity interpretingEntity = null;
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) {
			interpretingEntity = interpretationOfSyntheticEntity(interpreter, entities.get(0));
			if (interpretingEntity == null) {
				interpretingEntity = interpretSingleEntity(entities.get(0), originalContext);
			}
		}
		else {
			entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																					   entities.size() - 1,
																					   originalContext,
																					   context,
																					   context.getEntityInterpretationHistorySize(),
																					   context.getLinkOperationVisitor(),
																					   VWMLOperationUtils.s_addIfUnknown);
			interpretingEntity = interpretationOfSyntheticEntity(interpreter, entity);
			if (interpretingEntity == null) {
				interpretingEntity = entity.getInterpreting();
			}
		}
		inspector.clear();
		entities.clear();
		if (interpretingEntity == null) {
			throw new Exception("the interpreting entity '" + entity.getReadableId() + "' can't be 'null'; check VWML's code; entity '" + entity + "'");
		}
		stack.push(interpretingEntity);
	}
	
	protected VWMLEntity interpretSingleEntity(VWMLEntity entity, VWMLContext originalContext) throws Exception {
		VWMLEntity initialEntity = entity;
		if (entity == null) {
			throw new Exception("trying to execute 'INTERPRET' operation on empty stack");
		}
		VWMLEntity interpretingEntity = null;
		if (entity.isTerm()) {
			entity = ((VWMLTerm)entity).getAssociatedEntity();
			if (entity == null) {
				throw new Exception("inconsistency found for term '" + entity + "'; please check initial state initialization");
			}
		}
		// if entity wasn't defined using IAS operator and its interpretation was setup using 
		// undefined interpetation strategy then interpretation 
		// should be considered in more complicated way
		if (entity.isRecursiveInterpretationOnOriginal()) {
			if (entity.getResolvedInRuntime() == null) {
				// looking for entities for which interpreting expression ^ was applied
				String contextId = entity.getContext().getContext();
				int le = contextId.lastIndexOf(".");
				if (le != -1) {
					contextId = contextId.substring(0, le);
					String fullEntityId = contextId + "." + entity.buildReadableId();
					entity = (VWMLEntity)VWMLObjectsRepository.instance().get(fullEntityId, originalContext);
					if (entity == null) {
						entity = initialEntity;
					}
					else {
						entity.setResolvedInRuntime(entity);
					}
				}
			}
			else {
				entity = entity.getResolvedInRuntime();
			}
		}
		interpretingEntity = entity.getInterpreting();
		if (interpretingEntity == null) {
			String fullEntityId = entity.getContext().getContext() + "." + entity.buildReadableId();
			entity = (VWMLEntity)VWMLObjectsRepository.instance().get(fullEntityId, originalContext);
			if (entity == null) {
				throw new Exception("couldn't find entity '" + fullEntityId + "'");
			}
			interpretingEntity = entity.getInterpreting();
			if (interpretingEntity == null) {
				throw new Exception("interpreting entity wasn't found for entity '" + entity.getId() + "'");
			}
			// cached interpreting entity
			initialEntity.setInterpreting(interpretingEntity);
		}
		return interpretingEntity;
	}
	
	private VWMLEntity interpretationOfSyntheticEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity) {
		if (entity.isSynthetic() && interpreter.getInterpretingEntityForSyntheticEntity() != null && entity.isRecursiveInterpretationOnOriginal()) {
			return interpreter.getInterpretingEntityForSyntheticEntity();
		}
		return null;
	}
}
