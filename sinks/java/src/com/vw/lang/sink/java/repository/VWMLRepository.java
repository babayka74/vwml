package com.vw.lang.sink.java.repository;

import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Root class for all module's repositories
 * @author ogibayev
 *
 */
public class VWMLRepository {

	/**
	 * Overridden by concrete repository; returns structure visualizer
	 * @return
	 */
	public AbstractVWMLLinkVisitor getPreprocessorStructureVisualizer() {
		return null;
	}
}
