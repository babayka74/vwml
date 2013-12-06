package com.vw.lang.sink.java.interpreter.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vw.lang.sink.java.VWMLContextsRepository;
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
	
	private VWMLStack stack = null;	
	// processed contexts placed here; used only if current context belongs to lifeterm
	// we need it in order to restore entity's context after 'assemble' operation;
	// context is pushed in the same time as 'empty mark' is pushed to operational stack
	private VWMLStack contextsStack = null;	
	// used during main stack unwinding operation
	private VWMLStack recurseStack = null;
	// used in order to emulate recursion; aka code stack frame
	private VWMLStack codeStack = null;
	// next entity which will be processed after decomposition process
	private VWMLEntity nextProcessedEntity = null;
	// current stack frame
	private VWMLSequentialTermInterpreterCodeStackFrame currentCodeStackFrame = null;
	// reflects entity marked as observable to current top of stack; used during recursion detection and stack unwinding
	private Map<VWMLEntity, VWMLEntity> entitiesMarkedAsObservable = null;
	// stores entities which are marked as recursive; used for recursion detection and stack unwinding
	private Map<VWMLEntity, VWMLEntity> entitiesMarkedAsRecursive = null;
	// contains entity's dynamic properties which can't be stored inside VWMLEntity since entity can be interpreted by simultaneously
	private Map<VWMLEntity, VWMLDynamicEntityProperties> entityDynamicProperties = null;
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
	 * Returns 'true' in case if context identified by contextId is child of parent context
	 * @param parent
	 * @param contextId
	 * @return
	 */
	public static boolean isContextChildOf(String parent, String contextId) {
		boolean r = false;
		if ((contextId.intern() == VWMLContextsRepository.getDefaultContextId() || parent.intern() == VWMLContextsRepository.getDefaultContextId()) ||
		   (contextId.equals(parent)) || 
		   (contextId.startsWith(parent + "."))) {
			r = true;
		}
		return r;
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
		super.setReadableId((String)context);
		this.context = (String)context;
		setContextPath(VWMLJavaExportUtils.parseContext((String)context));
		if (getContextPath() != null) {
			super.setId(getContextPath()[getContextPath().length - 1]);
		}
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
		if (stack == null) {
			stack = VWMLStack.instance();
		}
		return stack;
	}

	public boolean isLifeTermContext() {
		return lifeTermContext;
	}

	public void setLifeTermContext(boolean lifeTermContext) {
		this.lifeTermContext = lifeTermContext;
	}

	public VWMLEntity getNextProcessedEntity() {
		return nextProcessedEntity;
	}

	public void setNextProcessedEntity(VWMLEntity nextProcessedEntity) {
		this.nextProcessedEntity = nextProcessedEntity;
	}

	public VWMLSequentialTermInterpreterCodeStackFrame getCurrentCodeStackFrame() {
		return currentCodeStackFrame;
	}

	public void setCurrentCodeStackFrame(VWMLSequentialTermInterpreterCodeStackFrame currentCodeStackFrame) {
		this.currentCodeStackFrame = currentCodeStackFrame;
	}

	/**
	 * Returns dynamic properties associated with given entity
	 * @param entity
	 * @param acquire
	 * @return
	 */
	public VWMLDynamicEntityProperties getEntityDynamicProperties(VWMLEntity entity, boolean acquire) {
		VWMLDynamicEntityProperties dynProps = getEntityDynamicProperties().get(entity);
		if (dynProps == null && acquire) {
			dynProps = new VWMLDynamicEntityProperties();
			getEntityDynamicProperties().put(entity, dynProps);
		}
		return dynProps;
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
	 * Looks up for context's source lifeterm
	 * @return
	 */
	public VWMLEntity findSourceLifeTerm() {
		VWMLEntity lfTerm = null;
		for(VWMLEntity e : associatedEntities) {
			if (e.isLifeTermAsSource()) {
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
			getContextsStack().push(context);
		}
	}
	
	public VWMLContext peekContext() {
		VWMLContext c = null;
		if (isLifeTermContext()) {
			c = (VWMLContext)getContextsStack().peek();
		}
		return c;
	}
	
	public void popContext() {
		if (isLifeTermContext()) {
			getContextsStack().pop();
		}
	}
	
	/**
	 * Following methods used during main stack unwinding operation
	 * @param entity
	 */
	public void pushRecurseEntity(VWMLEntity entity) {
		getRecurseStack().push(entity);
	}
	
	public void popRecurseEntity() {
		getRecurseStack().pop();
	}

	public VWMLEntity peekRecurseEntity() {
		return (VWMLEntity)getRecurseStack().peek();
	}
	
	/**
	 * Following methods used during code stack operation
	 * @param entity
	 */
	public void pushStackFrame(VWMLSequentialTermInterpreterCodeStackFrame frame) {
		getCodeStack().push(frame);
	}
	
	public void popStackFrame() {
		getCodeStack().pop();
	}

	public VWMLObject peekStackFrame() {
		return getCodeStack().peek();
	}
	
	/**
	 * Set of methods which are used in order to manage entities inside the context
	 * @param entity
	 */
	public void associateEntity(VWMLEntity entity) {
		associatedEntities.add(entity);
	}
	
	public VWMLEntity[] getAsStorage() {
		VWMLEntity[] entities = new VWMLEntity[associatedEntities.size()];
		return associatedEntities.toArray(entities);
	}
	
	public void unAssociateEntity(VWMLEntity entity) {
		associatedEntities.remove(entity);
	}
	
	public Set<VWMLEntity> getAssociatedEntities() {
		return associatedEntities;
	}
	
	public void removeAllAssociatedEntities() {
		associatedEntities.clear();
	}

	/**
	 * Following methods manage entity recursive property
	 * @param entity
	 */
	public void markEntityAsRecursiveInsideContext(VWMLEntity entity) {
		VWMLEntity e = (VWMLEntity)this.getStack().peek();
		if (e == null) {
			e = new VWMLEntity(); // fake object
		}
		getEntitiesMarkedAsRecursive().put(entity, e);
	}
	
	public boolean isEntityMarkedAsRecursiveInsideContext(VWMLEntity entity) {
		return getEntitiesMarkedAsRecursive().containsKey(entity);
	}
	
	public void unmarkEntityAsRecursiveInsideContext(VWMLEntity entity) {
		getEntitiesMarkedAsRecursive().remove(entity);
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
		getEntitiesMarkedAsObservable().put(entity, e);
	}
	
	public boolean isEntityMarkedAsObservableInsideContext(VWMLEntity entity) {
		return getEntitiesMarkedAsObservable().containsKey(entity);
	}
	
	public void unmarkEntityAsObservableInsideContext(VWMLEntity entity) {
		getEntitiesMarkedAsObservable().remove(entity);
	}
	
	public VWMLEntity getTopOfStackWhenEntityWasMarkedAsObservable(VWMLEntity entity) {
		return getEntitiesMarkedAsObservable().get(entity);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VWMLContext other = (VWMLContext) obj;
		if (context == null) {
			if (other.context != null) {
				return false;
			}
		} else if (!context.equals(other.context)) {
			return false;
		}
		return true;
	}

	protected VWMLStack getContextsStack() {
		if (contextsStack == null) {
			contextsStack = VWMLStack.instance();
		}
		return contextsStack;
	}

	protected VWMLStack getCodeStack() {
		if (codeStack == null) {
			codeStack = VWMLStack.instance();
		}
		return codeStack;
	}

	protected VWMLStack getRecurseStack() {
		if (recurseStack == null) {
			recurseStack = VWMLStack.instance();
		}
		return recurseStack;
	}

	protected Map<VWMLEntity, VWMLEntity> getEntitiesMarkedAsObservable() {
		if (entitiesMarkedAsObservable == null) {
			entitiesMarkedAsObservable = new HashMap<VWMLEntity, VWMLEntity>();
		}
		return entitiesMarkedAsObservable;
	}

	protected Map<VWMLEntity, VWMLEntity> getEntitiesMarkedAsRecursive() {
		if (entitiesMarkedAsRecursive == null) {
			entitiesMarkedAsRecursive = new HashMap<VWMLEntity, VWMLEntity>();
		}
		return entitiesMarkedAsRecursive;
	}

	protected Map<VWMLEntity, VWMLDynamicEntityProperties> getEntityDynamicProperties() {
		if (entityDynamicProperties == null) {
			entityDynamicProperties = new HashMap<VWMLEntity, VWMLDynamicEntityProperties>();
		}
		return entityDynamicProperties;
	}
}
