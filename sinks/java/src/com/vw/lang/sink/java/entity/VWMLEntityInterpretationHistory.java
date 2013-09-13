package com.vw.lang.sink.java.entity;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Collects all evaluations of entity's interpretation
 * @author ogibayev
 *
 */
public class VWMLEntityInterpretationHistory {
	
	protected static class VWMLEntityDescriptor {
		private VWMLEntity interpreting;
		private long time;
		
		private VWMLEntityDescriptor(VWMLEntity interpreting, long time) {
			super();
			this.interpreting = interpreting;
			this.time = time;
		}

		public static VWMLEntityDescriptor instance(VWMLEntity interpreting, long time) {
			return new VWMLEntityDescriptor(interpreting, time);
		}
		
		public VWMLEntity getInterpreting() {
			return interpreting;
		}

		public long getTime() {
			return time;
		}

		@Override
		public String toString() {
			return "VWMLEntityDescriptor [interpreting=" + interpreting + ", time=" + time + "]";
		}
	}
	
	private static final int s_defHistorySize = 4096;
	private int maxHistorySize = s_defHistorySize;
	private int historyPointer = 0;
	private List<VWMLEntityDescriptor> history = new ArrayList<VWMLEntityDescriptor>();
	
	public int getMaxHistorySize() {
		return maxHistorySize;
	}

	public void setMaxHistorySize(int maxHistorySize) {
		this.maxHistorySize = maxHistorySize;
	}

	/**
	 * Stores previous interpreting entity in history storage
	 * @param interpreting
	 */
	public void store(VWMLEntity interpreting) {
		if (maxHistorySize != 0) {
			if (history.size() <= maxHistorySize) {
				history.add(VWMLEntityDescriptor.instance(interpreting, GeneralUtils.getTimeAsLong()));
			}
			else {
				history.set(historyPointer, VWMLEntityDescriptor.instance(interpreting, GeneralUtils.getTimeAsLong()));
				historyPointer = historyPointer % maxHistorySize;
				historyPointer++;
			}
		}		
	}
	
	/**
	 * Restores history's state
	 * @throws Exception
	 */
	public void restore() throws Exception {
		
	}
	
	/**
	 * Flushes history to external storage
	 * @throws Exception
	 */
	public void flush() throws Exception {
		throw new Exception("unimplemented yet");
	}
}
