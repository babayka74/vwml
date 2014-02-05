package com.win.game.puzzleR2.model.fringe.gate.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

public class ConfigurationLoader implements IVWMLGate {

	private static abstract class LoadParamHandler {
		public abstract String load(String paramId);
	}
	
	private static class LoadFreePlacesConfParamHandler extends LoadParamHandler {
		public String load(String paramId) {
			return "(p1 p2 p3 p4 p5 p6 p7 p8 p9)";
		}
	}
	
	private static class RestPuzzlesConfParamHandler extends LoadParamHandler {
		public String load(String paramId) {
			return "((c1 counter1) (c2 counter2) (c3 counter3)(c4 counter4) (c5 counter5) (c6 counter6)(c7 counter7) (c8 counter8) (c9 counter9))";
		}
	}
	
	private static class CorrespondenceConfParamHandler extends LoadParamHandler {
		public String load(String paramId) {
			return "((p1 c1)(p2 c2)(p3 c3)(p4 c4)(p5 c5)(p6 c6)(p7 c7)(p8 c8)(p9 c9))";
		}
	}
	
	@SuppressWarnings("serial")
	private static Map<String, LoadParamHandler> s_paramsMap = new HashMap<String, LoadParamHandler>() {
		{
			put("freePlaces", new LoadFreePlacesConfParamHandler());
			put("restPuzzles", new RestPuzzlesConfParamHandler());
			put("correspondence", new CorrespondenceConfParamHandler());
		}
	};
	
	private int CONF_PARAM_NAME_INDEX = 0x0;
	
	private int LOAD = 0x0;
	private static String s_exportedMethods[] = {"load"};
	
	private static ConfigurationLoader s_instance = null;
	
	private ConfigurationLoader() {
		
	}
	
	public static synchronized ConfigurationLoader instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new ConfigurationLoader();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		EWEntity e = null;
		if (((String)commandId).equals(s_exportedMethods[LOAD])) {
			e = loadConfiguration(commandArgs);
			if (e == null) {
				e = EWEntityBuilder.buildSimpleEntity(EWEntity.s_NilEntityId, EWEntity.s_NilEntityId);
			}
		}
		return e;
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateConfiguration(Properties props) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private EWEntity loadConfiguration(EWEntity commandArgs) {
		EWEntity e = null;
		EWEntity confParam = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(CONF_PARAM_NAME_INDEX);
		LoadParamHandler handler = s_paramsMap.get((String)confParam.getId());
		if (handler != null) {
			String param = handler.load((String)confParam.getId());
			if (param != null) {
				try {
					e = EWEntityBuilder.buildFromString(param);
				} catch (Exception ex) {
				}
			}
		}
		return e;
	}
}
