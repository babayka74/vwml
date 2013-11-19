package com.vw.lang.beyond.java.fringe.gate.time;

import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

public class Time implements IVWMLGate {

	private static String s_exportedInMethod = "time";
	private static Time s_instance = null;
	
	private Time() {
		s_exportedInMethod.intern();
	}
	
	public static synchronized Time instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new Time();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		EWEntity e = null;
		if (((String)commandId).equalsIgnoreCase(IVWMLGate.builtInTimeCommandId)) {
			e = EWEntityBuilder.buildSimpleEntity(String.valueOf(System.currentTimeMillis()), null);
		}
		else
		if (((String)commandId).equalsIgnoreCase(IVWMLGate.builtInDelayCommandId)) {
			int delay = 0;
			if (commandArgs != null) {
				delay = Integer.valueOf(((String)commandArgs.getId())).intValue();
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e1) { // simple swallow it
			}
		}
		return e;
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public void init() throws Exception {
		
	}

	@Override
	public void done() throws Exception {
		
	}

	@Override
	public void activateConfiguration(Properties props) throws Exception {
		
	}

}
