package com.vw.lang.sink.java.interpreter.parallel;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Uses pool of threads in order to schedule terms interpretation
 * @author ogibayev
 *
 */
public class VWMLParallelTermInterpreter extends VWMLIterpreterImpl {

	private VWMLParallelTermInterpreter() {
	}
	
	private VWMLParallelTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
	}

	private VWMLParallelTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		setTerms(terms);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLParallelTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLParallelTermInterpreter(linkage, terms);
	}

	public static VWMLParallelTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		return new VWMLParallelTermInterpreter(linkage, terms, context);
	}
	
	
	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public VWMLEntity decomposeAndInterpret(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return entity;
	}

}
