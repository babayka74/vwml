package com.vw.lang.processor.model.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import com.vw.common.Debuggable;
import com.vw.lang.grammar.VirtualWorldModelingLanguageLexer;
import com.vw.lang.grammar.VirtualWorldModelingLanguageParser;
import com.vw.lang.processor.model.builder.VWML2TargetSpecificSteps.Step;
import com.vw.lang.processor.model.builder.specific.VWML2JavaSpecificSteps;
import com.vw.lang.processor.model.sink.CompilationSink;
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.InterpretationProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator;


/**
 * 
 * @author ogibayev
 *
 */
public class VWMLModelBuilder extends Debuggable {

	/**
	 * List of available sinks; actually defines supported languages
	 * @author ogibayev
	 *
	 */
	public static enum SINK_TYPE {
		JAVA, CPP, C, OBJECTIVE_C, RAW
	}

	/**
	 * Defines possible VWML building steps
	 * @author ogibayev
	 *
	 */
	public static enum BUILD_STEPS {
		SOURCE, SCAN, POM, COMPILE, TEST, MAIN, ALL
	}

	/**
	 * Defines possible testing modes
	 * @author Oleg
	 *
	 */
	public static enum TEST_MODE {
		NONE("none"),
		STATIC("static"),
		DYNAMIC("dynamic"),
		ALL("all");
		
		private String value;
		
		private TEST_MODE(String value) {
			this.value = value;
		}
		
		 public static TEST_MODE fromValue(String value) {  
			if (value != null) {  
				 for (TEST_MODE tm : values()) {  
					 if (tm.value.equals(value)) {  
						 return tm;  
					 }  
				 }  
			}  
			return getDefault();  
		 }
		 
		 public String toValue() {
			 return value;
		 }
		 
		 public static TEST_MODE getDefault() {
			 return TEST_MODE.STATIC;
		 }
	}

	/**
	 * Defines possible addons
	 * @author Oleg
	 *
	 */
	public static enum ADDONS {
		INTEGRATIONPOM("integration_pom"),
		NONE("none");
		
		private String value;
		
		private ADDONS(String value) {
			this.value = value;
		}
		
		 public static ADDONS fromValue(String value) {  
			if (value != null) {  
				 for (ADDONS tm : values()) {  
					 if (tm.value.equals(value)) {  
						 return tm;  
					 }  
				 }  
			}  
			return getDefault();  
		 }
		 
		 public String toValue() {
			 return value;
		 }
		 
		 public static ADDONS getDefault() {
			 return ADDONS.NONE;
		 }
	}
		
	public static class CodeGeneratorAux {
		private ICodeGenerator generator;
		private VWML2TargetSpecificSteps programSteps;
		
		public CodeGeneratorAux(ICodeGenerator generator, VWML2TargetSpecificSteps programSteps) {
			super();
			this.generator = generator;
			this.programSteps = programSteps;
		}

		public ICodeGenerator getGenerator() {
			return generator;
		}

		public VWML2TargetSpecificSteps getProgramSteps() {
			return programSteps;
		}

		public void setGenerator(ICodeGenerator generator) {
			this.generator = generator;
		}

		public void setProgramSteps(VWML2TargetSpecificSteps programSteps) {
			this.programSteps = programSteps;
		}
	}
	
	/**
	 * Default language is JAVA
	 */
	private SINK_TYPE sinkType = SINK_TYPE.JAVA;
	// Default build steps
	private BUILD_STEPS buildSteps = BUILD_STEPS.SOURCE;
	private String projectPath = null;
	private InterpretationProps interpretationProps = null;
	private StartModuleProps projectProps = null;
	private boolean operatedFromIDE = false;
	private final Logger logger = Logger.getLogger(VWMLModelBuilder.class);
	// association between sink type and code generator
	@SuppressWarnings("serial")
	private Map<SINK_TYPE, CodeGeneratorAux> codeGeneratorsAux = new HashMap<SINK_TYPE, CodeGeneratorAux>() {
		{ put(SINK_TYPE.JAVA,        new CodeGeneratorAux(JavaCodeGenerator.instance(), null));}
		{ put(SINK_TYPE.CPP,         null);                        }
		{ put(SINK_TYPE.C,           null);                        }
		{ put(SINK_TYPE.OBJECTIVE_C, null);                        }
		{ put(SINK_TYPE.RAW,         new CodeGeneratorAux(JavaCodeGenerator.instance(), null));}
	};
	
