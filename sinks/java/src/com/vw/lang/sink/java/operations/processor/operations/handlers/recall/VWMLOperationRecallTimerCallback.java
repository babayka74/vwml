package com.vw.lang.sink.java.operations.processor.operations.handlers.recall;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

public class VWMLOperationRecallTimerCallback extends VWMLInterpreterTimerCallback {
		
	private VWMLEntity runOnRegularCompletition = null;
	private VWMLEntity runOnTerminatedCompletition = null;
	
	public VWMLOperationRecallTimerCallback(VWMLEntity runOnRegularCompletition, VWMLEntity runOnTerminatedCompletition) {
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
		if (interpreter.getObserver().getBlockedByGate() != null) {
			interpreter.getObserver().getBlockedByGate().unblockActivity();
		}
		if (completitionTerm != null) {
			if (!delayExecution) {
				try {
					VWMLOperationUtils.activateTerm(interpreter, getArgComponent(), false, completitionTerm, "relaxCbk_", "Relax", null);
				} catch (Exception e) { // swallow it for now
				}
			}
			else {
				VWMLInterpreterInterruptTimerDeferredTask task = new VWMLInterpreterInterruptTimerDeferredTask();
				task.setActiveInterpreter(interpreter);
				task.setArgs(getArgComponent());
				task.setTerm(completitionTerm);
				task.setContextPrefix("recallCbk_");
				task.setOperation("Recall");
				interpreter.setDeferredTask(task);
			}
		}
	}
}
