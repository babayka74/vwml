package com.vw.lang.sink.java.interpreter;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
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
	private VWMLStack stack = VWMLStack.instance();
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

	public VWMLStack getStack() {
		return stack;
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
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	public abstract VWMLEntity startOnExistedStack(VWMLLinkage linkage, VWMLStack stack, VWMLEntity entity) throws Exception;

	/**
	 * Starts term's interpretation process
	 * @param linkage
	 * @param stack
	 * @param le
	 * @throws Exception
	 */
	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		throw new Exception("not implemented");
	}

	protected void termInterpretation(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		throw new Exception("not implemented");
	}
	
	protected void setStack(VWMLStack stack) {
		this.stack = stack;
	}
}
