package com.vw.lang.beyond.java.fringe.entity;

/**
 * EW's base entity (see language specification)
 * @author ogibayev
 *
 */
public class EWEntity extends EWObject {

	private String context;
	protected boolean isMarkedAsComplexEntity = false;

	public EWEntity() {
		super();
	}

	public EWEntity(Object id, String readableId) {
		super(id, readableId);
	}

	public boolean isMarkedAsComplexEntity() {
		return isMarkedAsComplexEntity;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
