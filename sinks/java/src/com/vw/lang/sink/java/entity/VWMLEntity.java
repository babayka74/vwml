package com.vw.lang.sink.java.entity;

import java.util.concurrent.atomic.AtomicBoolean;

import com.vw.lang.sink.OperationInfo;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperations;

/**
 * VWML's base entity (see language specification)
 * @author ogibayev
 *
 */
public class VWMLEntity extends VWMLObject {

	public static final String s_NilEntityId       = "nil";
	public static final String s_NullEntityId      = "null";
	public static final String s_EmptyEntityId     = "()";
	public static final String s_doNothingEntityId = "doNothing";
	public static final String s_doNothingEntityId2 = "DoNothing";
	public static final String s_doNothingEntityId3 = "donothing";	
	public static final String s_trueEntityId      = "true";
	public static final String s_falseEntityId     = "false";

	// true means that entity lives on intersection of worlds (see VWMLCreature)
	protected boolean isCreature = false;
	
	private VWMLContext context;
	// this entity is interpreted as another entity/term
	private volatile VWMLEntity originalInterpreting;
	private volatile VWMLEntity interpreting;
	private VWMLEntityInterpretationHistory interpretationHistory = new VWMLEntityInterpretationHistory();
	private VWMLOperations associatedOperations = new VWMLOperations("__associated_operation__" + this);
	private boolean isLifeTerm = false;
	private boolean isLifeTermAsSource = false;
	private boolean isMarkedAsComplexEntity = false;
	// set when operation is added to entity, but initially entity wasn't marked as term
	private boolean isMarkedAsArtificalTerm = false;
	// marked by interpreter in case when operation EXE is being applied
	private boolean isOperatesByExe = false;
	// means that entity is created during initialization phase
	private boolean isOriginal = false;
	// entity is marked as synthetic
	private boolean isSynthetic = false;
	// addressed using dynamic context in runtime (->)
	// used during interpretation phase in order to deduce active context
	private boolean isDynamicAddressedInRunTime = false;
	// entity is interpreted as argument pair (see CallP operation)
	private ArgPair asArgPair;
	private int interpretationHistorySize;
	// if entity is cloned this field is set to entity from which it was cloned 
	private VWMLEntity clonedFrom = null;
	// entity which was not declared as context is declared as recursive entity (interpreted as is) on context
	// where compiler found it, this process is called 'autodeclaration.
	// Operation ^ creates new interpreting entity, this process is called - implicit declaration.
	// So in order to use implicit declared entity on context where entity is auto-declared field resolvedInRuntime is used
	// (not cloned)
	private VWMLEntity resolvedInRuntime = null;
	// entity linked entity; used when special entity __mark__ is pushed to stack
	private VWMLEntity specialLinkedEntity = null;
	// context may consist form terms
	private boolean isPartOfDynamicContext = false;
	// setup on 'Activate' operation in case if entity is not life or source lifeterm
	private boolean activated = false;
	private boolean generatedFromStack = false;
	private boolean regeneratable = false;
	// sets during release operation in order to mark entity as removed from context and related storage
	private AtomicBoolean removed = new AtomicBoolean(false);
	private AtomicBoolean lock = new AtomicBoolean(false);
	// for debug purposes
	private Object oldId = null;
	private String oldReadableId = null;
	
	public VWMLEntity(Object hashId) {
		super(hashId);
	}

	public VWMLEntity(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
	}

