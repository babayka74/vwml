package com.vw.lang.sink.java;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;

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
	 * Releases all resources and entities which were allocated during the cloneContext operation
	 * @param clonedFrom
	 * @param clonedContext
	 * @throws Exception
	 */
	public static void releaseClonedContext(VWMLEntity clonedFrom, VWMLContext clonedContext) throws Exception {
		VWMLContextsRepository.releaseCloned(clonedContext);
		VWMLContextsRepository.removeAssociatedEntityFromContext(clonedFrom, clonedFrom.getContext());
	}
}
