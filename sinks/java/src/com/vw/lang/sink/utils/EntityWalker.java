package com.vw.lang.sink.utils;

import java.util.Deque;
import java.util.Iterator;
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
		if (ids.isEmpty()) {
			return null;
		}
		return ids.pop();
	}
	
	public Object peek() {
		return ids.peek();
	}
	
	public Iterator<Object> getListIterator() {
		return ids.descendingIterator();
	}
	
	public Iterator<Object> getQueueIterator() {
		return ids.iterator();
	}
	
	public void clear() {
		ids.clear();
	}
	
	public int size() {
		return ids.size();
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

