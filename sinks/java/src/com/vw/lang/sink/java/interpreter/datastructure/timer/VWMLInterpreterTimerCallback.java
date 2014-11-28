package com.vw.lang.sink.java.interpreter.datastructure.timer;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

/**
 * Timer's callback; called when timer is expired
 * @author Oleg
 *
 */
public abstract class VWMLInterpreterTimerCallback {
	private VWMLEntity argComponent;
	private VWMLConflictRing ring;
	
	public VWMLEntity getArgComponent() {
		return argComponent;
	}

	public void setArgComponent(VWMLEntity argComponent) {
		this.argComponent = argComponent;
	}

	public VWMLConflictRing getRing() {
		return ring;
	}

	public void setRing(VWMLConflictRing ring) {
		this.ring = ring;
	}

	public abstract void unblockActivity(VWMLInterpreterImpl interpreter);
	
	public abstract void timerCbk(VWMLInterpreterTimer timer);
	
	public abstract String getTaskName();
	
	public abstract void askDeferredProcessingForTimerCbk(VWMLInterpreterInterruptTimerDeferredTask task);
	
	public void interruptedTimerCbk(VWMLInterpreterTimer timer) {
		
	}
	
	public void defCallback(VWMLInterpreterTimer timer, VWMLEntity completitionTerm, boolean delayExecution) {
		VWMLInterpreterConfiguration.RESOURCE_STRATEGY st = VWMLResourceHostManagerFactory.getResourceStrategy();
		VWMLInterpreterImpl interpreter = (VWMLInterpreterImpl)timer.getUserData();
		unblockActivity(interpreter);
		if (completitionTerm != null) {
			if (!delayExecution && st == VWMLInterpreterConfiguration.RESOURCE_STRATEGY.ST) {
				try {
					VWMLOperationUtils.activateTerm(interpreter, null, false, completitionTerm, "relaxCbk_", "Relax", null);
				} catch (Exception e) { // swallow it for now
				}
			}
			else {
				VWMLInterpreterInterruptTimerDeferredTask task = prepareTask(interpreter, completitionTerm, getTaskName());
				if (st != VWMLInterpreterConfiguration.RESOURCE_STRATEGY.MT) {
					interpreter.setDeferredTask(task);
				}
				else {
					if (this.getRing() != null) {
						askDeferredProcessingForTimerCbk(task);
					}
				}
			}
		}
	}
	
	private VWMLInterpreterInterruptTimerDeferredTask prepareTask(VWMLInterpreterImpl interpreter, VWMLEntity completitionTerm, String name) {
		VWMLInterpreterInterruptTimerDeferredTask task = new VWMLInterpreterInterruptTimerDeferredTask();
		task.setActiveInterpreter(interpreter);
		task.setArgs(getArgComponent());
		task.setTerm(completitionTerm);
		task.setContextPrefix(name + "_");
		task.setOperation(name);
		return task;
	}
}
