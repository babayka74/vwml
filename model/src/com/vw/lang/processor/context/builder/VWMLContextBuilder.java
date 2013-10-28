package com.vw.lang.processor.context.builder;

import com.vw.lang.utils.Stack;

public class VWMLContextBuilder {
	public static class ContextInspector extends Stack.Inspector {

		private String context = "";
		
		public boolean inspected(Object obj) {
			String s = "." + obj + context;
			context = s;
			return true;
		}

		public String getContext() {
			return context;
		}
	}
	
	private Stack stack = Stack.instance();
	private String effectiveContext = null;
	
	private VWMLContextBuilder() {
		
	}
	
	public static VWMLContextBuilder instance() {
		return new VWMLContextBuilder();
	}

	/**
	 * Returns 'true' in case if effective context detected; effective context is marked by tailed '.'
	 * @param context
	 * @return
	 */
	public static boolean isEffectiveContext(String context) {
		boolean r = false;
		if (context != null) {
			r = context.endsWith(".");
		}
		return r;
	}

	/**
	 * Normalizes context by removing trailed '.' if exists
	 * @param context
	 * @return
	 */
	public static String normalizeContext(String context) {
		if (isEffectiveContext(context)) {
			context = context.substring(0, context.length() - 1);
		}
		return context;
	}
	
	/**
	 * Builds full entity name 
	 * @param context
	 * @param entityId
	 * @return
	 */
	public static String buildFullEntityName(String context, String entityId) {
		return normalizeContext(context) + "." + entityId;
	}
	
	/**
	 * Pushes IAS's entity id
	 * @param entityId
	 */
	public void push(Object entityId) {
		stack.push(entityId);
	}
	
	/**
	 * Peeks IAS's entity id
	 * @return
	 */
	public Object peek() {
		return stack.peek();
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
	
	/**
	 * Sets effective context
	 * @param context
	 */
	public void addEffectiveContext(String context) {
		if (isEffectiveContext(context)) {
			context = context.substring(0, context.length() - 1);
		}
		if (effectiveContext != null) {
			effectiveContext += "." + context;
		}
		else {
			effectiveContext = context;
		}
	}
	
	public String getEffectiveContext() {
		return effectiveContext;
	}
	
	public boolean isEffectiveContextUsed() {
		return (effectiveContext != null) ? true : false;
	}
	
	public void resetEffectiveContext() {
		effectiveContext = null;
	}
	
	public String createAbsoluteEffectiveContextPath(Object id) {
		return getEffectiveContext() + "." + id;
	}
}
