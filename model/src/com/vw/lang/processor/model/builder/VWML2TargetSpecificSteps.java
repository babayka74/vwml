package com.vw.lang.processor.model.builder;

import com.vw.lang.sink.ICodeGenerator.StartModuleProps;

/**
 * Abstract class which declares functionality which should be implemented by specific builder
 * @author ogibayev
 *
 */
public abstract class VWML2TargetSpecificSteps {
	/**
	 * Integrates sink's sources with generated sources (VWML -> targetLanguage)
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public abstract void setupSinkSources(String codeGeneratorName, StartModuleProps props) throws Exception;
}
