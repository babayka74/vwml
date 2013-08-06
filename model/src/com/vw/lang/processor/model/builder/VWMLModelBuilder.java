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
import com.vw.lang.sink.ICodeGenerator;
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
	 * Default language is JAVA
	 */
	private SINK_TYPE sinkType = SINK_TYPE.JAVA;
	
	// builder is implemented as singleton
	private static volatile VWMLModelBuilder s_builder = null;
	private final Logger logger = Logger.getLogger(VWMLModelBuilder.class);
	// association between sink type and code generator
	private static Map<SINK_TYPE, ICodeGenerator> s_codeGenerators = new HashMap<SINK_TYPE, ICodeGenerator>() {
		{ put(SINK_TYPE.JAVA,        JavaCodeGenerator.instance());}
		{ put(SINK_TYPE.CPP,         null);                        }
		{ put(SINK_TYPE.C,           null);                        }
		{ put(SINK_TYPE.OBJECTIVE_C, null);                        }
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
	
	/**
	 * Changes/adds sink's association
	 * @param sink
	 * @param codeGenerator
	 */
	public void changeSink(SINK_TYPE sink, ICodeGenerator codeGenerator) {
		s_codeGenerators.put(sink, codeGenerator);
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
		return s_codeGenerators.get(sink);
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
}
