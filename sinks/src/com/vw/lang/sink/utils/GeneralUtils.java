package com.vw.lang.sink.utils;

/**
 * Set of helper methods which are used by both code generators and parsers
 * @author ogibayev
 *
 */
public class GeneralUtils {
	
	public static String trimQuotes(String str) {
		return str.replaceAll("^\"|\"$", "");
	}
}
