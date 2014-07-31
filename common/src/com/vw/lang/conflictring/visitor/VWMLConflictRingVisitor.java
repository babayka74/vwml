package com.vw.lang.conflictring.visitor;

/**
 * Implemented by conflict ring in order to display related information
 * @author Oleg
 *
 */
public class VWMLConflictRingVisitor {
	
	public abstract static class VWMLConflictRingVisitorSink {
		
		public abstract void init();
		
		public abstract void done();
		
		public abstract void print(String str);
	}
	
	public static class VWMLConflictConsoleRingVisitorSink extends VWMLConflictRingVisitorSink {

		@Override
		public void init() {
		}

		@Override
		public void done() {
		}

		@Override
		public void print(String str) {
			System.out.println(str);
		}
	}

	private VWMLConflictRingVisitorSink sink = new VWMLConflictConsoleRingVisitorSink();
	
	public VWMLConflictRingVisitor() {
	}
	
	/**
	 * Initialization step
	 */
	public void init() {
		if (getSink() != null) {
			getSink().init();
		}
	}
	
	/**
	 * Releases allocated resources
	 */
	public void done() {
		if (getSink() != null) {
			getSink().done();
		}
	}
	
	/**
	 * Prints given string to selected sink
	 * @param str
	 */
	public void print(String str) {
		if (getSink() != null) {
			getSink().print(str);
		}
	}

	public VWMLConflictRingVisitorSink getSink() {
		return sink;
	}

	public void setSink(VWMLConflictRingVisitorSink sink) {
		this.sink = sink;
	}
}
