package com.vw.lang.processor.model.builder.specific.tests;

import java.io.File;
import java.io.FileWriter;

import com.vw.lang.processor.model.builder.specific.VWML2JavaInterpreterBridge;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

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
	public void build(JavaModuleStartProps projProps) throws Exception {
		// iterates through all processed modules and builds unit-test which covers module's 'build' feature -> 
		// generating initial state of described world
		modulesTestCases(projProps);
	}

	/**
	 * Prepares module's test cases which are run during unit-test phase
	 * @throws Exception
	 */
	protected void modulesTestCases(JavaModuleStartProps projProps) throws Exception {
		final int IMPORT_INDEX = 0x2;
		final int BODY_INDEX   = 0x4;
		String[] code = {
				// 1. caption
				prepareCaption(projProps) + "\r\n\r\n",
				// 2. package
				"package test.java;\r\n\r\n",
				null,				
				"public class " + s_testClassName + " {\r\n",
				null,
				"};\r\n"
			};
		// generates import section
		code[IMPORT_INDEX] = prepareImports(projProps);
		code[BODY_INDEX] = prepareTestBody();
		// actually generates test code
		String testSourcePath = StartModuleProps.getSourcesPath() + "/test/java";
		File f = new File(testSourcePath);
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("couldn't create directory for test cases; directory is '" + testSourcePath + "'");
		}
		FileWriter fw = new FileWriter(testSourcePath + "/" + s_testClassName + ".java");
		for(String section : code) {
			fw.write(section);
		}
		fw.close();
	}
	
	/**
	 * Returns test's caption constructed from modules' properties
	 * @param props
	 * @return
	 */
	protected String prepareCaption(JavaModuleStartProps projProps) throws Exception {
		return String.format(s_caption, projProps.getAuthor(), projProps.getDate());
	}
	
	private String prepareImports(JavaModuleStartProps projProps) {
		String imports = "import junit.framework.Assert;\r\nimport org.junit.Test;\r\n";
		String bridgePackage = VWML2JavaInterpreterBridge.generatePackageName(projProps);
		String[] importClasses = {
									"import " + bridgePackage + "." + VWML2JavaInterpreterBridge.s_className + ";"
				 				 };
		for(String importClass : importClasses) {
			imports += importClass + "\r\n";
		}
		imports += "\r\n";
		return imports;
	}	
	
	private String prepareTestBody() {
		String body = "\r\n\t@Test\r\n\tpublic void testGeneratedCode() throws Exception {\r\n";
		body += "\t\ttry {\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().startInterpretationProcess();\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().clearResources();\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().startInterpretationProcess();\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().clearResources();\r\n";
		body += "\t\t} catch(Exception e) {\r\n";
		body += "\t\t\tSystem.out.println(\"Exception caught during interpretation; exception '\" + e.getMessage() + \"'\");\r\n";
		body += "\t\t\tSystem.console().readLine();\r\n";
		body += "\t\t}\r\n";
		body += "\t}\r\n\r\n";
		return body;
	}
}
