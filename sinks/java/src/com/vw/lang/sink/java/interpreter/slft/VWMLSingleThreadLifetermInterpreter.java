package com.vw.lang.sink.java.interpreter.slft;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * Interprets single thread life term only
 * @author ogibayev
 *
 */
public class VWMLSingleThreadLifetermInterpreter {
	
	// lifeterm to be interpreted
	private VWMLEntity lifeTerm = null; 
	
	private VWMLSingleThreadLifetermInterpreter() {
		
	}
	
	public static VWMLSingleThreadLifetermInterpreter instance() {
		return new VWMLSingleThreadLifetermInterpreter();
	}

	public VWMLEntity getLifeTerm() {
		return lifeTerm;
	}

	public void setLifeTerm(VWMLEntity lifeTerm) {
		this.lifeTerm = lifeTerm;
	}
}
