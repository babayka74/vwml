package com.vw.lang.sink.java.interpreter;

import java.util.List;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.processor.VWMLOperationProcessor;

/**
 * Abstract interpreter's functionality
 * @author ogibayev
 *
 */
public abstract class VWMLInterpreterImpl extends VWMLObject {

	public static final int continueProcessingOfCurrentEntity = 0;
	public static final int nextEntityToProcess = 1;
	public static final int stopProcessing = 2;
	public static final int finishedEntityProcessing = 3;
	
	// list of terms to be interpreted
	private List<VWMLEntity> terms;
	// linkage (usually linkage module of the main module)
	private VWMLLinkage linkage;
	// interpreter's configuration
	private VWMLInterpreterConfiguration config = null;
	// internal worker thread
	private VWMLContext context = VWMLContext.instance();
	// stack of child interpreters; the interpreter is interpreted as child when it is instantiated during
	// 'activate context' operation
	private VWMLStack childInterpreters = VWMLStack.instance();
	// operating processor
	private VWMLOperationProcessor processor = VWMLOperationProcessor.instance();
	// observer
	private VWMLInterpreterObserver observer = new VWMLInterpreterObserver();
	// reactive timer manager
	private VWMLInterpreterTimerManager timerManager = VWMLInterpreterTimerManager.instance();	

	/**
	 * Starts interpretation logic
	 * @throws Exception
	 */
	public abstract void start() throws Exception;

	/**
	 * Clones current interpreter
	 */
	public abstract VWMLInterpreterImpl clone();
	
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

	public VWMLInterpreterObserver getObserver() {
		return observer;
	}

	public void setObserver(VWMLInterpreterObserver observer) {
		this.observer = observer;
		// delegates it to processor
		processor.setObserver(observer);
	}

	public VWMLInterpreterTimerManager getTimerManager() {
		return timerManager;
	}

	public void setTimerManager(VWMLInterpreterTimerManager timerManager) {
		this.timerManager = timerManager;
	}

	public void pushInterpreterToChildStack(VWMLInterpreterImpl interpreter) {
		childInterpreters.push(interpreter);
	}
	
	public VWMLInterpreterImpl peekInterpreterFromChildStack() {
		return (VWMLInterpreterImpl)childInterpreters.peek();
	}
	
	public void popInterpreterFromChildStack() {
		childInterpreters.pop();
	}
	
	/**
	 * Step-by-step interpretation
	 * @return 'false' in case if interpretation finished, otherwise 'true' is returned
	 * @throws Exception
	 */
	public boolean step() throws Exception {
		throw new Exception("not implemented");
	}
	
	protected void setContext(VWMLContext context) {
		this.context = context;
	}
	
	/**
	 * Returns last interpreter's status
	 * @return
	 */
	public int getStatus() {
		return stopProcessing;
	}
}
