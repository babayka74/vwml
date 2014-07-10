package com.win.game.model.fringe.gate.algorithm.graphloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

public class GraphLoader implements IVWMLGate {

	public static String GRAPH_DATA_OUT_PATH_KEY = "outPath";
	public static String DEF_PATH = "/tmp";
	
	protected static abstract class Handler {
		public abstract EWEntity handle(GraphLoader gl, String graphName) throws Exception;
	}
	
	protected static class Load extends Handler {
		public EWEntity handle(GraphLoader gl, String graphName) throws Exception {
			EWEntity e = null;
			if (gl.getPathToStoreGraphData() == null) {
				gl.setPathToStoreGraphData(DEF_PATH);
			}
			String fullPath = gl.getPathToStoreGraphData() + "/" + graphName + ".graph";
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            line = br.readLine();
		        }
		        e = EWEntityBuilder.buildFromString(sb.toString());
		    } finally {
		        br.close();
		    }				
			return e;
		}
	}
	
	@SuppressWarnings("serial")
	private Map<String, Handler> handlers = new HashMap<String, Handler>() {
		{
			put("load", new Load());
		}
	};
	
	private String pathToStoreGraphData = null;
	private static GraphLoader s_instance = null;
	
	private GraphLoader() {
		
	}
	
	public static synchronized GraphLoader instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new GraphLoader();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	public String getPathToStoreGraphData() {
		return pathToStoreGraphData;
	}

	public void setPathToStoreGraphData(String pathToStoreGraphData) {
		this.pathToStoreGraphData = pathToStoreGraphData;
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
			EWEntity eAsGraphName = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
			try {
				EWEntity entity = handler.handle(this, (String)eAsGraphName.getId());
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
		if (props.get(GRAPH_DATA_OUT_PATH_KEY) != null) {
			setPathToStoreGraphData((String)props.get(GRAPH_DATA_OUT_PATH_KEY));
		}
	}
}
