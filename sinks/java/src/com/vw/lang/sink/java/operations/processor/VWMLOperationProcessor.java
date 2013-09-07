package com.vw.lang.sink.java.operations.processor;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Defines logic of executing operations
 * @author ogibayev
 *
 */
public class VWMLOperationProcessor {
	
	private static Map<VWMLOperation, VWMLOperationHandler> s_processorMap = new HashMap<VWMLOperation, VWMLOperationHandler>() {
		{
			
		}
	};

	private VWMLOperationProcessor() {
		
	}
	
	/**
	 * Cnstructs and initializes new operation processor
	 * @return
	 */
	public static VWMLOperationProcessor instance() {
		return new VWMLOperationProcessor();
	}
}
