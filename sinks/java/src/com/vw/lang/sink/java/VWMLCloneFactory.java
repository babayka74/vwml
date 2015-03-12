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
			VWMLContext ctx = VWMLContextsRepository.cloneLazy(clonedObject.getNativeId(), ctxForClonedEntity);
			clonedEntity = (VWMLEntity)VWMLObjectsRepository.acquire(clonedObject.deduceEntityType(),
																	clonedObject.getId(),
																	((VWMLContext)ctxForClonedEntity.getLink().getParent()).getContext(),
																	origEntity.getInterpretationHistorySize(),
																	VWMLObjectsRepository.notAsOriginal,
																	origEntity.getLink().getLinkOperationVisitor());
			clonedEntity.setNativeId(clonedObject.getNativeId());
			ContextIdPair clonedPair =  VWMLContextsRepository.instance().wellFormedContext(ctx.getContext());
			clonedEntity.setInterpreting((VWMLEntity)VWMLObjectsRepository.getAndCreateInCaseOfClone(clonedPair, origEntity.getInterpreting()));
			clonedEntity.setClonedFrom(origEntity);
			VWMLLinkIncrementalIterator it = clonedObject.getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					clonedEntity.getLink().link(clonedObject.getLink().getConcreteLinkedEntity(it.getIt()));
				}
			}
			VWMLObjectsRepository.instance().lateBinding(clonedEntity, (String)clonedObject.getNativeId());
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