	/**
	 * VWML's build steps
	 */
	@SuppressWarnings("serial")
	private Map<BUILD_STEPS, Step> buildStepsMap = new HashMap<BUILD_STEPS, Step>() {
		{ 
			put(BUILD_STEPS.SOURCE,  new VWML2JavaSpecificSteps.SourceStep());
			put(BUILD_STEPS.SCAN,    new VWML2JavaSpecificSteps.ScanStep());
			put(BUILD_STEPS.POM,     new VWML2JavaSpecificSteps.PomStep());
			put(BUILD_STEPS.COMPILE, new VWML2JavaSpecificSteps.CompileStep());
			put(BUILD_STEPS.TEST,    new VWML2JavaSpecificSteps.TestStep());
			put(BUILD_STEPS.MAIN,    new VWML2JavaSpecificSteps.MainStep());
		}
	};

	private CompilationSink compilationSink = null;
	// associates modules' names and their info - this information is needed on final steps of source generation
	private List<VWMLModuleInfo> modulesInfo = new ArrayList<VWMLModuleInfo>();
	
	// testing mode property
	public static final String s_TestModeProp = "testMode";
	
	public VWMLModelBuilder() {
	}

	/**
	 * Returns set of VWML processed modules
	 * @return
	 */
	public List<VWMLModuleInfo> getProcessedModules() {
		return modulesInfo;
	}
	
	/**
	 * Associates module's name and module info structure
	 * @param name
	 * @param mi
	 */
	public void addModuleInfo(String name, VWMLModuleInfo mi) {
		mi.setName(name);
		modulesInfo.add(mi);
	}
	
	public InterpretationProps getInterpretationProps() {
		return interpretationProps;
	}

	public void setInterpretationProps(InterpretationProps interpretationProps) {
		this.interpretationProps = interpretationProps;
	}

	public BUILD_STEPS getBuildSteps() {
		return buildSteps;
	}

	public void setBuildSteps(BUILD_STEPS buildSteps) {
		this.buildSteps = buildSteps;
	}

	public StartModuleProps getProjectProps() {
		return projectProps;
	}

	public void setProjectProps(StartModuleProps modProps) {
		this.projectProps = modProps;
	}

	public CompilationSink getCompilationSink() {
		return compilationSink;
	}

	public void setCompilationSink(CompilationSink compilationSink) {
		this.compilationSink = compilationSink;
	}

	public boolean isOperatedFromIDE() {
		return operatedFromIDE;
	}

	public void setOperatedFromIDE(boolean operatedFromIDE) {
		this.operatedFromIDE = operatedFromIDE;
	}

	/**
	 * Changes/adds sink's association
	 * @param sink
	 * @param codeGenerator
	 */
	public void changeSink(SINK_TYPE sink, ICodeGenerator codeGenerator) {
		CodeGeneratorAux caux = codeGeneratorsAux.get(sink);
		if (caux == null) {
			caux = new CodeGeneratorAux(codeGenerator, null);
			codeGeneratorsAux.put(sink, caux);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("The sink '" + sink + "' associated with code generator '" + codeGenerator + "'");
		}
	}
	
	/**
	 * Returns code generator associated with given sink
	 * @param sink
	 * @return
	 */
	public ICodeGenerator getCodeGenerator(SINK_TYPE sink) {
		ICodeGenerator cg = null;
		CodeGeneratorAux caux = codeGeneratorsAux.get(sink);
		if (caux != null) {
			cg = caux.getGenerator();
			
		}
		return cg;
	}

	/**
	 * Normalizes properties by filling non-set values by project properties
	 * @param props
	 * @return
	 */
	public StartModuleProps normalizeProps(StartModuleProps props) {
		return getCodeGenerator(getSinkType()).normalizeProps(props, this.getProjectProps());
	}

	/**
	 * Runs builder's initialization process
	 * @throws Exception
	 */
	public void init() throws Exception {
		codeGeneratorsAux.get(getSinkType()).setProgramSteps(new VWML2JavaSpecificSteps(this));
	}
	
