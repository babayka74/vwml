package com.vw.lang.sink.java.interpreter;

/**
 * Interpreter's configuration; based on properties
 * @author ogibayev
 *
 */
public class VWMLInterpreterConfiguration {
	
	public static final int DEF_EXECUTION_STEP_DELAY = 500; // in ms
	
	private int executionStepDelay = DEF_EXECUTION_STEP_DELAY;
	
	private VWMLInterpreterConfiguration() {
		super();
	}
	
	public static VWMLInterpreterConfiguration instance() {
		return new VWMLInterpreterConfiguration();
	}

	public int getExecutionStepDelay() {
		return executionStepDelay;
	}
	
	public void setExecutionStepDelay(int executionStepDelay) {
		this.executionStepDelay = executionStepDelay;
	}

	@Override
	public String toString() {
		return "VWMLInterpreterConfiguration [executionStepDelay="
				+ executionStepDelay + "]";
	}
}
