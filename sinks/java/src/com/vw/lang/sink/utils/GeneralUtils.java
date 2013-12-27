package com.vw.lang.sink.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of helper methods which are used by both code generators and parsers
 * @author ogibayev
 *
 */
public class GeneralUtils {
	
	private static List<Object> s_objContainer = new ArrayList<Object>();
	
	/**
	 * Trims string's quotes (lead and tailored)
	 * @param str
	 * @return
	 */
	public static String trimQuotes(String str) {
		return str.replaceAll("^\"|\"$", "");
	}
	
	/**
	 * Simple class instantiation; the class should have public default constructor
	 * @param className
	 * @return
	 */
	public static Object instantiateClass(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			// swallow it
		}
		return null;
	}
	
	/**
	 * Instantiates class using its static method instead of calling constructor
	 * @param className
	 * @param method
	 * @return
	 */
	public static Object instantiateClassThroughStaticMethod(String className, String method) {
		Object obj = null;
		try {
			Class<?> clazz = Class.forName(className);
			obj = clazz.getMethod(method).invoke(null);
		} catch (Exception e) {
			// swallow it
		}
		return obj;
	}
	
	/**
	 * Simple adds object to container
	 * @param string
	 */
	public static void addObject(Object string) {
		s_objContainer.add(string);
	}
	
	
	/**
	 * Clears container
	 */
	public static void clearContainer() {
		s_objContainer.clear();
	}
	
	/**
	 * Returns int value inside range defined by [lr, rr)
	 * @param lr
	 * @param rr
	 * @return
	 */
	public static int getIntInRange(int lr, int rr) {
		if (lr == rr) {
			return lr;
		}
		return lr + (int)(Math.random() * ((rr - lr)));
	}
	
	/**
	 * Returns UTC time measured in ms
	 * @return
	 */
	public static long getTimeAsLong() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Returns reference to objects' container
	 * @return
	 */
	public List<Object> getObjContainer() {
		return s_objContainer;
	}	
}
