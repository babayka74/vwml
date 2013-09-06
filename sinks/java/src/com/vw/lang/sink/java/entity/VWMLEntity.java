package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperations;

/**
 * VWML's base entity (see language specification)
 * @author ogibayev
 *
 */
public class VWMLEntity extends VWMLObject {

	public static final String s_NilEntityId = "nil";
	
	// this entity is interpreted as another entity/term
	private VWMLEntity interpreting;
	private VWMLOperations associatedOperations = new VWMLOperations();
	private boolean isLifeTerm = false;
	
	public VWMLEntity() {
		super();
	}

	public VWMLEntity(Object id, String readableId) {
		super(id, readableId);
	}

	public VWMLEntity getInterpreting() {
		return interpreting;
	}

	public void setInterpreting(VWMLEntity interpreting) {
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
	public VWMLOperation getOperation() {
		return associatedOperations.peekOperation();
	}
	
	public boolean isLifeTerm() {
		return isLifeTerm;
	}

	public void setLifeTerm(boolean isLifeTerm) {
		this.isLifeTerm = isLifeTerm;
	}

	@Override
	public String toString() {
		return "VWMLEntity [interpreting=" + interpreting + ", getLink()="
				+ getLink() + "]";
	}	
}
