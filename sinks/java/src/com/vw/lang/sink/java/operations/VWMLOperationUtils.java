package com.vw.lang.sink.java.operations;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterListener;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Some aux. methods related to VWML's operations
 * @author ogibayev
 *
 */
public class VWMLOperationUtils {

	public static boolean s_addIfUnknown = true;
	public static boolean s_dontAddIfUnknown = false;
	
	
	/**
	 * Generates complex entity from list by adding entities starting from the end of the list; name is random
	 * @param rebuiltEntity
	 * @param entities
	 * @param fromPos
	 * @param context
	 * @param effectiveContext
	 * @param interpretationHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLEntity generateComplexEntityFromEntitiesReversedStack(List<VWMLEntity> entities,
			                                                                int fromPos,
			                                                                VWMLContext context,
			                                                                VWMLContext effectiveContext,
			                                                                int interpretationHistorySize,
			                                                                AbstractVWMLLinkVisitor visitor,
			                                                                boolean addIfUnknown) throws Exception {
		// rebuilt entity is null, so try to generate it
		VWMLEntity newComplexEntity = null;
		VWMLContext origEffectiveContext = context;
		// if effective context (lifeterm's context) isn't parent of entity's context it means that 
		// actual interpretation is being run on context identified by effectiveContext id
		if (!VWMLContext.isContextChildOf(effectiveContext.getContext(), context.getContext())) {
			context = effectiveContext;
		}
		String cen = ComplexEntityNameBuilder.generateRandomName();
		if (entities.size() > 0) {
			newComplexEntity = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY, cen, cen, null, 0, null);
			for(int i = fromPos; i >= 0; i--) {
				newComplexEntity.getLink().link(entities.get(i));
			}
			if (addIfUnknown) {
				VWMLEntity e = null;
				if (origEffectiveContext.equals(context)) {
					// lookups for entity by its id (builds id and searches in repository); 
					// if entity found then it is returned, otherwise 'newComplexEntity' is returned
					e = lookupAndRelinkEntityOnContext(origEffectiveContext, newComplexEntity);
					// if found entity and newComplexEntity are the same then we add it to repository and return null value
					addToRepositoryIfTheSame(e, newComplexEntity, origEffectiveContext);
				}
				else {
					e = lookupAndRelinkEntityOnContext(origEffectiveContext, newComplexEntity);
					if (e == newComplexEntity) { // not found on 'origEffectiveContext'
						e = lookupAndRelinkEntityOnContext(context, newComplexEntity);
						addToRepositoryIfTheSame(e, newComplexEntity, context);
					}
				}
				newComplexEntity = e;
			}
			else {
				if (newComplexEntity.getContext() == null) {
					newComplexEntity.setContext(context);
				}
			}
		}
		else {
			newComplexEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
		}
		return newComplexEntity;
	}

	/**
	 * Rebuilds complex entity from list by adding entities starting from the end of the list; name is random
	 * @param entities
	 * @param fromPos
	 * @param context
	 * @param effectiveContext
	 * @param interpretationHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLEntity rebuildComplexEntityFromEntitiesReversedStack(VWMLEntity rebuiltEntity,
																		   List<VWMLEntity> entities,
			                                                               int fromPos) throws Exception {
		if (!rebuiltEntity.isMarkedAsComplexEntity()) {
			return rebuiltEntity;
		}
		// removes old linkage
		if (rebuiltEntity.getLink() != null) {
			rebuiltEntity.getLink().getLinkedObjects().clear();
		}
		for(int i = fromPos; i >= 0; i--) {
			rebuiltEntity.getLink().link(entities.get(i));
		}
		return rebuiltEntity;
	}
	
	/**
	 * Activates term on separated interpreter
	 * @param interpreter
	 * @param component
	 * @param term
	 * @param contextPrefix
	 * @param onOperation
	 * @return
	 * @throws Exception
	 */
	public static boolean activateTerm(VWMLInterpreterImpl interpreter, VWMLEntity component, boolean interpretComponentAsArg, VWMLEntity term, String contextPrefix, String onOperation) throws Exception {
		boolean continueCycle = true;
		if (onOperation == null) {
			throw new Exception("The parameter 'onOperation' must be specified on activateTerm");
		}
		VWMLConflictRingExecutionGroup g = null;
		VWMLInterpreterImpl activeInterpreter = interpreter;
		VWMLInterpreterListener listener = new VWMLInterpreterListenerForOperation();
		if (interpreter.getRtNode() != null) {
			g = interpreter.getRtNode().getExecutionGroup();
		}
		if (interpreter.getMasterInterpreter() != null) {
			interpreter = interpreter.getMasterInterpreter();
		}
		// term is interpreted by own interpreter
		VWMLContext forcedContext = VWMLContext.instance((contextPrefix != null) ? "contextPrefix" : onOperation + term.getContext().getContext());
		forcedContext.setContext(term.getContext().getContext());
		VWMLInterpreterImpl i = interpreter.addTermInRunTime(g, activeInterpreter, term, forcedContext, listener, true);
		if (i != null) {
			if (!interpretComponentAsArg) {
				// the synthetic entity '$' will be interpreted as component
				i.setInterpretingEntityForSyntheticEntity(component);
			}
			else {
				// all arguments are passed inside the one complex entity. The interpreted term can reference to arguments by $1, $2...
				// The counting direction is left -> right
				i.setInterpretingEntityForArgEntity(component);
			}
			if (i.getConfig().isStepByStepInterpretation()) {
				interpreter.conditionalLoop(listener);
			}
			continueCycle = !listener.isForcedStop();
			interpreter.releaseTermResourcesAfterInterpretationDone(i.getRtNode(), i, term);
		}
		else {
			continueCycle = false;
			throw new Exception("Couldn't activate interpreter for term '" + term + "'; operation '" + onOperation + "'");
		}
		return continueCycle;
		
	}
	
