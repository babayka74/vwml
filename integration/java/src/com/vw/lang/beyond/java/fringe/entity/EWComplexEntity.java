package com.vw.lang.beyond.java.fringe.entity;

/**
 * EW's complex entity
 * @author ogibayev
 *
 */
public class EWComplexEntity extends EWEntity {

	public EWComplexEntity() {
		super();
		markAsComplexEntity();
	}

	public EWComplexEntity(Object id, String readableId) {
		super(id, readableId);
		markAsComplexEntity();
	}
	
	public void markAsComplexEntity() {
		this.isMarkedAsComplexEntity = true;
	}
	
	@Override
	public void release() {
		synchronized(this) {
			for(int i = 0; i < getLink().getLinkedObjectsOnThisTime(); i++) {
				((EWEntity)(getLink().getConcreteLinkedEntity(i))).release();
			}
			getLink().clear();
		}
		EWEntityBuilder.returnToPool(this);
	}
}
