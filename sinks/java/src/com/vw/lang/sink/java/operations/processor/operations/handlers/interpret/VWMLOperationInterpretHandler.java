package com.vw.lang.sink.java.operations.processor.operations.handlers.interpret;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
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
			entity = entities.get(0);
			interpretingEntity = interpretationOfArgumentPair(interpreter, entity);
			if (interpretingEntity == null) {
				interpretingEntity = interpretationOfSyntheticEntity(interpreter, entity);
				if (interpretingEntity == null) {
					interpretingEntity = interpretSingleEntity(interpreter, entity, originalContext);
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
			interpretingEntity = lookingForHighLevelEntity(interpreter, entity);
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
				interpretingEntity = lazyInterpeting(entity);
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
		interpretingEntity = lazyInterpeting(entity);
		if (interpretingEntity == null) {
			interpretingEntity = lookingForHighLevelEntity(interpreter, entity);
			if (interpretingEntity == null) {
				throw new Exception("interpreting entity wasn't found for entity '" + entity.buildReadableId() + "'; on contexts '" + entity.getContext().getContext() + "/" + originalContext.getContext() + "'");
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
	
	private VWMLEntity lazyInterpeting(VWMLEntity entity) throws Exception {
		VWMLEntity e = null;
		e = entity.getInterpreting();
		return e;
	}
	
	private VWMLEntity lookingForHighLevelEntity(VWMLInterpreterImpl interpreter, VWMLEntity fromEntity) throws Exception {
		VWMLEntity interpretingEntity = null;
		// this case is checked when entity is defined on some contexts, usually happens during static entity definition (see Maze, battleModel3A)
		if (fromEntity.getContext().getLink().getParent() != null) {
			VWMLContext parentCtx = (VWMLContext)fromEntity.getContext().getLink().getParent();
			ContextIdPair parentCtxPair = VWMLContextsRepository.instance().wellFormedContext(parentCtx.getContext());
			if (parentCtxPair != null) {
				VWMLEntity highLevelEntityOnModel = (VWMLEntity)VWMLObjectsRepository.instance().get(fromEntity.getId(), VWMLContextsRepository.instance().get(parentCtxPair.getOrigContextId()));
				if (highLevelEntityOnModel != null) {
					VWMLEntity entity = (VWMLEntity)VWMLObjectsRepository.getAndCreateInCaseOfClone(parentCtx.getContext(), highLevelEntityOnModel, true);
					interpretingEntity = deduceInterpretingEntity(interpreter, entity);
				}
			}
		}
		return interpretingEntity;
	}
}
