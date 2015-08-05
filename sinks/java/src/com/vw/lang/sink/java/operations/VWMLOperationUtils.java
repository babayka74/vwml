package com.vw.lang.sink.java.operations;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLInterceptorsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interceptor.VWMLInterceptor;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterListener;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interceptor.VWMLActivateInterceptorDeferredTask;
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
				boolean returnToPool = false;
				if (origEffectiveContext.equals(context)) {
					// lookups for entity by its id (builds id and searches in repository); 
					// if entity found then it is returned, otherwise 'newComplexEntity' is returned
					e = lookupAndRelinkEntityOnContext(origEffectiveContext, newComplexEntity);
					// if found entity and newComplexEntity are the same then we add it to repository and return null value
					if (!addToRepositoryIfTheSame(e, newComplexEntity, origEffectiveContext)) {
						returnToPool = true;
					}
				}
				else {
					e = lookupAndRelinkEntityOnContext(origEffectiveContext, newComplexEntity);
					if (e == newComplexEntity) { // not found on 'origEffectiveContext'
						e = lookupAndRelinkEntityOnContext(context, newComplexEntity);
						if (!addToRepositoryIfTheSame(e, newComplexEntity, context)) {
							returnToPool = true;
						}
					}
				}
/*				
				if (returnToPool) {
					newComplexEntity.getLink().getLinkedObjects().clear();
					newComplexEntity.getLink().setParent(null);
					VWMLObjectBuilder.returnToPool(newComplexEntity);
				}
*/				
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
	 * Activates term on separated interpreter, but current node is blocked
	 * @param interpreter
	 * @param component
	 * @param term
	 * @param contextPrefix
	 * @param onOperation
	 * @return
	 * @throws Exception
	 */
	public static boolean activateTerm(VWMLInterpreterImpl interpreter, VWMLEntity component, boolean interpretComponentAsArg, VWMLEntity term, String contextPrefix, String onOperation, String forcedContextName) throws Exception {
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
		String ctxName = forcedContextName;
		if (ctxName == null) {
			ctxName = term.getContext().getContext();
		}
		forcedContext.setContext(ctxName);
		VWMLInterpreterImpl i = interpreter.addTermInRunTime(g, activeInterpreter, term, forcedContext, listener, true);
		if (i != null) {
			i.setPushed(true);
			if (!interpretComponentAsArg) {
				i.setInterpretingEntityForArgEntity(activeInterpreter.getInterpretingEntityForArgEntity());
				// the synthetic entity '$' will be interpreted as component
				i.setInterpretingEntityForSyntheticEntity(component);
			}
			else {
				i.setInterpretingEntityForSyntheticEntity(activeInterpreter.getInterpretingEntityForSyntheticEntity());
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
	 * Activates cloned term
	 * @param ring
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 * @throws Exception
	 */
	public static void activateClonedTerm(VWMLConflictRing ring, VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		// lookup for original
		VWMLEntity p = clonedSourceLft;
		while(p.getClonedFrom() != null) {
			p = p.getClonedFrom();
		}
		VWMLConflictRingExecutionGroup group = ring.findGroupByEntityContext(p.getContext().getContext(), true);
		if (group == null) {
			throw new Exception("couldn't ring find group by context '" + clonedSourceLft.getContext().getContext() + "'");
		}
		VWMLConflictRingNode ringGroupMasterNode = findRingMasterNodeByGroup(group);
		if (ringGroupMasterNode == null) {
			throw new Exception("couldn't find ring node by context '" + clonedSourceLft.getContext().getContext() + "'");
		}
		VWMLInterpreterImpl clonedInterpreter = interpreter.clone();
		if (interpreter.getMasterInterpreter() != ring.getRingInitialInterpreter()) {
			clonedInterpreter.move(ring.getRingInitialInterpreter());
		}
		List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
		tl.add(clonedSourceLft);
		VWMLConflictRingNode clonedNode = ringGroupMasterNode.clone(clonedInterpreter);
		// interpreter was instantiated as result of cloning entity => cloned.getClonedFrom()
		// needed when resources should be released
		clonedInterpreter.setClonedFromEntity(cloned);
		clonedInterpreter.setCloned(true);
		clonedInterpreter.setTerms(tl);
		clonedNode.setExecutionGroup(group);
		group.add(clonedNode);
		clonedInterpreter.start();
	}

	/**
	 * Activates given term; current node is not blocked; general case for activateClonedTerm method; takes 'real parallelism' into consideration
	 * @param interpreter
	 * @param context - context
	 * @param term - (source | life | any); in case 'any' => term.getInterpreting;
	 * @throws Exception
	 */
	public static void activateTerm(VWMLInterpreterImpl interpreter, VWMLEntity context, VWMLEntity term) throws Exception {
		VWMLConflictRing operationalRing = null;
		if (interpreter.isPushed()) {
			if (interpreter.getRtNode() == null) {
				throw new Exception("interpreter '" + interpreter + "' doesn't have associated RT node; context '" + context.getContext() + "'");
			}
			interpreter = interpreter.getRtNode().firstPushedInterpreter();
			if (interpreter == null) {
				throw new Exception("Didn't find first pushed interpreter; context '" + context.getContext() + "'");
			}
		}
		term.setActivated(true);
		operationalRing = VWMLResourceHostManagerFactory.hostManagerInstance().findMostFreeRing(interpreter.getConfig());
		if (operationalRing == null) {
			// all rings are overloaded; new one should be created
			VWMLInterpreterImpl ii = interpreter;
			while(ii.getMasterInterpreter() != null) {
				ii = ii.getMasterInterpreter();
			}
			List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
			tl.add(term);
			ii.newActivity(tl, context);
		}
		else {
			if (operationalRing != interpreter.getRing()) {
				// sends message to add node and to activate it on another ring
				System.out.println("Expand ring '" + operationalRing + "'");
				operationalRing.askActivateNode(interpreter, context, term);
			}
			else {
				VWMLOperationUtils.activateClonedTerm(operationalRing, interpreter, context, term);
			}
		}
	}
	
	/**
	 * Lookups for ring's master node based on given group
	 * @param group
	 * @return
	 */
	public static VWMLConflictRingNode findRingMasterNodeByGroup(VWMLConflictRingExecutionGroup group) {
		VWMLConflictRingNode ringGroupMasterNode = group.findMasterNode();
		if (ringGroupMasterNode == null) {
			ringGroupMasterNode = group.findMasterInAnyCase();
		}
		return ringGroupMasterNode;
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
			VWMLComplexEntity args = (VWMLComplexEntity)interpreter.getInterpretingEntityForArgEntity();
			int num = Integer.valueOf(entity.getAsArgPair().getPlaceNumber());
			if (num >= args.getLink().getLinkedObjectsOnThisTime()) {
				throw new Exception("argument's number '" + num + "' exceeds entity's number of arguments; args '" + args.getId() + "'");
			}
			e = (VWMLEntity)args.getLink().getConcreteLinkedEntity(num);
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
				VWMLLinkIncrementalIterator it = newComplexEntity.getLink().acquireLinkedObjectsIterator();
				if (it != null) {
					for(; it.isCorrect(); it.next()) {
						VWMLEntity ei = (VWMLEntity)newComplexEntity.getLink().getConcreteLinkedEntity(it.getIt());
						if (ei != null) {
							lookedEntity.getLink().link(ei);
						}
						else {
							System.out.println("Strange ! newComplexEntity has 'null' element; newComplexEntity '" + id + "' before relink");
						}
					}
				}
				activateUnlink = false;
			}
			VWMLObjectsRepository.instance().remove(newComplexEntity);
			if (activateUnlink) {
				newComplexEntity.getLink().unlinkFromAll();
			}
			//newComplexEntity.setLink(null);
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


	/**
	 * Activates interceptor on deferred task
	 * @param interpreter
	 * @param intercept
	 * @param trigger
	 */
	public static void activateInterceptor(VWMLInterpreterImpl interpreter, VWMLEntity intercept, VWMLEntity trigger) {
		VWMLInterceptor interceptor = VWMLInterceptorsRepository.instance().getInterceptor(intercept, trigger);
		if (interceptor != null) {
			VWMLActivateInterceptorDeferredTask task = new VWMLActivateInterceptorDeferredTask(interpreter, interceptor);
			interpreter.setDeferredTask(task);
		}
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
