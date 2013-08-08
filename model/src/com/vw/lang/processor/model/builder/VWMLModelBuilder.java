package com.vw.lang.processor.model.builder;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import com.vw.common.Debuggable;
import com.vw.lang.grammar.VirtualWorldModelingLanguageLexer;
import com.vw.lang.grammar.VirtualWorldModelingLanguageParser;
import com.vw.lang.processor.model.builder.VWML2TargetSpecificSteps.IStep;
import com.vw.lang.processor.model.builder.specific.VWML2JavaSpecificSteps;
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
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
		JAVA, CPP, C, OBJECTIVE_C
	}

	/**
	 * Defines possible VWML building steps
	 * @author ogibayev
	 *
	 */
	public static enum BUILD_STEPS {
		SOURCE, POM, COMPILE, TEST, ALL
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
	
	// builder is implemented as singleton
	private static volatile VWMLModelBuilder s_builder = null;
	private final Logger logger = Logger.getLogger(VWMLModelBuilder.class);
	// association between sink type and code generator
	private static Map<SINK_TYPE, CodeGeneratorAux> s_codeGeneratorsAux = new HashMap<SINK_TYPE, CodeGeneratorAux>() {
		{ put(SINK_TYPE.JAVA,        new CodeGeneratorAux(JavaCodeGenerator.instance(), new VWML2JavaSpecificSteps()));}
		{ put(SINK_TYPE.CPP,         null);                        }
		{ put(SINK_TYPE.C,           null);                        }
		{ put(SINK_TYPE.OBJECTIVE_C, null);                        }
	};
	
	/**
	 * VWML's build steps
	 */
	private static Map<BUILD_STEPS, IStep> s_buildSteps = new HashMap<BUILD_STEPS, IStep>() {
		{ 
			put(BUILD_STEPS.SOURCE,  new VWML2JavaSpecificSteps.SourceStep());
			put(BUILD_STEPS.POM,     new VWML2JavaSpecificSteps.PomStep());
			put(BUILD_STEPS.COMPILE, new VWML2JavaSpecificSteps.CompileStep());
			put(BUILD_STEPS.TEST,    new VWML2JavaSpecificSteps.TestStep());
		}
	};
	
	private VWMLModelBuilder() {
		
	}
	
	/**
	 * Creates and initializes builder
	 * @return
	 * @throws Exception
	 */
	public static VWMLModelBuilder instance() {
		if (s_builder != null) {
			return s_builder;
		}
		synchronized(VWMLModelBuilder.class) {
			if (s_builder != null) {
				return s_builder;
			}
			VWMLModelBuilder builder = new VWMLModelBuilder();
			try {
				builder.init();
				s_builder = builder;
			} catch (Exception e) {
				builder.trace("exception caught '" + e + "'");
			}
		}
		return s_builder;
	}
	
	public BUILD_STEPS getBuildSteps() {
		return buildSteps;
	}

	public void setBuildSteps(BUILD_STEPS buildSteps) {
		this.buildSteps = buildSteps;
	}

	/**
	 * Changes/adds sink's association
	 * @param sink
	 * @param codeGenerator
	 */
	public void changeSink(SINK_TYPE sink, ICodeGenerator codeGenerator) {
		CodeGeneratorAux caux = s_codeGeneratorsAux.get(sink);
		if (caux == null) {
			caux = new CodeGeneratorAux(codeGenerator, null);
			s_codeGeneratorsAux.put(sink, caux);
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
		CodeGeneratorAux caux = s_codeGeneratorsAux.get(sink);
		if (caux != null) {
			cg = caux.getGenerator();
		}
		return cg;
	}
	
	/**
	 * Runs builder's initialization process
	 * @throws Exception
	 */
	public void init() throws Exception {
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
	 * Compiles VWML into selected language, defined by sink
	 * @param vwmlFilePath
	 * @throws Exception
	 */
	public void compile(String vwmlFilePath) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("compiling '" +  vwmlFilePath + "'; sink is '" + this.getSinkType() + "'");
		}
        VirtualWorldModelingLanguageLexer lex = new VirtualWorldModelingLanguageLexer(new ANTLRFileStream(vwmlFilePath, "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        VirtualWorldModelingLanguageParser g = new VirtualWorldModelingLanguageParser(tokens);
        try {
            g.filedef();
    		if (logger.isInfoEnabled()) {
    			logger.info("compiled '" +  vwmlFilePath + "'... OK");
    		}
        } catch (RecognitionException e) {
        	logger.error("couldn't compile '" + vwmlFilePath + "'; error is '" + e.getMessage() + "'; position '" + e.line + ":" + e.charPositionInLine + "'; token '" + ((e.token != null) ? e.token.getText() : "undefined" + "'"));
            throw e;
        }
	}
	
	/**
	 * Final step in source generation phase; called by parser when all files have been compiled
	 */
	public void finalProcedure(StartModuleProps props) throws Exception {
		CodeGeneratorAux caux = s_codeGeneratorsAux.get(getSinkType());
		if (caux == null) {
			throw new Exception("invalid sink type '" + getSinkType() + "'");
		}
		if (caux.getProgramSteps() != null) {
			IStep step = s_buildSteps.get(this.getBuildSteps());
			if (step == null) {
				throw new Exception("invalid or unsupported step '" + this.getBuildSteps() + "'");
			}
			step.step(caux.getProgramSteps(), getCodeGenerator(getSinkType()).getLangAsString(), props);
		}
	}
}
