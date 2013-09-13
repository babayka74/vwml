package com.vw.lang.sink.java.interpreter.datastructure;

/**
 * Defines lookup logic; looks for pair identified by name/value
 * This class is used instead of standard Properties due to portability issue.
 * @author ogibayev
 *
 */
public class VWMLPairLookUp {
	
	/**
	 * Looks for pair identified by name; complexity O(N) since we will not have more than 100 options and
	 * this operation is called only once during initialization steps 
	 * @param pairs
	 * @param name
	 * @return
	 */
	public static VWMLPair lookByName(VWMLPair[] pairs, String name) {
		if (pairs == null || pairs.length == 0) {
			return null;
		}
		for(VWMLPair pair : pairs) {
			if (pair.getName().compareToIgnoreCase(name) == 0) {
				return pair;
			}
		}
		return null;
	}

	/**
	 * Looks for pair identified by value; complexity O(N) since we will not have more than 100 options and
	 * this operation is called only once during initialization steps 
	 * @param pairs
	 * @param value
	 * @return
	 */
	public static VWMLPair lookByValue(VWMLPair[] pairs, String value) {
		if (pairs == null || pairs.length == 0) {
			return null;
		}
		for(VWMLPair pair : pairs) {
			if (pair.getValue().compareToIgnoreCase(value) == 0) {
				return pair;
			}
		}
		return null;
	}
}
