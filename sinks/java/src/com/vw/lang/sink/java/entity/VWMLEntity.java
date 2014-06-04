package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.VWMLCloneAuxCache;
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
	private VWMLEntity originalInterpreting;
	private VWMLEntity interpreting;
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
	
	public VWMLEntity(Object hashId) {
		super(hashId);
	}

	public VWMLEntity(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
	}

	/**
	 * New entity was born
	 * @param proto
	 * @param id (new entity's id)
	 * @param initialContext
	 * @param auxCache
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity born(VWMLEntity proto, Object id, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		VWMLEntity cloned = born(proto, (String)getId(), (String)id, getContext(), initialContext, auxCache);
		return cloned;
	}

	/**
	 * Born new entity based on current entity, the Id is changed to newId
	 * (usually used by operation 'Born' when new source lifeterm is created in runtime)
	 * @param oldId
	 * @param newId
	 * @param context
	 * @param initialContext
	 * @param auxCache
	 * @return
	 */
	public VWMLEntity born(VWMLEntity proto, String oldId, String newId, VWMLContext context, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		return clone(proto, oldId, newId, context, initialContext, auxCache, true, true);
	}
	
	/**
	 * Clones entity
	 * @param proto
	 * @param id (new entity's id)
	 * @param initialContext
	 * @param auxCache
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity clone(VWMLEntity proto, Object id, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		VWMLEntity cloned = clone(proto, (String)getId(), (String)id, getContext(), initialContext, auxCache);
		return cloned;
	}
	
	/**
	 * Clones current entity, the Id is changed to newId
	 * (usually used by operation 'Clone' when new source lifeterm is created in runtime)
	 * @param oldId
	 * @param newId
	 * @param context
	 * @param initialContext
	 * @param auxCache
	 * @return
	 */
	public VWMLEntity clone(VWMLEntity proto, String oldId, String newId, VWMLContext context, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		return clone(proto, oldId, newId, context, initialContext, auxCache, true, false);
	}

	/**
	 * More parameters for clone operations
	 * @param proto
	 * @param oldId
	 * @param newId
	 * @param context
	 * @param initialContext
	 * @param auxCache
	 * @param firstIteration
	 * @param bornMode
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity clone(VWMLEntity proto, String oldId, String newId, VWMLContext context, VWMLContext initialContext, VWMLCloneAuxCache auxCache, boolean firstIteration, boolean bornMode) throws Exception {
		// check if entity is in cloned context
		if (!firstIteration) {
			if ((!VWMLContext.isContextChildOf(initialContext.getContext(), getContext().getContext())) ||
				(this.getContext() == VWMLContextsRepository.instance().getDefaultContext())) {
				return this; // no need to clone entity which doesn't belong to cloned context
			}
		}
		if (auxCache != null && auxCache.check(this)) {
			VWMLEntity cloned = auxCache.get(this);
			if (cloned != null) {
				return cloned;
			}
		}
		// entity declared on another context, so it should be changed on new
		context = createEntityContextBasedOnNewEntityName(context, oldId, newId);
		VWMLEntity termAssociatedEntity = null;
		// proto is active for first iteration only
		VWMLObjectBuilder.VWMLObjectType type = null;
		if (proto == null) {
			type = deductEntityTypeByProto(this);
		}
		else {
			type = deductEntityTypeByProto(proto);			
		}
		if (type == VWMLObjectBuilder.VWMLObjectType.TERM) {
			termAssociatedEntity = ((VWMLTerm)this).getOriginalAssociatedEntity();
		}		
		if (termAssociatedEntity != null) {
			termAssociatedEntity = termAssociatedEntity.clone(null,
															  oldId,
															  newId,
															  termAssociatedEntity.getContext(),
															  initialContext,
															  auxCache,
															  false,
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
			interpretingEntity = eIAS.clone( null,
											 oldId,
											 newId,
											 eIAS.getContext(),
											 initialContext,
											 auxCache,
											 false,
											 bornMode);	
		}
		else
		if (eIAS != null && recursion) {
			interpretingEntity = eIAS;
		}
		// new entity is registered on repository
		Object eId = getId();
		if (firstIteration) {
			eId = newId;
		}
		if (((String)eId).contains("." + oldId)) {
			eId = ((String)eId).replaceFirst("\\." + oldId, "." + newId);
		}
		VWMLEntity cloned = (VWMLEntity)VWMLObjectsRepository.acquire(type,
																	  eId,
																	  context.getContext(),
																	  getInterpretationHistorySize(),
																	  VWMLObjectsRepository.asOriginal,
																	  getLink().getLinkOperationVisitor());
		if (firstIteration && proto != null && proto.isMarkedAsComplexEntity() && cloned.isMarkedAsComplexEntity()) {
			VWMLLinkIncrementalIterator it = proto.getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					cloned.getLink().link(proto.getLink().getConcreteLinkedEntity(it.getIt()));
				}
			}
		}
		auxCache.add(this, cloned);
		cloned.setSynthetic(isSynthetic());
		cloned.setLifeTerm(isLifeTerm());
		cloned.setLifeTermAsSource(isLifeTermAsSource());
		cloned.setClonedFrom(this);
		if (termAssociatedEntity != null) {
			cloned.setAssociatedOperations(getAssociatedOperations());
			((VWMLTerm)cloned).setAssociatedEntity(termAssociatedEntity);
		}
		cloned.setInterpreting(interpretingEntity);
		if (interpretingEntity == null) {
			VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					VWMLEntity linked = ((VWMLEntity)getLink().getConcreteLinkedEntity(it.getIt()));
					linked = linked.clone(null,
										  oldId,
							              newId,
							              linked.getContext(),
							              initialContext,
							              auxCache,
							              false,
							              bornMode);
					cloned.getLink().link(linked);
				}
			}
		}
		return cloned;
	}
	
	@Override
	public String buildReadableId() {
		if (getReadableId() == null) {
			if (isTerm()) {
				return ((VWMLTerm)this).getAssociatedEntity().buildReadableId();
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
		this.context = context;
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

	public void setInterpreting(VWMLEntity interpreting) {
		this.interpreting = interpreting;
		if (interpreting != null) {
			interpretationHistory.store(interpreting);
			if (interpreting.equals(VWMLObjectsRepository.instance().getNullEntity())) {
				interpreting = null;
			}
		}
		if (originalInterpreting == null) {
			originalInterpreting = interpreting;
		}
		if (this.getLink().getLinkOperationVisitor() != null) {
			this.getLink().getLinkOperationVisitor().interpretObjectAs(this, interpreting);
		}
	}
	
	/**
	 * Adds operation to set of associative operations
	 * @param op
	 */
	public void addOperation(VWMLOperation op) {
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

	protected void setClonedFrom(VWMLEntity clonedFrom) {
		this.clonedFrom = clonedFrom;
	}

	protected VWMLOperations getAssociatedOperations() {
		return associatedOperations;
	}

	protected void setAssociatedOperations(VWMLOperations associatedOperations) {
		this.associatedOperations = associatedOperations;
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
	
	protected VWMLObjectBuilder.VWMLObjectType deductEntityTypeByProto(VWMLEntity proto) {
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
