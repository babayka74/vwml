package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;

/**
 * Creates and initializes requested host manager
 * @author Oleg
 *
 */
public class VWMLResourceHostManagerFactory {

	private static VWMLInterpreterConfiguration.RESOURCE_STRATEGY resourceStrategy = VWMLInterpreterConfiguration.RESOURCE_STRATEGY.ST;
	
	public static VWMLResourceHostManager hostManagerInstance() {
		if (getResourceStrategy() == VWMLInterpreterConfiguration.RESOURCE_STRATEGY.MT) {
			return instanceMT();
		}
		return instanceST();
	}
	
	public static VWMLInterpreterConfiguration.RESOURCE_STRATEGY getResourceStrategy() {
		return resourceStrategy;
	}

	public static void setResourceStrategy(VWMLInterpreterConfiguration.RESOURCE_STRATEGY ringStrategy) {
		VWMLResourceHostManagerFactory.resourceStrategy = ringStrategy;
	}
	
	protected static VWMLResourceHostManager instanceMT() {
		return VWMLResourceHostManagerMT.instance();
	}

	protected static VWMLResourceHostManager instanceST() {
		return VWMLResourceHostManagerST.instance();
	}
}
