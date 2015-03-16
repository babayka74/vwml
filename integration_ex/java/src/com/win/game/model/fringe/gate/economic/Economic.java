package com.win.game.model.fringe.gate.economic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

public class Economic implements IVWMLGate {

	protected static abstract class Handler {
		public abstract EWEntity handle(Economic economic, EWEntity commandArgs) throws Exception;
	}
	
	protected static class RecalcResourceQuantum extends Handler {

		private static final int QUANTUM 	= 0x0;
		private static final int INVESTMENT = 0x1;
		private static final int KFACTOR 	= 0x2;
		
		@Override
		public EWEntity handle(Economic economic, EWEntity commandArgs) throws Exception {
			EWEntity q = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(QUANTUM);
			EWEntity i = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(INVESTMENT);
			EWEntity k = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(KFACTOR);
			Float qFactor = Float.valueOf((String)q.getId());
			Float iFactor = Float.valueOf((String)i.getId());
			Float kFactor = Float.valueOf((String)k.getId());
			Float dp = (float)(kFactor * Math.log1p((1 / qFactor) * iFactor));
			float r = dp.floatValue() + qFactor.floatValue();
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(r), null);
		}
	}
	
	protected static class RecalcBattleResult extends Handler {

		private static final int QUANTITY 	= 0x0;
		private static final int LH 		= 0x1;
		
		
		@Override
		public EWEntity handle(Economic economic, EWEntity commandArgs) throws Exception {
			EWEntity q = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(QUANTITY);
			EWEntity lh = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(LH);
			Float qFactor = Float.valueOf((String)q.getId());
			Float lhFactor = Float.valueOf((String)lh.getId());
			float loss = (qFactor * lhFactor);
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(loss), null);
		}
		
	}
	
	@SuppressWarnings("serial")
	private Map<String, Handler> handlers = new HashMap<String, Handler>() {
		{
			put("recalcquantum", new RecalcResourceQuantum());
			put("recalcbattleresult", new RecalcBattleResult());
		}
	};
	
	private static Economic s_instance = null;
	
	public static synchronized Economic instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new Economic();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	@Override
	public EWEntity invokeVW(String commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}

	@Override
	public EWEntity invokeEW(String commandId, EWEntity commandArgs) {
		EWEntity e = EWEntityBuilder.buildSimpleEntity(EWEntity.s_NilEntityId, null);
		Handler handler = handlers.get(commandId);
		if (handler != null && commandArgs.getLink().getLinkedObjectsOnThisTime() > 0) {
			try {
				EWEntity entity = handler.handle(this, commandArgs);
				if (entity != null) {
					e = entity;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return e;
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
