package com.vw.lang.sink.java.interpreter.parallel;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Uses pool of threads in order to schedule terms interpretation
 * @author ogibayev
 *
 */
public class VWMLParallelTermInterpreter extends VWMLInterpreterImpl {

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
		if (getConfig() == null) {
			// default configuration is used
			setConfig(VWMLInterpreterConfiguration.instance());
		}
		if (getTerms() == null  || getTerms().size() == 0) {
			throw new Exception("term should be set before method is called");
		}
		VWMLConflictRing.instance().setRingVisitor(getConfig().getRingVisitor());
		getConfig().setStepByStepInterpretation(true);
	}

	@Override
	public VWMLInterpreterImpl clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
