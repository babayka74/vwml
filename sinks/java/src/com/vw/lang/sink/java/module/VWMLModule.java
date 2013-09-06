package com.vw.lang.sink.java.module;

import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.repository.VWMLRepository;


/**
 * Root class for VWML's modules
 * @author ogibayev
 *
 */
public abstract class VWMLModule {
	/**
	 * Builds module, as result - established connections among entities and related operations
	 * @throws Exception
	 */
	public abstract void build() throws Exception;
	
	/**
	 * Returns repository associated with given module; overridden by concrete module
	 * @return
	 */
	public VWMLRepository getRepository() {
		return null;
	}
	
	/**
	 * Returns module's linkage; overridden by concrete module
	 * @return
	 */
	public VWMLLinkage getLinkage() {
		return null;
	}
}
