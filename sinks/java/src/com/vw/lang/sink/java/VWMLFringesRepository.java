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
	
	private static VWMLFringesRepository s_instance = new VWMLFringesRepository();
	
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
	
	protected void associateFringe2Gates(String fringe, IVWMLGate gate) {
		fringeGates.put(fringe, gate);
	}
	
	protected IVWMLGate getFringeGate(String fringe) {
		return fringeGates.get(fringe);
	}
}
