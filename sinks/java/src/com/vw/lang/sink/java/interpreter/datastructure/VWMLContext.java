package com.vw.lang.sink.java.interpreter.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vw.lang.sink.java.VWMLJavaExportUtils;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
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
	// processed contexts placed here; used only if current context belongs to lifeterm
	// we need it in order to restore entity's context after 'assemble' operation;
	// context is pushed in the same time as 'empty mark' is pushed to operational stack
	private VWMLStack contextsStack = VWMLStack.instance();	
	// used during main stack unwinding operation
	private VWMLStack recurseStack = VWMLStack.instance();
	// reflects entity marked as observable to current top of stack; used during recursion detection and stack unwinding
	private Map<VWMLEntity, VWMLEntity> entitiesMarkedAsObservable = new HashMap<VWMLEntity, VWMLEntity>();
	// set to 'true' in case if context belongs to lifeterm
	private boolean lifeTermContext = false;
	private boolean unwinding = false;
	// associated entities
	private Set<VWMLEntity> associatedEntities = new HashSet<VWMLEntity>();
	private String contextName;
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
		this.context = (String)context;
		setContextPath(VWMLJavaExportUtils.parseContext((String)context));
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
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

	public boolean isLifeTermContext() {
		return lifeTermContext;
	}

	public void setLifeTermContext(boolean lifeTermContext) {
		this.lifeTermContext = lifeTermContext;
	}
	
	/**
	 * Looks up for context's lifeterm
	 * @return
	 */
	public VWMLEntity findLifeTerm() {
		VWMLEntity lfTerm = null;
		for(VWMLEntity e : associatedEntities) {
			if (e.isLifeTerm()) {
				lfTerm = e;
				break;
			}
		}
		return lfTerm;
	}
	
	/**
	 * Following methods are Push/Peek/Pop context to/from special internal stack (used in order to restore entity)
	 * @param context
	 */
	public void pushContext(VWMLContext context) {
		if (isLifeTermContext()) {
			contextsStack.push(context);
		}
	}
	
	public VWMLContext peekContext() {
		VWMLContext c = null;
		if (isLifeTermContext()) {
			c = (VWMLContext)contextsStack.peek();
		}
		return c;
	}
	
	public void popContext() {
		if (isLifeTermContext()) {
			contextsStack.pop();
		}
	}
	
	/**
	 * Following methods used during main stack unwinding operation
	 * @param entity
	 */
	public void pushRecurseEntity(VWMLEntity entity) {
		recurseStack.push(entity);
	}
	
	public void popRecurseEntity() {
		recurseStack.pop();
	}

	public VWMLEntity peekRecurseEntity() {
		return (VWMLEntity)recurseStack.peek();
	}
	
	/**
	 * Set of methods which are used in order to manage entities inside the context
	 * @param entity
	 */
	public void associateEntity(VWMLEntity entity) {
		associatedEntities.add(entity);
	}
	
	public VWMLEntity[] getAsStorage() {
		return (VWMLEntity[])associatedEntities.toArray();
	}
	
	public void unAssociateEntity(VWMLEntity entity) {
		associatedEntities.remove(entity);
	}
	
	/**
	 * Following methods manage entity observable property
	 * @param entity
	 */
	public void markEntityAsObservableInsideContext(VWMLEntity entity) {
		VWMLEntity e = (VWMLEntity)this.getStack().peek();
		if (e == null) {
			e = new VWMLEntity(); // fake object
		}
		entitiesMarkedAsObservable.put(entity, e);
	}
	
	public boolean isEntityMarkedAsObservableInsideContext(VWMLEntity entity) {
		return entitiesMarkedAsObservable.containsKey(entity);
	}
	
	public void unmarkEntityAsObservableInsideContext(VWMLEntity entity) {
		entitiesMarkedAsObservable.remove(entity);
	}
	
	public VWMLEntity getTopOfStackWhenEntityWasMarkedAsObservable(VWMLEntity entity) {
		return entitiesMarkedAsObservable.get(entity);
	}
	
	public void unwindStackTill(VWMLEntity tillEntity) {
		getStack().unwindTill(tillEntity);
	}
	
	public void prepareForUnwinding() {
		unwinding = true;
	}
	
	public boolean isUnwinding() {
		return unwinding;
	}
	
	public void finishUnwinding() {
		unwinding = false;
	}
}
