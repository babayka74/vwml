package com.vw.lang.sink.java.operations.processor.operations.handlers.born;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.operations.processor.operations.handlers.clone.VWMLOperationCloneHandler;

/**
 * Handler of 'OPBORN' operation
 * @author ogibayev
 *
 */
public class VWMLOperationBornHandler extends VWMLOperationCloneHandler {

	@Override
	public VWMLEntity clone(VWMLEntity origEntity, VWMLEntity clonedObject) throws Exception {
		VWMLContext newContext = VWMLCloneFactory.cloneContext(origEntity, clonedObject.getId(), true);
		return postClone(newContext, origEntity, clonedObject);	}
	
}
