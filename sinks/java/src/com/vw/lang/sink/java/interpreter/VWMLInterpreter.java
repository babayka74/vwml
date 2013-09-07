package com.vw.lang.sink.java.interpreter;

import java.util.List;

import com.vw.lang.sink.java.IVWMLInterpreter;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.module.VWMLModule;

/**
 * Defines logic of interpreter for one VWML's lifterm 
 * @author ogibayev
 *
 */
public class VWMLInterpreter implements IVWMLInterpreter {
	
	private VWMLModule[] modules = null;
	private boolean modulesBuilt = false;
	
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
	public static IVWMLInterpreter instance(VWMLModule[] modules) {
		VWMLInterpreter in = new VWMLInterpreter();
		in.setModules(modules);
		return in;
	}

	public VWMLModule[] getModules() {
		return modules;
	}

	public void setModules(VWMLModule[] modules) {
		this.modules = modules;
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
}
