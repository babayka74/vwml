package com.vw.lang.sink.java.interpreter.datastructure.timer;

/**
 * Timer's callback; called when timer is expired
 * @author Oleg
 *
 */
public abstract class VWMLInterpreterTimerCallback {
	public abstract void timerCbk(VWMLInterpreterTimer timer);
}
