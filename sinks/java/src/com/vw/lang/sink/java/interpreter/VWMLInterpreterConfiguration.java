package com.vw.lang.sink.java.interpreter;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.conflictring.visitor.VWMLConflictRingVisitor;

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
	
	public static enum RESOURCE_STRATEGY {
		ST,
		MT
	}
	
	public static final int DEF_EXECUTION_STEP_DELAY = 500; // in ms
	public static final int DEF_NODES_PER_RING = 10;
	
	private int executionStepDelay = DEF_EXECUTION_STEP_DELAY;
	private INTERPRETER_MT_STRATEGY interpretationMtStrategy;
	private RESOURCE_STRATEGY resourceStrategy = RESOURCE_STRATEGY.ST;
	private IVWMLGate debuggerGate = null;
	private boolean isStepByStepInterpretation = false;
	private VWMLConflictRingVisitor ringVisitor = null;
	private int nodesPerRing = DEF_NODES_PER_RING;
	
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
	
	public IVWMLGate getDebuggerGate() {
		return debuggerGate;
	}

	public void setDebuggerGate(IVWMLGate debuggerGate) {
		this.debuggerGate = debuggerGate;
	}

	public boolean isStepByStepInterpretation() {
		return isStepByStepInterpretation;
	}

	public void setStepByStepInterpretation(boolean isStepByStepInterpretation) {
		this.isStepByStepInterpretation = isStepByStepInterpretation;
	}

	public VWMLConflictRingVisitor getRingVisitor() {
		return ringVisitor;
	}

	public void setRingVisitor(VWMLConflictRingVisitor ringVisitor) {
		this.ringVisitor = ringVisitor;
	}

	public RESOURCE_STRATEGY getResourceStrategy() {
		return resourceStrategy;
	}

	public void setResourceStrategy(RESOURCE_STRATEGY resourceStrategy) {
		this.resourceStrategy = resourceStrategy;
	}

	public int getNodesPerRing() {
		return nodesPerRing;
	}

	public void setNodesPerRing(int nodesPerRing) {
		this.nodesPerRing = nodesPerRing;
	}

	@Override
	public String toString() {
		return "VWMLInterpreterConfiguration [executionStepDelay="
				+ executionStepDelay + ", interpretationMtStrategy="
				+ interpretationMtStrategy + ", resourceStrategy="
				+ resourceStrategy + ", debuggerGate=" + debuggerGate
				+ ", isStepByStepInterpretation=" + isStepByStepInterpretation
				+ ", ringVisitor=" + ringVisitor + ", nodesPerRing="
				+ nodesPerRing + "]";
	}
}
