package com.vw.lang.sink.java.interpreter.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
import com.vw.lang.sink.java.VWMLJavaExportUtils;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Stack; interpreter's data structure
 * @author ogibayev
 *
 */
public class VWMLContext extends VWMLObject {

	public static String same_context = ".";
	
	private static String contexts_fake_object = "__fake_object__";
	
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
	// in case if context is cloned from another context this field is set
	private VWMLContext clonedFrom = null;
	// set to 'true' in case if context belongs to lifeterm
	private boolean lifeTermContext = false;
	private boolean unwinding = false;
	// associated entities
	private Set<VWMLEntity> associatedEntities = null;
	private String contextName;
	private int entityInterpretationHistorySize = 0;
	private AbstractVWMLLinkVisitor linkOperationVisitor = null;
	
	private VWMLContext(Object hashId) {
		super(hashId);
		init();
	}
	
	/**
	 * Simple stack's factory
	 * @return
	 */
	public static VWMLContext instance(Object hashId) {
		return new VWMLContext(hashId);
	}
	
	/**
	 * Builds new context by copying context's name and parsed value only
	 * @param proto
	 * @return
	 */
	public static VWMLContext lazyClone(VWMLContext proto) {
		VWMLContext c = instance(proto.getHashId());
		c.setContext(proto.getContext());
		c.setContextName(proto.getContextName());
		c.setContextPath(proto.getContextPath());
		return c;
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
	 * Returns 'true' in case if context identified by contextId is child of parent context
	 * @param cPair
	 * @param contextId
	 * @return
	 */
	public static boolean isContextChildOf(ContextIdPair cPair, String contextId) {
		if (contextId.equals(VWMLContextsRepository.getDefaultContextId())) {
			return false;
		}
		String[] ctxs = {cPair.getOrigContextId(), cPair.getEffectiveContextId()};
		for(String ctx : ctxs) {
			if (ctx != null) {
				if (VWMLContext.isContextChildOf(ctx, contextId)) {
					return true; // should be cloned
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns relative context path
	 * @param parent
	 * @param contextId
	 * @return
	 */
	public static String getRelContextPath(String parent, String contextId) {
		String p = parent + ".";
		if ((contextId.intern() == VWMLContextsRepository.getDefaultContextId() || parent.intern() == VWMLContextsRepository.getDefaultContextId())) {
			return null;
		}
		if (contextId.equals(parent)) {
			return same_context;
		}
		if (contextId.startsWith(p)) {
			return contextId.substring(p.length());
		}
		return null;
	}

	/**
	 * Returns common contexts path
	 * @param ctx1
	 * @param ctx2
	 * @return
	 */
	public static String getCommonContextPath(String ctx1, String ctx2) {
		if ((ctx1.intern() == VWMLContextsRepository.getDefaultContextId() || ctx1.intern() == VWMLContextsRepository.getDefaultContextId())) {
			return null;
		}
		if ((ctx2.intern() == VWMLContextsRepository.getDefaultContextId() || ctx2.intern() == VWMLContextsRepository.getDefaultContextId())) {
			return null;
		}
		if (ctx1.equals(ctx2)) {
			return ctx1;
		}
		String[] p1 = VWMLJavaExportUtils.parseContext(ctx1);
		String[] p2 = VWMLJavaExportUtils.parseContext(ctx2);
		return getCommonContextPath(p1, p2);
	}

	/**
	 * Returns common contexts path
	 * @param ctx1
	 * @param ctx2
	 * @return
	 */
	public static String getCommonContextPath(String[] ctx1, String[] ctx2) {
		int l1 = ctx1.length;
		int l2 = ctx2.length;
		int sF = l1;
		if (l1 > l2) {
			sF = l2;
		}
		String c = null;
		for(int i = 0; i < sF && ctx1[i].equals(ctx2[i]); i++) {
			if (c == null) {
				c = new String(ctx1[i]);
			}
			else {
				c += "." + ctx1[i];
			}
		}
		return c;
	}
	
	/**
	 * Returns context (as string) by parsing it and fetching sub-context by specified depth
	 * @param context
	 * @param depth
	 * @return
	 */
	public static String getSubContextAsString(String context, int depth) {
		String[] p = VWMLJavaExportUtils.parseContext(context);
		if (p == null || p.length <= depth) {
			return context;
		}
		String c = null;
		for(int i = 0; i < depth; i++) {
			if (c == null) {
				c = new String(p[i]);
			}
			else {
				c += "." + p[i];
			}
		}
		return c;
	}
	
	public static boolean isDynamicContextPointsToSelf(String dynContext) {
		return dynContext.equals("$$");
	}
	
	public static String changeSelfAddressedDynamicContextNameTo(String dynContext, String toContext) {
		return dynContext.replaceFirst("\\$\\$", toContext); 
	}
	/**
	 * Constructs context name in run-time; used when dynamic context name is generated (see OPDYNCONTEXT)
	 * @param contextNameBuf
	 * @param part
	 * @return
	 */
	public static String constructContextNameInRunTime(String contextNameBuf, VWMLEntity e) {
		String part = e.getReadableId();
		if (e.getReadableId() == null) {
			e.buildReadableId();
		}
		if (!VWMLContext.isDynamicContextPointsToSelf(e.getReadableId())) {
			part = ((e.getContext() != null) ? (e.getContext().getContext() + ".") : "") + e.getReadableId();
		}
		if (contextNameBuf != null) {
			return constructContextNameFromParts(contextNameBuf, part);
		}
		return part;
	}

	/**
	 * Constructs context from two parts
	 * @param contextPrefix
	 * @param contextSuffix
	 * @return
	 */
	public static String constructContextNameFromParts(String contextPrefix, String contextSuffix) {
		String c = contextPrefix;
		if (!contextSuffix.equals(same_context)) {
			c = contextPrefix + "." + contextSuffix;
		}
		return c;
	}
	
	/**
	 * Returns context's first cloned context on its path, if exists
	 * @return
	 */
	public VWMLContext getFirstCloned() {
		VWMLContext p = this, r = null;
		while (p != null) {
			r = p;
			if (p.getClonedFrom() != null) {
				break;
			}
			p = (VWMLContext)p.getLink().getParent();
		}
		return r;
	}
	
	/**
	 * Clears context's resources
	 */
	public void clear() {
		VWMLStack[] stacks = {contextsStack, stack, recurseStack, codeStack};
		for(VWMLStack stack : stacks) {
			if (stack != null) {
				stack.unwindTill(null);
			}
		}
		contextsStack = stack = recurseStack = codeStack = null;
		nextProcessedEntity = null;
		currentCodeStackFrame = null;
		Map<?, ?>[] containers = {entitiesMarkedAsObservable, entitiesMarkedAsRecursive, entityDynamicProperties};
		for(Map<?, ?> container : containers) {
			if (container != null) {
				container.clear();
			}
		}
		removeAllAssociatedEntities();		
		entitiesMarkedAsObservable = null;
		entitiesMarkedAsRecursive = null;
		entityDynamicProperties = null;
		lifeTermContext = false;
		unwinding = false;
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

	public VWMLContext getClonedFrom() {
		return clonedFrom;
	}

	public void setClonedFrom(VWMLContext clonedFrom) {
		this.clonedFrom = clonedFrom;
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
	
	public VWMLObject peekParentEntity() {
		VWMLObject obj = null;
		VWMLSequentialTermInterpreterCodeStackFrame frame = (VWMLSequentialTermInterpreterCodeStackFrame)peekStackFrame();
		if (frame != null) {
			obj = frame.getAssociatedEntity();
		}
		return obj;
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
		for(VWMLEntity e : associatedEntities) {
			VWMLObjectsRepository.instance().removeWithoutContextCleaning(e);
		}
		associatedEntities.clear();
	}

	/**
	 * Following methods manage entity recursive property
	 * @param entity
	 */
	public void markEntityAsRecursiveInsideContext(VWMLEntity entity) {
		VWMLEntity e = (VWMLEntity)this.getStack().peek();
		if (e == null) {
			e = new VWMLEntity(contexts_fake_object); // fake object
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
			e = new VWMLEntity(contexts_fake_object); // fake object
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

	protected void init() {
		associatedEntities = VWMLResourceHostManagerFactory.hostManagerInstance().requestEntityAssociatedSet();
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
			entitiesMarkedAsObservable = VWMLResourceHostManagerFactory.hostManagerInstance().requestEntityAssociatedContainer();
		}
		return entitiesMarkedAsObservable;
	}

	protected Map<VWMLEntity, VWMLEntity> getEntitiesMarkedAsRecursive() {
		if (entitiesMarkedAsRecursive == null) {
			entitiesMarkedAsRecursive = VWMLResourceHostManagerFactory.hostManagerInstance().requestEntityAssociatedContainer();
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
