package com.vw.lang.sink.java;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * Performs clone building process
 * @author Oleg
 *
 */
public class VWMLCloneFactory {

	/**
	 * Clones interpreting entities (aka context)
	 * @param origEntity
	 * @param clonedObjectId
	 * @return
	 * @throws Exception
	 */
	public static VWMLEntity cloneContext(VWMLEntity origEntity, Object clonedObjectId) throws Exception {
		VWMLEntity clonedEntity = null;
		if (origEntity.getInterpreting() != null) {
			VWMLCloneAuxCache auxCache = VWMLCloneAuxCache.instance();
			// clones entity itself and all entities which can be accessed directly
			clonedEntity = origEntity.clone(clonedObjectId, origEntity.getInterpreting().getContext(), auxCache);
			// clones rest contexts and linked entities
			VWMLContextsRepository.clone(clonedObjectId, origEntity.getInterpreting().getContext(), auxCache);
			auxCache.reset();
		}
		return clonedEntity;
	}
}
