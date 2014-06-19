package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.utils.Stack;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */

public class VWMLStack {
	public static String s_specialMark = "__empty__entity__";
	
	/**
	 * Usually is used by caller in case if stack's content should be inspected
	 * @author ogibayev
	 *
	 */
	public static class VWMLStackInspector extends Stack.Inspector {
		
		@Override
		public boolean inspected(Object obj) throws Exception {
			return true;
		}
	}
	
	public static class VWMLInternalStackInspector extends Stack.Inspector {
		
		private Stack stack = Stack.instance();
		
		@Override
		public boolean inspected(Object obj) throws Exception {
			stack.push(obj);
			return true;
		}
	}
	
	private Stack stack = Stack.instance();
	private Stack internalStack = Stack.instance();

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
	public void inspect(VWMLStack.VWMLStackInspector inspector) throws Exception {
		stack.inspect(inspector);
	}
	
	/**
	 * Returns bottom element
	 * @return
	 */
	public VWMLObject bottom() {
		return (VWMLObject)stack.bottom();
	}
	
	public void pushEmptyMark() {
		VWMLEntity e = new VWMLEntity(s_specialMark, s_specialMark, null);
		stack.push(e);
	}
	
	public boolean popEmptyMark() {
		VWMLObject o = pop();
		return (o != null && o.getId() != s_specialMark);
	}
	
	public void consumeEmptyMark() {
		VWMLObject o = peek();
		while (o != null && o.getId() != s_specialMark) {
			internalStack.push(o);
			pop();
			o = peek();
		}
		if (o != null && o.getId() == s_specialMark) {
			popEmptyMark();
		}
		while(internalStack.peek() != null) {
			push((VWMLObject)internalStack.peek());
			internalStack.pop();
		}
	}
	
	public void popUntilEmptyMark() {
		VWMLObject o = peek();
		while (o != null && o.getId() != s_specialMark) {
			pop();
			o = peek();
		}
	}
	
	public void unwindTill(VWMLObject obj) {
		VWMLObject o = peek();
		while (o != null && obj != o) {
			pop();
			o = peek();
		}
	}
	
	public void printStack() {
		VWMLObject o = peek();
		while (o != null) {
			System.out.println(((VWMLEntity)o).getId());
			pop();
			o = peek();
		}
	}
}