	/**
	 * Explicitly sets sink's type
	 * @return
	 */
	public SINK_TYPE getSinkType() {
		return sinkType;
	}

	public void setSinkType(SINK_TYPE sinkType) {
		this.sinkType = sinkType;
	}
	
	@Override
	public void debugEnter(Object to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debugExit(Object from) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String trace) {
		if (isTraceable()) {
			if (isDebug()) {
				System.out.println(trace);
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug(trace);
				}
			}
		}
	}

	/**
	 * Compiles chunk of VWML into selected language, defined by sink
	 * @param vwmlCode
	 * @throws Exception
	 */
	public void compileInMemoryFile(String vwmlCode) throws Exception {
        VirtualWorldModelingLanguageLexer lex = new VirtualWorldModelingLanguageLexer(new ANTLRStringStream(vwmlCode));
        CommonTokenStream vwmlTokens = new CommonTokenStream(lex);
        VirtualWorldModelingLanguageParser g = new VirtualWorldModelingLanguageParser(vwmlTokens);
        g.setVwmlModelBuilder(this);
        g.setCompilationSink(compilationSink);
        try {
            g.filedef();
        }
        catch (VirtualWorldModelingLanguageParser.VWMLCodeGeneratorRecognitionException e) {
        	com.vw.lang.sink.OperationInfo lastOpInfo = e.getLastOperationInfo();
        	logger.error("couldn't compile; '" + lastOpInfo + "'");
            throw e;
        }
        catch (RecognitionException e) {
        	g.reportError(e);
        	logger.error("couldn't compile; position '" + e.line + ":" + e.charPositionInLine + "'; token '" + ((e.token != null) ? e.token.getText() : "undefined" + "'"));
            throw e;
        }
	}

	/**
	 * Compiles VWML into selected language, defined by sink
	 * @param vwmlFilePath
	 * @throws Exception
	 */
	public void compile(String vwmlFilePath) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("compiling '" +  vwmlFilePath + "'; sink is '" + this.getSinkType() + "'");
		}
		if (projectPath == null) {
			projectPath = new File(vwmlFilePath).getParent();
		}
		else {
			if (!new File(vwmlFilePath).isAbsolute()) {
				vwmlFilePath = projectPath + "/" + vwmlFilePath;
			}
		}
        VirtualWorldModelingLanguageLexer lex = new VirtualWorldModelingLanguageLexer(new ANTLRFileStream(vwmlFilePath, "UTF8"));
        CommonTokenStream vwmlTokens = new CommonTokenStream(lex);
        VirtualWorldModelingLanguageParser g = new VirtualWorldModelingLanguageParser(vwmlTokens);
        g.setVwmlModelBuilder(this);
        g.setCompilationSink(compilationSink);
        try {
            g.filedef();
    		if (logger.isInfoEnabled()) {
    			logger.info("compiled '" +  vwmlFilePath + "'... OK");
    		}
        }
        catch (VirtualWorldModelingLanguageParser.VWMLCodeGeneratorRecognitionException e) {
        	com.vw.lang.sink.OperationInfo lastOpInfo = e.getLastOperationInfo();
        	logger.error("couldn't compile '" + vwmlFilePath + "'; '" + lastOpInfo + "'");
            throw e;
        }
        catch (RecognitionException e) {
        	g.reportError(e);
        	logger.error("couldn't compile '" + vwmlFilePath + "'; position '" + e.line + ":" + e.charPositionInLine + "'; token '" + ((e.token != null) ? e.token.getText() : "undefined" + "'"));
            throw e;
        }
	}
	
	/**
	 * Final step in source generation phase
	 */
	public void finalProcedure(StartModuleProps props) throws Exception {
		CodeGeneratorAux caux = codeGeneratorsAux.get(getSinkType());
		if (caux == null) {
			throw new Exception("invalid sink type '" + getSinkType() + "'");
		}
		if (caux.getProgramSteps() != null) {
			Step step = buildStepsMap.get(this.getBuildSteps());
			if (step == null) {
				throw new Exception("invalid or unsupported step '" + this.getBuildSteps() + "'");
			}
			step.setCompilationSink(compilationSink);
			step.setModelBuilder(this);
			step.step(caux.getProgramSteps(), getCodeGenerator(getSinkType()).getLangAsString(), props);
		}
	}
}
