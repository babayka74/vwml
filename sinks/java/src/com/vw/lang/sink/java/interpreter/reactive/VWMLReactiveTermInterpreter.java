package com.vw.lang.sink.java.interpreter.reactive;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Interprets multiple terms in one thread; simulating parallel interpreting (using reactor pattern)
 * @author ogibayev
 *
 */
public class VWMLReactiveTermInterpreter extends VWMLIterpreterImpl {

	
	private VWMLReactiveTermInterpreter() {
	}
	
	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
	}

	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLStack stack) {
		setTerms(terms);
		setLinkage(linkage);
		setStack(stack);
	}
	
	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLReactiveTermInterpreter(linkage, terms);
	}

	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLStack stack) {
		return new VWMLReactiveTermInterpreter(linkage, terms, stack);
	}
	
	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void startOnExistedStack(VWMLLinkage linkage, VWMLStack stack,
			VWMLEntity entity) throws Exception {
		// TODO Auto-generated method stub

	}
}
