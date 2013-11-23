package com.vw.lang.sink.java.test;

import org.junit.Test;

import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerCallback;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;

public class VWMLInterpreterTimerTest {

	private static VWMLInterpreterTimerManager tm = VWMLInterpreterTimerManager.instance();
	
	protected class VWMLInterpreterTestTimerCallback extends VWMLInterpreterTimerCallback {

		private int expired = 0;
		private int expectedToExpire = 0;

		public VWMLInterpreterTestTimerCallback() {
			super();
		}

		public VWMLInterpreterTestTimerCallback(int expectedToExpire) {
			super();
			this.expectedToExpire = expectedToExpire;
		}

		@Override
		public void timerCbk(VWMLInterpreterTimer timer) {
			System.out.println("timer '" + timer.getId() + "' expired");
			VWMLInterpreterTestTimerCallback callback = new VWMLInterpreterTestTimerCallback(1);
			tm.addTimer("t1", 996, System.currentTimeMillis(), null, callback);
			expired++;
		}

		public int getExpired() {
			return expired;
		}

		public void setExpired(int expired) {
			this.expired = expired;
		}

		public int getExpectedToExpire() {
			return expectedToExpire;
		}

		public void setExpectedToExpire(int expectedToExpire) {
			this.expectedToExpire = expectedToExpire;
		}
		
		public boolean expiredAll() {
			return expired == expectedToExpire;
		}
	}
	
	@Test
	public void test() throws Exception {
		VWMLInterpreterTestTimerCallback callback = new VWMLInterpreterTestTimerCallback(2);
		tm.addTimer("t1", 996, System.currentTimeMillis(), null, callback);
		tm.addTimer("t2", 2100, System.currentTimeMillis(), null, callback);
		while(!callback.expiredAll()) {
			tm.processReactive(System.currentTimeMillis());
			Thread.sleep(0);
		}
		tm.stop();
	}
}
