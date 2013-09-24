package com.vw.lang.sink.java.operations.processor.operations.handlers.unknown;

import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Default handler which is called in case if unknown operation is encountered
 * @author ogibayev
 *
 */
public class VWMLOperationUnknownOperationHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		// TODO Auto-generated method stub
	}

}
