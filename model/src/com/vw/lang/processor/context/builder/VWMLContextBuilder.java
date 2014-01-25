package com.vw.lang.processor.context.builder;

import java.util.ArrayList;
import java.util.List;

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

	public static class ContextBunchElement {
		private Object id;

		private ContextBunchElement(Object id) {
			this.id = id;
		}
		
		public static ContextBunchElement build(Object id) {
			return new ContextBunchElement(id);
		}
		
		public Object getId() {
			return id;
		}

		public void setId(Object id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "ContextBunchElement [id=" + id + "]";
		}
	}
	
	public static class IteratableContainer<T> {
		private int it = 0;
		private List<T> container = new ArrayList<T>();

		protected IteratableContainer() {
		}
		
		public void add(T e) {
			container.add(e);
		}
		
		public T next() {
			T e = null;
			if (container.size() != 0 && container.size() > it) {
				e = container.get(it);
				it++;
			}
			return e;
		}

		public void resetIterator() {
			it = 0;
		}
		
		public void clear() {
			container.clear();
		}

		@Override
		public String toString() {
			return "IteratableContainer [container=" + container + "]";
		}
		
		protected List<T> getContainer() {
			return container;
		}
	}

	public static class Contexts extends IteratableContainer<String> {
		
		private Contexts() {
		}
		
		public static Contexts instance() {
			return new Contexts();
		}

		@Override
		public String toString() {
			return "Contexts [getContainer()=" + getContainer().toString() + "]";
		}
	}
	
	public static class ContextBunch extends IteratableContainer<ContextBunchElement> {
		
		private Contexts contexts = Contexts.instance();
		
		private ContextBunch() {
		}
		
		public static ContextBunch instance() {
			return new ContextBunch();
		}
		
		public void addContext(String context) {
			contexts.add(context);
		}
		
		public Contexts getContexts() {
			return contexts;
		}

		@Override
		public String toString() {
			return "ContextBunch [contexts=" + contexts + "]";
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
	 * Pushes IAS's bunch
	 * @param entityId
	 */
	public void push(Object entityId) {
		ContextBunch child = (ContextBunch)entityId;
		ContextBunch parent = (ContextBunch)stack.peek();
		if (parent != null) {
			Contexts contexts = parent.getContexts();
			contexts.resetIterator();
			for(String c = contexts.next(); c != null; c = contexts.next()) {
				for(ContextBunchElement e = child.next(); e != null; e = child.next()) {
					child.getContexts().add(c + "." + (String)e.getId());
				}
			}
			contexts.resetIterator();
		}
		else {
			for(ContextBunchElement e = child.next(); e != null; e = child.next()) {
				child.getContexts().add((String)e.getId());
			}
		}
		child.resetIterator();
		stack.push(child);
	}
	
	/**
	 * Peeks IAS's bunch
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
	public Contexts buildContext() {
		Contexts contexts = null;
		ContextBunch cb = (ContextBunch)stack.peek();
		if (cb != null) {
			contexts = cb.getContexts();
		}
		else {
			contexts = Contexts.instance();
			contexts.add("");
		}
		contexts.resetIterator();
		return contexts;
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
