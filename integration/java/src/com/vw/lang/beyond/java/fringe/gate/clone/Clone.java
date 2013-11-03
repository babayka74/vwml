package com.vw.lang.beyond.java.fringe.gate.clone;

import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.beyond.java.fringe.gate.console.Console;

/**
 * Clones entities
 * @author Oleg
 *
 */
public class Clone implements IVWMLGate {

	private static String s_exportedInMethod = "clone";

	private static Clone s_instance = null;
	
	private Clone() {
		s_exportedInMethod.intern();
	}
	
	public static synchronized Clone instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new Clone();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		EWEntity res = commandArgs;
		if (!commandId.equals(s_exportedInMethod)) {
			res = null;
		}
		return res;
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
