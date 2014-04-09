package com.vw.lang.beyond.java.fringe.gate.movement;

import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Notifies EW about movement
 * @author Oleg
 *
 */
public class Go implements IVWMLGate {

	private static final String s_ExportedCommand = "go";
	
	private static Go s_go = null;
	
	private Go() {
		s_ExportedCommand.intern();
	}
	
	public static synchronized Go instance() {
		if (s_go != null) {
			return s_go;
		}
		s_go = new Go();
		try {
			s_go.init();
		} catch (Exception e) {
			s_go = null;
		}
		return s_go;
	}
	
	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}
	
	@Override
	public void activateConfiguration(Properties props) throws Exception {
	}
	
	@Override
	public EWEntity invokeVW(String commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public EWEntity invokeEW(String commandId, EWEntity commandArgs) {
		EWEntity e = null;
		if (commandArgs != null && commandArgs.isMarkedAsComplexEntity() && commandArgs.getLink().getLinkedObjectsOnThisTime() >= 2) {
			Object from = commandArgs.getLink().getConcreteLinkedEntity(0).getId();
			Object to = commandArgs.getLink().getConcreteLinkedEntity(1).getId();
			System.out.println("moved from '" + from + "' to '" + to + "'");
		}
		return e;
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void done() throws Exception {
	}
}
