package com.vw.lang.sink.java.interpreter;

/**
 * Interpreter's configuration; based on properties
 * @author ogibayev
 *
 */
public class VWMLInterpreterConfiguration {
	
	public static enum INTERPRETER_MT_STRATEGY {
		// only one lifeterm can be interpreted
		SINGLE("single"),
		// term by term interpretation
		SINGLE_SEQUENTIAL("sequential"),
		// reactor pattern is used for scheduling of term's interpretation
		REACTIVE("reactive"),
		// parallel - pool of threads is created
		PARALLEL("parallel");
		
		private String value;
		
		private INTERPRETER_MT_STRATEGY(String value) {
			this.value = value;
		}
		
		public static INTERPRETER_MT_STRATEGY fromValue(String value) {
			if (value != null && value.length() > 0) {
				for(INTERPRETER_MT_STRATEGY v : INTERPRETER_MT_STRATEGY.values()) {
					if (v.getValue().compareToIgnoreCase(value) == 0) {
						return v;
					}
				}
			}
			return defaultValue();
		}
		
		public static INTERPRETER_MT_STRATEGY defaultValue() {
			return SINGLE;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public static final int DEF_EXECUTION_STEP_DELAY = 500; // in ms
	
	private int executionStepDelay = DEF_EXECUTION_STEP_DELAY;
	private INTERPRETER_MT_STRATEGY interpretationMtStrategy;
	
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

	
	public INTERPRETER_MT_STRATEGY getInterpretationMtStrategy() {
		return interpretationMtStrategy;
	}

	public void setInterpretationMtStrategy(String interpretationMtStrategy) {
		this.interpretationMtStrategy = INTERPRETER_MT_STRATEGY.fromValue(interpretationMtStrategy);
	}
	
	@Override
	public String toString() {
		return "VWMLInterpreterConfiguration [executionStepDelay="
				+ executionStepDelay + "]";
	}
}
