package com.vw.lang.sink.java.operations.processor.operations.handlers.interceptor;

import com.vw.lang.sink.java.interceptor.VWMLInterceptor;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterDeferredTask;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

public class VWMLActivateInterceptorDeferredTask extends VWMLInterpreterDeferredTask {

	private VWMLInterpreterImpl interpreter;
	private VWMLInterceptor interceptor;
	
	public VWMLActivateInterceptorDeferredTask(VWMLInterpreterImpl interpreter, VWMLInterceptor interceptor) {
		super();
		this.interpreter = interpreter;
		this.interceptor = interceptor;
	}

	@Override
	public Object execute() throws Exception {
		interceptor.activate(interpreter);
		return null;
	}
}
