package com.vw.lang.utils;


/**
 * Stack
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
		
		public boolean inspected(Object obj) throws Exception {
			return true;
		}

		public boolean popInspectedNode(Object obj) {
			return false;
		}
		
		public boolean future(Stack stack, Object obj) throws Exception {
			return false;
		}
		
		public Object toStack() {
			return null;
		}
	}
	
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
	private int depth = 0;
	private IStackVisualizer stackVisualizer = null;
	
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
		depth++;
		if (stackVisualizer != null) {
			stackVisualizer.objectPushed(data);
		}
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
		if (depth > 0) {
			depth--;
		}
		if (stackVisualizer != null) {
			stackVisualizer.objectPopped(data);
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
	
	/**
	 * Returns active stack's depth
	 * @return
	 */
	public int depth() {
		return depth;
	}
	
	/**
	 * Inspects stack's content by calling inspector for each node; the stack's top is not changed
	 * @param inspector
	 */
	public void inspect(Stack.Inspector inspector) throws Exception {
		Node t = top;
		while (t.getNext() != null) {
			if (!inspector.inspected(t.getData())) {
				t = t.getNext();
				if (t != null) {
					if (!inspector.future(this, t.getData())) {
						break;
					}
				}
				else {
					break;
				}
			}
			else {
				t = t.getNext();
			}
			if (inspector.popInspectedNode(t)) {
				pop();
			}
		}
	}

	public IStackVisualizer getStackVisualizer() {
		return stackVisualizer;
	}

	public void setStackVisualizer(IStackVisualizer stackVisualizer) {
		this.stackVisualizer = stackVisualizer;
	}
}
