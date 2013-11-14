package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.VWMLObject;
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
	
	// this entity is interpreted as another entity/term
	private VWMLContext context;
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
	private int interpretationHistorySize;
	
	public VWMLEntity() {
		super();
	}

	public VWMLEntity(Object id, String readableId) {
		super(id, readableId);
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

	public void setOperateByExe(boolean isOperatesByExe) {
		this.isOperatesByExe = isOperatesByExe;
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
}
