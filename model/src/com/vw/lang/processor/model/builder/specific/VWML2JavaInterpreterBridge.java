package com.vw.lang.processor.model.builder.specific;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.InterpretationProps;
import com.vw.lang.sink.java.IVWMLInterpreterBroker;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;
import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterBroker;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLPair;
import com.vw.lang.sink.java.module.VWMLModule;

/**
 * Acts as bridge between generated sources and interpreter 
 * @author ogibayev
 *
 */
public class VWML2JavaInterpreterBridge {
	private String debuggerGateName = null;
	private VWMLModelBuilder modelBuilder = null;
	public static String s_className = "VWML2JavaInterpreterBridge";
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: Bridge between generated sources and interpreter \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";
	private static String s_bridgePackageSuffix = "bridge";

	public static String generatePackageName(JavaModuleStartProps projProps) {
		// usually first module defines project's properties
		String bridgePackage = null;
		int prefixRRange = projProps.getModulePackage().lastIndexOf(".");
		if (prefixRRange == -1) {
			bridgePackage = s_bridgePackageSuffix;
		}
		else {
			bridgePackage = projProps.getModulePackage().substring(0, prefixRRange) + "." + s_bridgePackageSuffix;
		}
		return bridgePackage;
	}
	
	public VWMLModelBuilder getModelBuilder() {
		return modelBuilder;
	}

	public void setModelBuilder(VWMLModelBuilder modelBuilder) {
		this.modelBuilder = modelBuilder;
	}

	public void build() throws Exception {
		buildCode();
	}
	
