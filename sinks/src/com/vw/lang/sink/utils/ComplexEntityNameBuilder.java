package com.vw.lang.sink.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Helper class which allows to build complex entity name from set of objects' ids
 * @author ogibayev
 *
 */
public class ComplexEntityNameBuilder {
	
	private final static String s_complexEntityNameFormat = "(%s)";
	
	private List<Object> ids = new ArrayList<Object>();
	private boolean inProgress = false;
	
	private ComplexEntityNameBuilder() {
		
	}
	
	/**
	 * Simple instance creator; incapsulates builder's creation mechanism
	 * @return
	 */
	public static ComplexEntityNameBuilder instance() {
		return new ComplexEntityNameBuilder();
	}
	
	/**
	 * Generates random name / id
	 * @return
	 */
	public static String generateRandomName() {
		return "CE_" + UUID.randomUUID().toString();
	}
	
	/**
	 * Adds object's id to complex entity
	 */
	public void addObjectId(Object id) {
		ids.add(id);
	}
	
	/**
	 * Actually builds entity's name which, in turn, is considered as its Id
	 * @return
	 */
	public String build() {
		String seq = new String();
		for(Object id : ids) {
			seq += (id + " ");
		}
		clear();
		seq = seq.trim();
		return String.format(s_complexEntityNameFormat, seq);
	}
	
	/**
	 * Set start/stop progress's status
	 */
	public void startProgress() {
		inProgress = true;
	}
	
	public void stopProgress() {
		inProgress = false;
	}
	
	/**
	 * Returns 'true' in case if entity's name is in building process
	 * @return
	 */
	public boolean isInProgress() {
		return inProgress;
	}
	
	/**
	 * Clears name builder's storage
	 */
	public void clear() {
		ids.clear();
	}
}

