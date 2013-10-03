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
	
	// this entity is interpreted as another entity/term
	private VWMLContext context;
	
	private VWMLEntity interpreting;
	private VWMLEntityInterpretationHistory interpretationHistory = new VWMLEntityInterpretationHistory();
	private VWMLOperations associatedOperations = new VWMLOperations();
	private boolean isLifeTerm = false;
	private boolean isMarkedAsComplexEntity = false;
	// set when operation is added to entity, but initially entity wasn't marked as term
	private boolean isMarkedAsArtificalTerm = false;
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

	public boolean isMarkedAsArtificalTerm() {
		return isMarkedAsArtificalTerm;
	}

	public void setMarkedAsArtificalTerm(boolean isMarkedAsArtificalTerm) {
		this.isMarkedAsArtificalTerm = isMarkedAsArtificalTerm;
	}

	@Override
	public String toString() {
		return "VWMLEntity [interpreting=" + interpreting + ", getLink()="
				+ getLink() + "]";
	}	
}
