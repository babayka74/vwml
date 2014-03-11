package com.vw.lang.sink.java;

/**
 * Various methods used by exported code
 * @author ogibayev
 *
 */
public class VWMLJavaExportUtils {

	public static String[] parseContext(String context) {
		return (context != null) ? context.split("\\.") : null;
	}
}
