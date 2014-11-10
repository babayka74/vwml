package com.vw.lang.sink.java.interceptor;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

/**
 * VWML world modification interceptor
 * @author Oleg
 *
 */
public class VWMLInterceptor {
	private VWMLEntity intercept = null;
	private VWMLEntity trigger = null;
	private VWMLEntity term = null;
	
	
	public VWMLInterceptor() {
		super();
	}

	protected VWMLInterceptor(VWMLEntity intercept, VWMLEntity trigger, VWMLEntity term) {
		super();
		this.intercept = intercept;
		this.trigger = trigger;
		this.term = term;
	}

	public static VWMLInterceptor instance(VWMLEntity intercept, VWMLEntity trigger, VWMLEntity term) {
		return new VWMLInterceptor(intercept, trigger, term);
	}

	public static VWMLInterceptor instance(VWMLEntity intercept, VWMLEntity trigger) {
		return new VWMLInterceptor(intercept, trigger, null);
	}
	
	public VWMLEntity getIntercept() {
		return intercept;
	}
	
	public void setIntercept(VWMLEntity intercept) {
		this.intercept = intercept;
	}
	
	public VWMLEntity getTrigger() {
		return trigger;
	}
	
	public void setTrigger(VWMLEntity trigger) {
		this.trigger = trigger;
	}
	
	public VWMLEntity getTerm() {
		return term;
	}
	
	public void setTerm(VWMLEntity term) {
		this.term = term;
	}
	
	public String key() {
		return VWMLContext.constructContextNameInRunTime(null, intercept) + "::" + getTrigger().buildReadableId();
	}
	
	public void activate(VWMLInterpreterImpl interpreter) throws Exception {
		VWMLOperationUtils.activateTerm(interpreter, null, false, getTerm(), "Interceptor_", "Interceptor", null);
	}
}
