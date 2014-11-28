package com.vw.lang.sink.java.operations.processor.operations.handlers.recall;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;

public class VWMLOperationRecallTimerCallback extends VWMLInterpreterTimerCallback {
		
	private VWMLEntity runOnRegularCompletition = null;
	private VWMLEntity runOnTerminatedCompletition = null;
	
	public VWMLOperationRecallTimerCallback() {
		
	}
	
	public VWMLOperationRecallTimerCallback(VWMLEntity runOnRegularCompletition, VWMLEntity runOnTerminatedCompletition) {
		this.runOnRegularCompletition = runOnRegularCompletition;
		this.runOnTerminatedCompletition = runOnTerminatedCompletition;
	}

	@Override
	public void unblockActivity(VWMLInterpreterImpl interpreter) {
		if (interpreter.getObserver().getBlockedByGate() != null) {
			interpreter.getObserver().getBlockedByGate().unblockActivity();
		}
	}

	@Override
	public String getTaskName() {
		return "Recall";
	}

	@Override
	public void askDeferredProcessingForTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) {
		if (getRing() != null) {
			try {
				getRing().askProcessingForRecallTimerCbk(task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		defCallback(timer, completitionTerm, delayExecution);
	}
}
