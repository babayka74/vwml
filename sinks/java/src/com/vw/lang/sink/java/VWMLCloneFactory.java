package com.vw.lang.sink.java;

import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Performs clone building process
 * @author Oleg
 *
 */
public class VWMLCloneFactory {

	/**
	 * Clones interpreting entities (aka context)
	 * @param origEntity
	 * @param protoClonedEntity
	 * @param clonedObjectId
	 * @param bornMode
	 * @return
	 * @throws Exception
	 */
	public static VWMLEntity cloneContext(VWMLEntity origEntity, VWMLEntity protoClonedEntity, Object clonedObjectId, boolean bornMode) throws Exception {
		VWMLEntity clonedEntity = null;
		if (origEntity.getInterpreting() != null) {
			VWMLCloneAuxCache auxCache = VWMLCloneAuxCache.instance();
			if (!bornMode) {
				// clones entity itself and all entities which can be accessed directly
				clonedEntity = origEntity.clone(protoClonedEntity, clonedObjectId, origEntity.getInterpreting().getContext(), auxCache);
			}
			else {
				// clones entity itself and all entities which can be accessed directly
				clonedEntity = origEntity.born(protoClonedEntity, clonedObjectId, origEntity.getInterpreting().getContext(), auxCache);
			}
			// clones rest contexts and linked entities
			VWMLContextsRepository.clone(clonedObjectId, origEntity.getInterpreting().getContext(), auxCache, bornMode);
			auxCache.reset();
		}
		return clonedEntity;
	}

	/**
	 * Clones interpreting entities in lazy manner
	 * @param origEntity
	 * @param clonedObjectId
	 * @return
	 * @throws Exception
	 */
	public static VWMLEntity cloneContextLazy(VWMLEntity origEntity, VWMLEntity clonedObject) throws Exception {
		VWMLEntity clonedEntity = null;
		if (origEntity.getInterpreting() != null) {
			ContextIdPair origEntityCtxPair = VWMLContextsRepository.instance().wellFormedContext(origEntity.getContext().getContext());
			if (origEntityCtxPair == null) {
				throw new Exception("unknown context's parts '" + origEntity.getContext().getContext() + "'; see VWML code");
			}
			VWMLContext ctxForClonedEntity = origEntity.getInterpreting().getContext();
			if (origEntityCtxPair.isCloneOfOriginal()) {
				ContextIdPair origEntityInterpCtxPair = VWMLContextsRepository.instance().wellFormedContext(origEntity.getInterpreting().getContext().getContext());
				ctxForClonedEntity = VWMLContextsRepository.instance().updateContextAndCreateInCaseOfClone(origEntityCtxPair, origEntityInterpCtxPair);
			}
			VWMLContextsRepository.cloneLazy(clonedObject.getId(), ctxForClonedEntity);
			clonedEntity = (VWMLEntity)VWMLObjectsRepository.acquire(clonedObject.deduceEntityType(),
																	clonedObject.getId(),
																	((VWMLContext)ctxForClonedEntity.getLink().getParent()).getContext(),
																	origEntity.getInterpretationHistorySize(),
																	VWMLObjectsRepository.asOriginal,
																	origEntity.getLink().getLinkOperationVisitor());
			clonedEntity.setInterpreting(origEntity.getInterpreting());
			VWMLLinkIncrementalIterator it = clonedObject.getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					clonedEntity.getLink().link(clonedObject.getLink().getConcreteLinkedEntity(it.getIt()));
				}
			}
			clonedEntity.buildReadableId();
		}
		return clonedEntity;
	}
	
	/**
	 * Releases all resources and entities which were allocated during the cloneContext operation
	 * @param clonedFrom
	 * @param clonedContext
	 * @throws Exception
	 */
	public static void releaseClonedContext(VWMLEntity clonedFrom, VWMLContext clonedContext) throws Exception {
		VWMLContextsRepository.releaseCloned(clonedContext);
		VWMLObjectsRepository.instance().remove(clonedFrom);
	}
}
