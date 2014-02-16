package com.win.game.puzzleR2.model.fringe.gate.ext.strategy;

import java.util.List;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.entity.EWObject;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

public class WinnerSelector implements IVWMLGate {

	private static WinnerSelector s_instance = null;
	
	private WinnerSelector() {
		
	}
	
	public static synchronized WinnerSelector instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new WinnerSelector();
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
		try {
			if (commandArgs == null) {
				return EWEntityBuilder.buildFromString(EWEntity.s_NilEntityId);
			}
			if (commandArgs.isMarkedAsComplexEntity()) {
				int maxBet = 0;
				EWEntity winBet = null;
				List<EWObject> linkedObjs = commandArgs.getLink().getLinkedObjects();
				for(EWObject obj : linkedObjs) {
					if (((EWEntity)obj).isMarkedAsComplexEntity()) {
						EWEntity e = (EWEntity)((EWEntity)obj).getLink().getConcreteLinkedEntity(0x01);
						int bet = Integer.valueOf((String)e.getId());
						if (bet > maxBet) {
							maxBet = bet;
							winBet = (EWEntity)obj;
						}
					}
				}
				return (winBet == null) ? EWEntityBuilder.buildFromString(EWEntity.s_NilEntityId) : winBet;
			}
		}
		catch(Exception ex) { // swallow
		}
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
