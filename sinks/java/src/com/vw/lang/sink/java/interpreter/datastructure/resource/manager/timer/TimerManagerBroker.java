package com.vw.lang.sink.java.interpreter.datastructure.resource.manager.timer;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;

/**
 * General interface for timer manager's broker
 * @author Oleg
 *
 */
public abstract class TimerManagerBroker {

	private IVWMLGate fringeGate = null;
	private VWMLInterpreterTimerManager timerManager = new VWMLInterpreterTimerManager();

	public void init() {
		timerManager.init();
	}
	
	public void done() {
		timerManager.done();
	}

	public IVWMLGate getFringeGate() {
		return fringeGate;
	}

	public void setFringeGate(IVWMLGate fringeGate) {
		this.fringeGate = fringeGate;
	}

	public VWMLInterpreterTimerManager getTimerManager() {
		return timerManager;
	}
}
