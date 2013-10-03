package com.vw.lang.sink.java.interpreter.seq;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Sequential interpreter; the terms are interpreted sequentially in one thread
 * @author ogibayev
 *
 */
public class VWMLSequentialTermInterpreter extends VWMLIterpreterImpl {

	
	private VWMLSequentialTermInterpreter() {
	}
	
	private VWMLSequentialTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
	}

	private VWMLSequentialTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		setTerms(terms);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLSequentialTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLSequentialTermInterpreter(linkage, terms);
	}

	public static VWMLSequentialTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		return new VWMLSequentialTermInterpreter(linkage, terms, context);
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
