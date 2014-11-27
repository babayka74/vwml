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
		VWMLContext originalContext = context.peekContext();
		VWMLStack stack = context.getStack();
		VWMLEntity interpretingEntity = null;
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		inspector.setAssemblyEntity(true);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		interpretingEntity = getInterpretationOf(entities, interpreter, linkage, context, originalContext, operation);
		inspector.clear();
		entities.clear();
		stack.push(interpretingEntity);
	}

	/**
	 * Gets interpretation of assembled entity
	 * @param entities
	 * @param interpreter
	 * @param linkage
	 * @param context
	 * @param originalContext
	 * @param operation
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity getInterpretationOf(List<VWMLEntity> entities, VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLContext originalContext, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLEntity interpretingEntity = null;
		if (entities.size() == 1) {
			interpretingEntity = interpretationOfArgumentPair(interpreter, entities.get(0));
			if (interpretingEntity == null) {
				interpretingEntity = interpretationOfSyntheticEntity(interpreter, entities.get(0));
				if (interpretingEntity == null) {
					interpretingEntity = interpretSingleEntity(interpreter, entities.get(0), originalContext);
				}
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
			
			interpretingEntity = deduceInterpretingEntity(interpreter, entity);
		}
		if (interpretingEntity == null) {
			if (entity.getContext() != null && entity.getContext().getLink().getParent() != null) {
				// sometimes we need search for entity starting from parent. Usually it happens when assemble operation
				// adds new entity to current context (but entity is moved to another, by Born/Clone command) and 
				// this entity is called from current context
				VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.instance().get(entity.getId(), (VWMLContext)(entity.getContext().getLink().getParent()));
				if (e != null) {
					interpretingEntity = deduceInterpretingEntity(interpreter, e);
				}
			}
			if (interpretingEntity == null) {
				throw new Exception("the interpreting entity '" + entity.getReadableId() + "' can't be 'null'; check VWML's code; entity '" + entity + "'");
			}
		}
		VWMLEntity argRef = interpretationOfArgumentPair(interpreter, interpretingEntity);
		if (argRef != null) {
			interpretingEntity = argRef;
		}
		return interpretingEntity;
	}
	
	protected VWMLEntity deduceInterpretingEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity) throws Exception {
		if (entity == null) {
			return null;
		}
		VWMLEntity interpretingEntity = interpretationOfArgumentPair(interpreter, entity);
		if (interpretingEntity == null) {
			interpretingEntity = interpretationOfSyntheticEntity(interpreter, entity);
			if (interpretingEntity == null) {
				interpretingEntity = entity.getInterpreting();
			}
		}
		return interpretingEntity;
	}

	
	protected VWMLEntity interpretSingleEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext originalContext) throws Exception {
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
			VWMLContext onContext = entity.getContext();
			String readableId = entity.buildReadableId();
			String fullEntityId = onContext.getContext() + "." + readableId;
			entity = (VWMLEntity)VWMLObjectsRepository.instance().get(fullEntityId, originalContext);
			if (entity == null) {
				entity = (VWMLEntity)VWMLObjectsRepository.instance().findOnConcreteContextByReadableId(readableId, onContext);
				if (entity == null) {
					throw new Exception("couldn't find entity '" + fullEntityId + "'");
				}
			}
			interpretingEntity = entity.getInterpreting();
			if (interpretingEntity == null) {
				// this case is checked when entity is defined on some contexts, usually happens during static entity definition (see Maze, battleModel3A)
				if (entity.getContext().getLink().getParent() != null) {
					VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.instance().get(readableId, (VWMLContext)(entity.getContext().getLink().getParent()));
					interpretingEntity = deduceInterpretingEntity(interpreter, e);
				}
				if (interpretingEntity == null) {
					throw new Exception("interpreting entity wasn't found for entity '" + readableId + "'; on contexts '" + onContext.getContext() + "/" + originalContext.getContext() + "'");
				}
			}
			// cached interpreting entity
			initialEntity.setInterpreting(interpretingEntity);
		}
		return interpretingEntity;
	}
	
	private VWMLEntity interpretationOfSyntheticEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity) {
		if (entity.isSynthetic() && interpreter.getInterpretingEntityForSyntheticEntity() != null) {
			return interpreter.getInterpretingEntityForSyntheticEntity();
		}
		return null;
	}
	
	private VWMLEntity interpretationOfArgumentPair(VWMLInterpreterImpl interpreter, VWMLEntity entity) throws Exception {
		return VWMLOperationUtils.getRelatedEntityByArgument(interpreter, entity);
	}
}