	/**
	 * Returns entity which is related to argument entity, argument entity has format => ${arg place in complex entity}; used by CallP operation
	 * @param interpreter
	 * @param entity
	 * @return
	 */
	public static VWMLEntity getRelatedEntityByArgument(VWMLInterpreterImpl interpreter, VWMLEntity entity) throws Exception {
		VWMLEntity e = null;
		if (entity.getAsArgPair() != null) {
			if (entity.getAsArgPair().getArgAsRef() == null) {
				if (entity.getAsArgPair().getPlaceNumber() != null &&
					interpreter.getInterpretingEntityForArgEntity() != null &&
					interpreter.getInterpretingEntityForArgEntity().isMarkedAsComplexEntity() &&
					entity.isRecursiveInterpretationOnOriginal()) {
					VWMLComplexEntity args = (VWMLComplexEntity)interpreter.getInterpretingEntityForArgEntity();
					int num = Integer.valueOf(entity.getAsArgPair().getPlaceNumber());
					if (num >= args.getLink().getLinkedObjectsOnThisTime()) {
						throw new Exception("argument's number '" + num + "' exceeds entity's number of arguments; args '" + args.getId() + "'");
					}
					e = (VWMLEntity)args.getLink().getConcreteLinkedEntity(num);
					entity.getAsArgPair().setArgAsRef(e);
				}
			}
			else {
				e = (VWMLEntity)entity.getAsArgPair().getArgAsRef();				
			}
		}
		return e;
	}
	
	
	private static void addToRepository(VWMLContext context, VWMLEntity newComplexEntity) throws Exception {
		VWMLObjectsRepository.instance().remove(newComplexEntity);
		String id = newComplexEntity.buildReadableId();
		newComplexEntity.setId(id);
		if (newComplexEntity.getContext() == null) {
			newComplexEntity.setContext(context);
		}
		VWMLObjectsRepository.instance().addConcrete(newComplexEntity, context);
	}
	
	private static VWMLEntity lookupAndRelinkEntityOnContext(VWMLContext context, VWMLEntity newComplexEntity) throws Exception {
		VWMLContext ctx = context;
		String id = newComplexEntity.buildReadableId();
		VWMLEntity lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctx);
		if (lookedEntity != null) {
			boolean activateUnlink = true;
			if (lookedEntity.isMarkedAsComplexEntity() && newComplexEntity.isMarkedAsComplexEntity()) {
				if (lookedEntity.getLink() != null) {
					lookedEntity.getLink().getLinkedObjects().clear();
				}
				lookedEntity.setLink(newComplexEntity.getLink());
				VWMLLinkIncrementalIterator it = newComplexEntity.getLink().acquireLinkedObjectsIterator();
				if (it != null) {
					for(; it.isCorrect(); it.next()) {
						newComplexEntity.getLink().getConcreteLinkedEntity(it.getIt()).getLink().setParent(lookedEntity);
					}
				}
				activateUnlink = false;
			}
			VWMLObjectsRepository.instance().remove(newComplexEntity);
			if (activateUnlink) {
				newComplexEntity.getLink().unlinkFromAll();
			}
			newComplexEntity.setLink(null);
			newComplexEntity = lookedEntity;
		}
		return newComplexEntity;
	}
	
	/**
	 * Complex entity which is generated from list by adding entities from the end of the list; the name is generated from names of entities
	 * @param entities
	 * @param fromPos
	 * @param context
	 * @param interpretationHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLEntity generateComplexEntityFromEntitiesReversedStackEx(List<VWMLEntity> entities,
																	          int fromPos,
																	          String context,
																	          String effectiveContext,
																	          int interpretationHistorySize,
																	          AbstractVWMLLinkVisitor visitor) throws Exception {

		String cen = ComplexEntityNameBuilder.generateRandomName();
		String id = "";
		VWMLEntity newEntity = null;
		// if effective context (lifeterm's context) isn't parent of entity's context it means that 
		// actual interpretation is being run on context identified by effectiveContext id
		if (!VWMLContext.isContextChildOf(effectiveContext, context)) {
			context = effectiveContext;
		}
		VWMLContext ctx = VWMLContextsRepository.instance().get(context);
		if (ctx == null) {
			throw new Exception("couldn't find context identified by '" + context + "'");
		}
		// complex entity
		if (entities.size() > 0) {
			// acquire new complex entity with random name
			newEntity = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
															cen,
															cen,
															ctx,
															interpretationHistorySize,
															visitor);
			// filling entity by entities from stack; the name consists from entities names 
			ComplexEntityNameBuilder ceb = ComplexEntityNameBuilder.instance();
			ceb.startProgress();
			for(int i = fromPos; i >= 0; i--) {
				newEntity.getLink().link(entities.get(i));
				ceb.addObjectId(entities.get(i).buildReadableId());
			}
			ceb.stopProgress();
			id = ceb.build();
			// sets entity's id to generated id
			newEntity.setId(id);
			newEntity = VWMLObjectsRepository.instance().addConcrete(newEntity, ctx);
		}
		else {
			// builds empty complex entity name (related to set of default entities)
			newEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
		}
		return newEntity;
	}
	
	private static boolean addToRepositoryIfTheSame(VWMLEntity e, VWMLEntity newComplexEntity, VWMLContext context) throws Exception {
		boolean added = false;
		if (e == newComplexEntity) {
			addToRepository(context, newComplexEntity);
			added = true;
		}
		return added;
	}
}
