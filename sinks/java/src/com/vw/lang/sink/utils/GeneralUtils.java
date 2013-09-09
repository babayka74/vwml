package com.vw.lang.sink.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Set of helper methods which are used by both code generators and parsers
 * @author ogibayev
 *
 */
public class GeneralUtils {
	
	private static Logger logger = Logger.getLogger(GeneralUtils.class);
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
			logger.error("couldn't instantiate class '" + className + "'; the cause is '" + e + "'", e);
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
			logger.error("couldn't instantiate class '" + className + "'; the cause is '" + e + "'", e);
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
		return 0;
	}
	
	/**
	 * Returns reference to objects' container
	 * @return
	 */
	public List<Object> getObjContainer() {
		return s_objContainer;
	}
}
