package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */
public class VWMLContext {
	
	
	private VWMLStack stack = VWMLStack.instance();
	private Object context = null;
	private String[] contextPath = null;
	private int entityInterpretationHistorySize = 0;
	private AbstractVWMLLinkVisitor linkOperationVisitor = null;
	
	private VWMLContext() {
		
	}
	
	/**
	 * Simple stack's factory
	 * @return
	 */
	public static VWMLContext instance() {
		return new VWMLContext();
	}
	

	/**
	 * Returns stack's associated context
	 * @return
	 */
	public Object getContext() {
		return context;
	}

	/**
	 * Associates current stack with specific context
	 * @return
	 */
	public void setContext(Object context) {
		this.context = context;
	}

	/**
	 * Returns stack's context path (parsed context)
	 * @return
	 */
	public String[] getContextPath() {
		return contextPath;
	}

	/**
	 * Sets stack's context path (parsed context)
	 * @param contextPath
	 */
	public void setContextPath(String[] contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * Returns stack's entity interpretation size	
	 * @return
	 */
	public int getEntityInterpretationHistorySize() {
		return entityInterpretationHistorySize;
	}

	/**
	 * Sets stack's entity interpretation size
	 * @param entityInterpretationHistorySize
	 */
	public void setEntityInterpretationHistorySize(int entityInterpretationHistorySize) {
		this.entityInterpretationHistorySize = entityInterpretationHistorySize;
	}

	/**
	 * Returns link operation visitor used by stack and new created entities
	 * @return
	 */
	public AbstractVWMLLinkVisitor getLinkOperationVisitor() {
		return linkOperationVisitor;
	}

	/**
	 * Sets link operation visitor which is used by stack
	 * @param linkOperationVisitor
	 */
	public void setLinkOperationVisitor(AbstractVWMLLinkVisitor linkOperationVisitor) {
		this.linkOperationVisitor = linkOperationVisitor;
	}

	public VWMLStack getStack() {
		return stack;
	}
}
