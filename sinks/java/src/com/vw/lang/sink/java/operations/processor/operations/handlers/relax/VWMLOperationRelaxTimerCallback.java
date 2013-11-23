package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;

/**
 * Called when timer is expired
 * @author Oleg
 *
 */
public class VWMLOperationRelaxTimerCallback extends VWMLInterpreterTimerCallback {
	
	@Override
	public void timerCbk(VWMLInterpreterTimer timer) {
		VWMLInterpreterImpl interpreter = (VWMLInterpreterImpl)timer.getUserData();
		if (interpreter != null && interpreter.getObserver() != null) {
			interpreter.getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), null);
		}
	}
}