	public boolean isEntityPooled() {
		String ids[] = {s_NilEntityId, s_NullEntityId, s_EmptyEntityId, s_doNothingEntityId, s_doNothingEntityId2, s_doNothingEntityId3, s_trueEntityId, s_falseEntityId};
		for(String s : ids) {
			if (getId().equals(s)) {
				return false;
			}
			String rid = getReadableId();
			if (rid != null && rid.equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void restore(Object hashId, Object id) {
		oldId = id;
		oldReadableId = getReadableId();
		super.restore(hashId, id);
		isCreature = false;
		context = null;
		originalInterpreting = null;
		interpreting = null;
		interpretationHistory.clear();
		associatedOperations.clear();
		isLifeTerm = false;
		isLifeTermAsSource = false;
		isMarkedAsComplexEntity = false;
		isMarkedAsArtificalTerm = false;
		isOperatesByExe = false;
		isOriginal = false;
		isSynthetic = false;
		isDynamicAddressedInRunTime = false;
		asArgPair = null;
		interpretationHistorySize = 0;
		clonedFrom = null;
		resolvedInRuntime = null;
		specialLinkedEntity = null;
		isPartOfDynamicContext = false;
		activated = false;
		generatedFromStack = false;
		regeneratable = false;
		removed.getAndSet(false);
	}
	
	public void resetRemovedFlag() {
		removed.getAndSet(false);
	}
	
	/**
	 * Born new entity based on current entity, the Id is changed to newId
	 * (usually used by operation 'Born' when new source lifeterm is created in runtime)
	 */
	public VWMLEntity born(VWMLContext relContext, VWMLContext onContext) throws Exception {
		return clone(relContext, onContext, true);
	}
	
	/**
	 * Clones entity
	 */
	public VWMLEntity clone(VWMLContext relContext, VWMLContext onContext) throws Exception {
		return clone(relContext, onContext, false);
	}
	
	/**
	 * More parameters for clone operations
	 * @param proto
	 * @param oldId
	 * @param newId
	 * @param context
	 * @param relContext
	 * @param auxCache
	 * @param firstIteration
	 * @param bornMode
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity clone(VWMLContext relContext, VWMLContext cloneOnContext, boolean bornMode) throws Exception {
		// check if entity is in cloned context (root level - context which is being cloned)
		if ((!VWMLContext.isContextChildOf(relContext.getContext(), getContext().getContext())) ||
			(this.getContext() == VWMLContextsRepository.instance().getDefaultContext()) || isSynthetic()) {
			return this; // no need to clone entity which doesn't belong to cloned context
		}
		// change context and creates new one
		String n = this.getContext().getContext().replace(relContext.getContext(), cloneOnContext.getContext());
		VWMLContext clonedContext = VWMLContextsRepository.instance().createContextIfNotExists(n);
		VWMLEntity alreadyCloned = clonedContext.findEntityByPrototype(this);
		if (alreadyCloned != null) {
			// System.out.println("duplicated '" + getId() + "' context '" + clonedContext.getContext() + "'");
			return alreadyCloned;
		}
		// proto is active for first iteration only
		VWMLObjectBuilder.VWMLObjectType type = deductEntityTypeByProto(this);
		VWMLEntity termAssociatedEntity = null;
		if (type == VWMLObjectBuilder.VWMLObjectType.TERM) {
			termAssociatedEntity = ((VWMLTerm)this).getOriginalAssociatedEntity();
			if (termAssociatedEntity != null && termAssociatedEntity.isSynthetic()) {
				return this;
			}
		}
		if (termAssociatedEntity != null) {
			termAssociatedEntity = termAssociatedEntity.clone(relContext,
															  cloneOnContext,
															  bornMode);	
		}
		VWMLEntity interpretingEntity = null, eIAS = null;
		boolean recursion = false;
		if (!bornMode) {
			eIAS = getOriginalInterpreting();
			if (eIAS != null) {
				recursion = eIAS.isRecursiveInterpretationOnOriginal();
			}
		}
		else {
			eIAS = getInterpreting();
			if (eIAS != null) {
				recursion = eIAS.isRecursiveInterpretationOnRuntime();
			}
		}
		if (eIAS != null && !recursion) {
			interpretingEntity = eIAS.clone(relContext,
											cloneOnContext,
											bornMode);	
		}
		else
		if (eIAS != null && recursion) {
			interpretingEntity = eIAS;
		}
		// new entity is registered on repository
		Object eId = getId();
		VWMLEntity cloned = (VWMLEntity)VWMLObjectsRepository.acquireWithoutCheckingOnExistence(type,
																	  eId,
																	  clonedContext,
																	  getInterpretationHistorySize(),
																	  VWMLObjectsRepository.asOriginal,
																	  getLink().getLinkOperationVisitor());
		// System.out.println("cloned '" + cloned.getId() + "' context '" + cloned.getContext() + "' <- '" + getContext().getContext() + "'");
		ArgPair argPair = getAsArgPair();
		if (argPair != null) {
			ArgPair clonedArgPair = new ArgPair();
			clonedArgPair.setPlaceNumber(argPair.getPlaceNumber());
			cloned.setAsArgPair(clonedArgPair);
		}
		cloned.setSynthetic(isSynthetic());
		cloned.setLifeTerm(isLifeTerm());
		cloned.setLifeTermAsSource(isLifeTermAsSource());
		cloned.setClonedFrom(this);
		cloned.setOriginal(true);
		if (termAssociatedEntity != null) {
			cloned.copyAssociatedOperations(getAssociatedOperations());
			((VWMLTerm)cloned).setAssociatedEntity(termAssociatedEntity);
		}
		cloned.setInterpreting(interpretingEntity);
		VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLEntity linked = ((VWMLEntity)getLink().getConcreteLinkedEntity(it.getIt()));
				linked = linked.clone(relContext, cloneOnContext, bornMode);
				cloned.getLink().link(linked);
			}
			cloned.setReadableId(null);
		}
		return cloned;
	}
	
	public void release(VWMLContext c) {
		if (getClonedFrom() == null || removed.get() || isSynthetic()) {
			return;
		}
		if ((!VWMLContext.isContextChildOf(c.getContext(), getContext().getContext())) ||
			(this.getContext() == VWMLContextsRepository.instance().getDefaultContext())) {
			return;
		}
		if (getClonedFrom() != null && isOriginal()) {
			removed.getAndSet(true);
			if (getOriginalInterpreting() != null) {
				if (!getOriginalInterpreting().isRecursiveInterpretationOnOriginal()) {
	//				System.out.println("interpreting release '" + getOriginalInterpreting().buildReadableId() + "'");
					getOriginalInterpreting().release(c);
				}
			}
			VWMLObjectBuilder.VWMLObjectType type = deductEntityTypeByProto(this);
			if (type == VWMLObjectBuilder.VWMLObjectType.TERM) {
				VWMLEntity et = ((VWMLTerm)this).getAssociatedEntity();
				if (et != null) {
					et.release(c);
					((VWMLTerm)this).setAssociatedEntity(null);
				}
			}
			for(VWMLObject e : getLink().getLinkedObjects()) {
				((VWMLEntity)e).release(c);
			}
	/*		
			if (buildReadableId() != null) {
				System.out.println("removed '" + buildReadableId() + "' context '" + ((context != null) ? context.getContext() : "null") + "'");
			}
	*/		
			VWMLObjectsRepository.instance().removeWithoutContextCleaning(this);
			//setReadableId("__removed__" + buildReadableId());
			//setId("__removed__");
			getLink().getLinkedObjects().clear();
			getLink().setParent(null);
			VWMLObjectBuilder.returnToPool(this);
		}
	}
	
	public boolean releaseProtocolEntity() {
		if (isOriginal() && getClonedFrom() != null) {
			return false;
		}
		if (!(isRegeneratable() && getRefCounter() == 0)) { 
			if ((removed.get() || isOriginal() || !isMarkedAsComplexEntity() || !isGeneratedFromStack() || getRefCounter() != 0 || isSynthetic() || getClonedFrom() != null || getInterpreting() != null || getContext() == VWMLContextsRepository.instance().getDefaultContext())) {
				return false;
			}
		}
		removed.getAndSet(true);
		for(VWMLObject e : getLink().getLinkedObjects()) {
			((VWMLEntity)e).releaseProtocolEntity();
		}
		VWMLObjectsRepository.instance().remove(this);
		// System.out.println(">>>>>>>>>> '" + buildReadableId() + "'");
		getLink().getLinkedObjects().clear();
		getLink().setParent(null);
		VWMLObjectBuilder.returnToPool(this);
		return true;
	}

	public boolean releaseByRefCounter(VWMLContext c) {
		if (removed.get() || isOriginal() || getRefCounter() != 0 || isSynthetic() || getClonedFrom() != null || getInterpreting() != null || getContext() == VWMLContextsRepository.instance().getDefaultContext()) {
			return false;
		}
		removed.getAndSet(true);
		for(VWMLObject e : getLink().getLinkedObjects()) {
			((VWMLEntity)e).releaseByRefCounter(c);
		}
		VWMLObjectsRepository.instance().removeWithoutContextCleaning(this);
		//System.out.println(">>>>>>>>>> '" + buildReadableId() + "'");
		getLink().getLinkedObjects().clear();
		getLink().setParent(null);
		VWMLObjectBuilder.returnToPool(this);
		return true;
	}

	@Override
	public String buildReadableId() {
		if (getReadableId() == null) {
			if (isTerm()) {
				if (((VWMLTerm)this).getAssociatedEntity() != null) {
					return ((VWMLTerm)this).getAssociatedEntity().buildReadableId();
				}
				else {
					return null; // removed term
				}
			}
			else {
				return (String)getId();
			}
		}
		return getReadableId();
	}
	
	public VWMLContext getContext() {
		return context;
	}

	public void setContext(VWMLContext context) {
		if (context != null) {
			if (this.context != null) {
				VWMLObjectsRepository.instance().remove(this);
			}
			this.context = context;
			VWMLObjectsRepository.instance().add(this);
		}
	}

	public VWMLEntity getInterpreting() {
		return interpreting;
	}

	public VWMLEntity getOriginalInterpreting() {
		return originalInterpreting;
	}

	public int getInterpretationHistorySize() {
		return interpretationHistorySize;
	}

	public void setInterpretationHistorySize(int interpretationHistorySize) {
		this.interpretationHistorySize = interpretationHistorySize;
		interpretationHistory.setMaxHistorySize(this.interpretationHistorySize);
	}

	public VWMLEntity getSpecialLinkedEntity() {
		return specialLinkedEntity;
	}

	public void setSpecialLinkedEntity(VWMLEntity specialLinkedEntity) {
		this.specialLinkedEntity = specialLinkedEntity;
	}

	public boolean isSynthetic() {
		return isSynthetic;
	}

	public void setSynthetic(boolean isSynthetic) {
		this.isSynthetic = isSynthetic;
	}

	public ArgPair getAsArgPair() {
		return asArgPair;
	}

	public void setAsArgPair(ArgPair asArgPair) {
		this.asArgPair = asArgPair;
	}

	public boolean isDynamicAddressedInRunTime() {
		return isDynamicAddressedInRunTime;
	}

	public void setDynamicAddressedInRunTime(boolean isDynamicAddressedInRunTime) {
		this.isDynamicAddressedInRunTime = isDynamicAddressedInRunTime;
	}

	public boolean isStaticAdressedInRunTime() {
		return ((String)getId()).contains(".");
	}

	public void setInterpreting(VWMLEntity interpreting) {
		if (interpreting == null) {
			return;
		}
		if (!interpreting.getId().equals(VWMLEntity.s_NullEntityId)) {
			if (interpreting.getId().equals(VWMLEntity.s_NilEntityId)) {
				this.decrementRefCounter();
			}
			else {
				interpreting.incrementRefCounter();
				this.incrementRefCounter();
			}
			if (this.interpreting != null) {
				this.interpreting.decrementRefCounter();
			}
		}
		else {
			if (this.interpreting != null) {
				this.interpreting.decrementRefCounter();
			}
			interpreting = null;
		}
		if (this.interpreting != null && this.interpreting.getRefCounter() == 0) {
			this.interpreting.releaseProtocolEntity();
		}
		this.interpreting = interpreting;
		if (originalInterpreting == null) {
			originalInterpreting = interpreting;
		}
		if (this.getLink().getLinkOperationVisitor() != null) {
			this.getLink().getLinkOperationVisitor().interpretObjectAs(this, interpreting);
		}
	}
	
	public void resetInterpreting() {
		interpretationHistory.reset(interpreting);
		originalInterpreting = null;
		this.interpreting = null;
	}
	
	/**
	 * Adds operation to set of associative operations
	 * @param op
	 * @param opDebugInfo
	 */
	public void addOperation(VWMLOperation op, OperationInfo opDebugInfo) {
		op.setDebugInfo(opDebugInfo);
		associatedOperations.addOperation(op);
		if (this.getLink().getLinkOperationVisitor() != null) {
			this.getLink().getLinkOperationVisitor().associateOperation(this, op);
		}
	}
	
	/**
	 * Removes operation from set of associative operations
	 * @param op
	 */
	public void removeOperation(VWMLOperation op) {
		associatedOperations.removeOperation(op);
		if (this.getLink().getLinkOperationVisitor() != null) {
			this.getLink().getLinkOperationVisitor().removeOperationFromAssociation(this, op);
		}
	}
	
	/**
	 * Returns operation and moves pointer to next operation if such exists
	 * @return
	 */
	public VWMLOperation getOperation(VWMLLinkIncrementalIterator it) {
		return associatedOperations.peekOperation(it);
	}
	
	/**
	 * Returns instance of iterator of container of operations objects
	 * @return
	 */
	public VWMLLinkIncrementalIterator acquireOperationsIterator() {
		VWMLLinkIncrementalIterator it = null;
		if (associatedOperations.operations() != 0) {
			it = new VWMLLinkIncrementalIterator(associatedOperations.operations());
		}
		return it;
	}
	
	public boolean isLifeTerm() {
		return isLifeTerm;
	}

	public void setLifeTerm(boolean isLifeTerm) {
		this.isLifeTerm = isLifeTerm;
	}
	
	public boolean isLifeTermAsSource() {
		return isLifeTermAsSource;
	}

	public void setLifeTermAsSource(boolean isLifeTermAsSource) {
		this.isLifeTermAsSource = isLifeTermAsSource;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * Returns true in case if entity can be considered as 'term'
	 * @return
	 */
	public boolean isTerm() {
		return (associatedOperations.operations() != 0) ? true : false;
	}

	public boolean isMarkedAsComplexEntity() {
		return isMarkedAsComplexEntity;
	}

	public void setMarkedAsComplexEntity(boolean isMarkedAsComplexEntity) {
		this.isMarkedAsComplexEntity = isMarkedAsComplexEntity;
	}

	/**
	 * Marks entity as artificial term - used when artificial operation is added temporally
	 * @param mark
	 */
	public void setMarkedAsArtificalTerm(boolean isMarkedAsArtificalTerm) {
		this.isMarkedAsArtificalTerm = isMarkedAsArtificalTerm;
	}

	public boolean isMarkedAsArtificalTerm() {
		return isMarkedAsArtificalTerm;
	}
	
	/**
	 * Returns 'true' in case if entity was marked as creature (entity which lives on intersection of worlds)
	 * @return
	 */
	public boolean isCreature() {
		return isCreature;
	}
	
	public boolean isOperateByExe() {
		return isOperatesByExe;
	}

	/**
	 * Returns 'true' in case if recursive interpretation is possible, otherwise 'false' is returned
	 * @return
	 */
	public boolean isRecursiveInterpretationOnRuntime() {
		return isRecursiveInterpretationOn(getInterpreting(), false);
	}

	/**
	 * Returns 'true' in case if recursive interpretation on original entity is possible, otherwise 'false' is returned
	 * @return
	 */
	public boolean isRecursiveInterpretationOnOriginal() {
		return isRecursiveInterpretationOn(getOriginalInterpreting(), true);
	}

	/**
	 * Returns 'true' in case if entity is part of dynamic context (see operation OPDYNCONTEXT)
	 * @return
	 */
	public boolean isPartOfDynamicContext() {
		return isPartOfDynamicContext;
	}

	public void setPartOfDynamicContext(boolean isPartOfDynamicContext) {
		this.isPartOfDynamicContext = isPartOfDynamicContext;
	}

	public void setOperateByExe(boolean isOperatesByExe) {
		this.isOperatesByExe = isOperatesByExe;
	}

	public VWMLEntity getClonedFrom() {
		return clonedFrom;
	}

	public void setClonedFrom(VWMLEntity clonedFrom) {
		this.clonedFrom = clonedFrom;
	}
	
	public boolean isOriginal() {
		return isOriginal;
	}

	public void setOriginal(boolean isOriginal) {
		this.isOriginal = isOriginal;
	}

	public VWMLEntity getResolvedInRuntime() {
		return resolvedInRuntime;
	}

	public void setResolvedInRuntime(VWMLEntity resolvedInRuntime) {
		this.resolvedInRuntime = resolvedInRuntime;
	}

	public Object getOldId() {
		return oldId;
	}

	public String getOldReadableId() {
		return oldReadableId;
	}

	public VWMLObjectBuilder.VWMLObjectType deductEntityType() {
		return deductEntityTypeByProto(this);
	}
	
	public VWMLObjectBuilder.VWMLObjectType deductEntityTypeByProto(VWMLEntity proto) {
		VWMLObjectBuilder.VWMLObjectType type = VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY;
		if (proto.isTerm()) {
			type = VWMLObjectBuilder.VWMLObjectType.TERM;
		}
		else
		if (proto.isMarkedAsComplexEntity()) {
			type = VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY;
		}
		return type;
	}
	
	public boolean isGeneratedFromStack() {
		return generatedFromStack;
	}

	public void setGeneratedFromStack(boolean generatedFromStack) {
		this.generatedFromStack = generatedFromStack;
	}

	public boolean isRegeneratable() {
		return regeneratable;
	}

	public void setRegeneratable(boolean regeneratable) {
		this.regeneratable = regeneratable;
	}

	public boolean isLocked() {
		return lock.get();
	}

	public void lock(boolean lock) {
		this.lock.getAndSet(lock);
	}

	@Override
	public String toString() {
		return "VWMLEntity [interpreting=" + interpreting + ", getLink()="
				+ getLink() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isMarkedAsComplexEntity ? 1231 : 1237);
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
		VWMLEntity other = (VWMLEntity) obj;		
		if (isMarkedAsComplexEntity != other.isMarkedAsComplexEntity) {
			return false;
		}
		return true;
	}

	protected VWMLOperations getAssociatedOperations() {
		return associatedOperations;
	}

	protected void setAssociatedOperations(VWMLOperations associatedOperations) {
		this.associatedOperations = associatedOperations;
	}
	
	protected void copyAssociatedOperations(VWMLOperations associatedOperations) {
		getAssociatedOperations().copyFrom(associatedOperations);
	}
	
	protected VWMLContext createEntityContextBasedOnNewEntityName(VWMLContext defaultContext, String lookupId, String newId) throws Exception {
		boolean found = false;
		VWMLContext context = defaultContext;
		String[] path = context.getContextPath().clone();
		for (int i = 0; i < path.length; i++) {
			if (path[i].equals(lookupId)) {
				path[i] = newId;
				found = true;
				break;
			}
		}
		if (!found) {
			context = defaultContext;
		}
		else {
			context = VWMLContextsRepository.instance().createFromContextPath(path);
		}
		return context;
	}
	
	protected boolean isRecursiveInterpretationOn(VWMLEntity e, boolean onOriginal) {
		boolean r = false;
		if (e != null) {
			VWMLEntity nextInterpreting = (onOriginal) ? e.getOriginalInterpreting() : e.getInterpreting();
			if (nextInterpreting != null) {
				r = nextInterpreting.equals(this);
			}
		}
		return r;
	}
	
}
