package com.vw.lang.sink.java.operations.processor.operations.handlers.foreach;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterListener;

/**
 * Called by interpreter when iteration step, of operation ForEach, has finished
 * @author Oleg
 *
 */
public class VWMLInterpreterListenerForOperationForEach extends VWMLInterpreterListener {

	private int status = VWMLInterpreterImpl.continueProcessingOfCurrentEntity;
	
	@Override
	public void hanldeStatus(VWMLInterpreterImpl interpreter) {
		status = interpreter.getStatus();
	}
	
	public int getInterpreterStatus() {
		return isForcedStop() ? VWMLInterpreterImpl.stopped : status;
	}
}
