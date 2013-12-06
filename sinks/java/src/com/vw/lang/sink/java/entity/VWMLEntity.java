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
	public static final String s_EmptyEntityId     = "()";
	public static final String s_doNothingEntityId = "doNothing";
	public static final String s_trueEntityId      = "true";
	public static final String s_falseEntityId     = "false";

	// true means that entity lives on intersection of worlds (see VWMLCreature)
	protected boolean isCreature = false;
	
	private VWMLContext context;
	// this entity is interpreted as another entity/term
	private VWMLEntity interpreting;
	private VWMLEntityInterpretationHistory interpretationHistory = new VWMLEntityInterpretationHistory();
	private VWMLOperations associatedOperations = new VWMLOperations();
	private boolean isLifeTerm = false;
	private boolean isLifeTermAsSource = false;
	private boolean isMarkedAsComplexEntity = false;
	// set when operation is added to entity, but initially entity wasn't marked as term
	private boolean isMarkedAsArtificalTerm = false;
	// marked by interpreter in case when operation EXE is being applied
	private boolean isOperatesByExe = false;
	// means that entity is created during initialization phase
	private boolean isOriginal = false;
	private int interpretationHistorySize;
	// if entity is cloned this field is set to entity from which it was cloned 
	private VWMLEntity clonedFrom = null;
	
	public VWMLEntity() {
		super();
	}

	public VWMLEntity(Object id, String readableId) {
		super(id, readableId);
	}

	/**
	 * Clones entity
	 * @param id (new entity's id)
	 * @param initialContext
	 * @param auxCache
	 * @return
	 * @throws Exception
	 */
	public VWMLEntity clone(Object id, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		VWMLEntity cloned = clone((String)getId(), (String)id, getContext(), initialContext, auxCache);
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
	public VWMLEntity clone(String oldId, String newId, VWMLContext context, VWMLContext initialContext, VWMLCloneAuxCache auxCache) throws Exception {
		return clone(oldId, newId, context, initialContext, auxCache, true);
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

	public int getInterpretationHistorySize() {
		return interpretationHistorySize;
	}

	public void setInterpretationHistorySize(int interpretationHistorySize) {
		this.interpretationHistorySize = interpretationHistorySize;
		interpretationHistory.setMaxHistorySize(this.interpretationHistorySize);
	}

	public void setInterpreting(VWMLEntity interpreting) {
		if (interpreting != null) {
			interpretationHistory.store(interpreting);
		}
		this.interpreting = interpreting;
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
	public boolean isRecursiveInterpretation() {
		boolean r = false;
		if (getInterpreting() != null) {
			VWMLEntity nextInterpreting = getInterpreting().getInterpreting();
			if (nextInterpreting != null) {
				r = nextInterpreting.equals(this);
			}
		}
		return r;
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
	
	public VWMLEntity clone(String oldId, String newId, VWMLContext context, VWMLContext initialContext, VWMLCloneAuxCache auxCache, boolean firstIteration) throws Exception {
		// check if entity is in cloned context
		if ((!firstIteration && !VWMLContext.isContextChildOf(initialContext.getContext(), getContext().getContext())) ||
			(this.getContext() == VWMLContextsRepository.instance().getDefaultContext())) {
			return this; // no need to clone entity which doesn't belong to cloned context
		}
		if (auxCache != null && auxCache.check(this)) {
			VWMLEntity cloned = auxCache.get(this);
			if (cloned != null) {
				return cloned;
			}
		}
		// entity declared on another context, so it should be changed on new
		context = createEntityContextBasedOnNewEntityName(context, oldId, newId);
		VWMLObjectBuilder.VWMLObjectType type = VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY;
		VWMLEntity termAssociatedEntity = null;
		if (isTerm()) {
			type = VWMLObjectBuilder.VWMLObjectType.TERM;
			termAssociatedEntity = ((VWMLTerm)this).getAssociatedEntity();
		}
		else
		if (isMarkedAsComplexEntity()) {
			type = VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY;
		}
		if (termAssociatedEntity != null) {
			termAssociatedEntity = termAssociatedEntity.clone(oldId,
															  newId,
															  termAssociatedEntity.getContext(),
															  initialContext,
															  auxCache,
															  false);	
		}
		VWMLEntity interpretingEntity = null;
		if (getInterpreting() != null && !getInterpreting().isRecursiveInterpretation()) {
			interpretingEntity = getInterpreting().clone(oldId,
														 newId,
														 getInterpreting().getContext(),
														 initialContext,
														 auxCache,
														 false);	
		}
		// new entity is registered on repository
		Object eId = getId();
		if (firstIteration) {
			eId = newId;
		}
		VWMLEntity cloned = (VWMLEntity)VWMLObjectsRepository.acquire(type,
																	  eId,
																	  context.getContext(),
																	  getInterpretationHistorySize(),
																	  VWMLObjectsRepository.notAsOriginal,
																	  getLink().getLinkOperationVisitor());
		auxCache.add(this, cloned);
		cloned.setLifeTerm(isLifeTerm());
		cloned.setLifeTermAsSource(isLifeTermAsSource());
		cloned.setClonedFrom(this);
		System.out.println("entity '" + getId() + " on context '" + getContext().getContext() + "' cloned with newId '" + eId + "' on context '" + context.getContext() + "'");
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
					linked = linked.clone(oldId,
							              newId,
							              linked.getContext(),
							              initialContext,
							              auxCache,
							              false);
					cloned.getLink().link(linked);
					System.out.println("Cloned '" + linked.getId() + "' added to '" + cloned.getId() + "'");
				}
			}
		}
		return cloned;
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
}
