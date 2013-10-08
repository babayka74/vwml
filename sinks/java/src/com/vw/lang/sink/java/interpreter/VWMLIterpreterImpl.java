package com.vw.lang.sink.java.interpreter;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.processor.VWMLOperationProcessor;

/**
 * Internal interpreter's functionality
 * @author ogibayev
 *
 */
public abstract class VWMLIterpreterImpl {

	// list of terms to be interpreted
	private List<VWMLEntity> terms;
	// linkage (usually linkage module of the main module)
	private VWMLLinkage linkage;
	// interpreter's configuration
	private VWMLInterpreterConfiguration config = null;
	// internal worker thread
	private VWMLContext context = VWMLContext.instance();
	// operating processor
	private VWMLOperationProcessor processor = VWMLOperationProcessor.instance();
	
	public List<VWMLEntity> getTerms() {
		return terms;
	}

	public void setTerms(List<VWMLEntity> terms) {
		this.terms = terms;
	}

	public VWMLLinkage getLinkage() {
		return linkage;
	}

	public void setLinkage(VWMLLinkage linkage) {
		this.linkage = linkage;
	}

	public VWMLInterpreterConfiguration getConfig() {
		return config;
	}

	public void setConfig(VWMLInterpreterConfiguration config) {
		this.config = config;
	}

	public VWMLContext getContext() {
		return context;
	}

	public VWMLOperationProcessor getProcessor() {
		return processor;
	}

	/**
	 * Starts interpretation logic
	 * @throws Exception
	 */
	public abstract void start() throws Exception;

	/**
	 * Starts interpretation on existed context by decomposing complex entity on simple; uses recursion
	 * @param context
	 * @throws Exception
	 */
	public abstract VWMLEntity decomposeAndInterpret(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception;

	/**
	 * Clones current interpreter
	 */
	public abstract VWMLIterpreterImpl clone();
	
	/**
	 * Starts term's interpretation process
	 * @param linkage
	 * @param context
	 * @param le
	 * @throws Exception
	 */
	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		throw new Exception("not implemented");
	}

	protected VWMLEntity termInterpretation(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		throw new Exception("not implemented");
	}
	
	protected void setContext(VWMLContext context) {
		this.context = context;
	}
}