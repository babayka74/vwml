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
	
	/**
	 * entity - entity relations
	 * @author ogibayev
	 *
	 */
	public static enum REL {
		ASSOCIATION,
		LINK,
		NONE
	}
	
	/**
	 * Defines entity relation
	 * @author ogibayev
	 *
	 */
	public static class Relation {
		private Object obj;
		private REL relation;
		private Object lastLink;

		public static Relation build(Object obj, REL relation, Object lastLink) {
			return new Relation(obj, relation, lastLink);
		}
		
		private Relation(Object obj, REL relation, Object lastLink) {
			super();
			this.obj = obj;
			this.relation = relation;
			this.lastLink = lastLink;
		}

		public Object getObj() {
			return obj;
		}
		
		public REL getRelation() {
			return relation;
		}

		public Object getLastLink() {
			return lastLink;
		}
		
		@Override
		public String toString() {
			return "Relation [obj=" + obj + ", relation=" + relation + ", lastLink=" + lastLink + "]";
		}
	}
	
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

