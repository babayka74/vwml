package com.vw.lang.processor.context.builder;

import com.vw.lang.utils.Stack;

public class VWMLContextBuilder {
	public static class ContextInspector extends Stack.Inspector {

		private String context = "";
		
		public void inspected(Object obj) {
			String s = "." + obj + context;
			context = s;
		}

		public String getContext() {
			return context;
		}
	}
	
	private Stack stack = Stack.instance();
	
	private VWMLContextBuilder() {
		
	}
	
	public static VWMLContextBuilder instance() {
		return new VWMLContextBuilder();
	}
	
	/**
	 * Pushes IAS's entity id
	 * @param entityId
	 */
	public void push(Object entityId) {
		stack.push(entityId);
	}
	
	/**
	 * Pops IAS's entity id from stack
	 */
	public void pop() {
		stack.pop();
	}
	
	/**
	 * Actually builds context
	 * @return
	 */
	public String buildContext() {
		ContextInspector ci = new ContextInspector();
		stack.inspect(ci);
		String context = ci.getContext();
		if (context.length() > 1) {
			context = context.substring(1); // removes lead '.'
		}
		return context;
	}

}
