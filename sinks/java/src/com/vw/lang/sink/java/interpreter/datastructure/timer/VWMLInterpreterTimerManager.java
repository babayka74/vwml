package com.vw.lang.sink.java.interpreter.datastructure.timer;

import java.util.LinkedList;
import java.util.List;

/**
 * Reactive timer manager
 * @author Oleg
 *
 */
public class VWMLInterpreterTimerManager {
	private List<VWMLInterpreterTimer> timers = new LinkedList<VWMLInterpreterTimer>();
	
	private VWMLInterpreterTimerManager() {
		
	}
	
	public static synchronized VWMLInterpreterTimerManager instance() {
		VWMLInterpreterTimerManager instance = new VWMLInterpreterTimerManager();
		instance.init();
		return instance;
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
/*		
		System.out.println("timers ->");
		for(VWMLInterpreterTimer t : timers) {
			System.out.print(t.getId() + ":" + t.getOrigTime() + ":" + t.getTime());
		}
		System.out.println();
*/		
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
	
	protected void init() {
		
	}
	
	protected void done() {
		timers.clear();
	}
}
