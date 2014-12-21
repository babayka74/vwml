package com.win.game.model.fringe.gate.configuration.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.win.game.model.fringe.gate.utils.Utils;

public class ConfigurationLoader implements IVWMLGate {

	public static String CONFUGURATION_OUT_PATH_KEY = "confPath";
	public static String DEF_PATH = "/tmp";
	
	protected static abstract class Handler {
		public abstract EWEntity handle(ConfigurationLoader cl, String name) throws Exception;
	}
	
	protected static class Load extends Handler {
		public EWEntity handle(ConfigurationLoader cl, String name) throws Exception {
			EWEntity e = null;
			if (cl.getConfigurationPath() == null) {
				cl.setConfigurationPath(DEF_PATH);
			}
			String fullPath = cl.getConfigurationPath() + "/" + name + ".conf";
			BufferedReader br = null;
		    try {
		    	FileReader fr = new FileReader(fullPath);
				br = new BufferedReader(fr);
				e = Utils.constructEntityFromTextFile(fullPath);
		    }
		    catch(Exception ex) {
		    	if (br != null) {
		    		br.close();
		    	}
		    }				
			return e;
		}
	}
	
	private String configurationPath = null;
	
	@SuppressWarnings("serial")
	private Map<String, Handler> handlers = new HashMap<String, Handler>() {
		{
			put("load", new Load());
		}
	};
	
	private static ConfigurationLoader s_instance = null;

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
			EWEntity eAsConfName = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
			try {
				EWEntity entity = handler.handle(this, (String)eAsConfName.getId());
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
		if (props.get(CONFUGURATION_OUT_PATH_KEY) != null) {
			setConfigurationPath((String)props.get(CONFUGURATION_OUT_PATH_KEY));
		}
	}

	public String getConfigurationPath() {
		return configurationPath;
	}

	public void setConfigurationPath(String configurationPath) {
		this.configurationPath = configurationPath;
	}
}
