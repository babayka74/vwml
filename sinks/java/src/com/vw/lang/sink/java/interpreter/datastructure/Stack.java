package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.VWMLObject;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */
public class Stack {
	
	/**
	 * Usually is used by caller in case if stack's content should be inspected
	 * @author ogibayev
	 *
	 */
	public static class Inspector {
		public void inspected(VWMLObject obj) {
			
		}
	}
	
	protected static class Node {
		private VWMLObject data;
		private Node next;
		
		public Node() {
			super();
		}

		public Node(VWMLObject data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}

		public VWMLObject getData() {
			return data;
		}

		public void setData(VWMLObject data) {
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
	public void push(VWMLObject data) {
		Node n = new Node(data, top);
		top = n;
	}
	
	/**
	 * Pops data from stack
	 * @return
	 */
	public VWMLObject pop() {
		VWMLObject data = top.getData();
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
	public VWMLObject peek() {
		return top.getData();
	}
	
	/**
	 * Inspects stack's content by calling inspector for each node; the stack's top is not changed
	 * @param inspector
	 */
	public void inspect(Stack.Inspector inspector) {
		Node t = top;
		while (t.getNext() != null) {
			inspector.inspected(t.getData());
			t = t.getNext();
		}
	}
}
