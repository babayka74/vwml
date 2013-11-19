package com.vw.lang.sink.java.interpreter.datastructure.timer;

/**
 * Regular data structure for timer
 * @author Oleg
 *
 */
public class VWMLInterpreterTimer {
	private Object id;
	private int time;
	private Object userData;
	private long timeStamp;
	private VWMLInterpreterTimerCallback callback;
	
	public VWMLInterpreterTimer() {
		super();
	}

	public VWMLInterpreterTimer(Object id, int time, Object userData, long timeStamp, VWMLInterpreterTimerCallback callback) {
		super();
		this.id = id;
		this.time = time;
		this.userData = userData;
		this.timeStamp = timeStamp;
		this.callback = callback;
	}

	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	public Object getUserData() {
		return userData;
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public VWMLInterpreterTimerCallback getCallback() {
		return callback;
	}

	public void setCallback(VWMLInterpreterTimerCallback callback) {
		this.callback = callback;
	}
}
