package com.vw.lang.beyond.java.fringe.entity;

import java.util.UUID;

/**
 * Builds entities related to external world (EW)
 * @author Oleg
 *
 */
public class EWEntityBuilder {
	
	public static class Stack {
		
		protected static class Node {
			private Object data;
			private Node next;
			
			public Node() {
				super();
			}

			public Node(Object data, Node next) {
				super();
				this.data = data;
				this.next = next;
			}

			public Object getData() {
				return data;
			}

			public void setData(Object data) {
				this.data = data;
			}

			public Node getNext() {
				return next;
			}

			public void setNext(Node next) {
				this.next = next;
			}
		}
		
		private Node top = new Node();
		
		private Stack() {
			
		}
		
		/**
		 * Simple stack's factory
		 * @return
		 */
		public static Stack instance() {
			return new Stack();
		}
		
		/**
		 * Pushes data to stack
		 * @param data
		 */
		public void push(Object data) {
			Node n = new Node(data, top);
			top = n;
		}
		
		/**
		 * Pops data from stack
		 * @return
		 */
		public Object pop() {
			Object data = top.getData();
			Node n = top.getNext();
			if (n != null) {
				top = n;
			}
			return data;
		}
		
		/**
		 * Peeks data from top of stack
		 * @return
		 */
		public Object peek() {
			return top.getData();
		}
	}
	
	
	/**
	 * Builds simple external world entity
	 * @param eId
	 * @param context
	 * @return
	 */
	public static EWEntity buildSimpleEntity(Object eId, String context) {
		return new EWEntity(eId, context);
	}

	/**
	 * Builds complex external world entity
	 * @param eId
	 * @param context
	 * @return
	 */
	public static EWComplexEntity buildComplexEntity(Object eId, String context) {
		return new EWComplexEntity(eId, context);
	}
	
	
	public static EWEntity buildFromString(String data) throws Exception {
		if (data == null) {
			throw new Exception("invalid argument; data can't be a 'null'");
		}
		data = data.trim();
		if (data.indexOf('(') == -1 && data.indexOf(' ') != -1) {
			throw new Exception("invalid syntax, use complex entity");
		}
		Stack stack = Stack.instance();
		EWComplexEntity ce = null;
		EWEntity lastProcessedEntity = null;
		int l = data.length();
		int ch = 0, lwr = -1;
		for(int i = 0; i < l; i++) {
			ch = data.charAt(i);
			if (ch == '(') {
				if (lwr != -1) {
					lastProcessedEntity = buildSimpleEntityAndLink((EWComplexEntity)stack.peek(), data, lwr, i);
					lwr = -1;
				}
				// starts complex entity
				stack.push(buildComplexEntity(UUID.randomUUID().toString(), null));
				ce = (EWComplexEntity)stack.peek();
				continue;
			}
			else
			if (ch == ')') {
				if (lwr != -1) {
					lastProcessedEntity = buildSimpleEntityAndLink((EWComplexEntity)stack.peek(), data, lwr, i);
					lwr = -1;
				}
				// pops created entity
				lastProcessedEntity = (EWEntity)stack.peek();
				stack.pop();
				ce = (EWComplexEntity)stack.peek();
				if (ce != null) {
					ce.link(lastProcessedEntity);
				}
				continue;
			}
			else
			if (ch <= 0x20 || ch >= 0x100) {
				if (lwr != -1) {
					lastProcessedEntity = buildSimpleEntityAndLink((EWComplexEntity)stack.peek(), data, lwr, i);
					lwr = -1;
				}
				continue; // swallow
			}
			if (lwr == -1) {
				lwr = i;
			}
		}
		if (stack.peek() != null) {
			throw new Exception("invalid syntax; the ')' is absent");
		}
		if (lwr != -1) {
			lastProcessedEntity = buildSimpleEntityAndLink(ce, data, lwr, l);
			if (ce != null) {
				lastProcessedEntity = ce;
			}
		}
		lwr = -1;
		return lastProcessedEntity;
	}
	
	private static EWEntity buildSimpleEntityAndLink(EWComplexEntity ce, String data, int lr, int rr) {
		EWEntity e = null;
		String entityId = data.substring(lr, rr);
		e = buildSimpleEntity(entityId, null);
		if (ce != null) {
			ce.getLink().link(e);
		}
		return e;
	}
}
