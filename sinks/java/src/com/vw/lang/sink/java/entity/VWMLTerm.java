package com.vw.lang.sink.java.entity;



/**
 * VWML's term (see language specification)
 * @author ogibayev
 *
 */
public class VWMLTerm extends VWMLEntity {

	private VWMLEntity associatedEntity;
	private VWMLEntity originalAssociatedEntity;
	
	public VWMLTerm(Object hashId) {
		super(hashId);
	}

	public VWMLTerm(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
	}

	@Override
	public void restore(Object hashId, Object id) {
		super.restore(hashId, id);
		associatedEntity = null;
		originalAssociatedEntity = null;
	}
	
	public boolean isTerm() {
		return true;
	}

	public VWMLEntity getAssociatedEntity() {
		return associatedEntity;
	}

	public void setAssociatedEntity(VWMLEntity associatedEntity) {
		this.associatedEntity = associatedEntity;
		if (originalAssociatedEntity == null) {
			originalAssociatedEntity = associatedEntity;
		}
	}

	public VWMLEntity getOriginalAssociatedEntity() {
		return originalAssociatedEntity;
	}
}
