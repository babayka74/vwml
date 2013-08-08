package com.vw.lang.processor.model.builder;

import com.vw.lang.sink.ICodeGenerator.StartModuleProps;

/**
 * Abstract class which declares functionality which should be implemented by specific builder
 * @author ogibayev
 *
 */
public abstract class VWML2TargetSpecificSteps {
	
	public static interface IStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception;
	}
	
}
