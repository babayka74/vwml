package com.vw.lang.sink.java.entity;

/**
 * VWML's simple entity (see language specification)
 * @author ogibayev
 *
 */
public class VWMLSimpleEntity extends VWMLEntity {

	public VWMLSimpleEntity(Object hashId) {
		super(hashId);
	}

	public VWMLSimpleEntity(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
	}
	
	@Override
	public String buildReadableId() {
		if (getReadableId() == null) {
			setReadableId((String)getId());
		}
		return getReadableId();
	}
}
