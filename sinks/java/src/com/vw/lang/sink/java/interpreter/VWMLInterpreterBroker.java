package com.vw.lang.sink.java.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;
import com.vw.lang.sink.java.IVWMLInterpreterBroker;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLPair;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLPairLookUp;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.parallel.VWMLParallelTermInterpreter;
import com.vw.lang.sink.java.interpreter.reactive.VWMLReactiveTermInterpreter;
import com.vw.lang.sink.java.interpreter.seq.VWMLSequentialTermInterpreter;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.module.VWMLModule;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Defines interpretater's broker; creates new interpreter according to properties and starts interpretation process
 * @author ogibayev
 *
 */
public class VWMLInterpreterBroker implements IVWMLInterpreterBroker {
	
	private VWMLModule[] modules = null;
	private VWMLPair[] propPairs = null;
	private boolean modulesBuilt = false;
	private InterpretationObserver interpretationObserver = null;
	private VWMLInterpreterConfiguration config = VWMLInterpreterConfiguration.instance();
	
	/**
	 * Hides inetrpreter's construction
	 */
	private VWMLInterpreterBroker() {
		
	}
	
	public InterpretationObserver getInterpretationObserver() {
		return interpretationObserver;
	}

	public void setInterpretationObserver(InterpretationObserver interpretationObserver) {
		this.interpretationObserver = interpretationObserver;
	}

	/**
	 * Instantiates and initializes interpreter
	 * @param modules
	 * @return
	 */
	public static IVWMLInterpreterBroker instance(VWMLModule[] modules, VWMLPair[] propPairs) {
		VWMLInterpreterBroker in = new VWMLInterpreterBroker();
		in.setModules(modules);
		in.setPropPairs(propPairs);
		in.prepareConfiguration();
		return in;
	}

	public VWMLModule[] getModules() {
		return modules;
	}

	public void setModules(VWMLModule[] modules) {
		this.modules = modules;
	}
	
	public VWMLPair[] getPropPairs() {
		return propPairs;
	}

	public void setPropPairs(VWMLPair[] propPairs) {
		this.propPairs = propPairs;
	}

	/**
	 * Builds modules' initial state
	 * @throws Exception
	 */
	public void build() throws Exception {
		if (modules == null) {
			throw new Exception("modules were not set; check flow");
		}
		VWMLObjectsRepository.instance().setUnderConstruction(true);
		for(VWMLModule module : modules) {
			if (getInterpretationObserver() != null) {
				module.setInterpretationObserver(getInterpretationObserver());
			}
			module.prepare();
		}
		for(VWMLModule module : modules) {
			module.build();
		}
		for(VWMLModule module : modules) {
			module.linkage();
		}
		Map<VWMLObject, VWMLObject> nr = VWMLObjectsRepository.instance().resolve();
		if (getInterpretationObserver() != null) {
			if (nr.size() != 0) {
				for(VWMLObject f : nr.keySet()) {
					getInterpretationObserver().unresolvedAmbiguous((VWMLEntity)f, (VWMLEntity)nr.get(f));
				}
			}
			else {
				getInterpretationObserver().resolvedAmbiguous();
			}
		}
		nr.clear();
		VWMLObjectsRepository.instance().setUnderConstruction(false);
		VWMLObjectsRepository.instance().rebuildEntitiesPrimaryIds();
		if (getInterpretationObserver() != null) {
			getInterpretationObserver().reset();
		}
		modulesBuilt = true;
	}

	public void setDebuggerGate(IVWMLGate debuggerGate) {
		config.setDebuggerGate(debuggerGate);
	}
	
	@Override
	public void start() throws Exception {
		if (!modulesBuilt) {
			build();
		}
		List<VWMLEntity> slftTerms = VWMLLinkage.getSourceLifeTerms();
		List<VWMLEntity> lftTerms = VWMLLinkage.getLifeTerms();
		List<VWMLEntity> terms = new ArrayList<VWMLEntity>();
		for(VWMLEntity e : slftTerms) {
			terms.add(e);
		}
		for(VWMLEntity e : lftTerms) {
			if (!slftTerms.contains(e)) {
				terms.add(e);
			}
		}
		VWMLInterpreterImpl impl = getConcreteInterpreterAccordingToConfiguration(getMainLinkage(), terms);
		if (impl == null) {
			throw new Exception("there were not found any interpreter; check interpreter's property file");
		}
		impl.start();
	}

	@Override
	public void clear() throws Exception {
		VWMLConflictRing.instance().clear();
		VWMLLinkage.resetAllLifeTerms();
		modulesBuilt = false;
	}
	
	protected void prepareConfiguration() {
		VWMLPair p = null;
		if (getPropPairs() != null) {
			p = VWMLPairLookUp.lookByName(propPairs, "interpreter.execution.step.delay");
			if (p != null) {
				config.setExecutionStepDelay(Integer.parseInt(p.getValue()));
			}
			p = VWMLPairLookUp.lookByName(propPairs, "interpreter.execution.strategy");
			if (p != null) {
				config.setInterpretationMtStrategy(p.getValue());
			}
			p = VWMLPairLookUp.lookByName(propPairs, "interpreter.ring.visitor");
			if (p != null) {
				VWMLConflictRingVisitor v = (VWMLConflictRingVisitor)GeneralUtils.instantiateClass(p.getValue());
				config.setRingVisitor(v);
			}
			p = VWMLPairLookUp.lookByName(propPairs, "interpreter.ring.nodes");
			if (p != null) {
				try {
					config.setNodesPerRing(Integer.valueOf(p.getValue()).intValue());
				}
				catch(Exception e) {
					config.setNodesPerRing(VWMLInterpreterConfiguration.DEF_NODES_PER_RING);
				}
			}
			configureResourceManagmentStrategy();
		}
	}

	protected VWMLInterpreterImpl getConcreteInterpreterAccordingToConfiguration(VWMLLinkage linkage, List<VWMLEntity> terms) {
		VWMLInterpreterImpl impl = null;
		if (config.getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.SINGLE) {
			impl = VWMLSequentialTermInterpreter.instance(linkage, terms.get(0));
			config.setStepByStepInterpretation(false);
		}
		else
		if (config.getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.SINGLE_SEQUENTIAL) {
			impl = VWMLSequentialTermInterpreter.instance(linkage, terms.get(0));
			config.setStepByStepInterpretation(true);
		}
		else
		if (config.getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.REACTIVE) {
			impl = VWMLReactiveTermInterpreter.instance(linkage, terms);
			config.setStepByStepInterpretation(true);
		}
		else
		if (config.getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.PARALLEL) {
			impl = VWMLParallelTermInterpreter.instance(linkage, terms);
			config.setStepByStepInterpretation(true);
		}
		if (impl != null) {
			impl.setConfig(config);
		}
		return impl;
	}
	
	protected void configureResourceManagmentStrategy() {
		if (config.getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.PARALLEL) {
			config.setResourceStrategy(VWMLInterpreterConfiguration.RESOURCE_STRATEGY.MT);
		}
		else {
			config.setResourceStrategy(VWMLInterpreterConfiguration.RESOURCE_STRATEGY.ST);
		}
		VWMLResourceHostManagerFactory.setResourceStrategy(config.getResourceStrategy());
	}
	
	protected VWMLLinkage getMainLinkage() {
		return modules[0].getLinkage();
	}
}
