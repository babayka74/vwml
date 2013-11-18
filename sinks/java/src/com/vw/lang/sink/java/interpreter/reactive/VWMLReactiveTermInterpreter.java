package com.vw.lang.sink.java.interpreter.reactive;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.seq.VWMLSequentialTermInterpreter;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Interprets multiple terms in one thread; simulating parallel interpreting (using reactor pattern)
 * @author ogibayev
 *
 */
public class VWMLReactiveTermInterpreter extends VWMLIterpreterImpl {

	private VWMLConflictRing ring = VWMLConflictRing.instance();
	
	private VWMLReactiveTermInterpreter() {
	}
	
	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
	}

	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		setTerms(terms);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLReactiveTermInterpreter(linkage, terms);
	}

	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		return new VWMLReactiveTermInterpreter(linkage, terms, context);
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
		getConfig().setStepByStepInterpretation(true);
		// iterates through the conflict ring and associates ring node with reactive sequential interpreter
		for(VWMLEntity e : getTerms()) {
			// looking for ring node by source lifeterm's context 
			VWMLConflictRingNode n = ring.findNodeByEntityContext(e.getContext().getContext());
			if (n == null) {
				throw new Exception("couldn't find ring node by context '" + e.getContext().getContext() + "'");
			}
			// instantiates new sequential interpreter
			VWMLSequentialTermInterpreter impl = VWMLSequentialTermInterpreter.instance(getLinkage(), e);
			impl.setConfig(getConfig());
			// associating interpreter and ring node
			n.setInterpreter(impl);
			// 'lazy' start (initializes interpreter's internal structures only; the execution phase is managed by ring)
			impl.start();
		}
		// starts reactive interpretation activity
		while(true) {
			VWMLConflictRingNode node = ring.next();
			if (node == null) {
				break;
			}
			node.operate();
		}
	}

	@Override
	public VWMLIterpreterImpl clone() {
		VWMLIterpreterImpl cloned = instance(super.getLinkage(), null, null);
		cloned.setConfig(this.getConfig());
		return cloned;
	}
}