	private void buildCode() throws Exception {
		final int PACKAGE_INDEX = 0x1;
		final int IMPORT_INDEX  = 0x2;
		final int BODY_INDEX    = 0x4;
		JavaModuleStartProps projProps = (JavaModuleStartProps)modelBuilder.getProjectProps();
		
		String[] code = {
				// 1. caption
				prepareCaption(projProps) + "\r\n\r\n",
				// 2. package
				null,
				null,				
				"public class " + s_className + " {\r\n\r\n",
				null,
				"};\r\n"
		};
		
		List<VWMLModuleInfo> vwmlModules = modelBuilder.getProcessedModules();
		if (vwmlModules == null) {
			throw new Exception("there are no any processed modules; bridge can't be created");
		}
		// usually first module defines project's properties
		String bridgePackage = generatePackageName(projProps);
		String fullSourcePath = projProps.getSrcPath() + "/" + bridgePackage.replaceAll("\\.", "/");
		// generates package
		code[PACKAGE_INDEX] = "package " + bridgePackage + ";\r\n\r\n";
		// generates import section
		code[IMPORT_INDEX] = prepareImports(projProps, vwmlModules);
		// generates body section
		code[BODY_INDEX] = prepareBody(vwmlModules, projProps);
		File f = new File(fullSourcePath);
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("couldn't create directory; directory is '" + fullSourcePath + "'");
		}
		FileWriter fw = new FileWriter(fullSourcePath + "/" + s_className + ".java");
		for(String section : code) {
			fw.write(section);
		}
		fw.close();
	}
	
	/**
	 * Returns caption constructed from modules' properties
	 * @param props
	 * @return
	 */
	protected String prepareCaption(StartModuleProps props) throws Exception {
		return String.format(s_caption, ((JavaModuleStartProps)props).getAuthor(), ((JavaModuleStartProps)props).getDate());
	}
	
	private String prepareImports(JavaModuleStartProps projProps, List<VWMLModuleInfo> vwmlModules) {
		String imports = "";
		// prepare imports
		imports += "import " + VWMLModule.class.getName() + ";\r\n";
		imports += "import " + IVWMLInterpreterBroker.class.getName() + ";\r\n";
		imports += "import " + VWMLInterpreterBroker.class.getName() + ";\r\n";
		imports += "import " + VWMLPair.class.getName() + ";\r\n";
		imports += "import " + IVWMLGate.class.getName() + ";\r\n";
		imports += "import " + InterpretationObserver.class.getName() + ";\r\n";
		// looking for debugger's property
		if (projProps.getInterpretationProps().isActivateDebugger()) {
			Properties p = projProps.getInterpretationProps().getDynamicProps();
			if (p != null) {
				Set<String> keys = p.stringPropertyNames();
				for(String k : keys) {
					if (k.equals("interpreter.debugger.fringe")) {
						String debuggerGateClass = p.getProperty(k);
						imports += "import " + debuggerGateClass + ";\r\n";
						int i = debuggerGateClass.lastIndexOf('.');
						if (i != -1) {
							debuggerGateName = debuggerGateClass.substring(i + 1);
						}
						else {
							debuggerGateName = debuggerGateClass;
						}
						break;
					}
				}
			}
		}
		for(VWMLModuleInfo mi : vwmlModules) {
			JavaModuleStartProps props = (JavaModuleStartProps)mi.getProps();
			imports += "import " + props.getModulePackage() + "." + props.getActualModuleName() + ";\r\n";
		}
		imports += "\r\n";
		return imports;
	}
	
	private String prepareBody(List<VWMLModuleInfo> vwmlModules, JavaModuleStartProps projProps) {
		String body = prepareDeclarations(vwmlModules, projProps);
		body += "\tprivate static " + s_className + " s_instance = new " + s_className + "();\r\n\r\n\tprivate " + s_className + "() {\r\n\t}\r\n\r\n";
		body += "\tpublic static " + s_className + " instance() {\r\n\t\ts_instance.init();\r\n\t\treturn s_instance;\r\n\t}\r\n\r\n";
		body += "\tpublic VWMLModule[] getModules() {\r\n\t\treturn modules;\r\n\t}\r\n\r\n";
		body += "\tpublic void setInterpretationObserver(InterpretationObserver interpretationObserver) {\r\n\t\tthis.interpretationObserver = interpretationObserver;\r\n\t}\r\n\r\n";
		body += "\tpublic void startInterpretationProcess() throws Exception {\r\n\t\tif (vwmlInterpreterBroker == null) {\r\n\t\t\tvwmlInterpreterBroker = VWMLInterpreterBroker.instance(modules, propPairs);\r\n\t\t}\r\n\t\tvwmlInterpreterBroker.setDebuggerGate(debuggerGate);\r\n\t\tvwmlInterpreterBroker.setInterpretationObserver(interpretationObserver);\r\n\t\tvwmlInterpreterBroker.start();\r\n\t}\r\n\r\n";
		body += "\tpublic void clearResources() throws Exception {\r\n\t\tvwmlInterpreterBroker.clear();\r\n\t\tvwmlInterpreterBroker = null;\r\n\t}\r\n\r\n";
		body += "\tpublic void buildLinks() throws Exception {\r\n\t\tif (vwmlInterpreterBroker == null) {\r\n\t\t\tvwmlInterpreterBroker = VWMLInterpreterBroker.instance(modules, propPairs);\r\n\t\t}\r\n\t\tvwmlInterpreterBroker.setInterpretationObserver(interpretationObserver);\r\n\t\t;vwmlInterpreterBroker.build();\r\n\t}\r\n\r\n";
		if (debuggerGateName != null) {
			body += "\tprotected void init() {\r\n\t\tdebuggerGate = " + debuggerGateName + ".instance();\r\n\t}\r\n";
		}
		else {
			body += "\tprotected void init() {\r\n\t\t\r\n\t}\r\n";
		}
		return body;
	}
	
	private String prepareDeclarations(List<VWMLModuleInfo> vwmlModules, JavaModuleStartProps projProps) {
		String declarations = prepareModulesDeclaration(vwmlModules) + "\r\n";
		declarations += prepareInterpretersProperties(projProps.getInterpretationProps());
		declarations += "\tprivate IVWMLInterpreterBroker vwmlInterpreterBroker = null;\r\n\r\n";
		declarations += "\tprivate IVWMLGate debuggerGate = null;\r\n\r\n";
		declarations += "\tprivate InterpretationObserver interpretationObserver = null;\r\n\r\n";
		return declarations;
	}
	
	private String prepareModulesDeclaration(List<VWMLModuleInfo> vwmlModules) {
		boolean firstIt = true;
		String modulesDeclaration = "";
		modulesDeclaration += "\tprivate VWMLModule modules[] = {\r\n";
		for(VWMLModuleInfo mi : vwmlModules) {
			if (!firstIt) {
				modulesDeclaration += ",\r\n";
			} 
			firstIt = false;
			JavaModuleStartProps props = (JavaModuleStartProps)mi.getProps();
			modulesDeclaration += "\t\tnew " + props.getActualModuleName() + "()";
		}
		modulesDeclaration += "\r\n\t};\r\n";
		return modulesDeclaration;
	}
	
	private String prepareInterpretersProperties(InterpretationProps props) {
		final String emptyProps = "\tprivate VWMLPair[] propPairs = null;\r\n\r\n";
		if (props == null) {
			return emptyProps;
		}
		Properties p = props.getDynamicProps();
		if (p == null) {
			return emptyProps;
		}
		boolean firstIt = true;
		Set<String> keys = p.stringPropertyNames();
		String sprops = "\tprivate VWMLPair[] propPairs = {\r\n";
		for(String key : keys) {
			if (!firstIt) {
				sprops += ",\r\n";
			}
			sprops += "\t\tnew VWMLPair(\"" + key + "\", \"" + p.getProperty(key) + "\")";
			firstIt = false;
		}
		sprops += "\r\n\t};\r\n\r\n";
		return sprops;
	}
}
 