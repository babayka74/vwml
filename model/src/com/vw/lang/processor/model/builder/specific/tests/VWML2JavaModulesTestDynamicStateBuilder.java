package com.vw.lang.processor.model.builder.specific.tests;

import java.util.Set;

/**
 * Actually runs dynamic tests (interpreter runs actual tasks using test environment)
 * @author ogibayev
 *
 */
public class VWML2JavaModulesTestDynamicStateBuilder {
	private static String s_testClassName = "VWML2JavaTestDynamicState";
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: Unit tests covereage \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";

	/**
	 * Actually builds tests
	 * @throws Exception
	 */
	public void build() throws Exception {
		// iterates through all processed modules and builds unit-test which covers module's 'build' feature -> 
		// generating initial state of described world
		modulesTestCases();
	}

	/**
	 * Prepares module's test cases which are run during unit-test phase
	 * @throws Exception
	 */
	protected void modulesTestCases() throws Exception {
	}
	
	/**
	 * Returns test's caption constructed from modules' properties
	 * @param props
	 * @return
	 */
	protected String prepareCaption() throws Exception {
		return String.format(s_caption, "VWML dynamic state test builder", "");
	}
	
	private String prepareImports(Set<String> vwmlModules) {
		String imports = "import junit.framework.Assert;\r\nimport org.junit.Test;\r\n\r\n";
		return null;
	}	
	
	private String prepareTestBody(Set<String> vwmlModules) {
		boolean firstIteration = true;
		String body = "\r\n\r\n\t@Test\r\n\tpublic void testGeneratedCode() {\r\n";
		return body;
	}
}
