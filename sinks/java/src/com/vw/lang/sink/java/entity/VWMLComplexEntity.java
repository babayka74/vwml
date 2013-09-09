package com.vw.lang.sink.java.entity;

/**
 * VWML's complex entity (see language specification)
 * @author ogibayev
 *
 */
public class VWMLComplexEntity extends VWMLEntity {

	public VWMLComplexEntity() {
		super();
	}

	public VWMLComplexEntity(Object id, String readableId) {
		super(id, readableId);
		setMarkedAsComplexEntity(true);
	}

}
