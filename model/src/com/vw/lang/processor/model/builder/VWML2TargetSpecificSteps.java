package com.vw.lang.processor.model.builder;

import com.vw.lang.sink.ICodeGenerator.StartModuleProps;

/**
 * Abstract class which declares functionality which should be implemented by specific builder
 * @author ogibayev
 *
 */
public abstract class VWML2TargetSpecificSteps {
	
	public static abstract class Step {
		public abstract void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception;
		public abstract void correctPoperties(StartModuleProps props);
	}
	
}
