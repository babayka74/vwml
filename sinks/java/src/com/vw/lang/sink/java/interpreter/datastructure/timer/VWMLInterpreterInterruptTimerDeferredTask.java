package com.vw.lang.sink.java.interpreter.datastructure.timer;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterDeferredTask;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

public class VWMLInterpreterInterruptTimerDeferredTask extends VWMLInterpreterDeferredTask {
	private VWMLInterpreterImpl activeInterpreter;
	private VWMLEntity term;
	private VWMLEntity args;
	private String contextPrefix;
	private String operation;
	
	public VWMLInterpreterImpl getActiveInterpreter() {
		return activeInterpreter;
	}
	
	public void setActiveInterpreter(VWMLInterpreterImpl activeInterpreter) {
		this.activeInterpreter = activeInterpreter;
	}
	
	public VWMLEntity getTerm() {
		return term;
	}
	
	public void setTerm(VWMLEntity term) {
		this.term = term;
	}
	
	public VWMLEntity getArgs() {
		return args;
	}
	
	public void setArgs(VWMLEntity args) {
		this.args = args;
	}
	
	public String getContextPrefix() {
		return contextPrefix;
	}
	
	public void setContextPrefix(String contextPrefix) {
		this.contextPrefix = contextPrefix;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public Object execute() throws Exception {
		VWMLOperationUtils.activateTerm(getActiveInterpreter(), getArgs(), false, getTerm(), getContextPrefix(), getOperation(), null);
		return null;
	}
}
