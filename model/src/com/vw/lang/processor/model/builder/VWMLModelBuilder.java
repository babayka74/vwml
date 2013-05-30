package com.vw.lang.processor.model.builder;

import org.apache.log4j.Logger;
import com.vw.common.Debuggable;


/**
 * 
 * @author ogibayev
 *
 */
public class VWMLModelBuilder extends Debuggable {

	/**
	 * List of available sinks; actually defines supported languages
	 * @author ogibayev
	 *
	 */
	public static enum SINK_TYPE {
		JAVA, CPP, C, OBJECTIVE_C
	}

	/**
	 * Default language is JAVA
	 */
	private SINK_TYPE sinkType = SINK_TYPE.JAVA;
	
	// builder is implemented as singleton
	private static volatile VWMLModelBuilder s_builder = null;
	private final Logger logger = Logger.getLogger(VWMLModelBuilder.class);

	private VWMLModelBuilder() {
		
	}
	
	/**
	 * Creates and initializes builder
	 * @return
	 * @throws Exception
	 */
	public static VWMLModelBuilder instance() {
		if (s_builder != null) {
			return s_builder;
		}
		synchronized(VWMLModelBuilder.class) {
			if (s_builder != null) {
				return s_builder;
			}
			VWMLModelBuilder builder = new VWMLModelBuilder();
			try {
				builder.init();
				s_builder = builder;
			} catch (Exception e) {
				builder.trace("exception caught '" + e + "'");
			}
		}
		return s_builder;
	}
	
	/**
	 * Runs builder's initialization process
	 * @throws Exception
	 */
	public void init() throws Exception {
	}
	
	/**
	 * Explicitly sets sink's type
	 * @return
	 */
	public SINK_TYPE getSinkType() {
		return sinkType;
	}

	public void setSinkType(SINK_TYPE sinkType) {
		this.sinkType = sinkType;
	}
	
	@Override
	public void debugEnter(Object to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debugExit(Object from) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String trace) {
		if (isTraceable()) {
			if (isDebug()) {
				System.out.println(trace);
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug(trace);
				}
			}
		}
	}
}
