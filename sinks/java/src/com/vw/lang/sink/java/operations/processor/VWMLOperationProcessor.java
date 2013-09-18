package com.vw.lang.sink.java.operations.processor;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;
import com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr.VWMLOperationCreateExprHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.exe.VWMLOperationExeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interpret.VWMLOperationInterpretHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.random.VWMLOperationRandomHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.unknown.VWMLOperationUnknownOperationHandler;

/**
 * Defines logic of executing operations
 * @author ogibayev
 *
 */
public class VWMLOperationProcessor {
	
	/**
	 * maps VWML operation to its handler
	 */
	private static Map<VWMLOperation, VWMLOperationHandler> s_processorMap = new HashMap<VWMLOperation, VWMLOperationHandler>() {
		{
			put(new VWMLOperation(VWMLOperationsCode.OPINTERPRET), new VWMLOperationInterpretHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCREATEEXPR), new VWMLOperationCreateExprHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPRANDOM), new VWMLOperationRandomHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPEXECUTE), new VWMLOperationExeHandler());
		}
	};
	// called when unknown/unsupported operation is going to be executed
	private VWMLOperationUnknownOperationHandler unknownOperationHandler = new VWMLOperationUnknownOperationHandler();

	private VWMLOperationProcessor() {
		
	}
	
	/**
	 * Constructs and initializes new operation processor
	 * @return
	 */
	public static VWMLOperationProcessor instance() {
		return new VWMLOperationProcessor();
	}
	
	/**
	 * Processes incoming command
	 * @param interpreter
	 * @param linkage
	 * @param stack
	 * @param operation
	 * @throws Exception
	 */
	public VWMLEntity processOperation(VWMLEntity entity, VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLOperationHandler handler = s_processorMap.get(operation);
		if (handler == null) {
			handler = unknownOperationHandler;
		}
		return handler.handle(entity, interpreter, linkage, stack, operation);
	}
}
