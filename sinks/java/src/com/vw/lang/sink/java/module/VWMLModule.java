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
	 * Performs module's initialization steps, runs before build method
	 * @throws Exception
	 */
	public abstract void prepare() throws Exception;
	
	/**
	 * Acquires entities and pushes them into repository
	 * @throws Exception
	 */
	public abstract void build() throws Exception;
	
	/**
	 * Builds module, as result - established connections among entities and related operations
	 * @throws Exception
	 */
	public abstract void linkage() throws Exception;
	
	/**
	 * Clears all resources which were allocated by module
	 * @throws Exception
	 */
	public void clear() throws Exception {
		throw new Exception("Unsupported method clear");
	}
	
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
