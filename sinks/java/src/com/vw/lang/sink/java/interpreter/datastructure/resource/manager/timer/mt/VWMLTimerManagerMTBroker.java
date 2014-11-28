package com.vw.lang.sink.java.interpreter.datastructure.resource.manager.timer.mt;

import java.util.concurrent.atomic.AtomicBoolean;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.timer.TimerManagerBroker;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;

/**
 * Broker for MT's implementation of timer manager
 * @author Oleg
 *
 */
public class VWMLTimerManagerMTBroker extends TimerManagerBroker implements Runnable {
	
	private AtomicBoolean started = new AtomicBoolean(false);
	
	private VWMLTimerManagerMTBroker() {
		
	}
	
	public static VWMLTimerManagerMTBroker instance() {
		VWMLTimerManagerMTBroker broker = new VWMLTimerManagerMTBroker();
		return broker;
	}
	
	public void init() {
		super.init();
		setFringeGate(VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName()));
		start();
	}
	
	public void done() {
		started.lazySet(false);
		setFringeGate(null);
		super.done();
	}
	
	public VWMLInterpreterTimerManager getTimerManager() {
		return super.getTimerManager();
	}

	@Override
	public void run() {
		if (getFringeGate() == null) {
			return;
		}
		started.lazySet(true);
		while(started.get()) {
			EWEntity e = getFringeGate().invokeEW(IVWMLGate.builtInTimeCommandId, null);
			if (e != null) {
				try {
					getTimerManager().processReactive(Long.valueOf((String)e.getId()));
					Thread.sleep(10);
				} catch (NumberFormatException ex) {
				} catch (Exception ex) {
				}
			}
		}
	}
	
	protected void start() {
		Thread t = new Thread(this);
		t.start();
	}
}
