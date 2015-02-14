package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterInterruptTimerDeferredTask;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;

/**
 * Called when timer is expired or interrupted
 * @author Oleg
 *
 */
public class VWMLOperationRelaxTimerCallback extends VWMLInterpreterTimerCallback {
	
	private VWMLEntity runOnRegularCompletition = null;
	private VWMLEntity runOnTerminatedCompletition = null;
	
	public VWMLOperationRelaxTimerCallback() {
		
	}
	
	public VWMLOperationRelaxTimerCallback(VWMLEntity runOnRegularCompletition, VWMLEntity runOnTerminatedCompletition) {
		this.runOnRegularCompletition = runOnRegularCompletition;
		this.runOnTerminatedCompletition = runOnTerminatedCompletition;
	}

	@Override
	public void askDeferredProcessingForTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task) {
		if (getRing() != null) {
			try {
				getRing().askProcessingForRelaxTimerCbk(task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	
	@Override
	public void unblockActivity(VWMLInterpreterImpl interpreter) {
		VWMLConflictRing.wakeupNode(interpreter.getRtNode());
	}
	
	@Override
	public void timerCbk(VWMLInterpreterTimer timer) {
		cbkHandler(timer, runOnRegularCompletition, true);
	}

	@Override
	public void interruptedTimerCbk(VWMLInterpreterTimer timer) {
		cbkHandler(timer, runOnTerminatedCompletition, true);
	}

	@Override
	public String getTaskName() {
		return "Relax";
	}
	
	private void cbkHandler(VWMLInterpreterTimer timer, VWMLEntity completitionTerm, boolean delayExecution) {
		defCallback(timer, completitionTerm, delayExecution);
	}

}
