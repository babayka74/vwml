package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.utils.Stack;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */
public class VWMLStack {
	
	/**
	 * Usually is used by caller in case if stack's content should be inspected
	 * @author ogibayev
	 *
	 */
	public static class VWMLStackInspector extends Stack.Inspector {
		
		@Override
		public void inspected(Object obj) {
			
		}
	}
	
	private Stack stack = Stack.instance();
	
	private VWMLStack() {
		
	}
	
	/**
	 * Simple stack's factory
	 * @return
	 */
	public static VWMLStack instance() {
		return new VWMLStack();
	}
	
	/**
	 * Pushes data to stack
	 * @param data
	 */
	public void push(VWMLObject data) {
		stack.push(data);
	}
	
	/**
	 * Pops data from stack
	 * @return
	 */
	public VWMLObject pop() {
		return (VWMLObject)stack.pop();
	}
	
	/**
	 * Peeks data from top of stack
	 * @return
	 */
	public VWMLObject peek() {
		return (VWMLObject)stack.peek();
	}
	
	/**
	 * Inspects stack's content by calling inspector for each node; the stack's top is not changed
	 * @param inspector
	 */
	public void inspect(VWMLStack.VWMLStackInspector inspector) {
		stack.inspect(inspector);
	}
}
