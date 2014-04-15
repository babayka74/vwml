package com.vw.lang.sink.java.operations.processor.operations.handlers.born;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.operations.processor.operations.handlers.clone.VWMLOperationCloneHandler;

/**
 * Handler of 'OPBORN' operation
 * @author ogibayev
 *
 */
public class VWMLOperationBornHandler extends VWMLOperationCloneHandler {

	@Override
	public VWMLEntity clone(VWMLEntity origEntity, VWMLEntity clonedObject) throws Exception {
		return VWMLCloneFactory.cloneContext(origEntity, clonedObject, clonedObject.getId(), true);
	}
	
}
