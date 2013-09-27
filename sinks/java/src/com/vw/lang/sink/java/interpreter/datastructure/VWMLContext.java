package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.VWMLJavaExportUtils;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */
public class VWMLContext extends VWMLObject {
	
	private String[] contextPath;
	private String context;
	
	private VWMLStack stack = VWMLStack.instance();	
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
	public String getContext() {
		return context;
	}
	
	/**
	 * Associates current stack with specific context
	 * @return
	 */
	public void setContext(Object context) {
		super.setId(context);
		super.setReadableId((String)context);
		setContextPath(VWMLJavaExportUtils.parseContext((String)context));
	}

	public void setContextPath(String[] contextPath) {
		this.contextPath = contextPath;
	}
	
	/**
	 * Returns stack's context path (parsed context)
	 * @return
	 */
	public String[] getContextPath() {
		return contextPath;
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
