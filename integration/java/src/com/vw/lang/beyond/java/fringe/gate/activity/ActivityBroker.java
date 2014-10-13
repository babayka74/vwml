package com.vw.lang.beyond.java.fringe.gate.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Manages model's activity by allowing to create and manage threads
 * @author Oleg
 *
 */
public class ActivityBroker implements IVWMLGate {

	protected static class ActivityState {
		protected enum STATE {
			ACTIVE,
			STOPPED
		}
		
		private String name;
		private STATE state = STATE.STOPPED;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public STATE getState() {
			return state;
		}

		public void setState(STATE state) {
			this.state = state;
		}
	}
	
	protected static abstract class Handler {
		protected abstract ActivityState handle(ActivityBroker broker, Long id, String name, ActivityState state);
	}

	/**
	 * Called upon activity creation
	 * @author Oleg
	 *
	 */
	protected static class CreateActivityHandler extends Handler {
		protected ActivityState handle(ActivityBroker broker, Long id, String name, ActivityState state) {
			if (state == null) {
				state = new ActivityState();
			}
			state.setState(ActivityState.STATE.ACTIVE);
			state.setName(name);
			broker.addActivity(id, state);
			return null;
		}
	}

	/**
	 * Called upon activity removing
	 * @author Oleg
	 *
	 */
	protected static class RemoveActivityHandler extends Handler {
		protected ActivityState handle(ActivityBroker broker, Long id, String name, ActivityState state) {
			broker.removeActivity(id);
			return null;
		}
	}

	/**
	 * Called upon request for activity state
	 * @author Oleg
	 *
	 */
	protected static class GetStateActivityHandler extends Handler {
		protected ActivityState handle(ActivityBroker broker, Long id, String name, ActivityState state) {
			return null;
		}
	}
	
	private static ActivityBroker s_instance = null;

	@SuppressWarnings("serial")
	private Map<String, Handler> handlers = new HashMap<String, Handler>() {
		{
			put("create", new CreateActivityHandler());
			put("remove", new RemoveActivityHandler());
			put("getstate", new GetStateActivityHandler());
		}
	};
	
	// activities set
	private Map<Long, ActivityState> activities = new ConcurrentHashMap<Long, ActivityState>();
	private static final int ACTIVITY_ARGS     = 2;
	private static final int ACTIVITY_ID_ARG   = 0;
	private static final int ACTIVITY_NAME_ARG = 1;
	
	private ActivityBroker() {
	}
	
	public static synchronized ActivityBroker instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new ActivityBroker();
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
		EWEntity stateAsEntity = null;
		Handler handler = handlers.get(commandId);
		if (handler != null && commandArgs.getLink().getLinkedObjectsOnThisTime() == ACTIVITY_ARGS) {
			Long id = Long.valueOf((String)commandArgs.getLink().getConcreteLinkedEntity(ACTIVITY_ID_ARG).getId());
			String name = (String)commandArgs.getLink().getConcreteLinkedEntity(ACTIVITY_NAME_ARG).getId();
			handler.handle(this, id, name, null);
		}
		return stateAsEntity;
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
	
	protected void addActivity(Long id, ActivityState state) {
		activities.put(id, state);
	}
	
	protected void removeActivity(Long id) {
		activities.remove(id);
	}
}
