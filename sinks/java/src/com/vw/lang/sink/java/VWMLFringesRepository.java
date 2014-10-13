package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Contains association between fringes and their gates
 * @author Oleg
 *
 */
public class VWMLFringesRepository {
	
	private Map<String, IVWMLGate> fringeGates = new HashMap<String, IVWMLGate>();
	
	private static String s_DebugFringeReservedName = "__vwml_debug_fringe__";
	private static String s_TimeFringeReservedName  = "__vwml_time_fringe__";
	private static String s_ActivityFringeReservedName  = "__vwml_activity_fringe__";
	
	private static VWMLFringesRepository s_instance = new VWMLFringesRepository();
	
	private VWMLFringesRepository() {
		associatePredefinedFringes();
	}
	
	public static VWMLFringesRepository instance() {
		return s_instance;
	}
	
	/**
	 * Registers fringe; associate fringe's name and its gate
	 * @param fringe
	 * @param gate
	 */
	public static void registerFringeGate(String fringe, IVWMLGate gate) {
		s_instance.associateFringe2Gates(fringe, gate);
	}
	
	/**
	 * Returns gate by fringe's name
	 * @param fringe
	 * @return
	 */
	public static IVWMLGate getGateByFringeName(String fringe) {
		return s_instance.getFringeGate(fringe);
	}

	/**
	 * Returns built-in debugging fringe's name
	 * @return
	 */
	public static String getDebugFringeName() {
		return s_DebugFringeReservedName;
	}

	/**
	 * Unregisters all registered fringes
	 * @throws Exception
	 */
	public void removeAll() throws Exception {
		fringeGates.clear();
		associatePredefinedFringes();
	}
	
	/**
	 * Returns built-in timer manager fringe's name
	 * @return
	 */
	public static String getTimerManagerFringeName() {
		return s_TimeFringeReservedName;
	}

	/**
	 * Returns built-in activity broker fringe's name
	 * @return
	 */
	public static String getActivityBrokerFringeName() {
		return s_ActivityFringeReservedName;
	}
	
	protected void associateFringe2Gates(String fringe, IVWMLGate gate) {
		fringeGates.put(fringe, gate);
	}
	
	protected IVWMLGate getFringeGate(String fringe) {
		return fringeGates.get(fringe);
	}
	
	protected void associatePredefinedFringes() {
		associateFringe2Gates(s_TimeFringeReservedName, com.vw.lang.beyond.java.fringe.gate.time.Time.instance());
		associateFringe2Gates(s_ActivityFringeReservedName, com.vw.lang.beyond.java.fringe.gate.activity.ActivityBroker.instance());
	}
}
