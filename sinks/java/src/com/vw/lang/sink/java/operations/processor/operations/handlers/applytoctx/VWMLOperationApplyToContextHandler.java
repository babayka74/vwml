package com.vw.lang.sink.java.operations.processor.operations.handlers.applytoctx;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPAPPLYTOCONTEXT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationApplyToContextHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		throw new Exception("Operation 'Context' is not supported now");
	}
	
	public static String buildAbsoluteContext(VWMLEntity e) {
		return e.getContext().getContext() + "." + (e.getReadableId() != null ? e.getReadableId() : e.getNativeId());
	}
}
