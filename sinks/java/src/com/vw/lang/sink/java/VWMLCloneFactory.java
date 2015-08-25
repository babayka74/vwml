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
	 * @param clonedObjectId
	 * @param bornMode
	 * @return
	 * @throws Exception
	 */
	public static VWMLContext cloneContext(VWMLEntity origEntity, Object clonedObjectId, boolean bornMode) throws Exception {
		VWMLContext context = null;
		if (origEntity.getInterpreting() != null) {
			// clones rest contexts and linked entities
			context = VWMLContextsRepository.clone(clonedObjectId, origEntity.getInterpreting().getContext(), bornMode);
		}
		return context;
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
