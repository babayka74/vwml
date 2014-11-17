package com.vw.lang.sink.java.interpreter.datastructure.timer;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * Timer's callback; called when timer is expired
 * @author Oleg
 *
 */
public abstract class VWMLInterpreterTimerCallback {
	private VWMLEntity argComponent;
	
	public VWMLEntity getArgComponent() {
		return argComponent;
	}

	public void setArgComponent(VWMLEntity argComponent) {
		this.argComponent = argComponent;
	}

	public abstract void timerCbk(VWMLInterpreterTimer timer);
	
	public void interruptedTimerCbk(VWMLInterpreterTimer timer) {
		
	}
}
