package com.vw.lang.utils;

/**
 * Defines methods for dynamic stack visualization procedure
 * @author ogibayev
 *
 */
public interface IStackVisualizer {
	/**
	 * Called when object is pushed to stack
	 * @param o
	 */
	public void objectPushed(Object o);
	
	/**
	 * Called when object is popped from stack
	 * @param o
	 */
	public void objectPopped(Object o);
}
