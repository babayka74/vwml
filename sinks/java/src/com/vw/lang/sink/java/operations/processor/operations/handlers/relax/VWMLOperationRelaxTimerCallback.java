package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

/**
 * Called when timer is expired or interrupted
 * @author Oleg
 *
 */
public class VWMLOperationRelaxTimerCallback extends VWMLInterpreterTimerCallback {
	
	private VWMLEntity runOnRegularCompletition = null;
	private VWMLEntity runOnTerminatedCompletition = null;
	
	public VWMLOperationRelaxTimerCallback(VWMLEntity runOnRegularCompletition, VWMLEntity runOnTerminatedCompletition) {
		this.runOnRegularCompletition = runOnRegularCompletition;
		this.runOnTerminatedCompletition = runOnTerminatedCompletition;
	}
	
	@Override
	public void timerCbk(VWMLInterpreterTimer timer) {
		cbkHandler(timer, runOnRegularCompletition, true);
	}

	@Override
	public void interruptedTimerCbk(VWMLInterpreterTimer timer) {
		cbkHandler(timer, runOnTerminatedCompletition, true);
	}

	private void cbkHandler(VWMLInterpreterTimer timer, VWMLEntity completitionTerm, boolean delayExecution) {
		VWMLInterpreterImpl interpreter = (VWMLInterpreterImpl)timer.getUserData();
		if (interpreter != null && interpreter.getObserver() != null) {
			interpreter.getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), null);
		}
		if (completitionTerm != null) {
			if (!delayExecution) {
				try {
					VWMLOperationUtils.activateTerm(interpreter, null, false, completitionTerm, "relaxCbk_", "Relax", null);
				} catch (Exception e) { // swallow it for now
				}
			}
			else {
				VWMLInterpreterInterruptTimerDeferredTask task = new VWMLInterpreterInterruptTimerDeferredTask();
				task.setActiveInterpreter(interpreter);
				task.setArgs(null);
				task.setTerm(completitionTerm);
				task.setContextPrefix("relaxCbk_");
				task.setOperation("Relax");
				interpreter.setDeferredTask(task);
			}
		}
	}
}
