package com.vw.lang.sink.java.interpreter.datastructure.timer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;

/**
 * Reactive timer manager
 * @author Oleg
 *
 */
public class VWMLInterpreterTimerManager {
	
	public static class TimerState {
		private int time;
		private int origTime;
		private long timeStamp;
		
		public TimerState(int time, int origTime, long timeStamp) {
			super();
			this.time = time;
			this.origTime = origTime;
			this.timeStamp = timeStamp;
		}

		public int getTime() {
			return time;
		}

		public int getOrigTime() {
			return origTime;
		}

		public long getTimeStamp() {
			return timeStamp;
		}
	}
	
	private List<VWMLInterpreterTimer> timers = VWMLResourceHostManagerFactory.hostManagerInstance().requestTimerManagerContainer();
	private Set<Object> timersMap = new HashSet<Object>();
	
	public VWMLInterpreterTimerManager() {
		
	}
	
	public static VWMLInterpreterTimerManager instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestTimerManager();
	}

	public static void invalidate() {
		VWMLResourceHostManagerFactory.hostManagerInstance().markTimerManagerAsInvalid();
	}
	
	/**
	 * Stops all timers and clears resources
	 */
	public void stop() {
		done();
	}
	
	/**
	 * Adding timer to timer manager
	 * @param id
	 * @param time
	 * @param timeStamp
	 * @param callback
	 */
	public void addTimer(Object id, int time, long timeStamp, Object userData, VWMLInterpreterTimerCallback callback) {
		int otime = time;
		if (timers.size() == 0) {
			timers.add(new VWMLInterpreterTimer(id, otime, time, userData, timeStamp, callback));
		}
		else {
			int i = 0; 
			VWMLInterpreterTimer t = null;
			for(; i < timers.size(); i++) {
				t = timers.get(i);
				if (time - t.getTime() > 0) {
					time -= t.getTime();
				}
				else {
					break;
				}
			}
			if (i == timers.size()) {
				timers.add(new VWMLInterpreterTimer(id, otime, time, userData, timeStamp, callback));
			}
			else {
				t = timers.get(i);
				t.setTime(t.getTime() - time);
				timers.add(i, new VWMLInterpreterTimer(id, otime, time, userData, timeStamp, callback));
			}
		}
	}
	
	/**
	 * Removes timer and activates its termination handler
	 * @param id
	 */
	public VWMLInterpreterTimer removeTimer(Object id) {
		VWMLInterpreterTimer t = null;
		for(int i = 0; i < timers.size(); i++) {
			t = timers.get(i);
			if (t.getId().equals(id)) {
				// start removing procedure
				if (i != timers.size() - 1) {
					VWMLInterpreterTimer tNext = timers.get(i + 1);
					tNext.setTime(tNext.getTime() + t.getTime());
				}
				timers.remove(i);
				if (t.getCallback() != null) {
					t.getCallback().interruptedTimerCbk(t);
				}
				break;
			}
			t = null;
		}
		return t;
	}

	/**
	 * Removes timer from scheduler and runs callback associated with one
	 * @param id
	 */
	public void instantFinishTimer(Object id) {
		VWMLInterpreterTimer t = removeTimer(id);
		if (t != null) {
			t.getCallback().timerCbk(t);
		}
	}
	
	/**
	 * Returns 'true' in case if timer has already been scheduled
	 * @param id
	 * @return
	 */
	public boolean checkTimer(Object id) {
		return timersMap.contains(id);
	}
	
	/**
	 * Adding timer to timer manager
	 * @param id
	 * @param time
	 * @param timeStamp
	 * @param callback
	 */
	public void updateTimer(Object id, int time, long timeStamp, Object userData, VWMLInterpreterTimerCallback callback) {
		if (checkTimer(id)) {
			removeTimer(id);
		}
		addTimer(id, time, timeStamp, userData, callback);
	}
	
	/**
	 * Returns timer state identified by id
	 * @param id
	 * @return
	 */
	public TimerState getTimerState(Object id) {
		TimerState ts = null;
		for(int i = 0; i < timers.size(); i++) {
			VWMLInterpreterTimer t = timers.get(i);
			if (t.getId().equals(id)) {
				ts = new TimerState(t.getTime(), t.getOrigTime(), t.getTimeStamp());
				break;
			}
		}
		return ts;
	}
	
	/**
	 * Runs timer manager in reactive manner
	 * @param currentTimeStamp
	 */
	public void processReactive(long currentTimeStamp) throws Exception {
		if (timers.size() == 0) {
			return;
		}
		VWMLInterpreterTimer tm = timers.get(0);
		int t =  tm.getOrigTime() - (int)(currentTimeStamp - tm.getTimeStamp());
		if (t <= 0) {
			tm.setTime(0);
			while(timers.size() != 0 && timers.get(0).getTime() == 0) {
				// calls callback
				tm = timers.get(0);
				if (tm.getCallback() != null) {
					tm.getCallback().timerCbk(tm);
				}
				timers.remove(0);
			}
		}
		else {
			tm.setTime(t);
		}
	}
	
	/**
	 * Return number of active timers
	 * @return
	 */
	public int timers() {
		return timers.size();
	}
	
	public void init() {
		
	}
	
	public void done() {
		timers.clear();
		timersMap.clear();
	}
}
