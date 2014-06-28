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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((interpreting == null) ? 0 : interpreting.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			VWMLEntityDescriptor other = (VWMLEntityDescriptor) obj;
			if (interpreting == null) {
				if (other.interpreting != null) {
					return false;
				}
			} else if (!interpreting.equals(other.interpreting)) {
				return false;
			}
			return true;
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
	
	public void reset(VWMLEntity interpreting) {
		history.remove(interpreting);
	}
	
	/**
	 * Flushes history to external storage
	 * @throws Exception
	 */
	public void flush() throws Exception {
		throw new Exception("unimplemented yet");
	}
}
