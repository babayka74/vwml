package com.vw.lang.sink.java.operations.processor;

import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Binds operation execution handler and operation
 * @author ogibayev
 *
 */
public abstract class VWMLOperationHandler {
	
	/**
	 * Abstract operation handler
	 * @param linkage
	 * @param stack
	 * @param operation
	 * @throws Exception
	 */
	public abstract void handle(VWMLLinkage linkage, Stack stack, VWMLOperation operation) throws Exception;
}
