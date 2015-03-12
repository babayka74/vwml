package com.vw.lang.sink.java.entity;

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

	public static final String s_NilEntityNId        = "nil";
	public static final String s_NullEntityNId       = "null";
	public static final String s_EmptyEntityNId      = "()";
	public static final String s_doNothingEntityNId  = "doNothing";
	public static final String s_doNothingEntityNId2 = "DoNothing";
	public static final String s_doNothingEntityNId3 = "donothing";
	public static final String s_trueEntityNId       = "true";
	public static final String s_falseEntityNId      = "false";

	public static final String s_NilEntityId        = (String)buildHashIdFrom(s_NilEntityNId);
	public static final String s_NullEntityId       = (String)buildHashIdFrom(s_NullEntityNId);
	public static final String s_EmptyEntityId      = (String)buildHashIdFrom(s_EmptyEntityNId);
	public static final String s_doNothingEntityId  = (String)buildHashIdFrom(s_doNothingEntityNId);
	public static final String s_doNothingEntityId2 = (String)buildHashIdFrom(s_doNothingEntityNId2);
	public static final String s_doNothingEntityId3 = (String)buildHashIdFrom(s_doNothingEntityNId3);
	public static final String s_trueEntityId       = (String)buildHashIdFrom(s_trueEntityNId);
	public static final String s_falseEntityId      = (String)buildHashIdFrom(s_falseEntityNId);

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
	// entities which are created by 'generateComplexEntityFromEntitiesReversedStack' with flag 'doNotAddIfUnknown'
	// must set this flag to 'true'
	private boolean isLookedByReadableId = false;
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
	// sets during release operation in order to mark entity as removed from context and related storage
	private boolean removed = false;
	// see description (non-cloned); usually is used on static linking phase in order to detect ambiguous 
	// interpretation (meaning entity has more than one statically defined interpretation)
	private InterpretationObserver interpretationObserver = null;
	// entities which are created during 'assemble' of 'relink' operations and not cloned from any entity are marked as fantom
	// the marking is needed in order to have some sign to remove it during 'release' operation
	// (not clonable)
	private boolean asFantom = false;
	// entity belongs to another context and entity to which belongs this entity is going to be released then 
	// this entity is marked as 'potential invalid' and must be re-assembled during get operation
	private boolean markedAsPotentialInvalid = false;
	// repository's registration key
	private String registrationKey = null;
	
	public VWMLEntity(Object hashId) {
		super(hashId);
	}

	public VWMLEntity(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
	}

	public void resurrect() {
		super.resurrect();
		isCreature = false;
		context = null;
		originalInterpreting = null;
		interpreting = null;
		interpretationHistory = new VWMLEntityInterpretationHistory();
		associatedOperations = new VWMLOperations("__associated_operation__" + this);
		isLifeTerm = false;
		isLifeTermAsSource = false;
		isMarkedAsArtificalTerm = false;
		isOperatesByExe = false;
		isOriginal = false;
		isSynthetic = false;
		isDynamicAddressedInRunTime = false;
		isLookedByReadableId = false;
		asArgPair = null;
		interpretationHistorySize = 0;
		clonedFrom = null;
		resolvedInRuntime = null;
		specialLinkedEntity = null;
		isPartOfDynamicContext = false;
		activated = false;
		removed = false;
		asFantom = false;
		interpretationObserver = null;
		markedAsPotentialInvalid = false;
		registrationKey = null;
	}
	
	/**
	 * Clones entity without taking into consideration following things:
	 * 1. terms
	 * The entity is cloned as data and moved to context 'cloneToCtx'
	 * @param cloneToCtx
	 * @param cloneInterpreting
	 * @return
	 */
	public VWMLEntity simpleCloneOnContext(VWMLContext cloneToCtx, boolean cloneInterpreting) throws Exception {
		VWMLEntity cloned = null;
		cloned = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(getId(), cloneToCtx);
		if (cloned == null) {
			VWMLObjectBuilder.VWMLObjectType type = deductEntityTypeByProto(this);
			cloned = (VWMLEntity)VWMLObjectsRepository.acquire(type,
														  this.getId(),
														  cloneToCtx.getContext(),
														  getInterpretationHistorySize(),
														  VWMLObjectsRepository.notAsOriginal,
														  getLink().getLinkOperationVisitor());
			cloned.setAsFantom(true);
			VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					VWMLEntity e = (VWMLEntity)getLink().getConcreteLinkedEntity(it.getIt());
					cloned.getLink().link(e.simpleCloneOnContext(cloneToCtx, cloneInterpreting));
				}
			}
			VWMLObjectsRepository.instance().lateBinding(cloned, (String)getNativeId());
		}
		if (cloneInterpreting) {
			if (getInterpreting() != null && !getInterpreting().isRecursiveInterpretationOnRuntime()) {
				VWMLEntity ic = getInterpreting().simpleCloneOnContext(cloneToCtx, cloneInterpreting);
				cloned.setInterpreting(ic);
			}
			else {
				cloned.setInterpreting(getInterpreting());
			}
		}
		return cloned;
	}
	
	public void release(VWMLContext controlling) {
		if (getClonedFrom() == null || removed) {
			return;
		}
//		String rid = buildReadableId();
		removed = true;
		if (!VWMLContext.isContextChildOf(controlling.getContext(), getContext().getContext())) {
//			System.out.println("ignored release '" + getContext().getContext() + "." + rid + "'");
			setMarkedAsPotentialInvalid(true);
			return;
		}
		if (getInterpreting() != null) {
//			System.out.println("interpreting release '" + getInterpreting().getContext().getContext() + "." + getInterpreting().buildReadableId() + "'");
			getInterpreting().release(controlling);
			setInterpreting(null);
		}
		VWMLObjectBuilder.VWMLObjectType type = deductEntityTypeByProto(this);
		if (type == VWMLObjectBuilder.VWMLObjectType.TERM) {
			VWMLEntity et = ((VWMLTerm)this).getAssociatedEntity();
			if (et != null) {
				et.release(controlling);
				((VWMLTerm)this).setAssociatedEntity(null);
				return;
			}
		}
		for(VWMLObject e : getLink().getLinkedObjects()) {
			((VWMLEntity)e).release(controlling);
		}		
//		if (buildReadableId() != null) {
//			System.out.println("released '" + getContext().getContext() + "." + rid + "/" + getId() + "'");
//		}		
		VWMLObjectsRepository.instance().removeWithoutContextCleaning(this);
		//setReadableId("__removed__" + buildReadableId());
		//setId("__removed__");
		getLink().getLinkedObjects().clear();
		getLink().setParent(null);
/*		
		if (VWMLObjectsRepository.instance().getGarbageManager() != null) {
			VWMLObjectsRepository.instance().getGarbageManager().garbageEntity(this);
		}
*/		
	}
	
	@Override
	public String buildReadableId() {
		return (String)getNativeId();
/*		
		if (isTerm()) {
			if (((VWMLTerm)this).getAssociatedEntity() != null) {
				return ((VWMLTerm)this).getAssociatedEntity().buildReadableId();
			}
			else {
				return null; // removed term
			}
		}
		else {
			return (String)getNativeId();
		}
*/		
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

	public boolean isDynamicAddressedInRunTime() {
		return isDynamicAddressedInRunTime;
	}

	public void setDynamicAddressedInRunTime(boolean isDynamicAddressedInRunTime) {
		this.isDynamicAddressedInRunTime = isDynamicAddressedInRunTime;
	}

	public boolean isLookedByReadableId() {
		return isLookedByReadableId;
	}

	public void setLookedByReadableId(boolean isLookedByReadableId) {
		this.isLookedByReadableId = isLookedByReadableId;
	}

	public boolean isStaticAdressedInRunTime() {
		return ((String)getId()).contains(".");
	}

	public InterpretationObserver getInterpretationObserver() {
		return interpretationObserver;
	}

	public void setInterpretationObserver(InterpretationObserver interpretationObserver) {
		this.interpretationObserver = interpretationObserver;
	}

	public boolean isAsFantom() {
		return asFantom;
	}

	public void setAsFantom(boolean asFantom) {
		this.asFantom = asFantom;
	}

	public boolean isMarkedAsPotentialInvalid() {
		return markedAsPotentialInvalid;
	}

	public void setMarkedAsPotentialInvalid(boolean markedAsPotentialInvalid) {
		this.markedAsPotentialInvalid = markedAsPotentialInvalid;
	}

	public String getRegistrationKey() {
		return registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

	public void setInterpreting(VWMLEntity interpreting) {
		if (getInterpreting() != null && VWMLObjectsRepository.instance().isUnderConstruction()) {
			// notify observer about ambiguous interpretation
			if (interpretationObserver != null && interpretationObserver.isAmbiguosOn()) {
				interpretationObserver.ambiguous(this, interpreting);
			}
			// ambiguous interpretation must be resolved on repository's construction phase
			// change interpeting.id -> this.getInterpreting().id
			VWMLObjectsRepository.instance().addTranslatedObject(interpreting, getInterpreting());
		}
		else {
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
	
	/**
	 * Copies operations and debug info
	 * @param from
	 */
	public void copyOperations(VWMLEntity from) {
		setAssociatedOperations(from.getAssociatedOperations());
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

	public VWMLObjectBuilder.VWMLObjectType deduceEntityType() {
		return deductEntityTypeByProto(this);
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
