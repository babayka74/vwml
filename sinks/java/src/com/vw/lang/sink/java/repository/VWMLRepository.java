package com.vw.lang.sink.java.repository;

import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Root class for all module's repositories
 * @author ogibayev
 *
 */
public class VWMLRepository {

	private InterpretationObserver interpretationObserver = null;
	
	public InterpretationObserver getInterpretationObserver() {
		return interpretationObserver;
	}

	public void setInterpretationObserver(
			InterpretationObserver interpretationObserver) {
		this.interpretationObserver = interpretationObserver;
	}

	/**
	 * Overridden by concrete repository; returns structure visualizer
	 * @return
	 */
	public AbstractVWMLLinkVisitor getPreprocessorStructureVisualizer() {
		return null;
	}
}
