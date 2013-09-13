package com.vw.lang.processor.model.builder.specific;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;

import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;
import com.vw.lang.sink.java.module.VWMLModule;

/**
 * Constructs java unit-test cases for created modules
 * @author ogibayev
 *
 */
public class VWML2JavaModulesTestBuilder {

	private static String s_testClassName = "VWML2JavaTestProject";
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
		final int IMPORT_INDEX = 0x2;
		final int BODY_INDEX   = 0x4;
		String[] code = {
				// 1. caption
				prepareCaption() + "\r\n\r\n",
				// 2. package
				"package test.java;\r\n\r\n",
				null,				
				"public class " + s_testClassName + " {\r\n",
				null,
				"};\r\n"
			};
		Set<String> vwmlModules = VWMLModelBuilder.getProcessedModules();
		if (vwmlModules == null) {
			throw new Exception("there are no any processed modules; test cases can't be created");
		}
		// generates import section
		code[IMPORT_INDEX] = prepareImports(vwmlModules);
		// generates body section
		code[BODY_INDEX] = prepareTestBody(vwmlModules);
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
	protected String prepareCaption() throws Exception {
		return String.format(s_caption, "VWML test builder", "");
	}
	
	private String prepareImports(Set<String> vwmlModules) {
		String imports = "import junit.framework.Assert;\r\nimport org.junit.Test;\r\n\r\n";
		// prepare imports
		imports += "import " + VWMLModule.class.getName() + ";\r\n\r\n";		
		for(String name : vwmlModules) {
			VWMLModuleInfo mi = VWMLModelBuilder.getModuleInfo(name);
			JavaModuleStartProps props = (JavaModuleStartProps)mi.getProps();
			imports += "import " + props.getModulePackage() + "." + props.getActualModuleName() + ";\r\n";
		}
		imports += "\r\n";
		return imports;
	}
	
	private String prepareTestBody(Set<String> vwmlModules) {
		boolean firstIteration = true;
		String body = "\r\n\r\n\t@Test\r\n\tpublic void testGeneratedCode() {\r\n";
		body += "\t\tVWMLModule modules[] = { ";
		for(String name : vwmlModules) {
			if (!firstIteration) {
				body += ", ";
			} 
			else {
				firstIteration = false;
			}
			VWMLModuleInfo mi = VWMLModelBuilder.getModuleInfo(name);
			JavaModuleStartProps props = (JavaModuleStartProps)mi.getProps();
			body += "new " + props.getActualModuleName() + "()";
		}
		body += " };\r\n\r\n";
		body += "\t\ttry {\r\n";
		body += "\t\t\tfor(VWMLModule module : modules) {\r\n\t\t\t\tmodule.build();\r\n\t\t\t}\r\n";
		body += "\t\t}\r\n\t\tcatch(Exception e) {\r\n\t\t\tSystem.out.println(e);\r\n\t\t\tAssert.assertFalse(true);\r\n\t\t}\r\n";
		body += "\t}\r\n";
		return body;
	}
}