package com.vw.lang.processor.model.builder.specific.main;

import java.io.File;
import java.io.FileWriter;

import com.vw.lang.processor.model.builder.specific.VWML2JavaInterpreterBridge;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

/**
 * Generates main entry point for the project; usually runs after all tests have been passed with successful result
 * @author ogibayev
 *
 */
public class VWML2JavaModulesMainStateBuilder {
	private static String s_mainEntryClassName = "VWML2JavaMainEntry";
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: Main entry point \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";

	/**
	 * Actually builds entry point
	 * @throws Exception
	 */
	public void build(JavaModuleStartProps projProps) throws Exception {
		modulesMainCases(projProps);
	}

	
	/**
	 * Prepares module's sources
	 * @throws Exception
	 */
	protected void modulesMainCases(JavaModuleStartProps projProps) throws Exception {
		final int IMPORT_INDEX = 0x2;
		final int BODY_INDEX   = 0x4;
		String[] code = {
				// 1. caption
				prepareCaption(projProps) + "\r\n\r\n",
				// 2. package
				"package main.java;\r\n\r\n",
				null,				
				"public class " + s_mainEntryClassName + " {\r\n",
				null,
				"};\r\n"
			};
		// generates import section
		code[IMPORT_INDEX] = prepareImports(projProps);
		code[BODY_INDEX] = prepareBody();
		// actually generates test code
		String testSourcePath = StartModuleProps.getSourcesPath() + "/main/java";
		File f = new File(testSourcePath);
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("couldn't create directory for test cases; directory is '" + testSourcePath + "'");
		}
		FileWriter fw = new FileWriter(testSourcePath + "/" + s_mainEntryClassName + ".java");
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
		String imports = "";
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
	
	private String prepareBody() {
		String body = "\r\n\r\n\tpublic static void main(String[] args) throws Exception {\r\n";
		body += "\t\ttry {\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().startInterpretationProcess();\r\n";
		body += "\t\t\t" + VWML2JavaInterpreterBridge.s_className + ".instance().clearResources();\r\n";
		body += "\t\t} catch(Exception e) {\r\n";
		body += "\t\t\tSystem.out.println(\"Exception caught during interpretation; exception '\" + e.getMessage() + \"'\");\r\n";
		body += "\t\t\tSystem.console().readLine();\r\n";
		body += "\t\t\tthrow e;\r\n";
		body += "\t\t}\r\n";
		body += "\t}\r\n\r\n";
		return body;
	}
}
