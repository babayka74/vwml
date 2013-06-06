package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.VWMLObject;

/**
 * VWML's base entity (see language specification)
 * @author ogibayev
 *
 */
public class VWMLEntity extends VWMLObject {

	// this entity is interpreted as another entity/term
	private VWMLEntity interpreting;
	
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
	}

	@Override
	public String toString() {
		return "VWMLEntity [interpreting=" + interpreting + ", getLink()="
				+ getLink() + "]";
	}	
}
