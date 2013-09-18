package com.vw.lang.sink.java.entity;



/**
 * VWML's term (see language specification)
 * @author ogibayev
 *
 */
public class VWMLTerm extends VWMLEntity {

	private VWMLEntity associatedEntity;
	
	public VWMLTerm() {
		super();
	}

	public VWMLTerm(Object id, String readableId) {
		super(id, readableId);
	}

	public boolean isTerm() {
		return true;
	}

	public VWMLEntity getAssociatedEntity() {
		return associatedEntity;
	}

	public void setAssociatedEntity(VWMLEntity associatedEntity) {
		this.associatedEntity = associatedEntity;
	}
}
