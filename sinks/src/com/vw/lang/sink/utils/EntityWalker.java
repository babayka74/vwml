package com.vw.lang.sink.utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Used during VXML's parsing phase
 * @author ogibayev
 *
 */
public class EntityWalker {
	private Deque<Object> ids = new LinkedList<Object>();
	private Object iasObj = null;
	
	private EntityWalker() {
		
	}
	
	public static EntityWalker instance() {
		return new EntityWalker();
	}
	
	public void push(Object obj) {
		ids.push(obj);
	}
	
	public Object pop() {
		return ids.pop();
	}
	
	public Object peek() {
		return ids.peek();
	}
	
	/**
	 * Sets iasObj that will be interpreted in future as entity (simple or complex)
	 * @param iasObj
	 */
	public void markFutureEntityAsIAS(Object iasObj) {
		this.iasObj = iasObj;
	}
	
	public Object getEntityMarkedAsIAS() {
		return iasObj;
	}
	
	public void resetFutureEntityAsIAS() {
		iasObj = null;
	}
}

