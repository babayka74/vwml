package com.vw.lang.sink.java.interpreter;

import java.util.List;

import com.vw.lang.sink.java.IVWMLInterpreter;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLPair;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLPairLookUp;
import com.vw.lang.sink.java.module.VWMLModule;

/**
 * Defines logic of interpreter for one VWML's lifterm 
 * @author ogibayev
 *
 */
public class VWMLInterpreter implements IVWMLInterpreter {
	
	private VWMLModule[] modules = null;
	private VWMLPair[] propPairs = null;
	private boolean modulesBuilt = false;
	private VWMLInterpreterConfiguration config = VWMLInterpreterConfiguration.instance();
	
	/**
	 * Hides inetrpreter's construction
	 */
	private VWMLInterpreter() {
		
	}
	
	/**
	 * Instantiates and initializes interpreter
	 * @param modules
	 * @return
	 */
	public static IVWMLInterpreter instance(VWMLModule[] modules, VWMLPair[] propPairs) {
		VWMLInterpreter in = new VWMLInterpreter();
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
		for(VWMLModule module : modules) {
			module.build();
		}
		modulesBuilt = true;
	}
	
	@Override
	public void start() throws Exception {
		if (!modulesBuilt) {
			build();
		}
	}
	
	protected List<VWMLEntity> getModuleLifeTerm(VWMLModule module) {
		return module.getLinkage().getModLifeTerms();
	}
	
	protected void prepareConfiguration() {
		VWMLPair p = null;
		if (getPropPairs() != null) {
			p = VWMLPairLookUp.lookByName(propPairs, "interpreter.execution.step.delay");
			if (p != null) {
				config.setExecutionStepDelay(Integer.parseInt(p.getValue()));
			}
		}
	}
}
