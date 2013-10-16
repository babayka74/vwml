package com.vw.lang.debug.common;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Transcodes objects to json and visa versa
 * @author Oleg
 *
 */
public class VWMLDebugCommandTranscoder {
	
	/**
	 * Transcodes regular object to JSON
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJSON(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
	
	/**
	 * Transcodes from JSON format to regular object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object fromJSON(String json, Class<?> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * Transcodes from JSON format to regular object
	 * @param is
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object fromJSONEx(InputStream is, Class<?> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, clazz);
	}
	
}
