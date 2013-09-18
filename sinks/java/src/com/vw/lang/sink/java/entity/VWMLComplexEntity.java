package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

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

	@Override
	public boolean isMarkedAsComplexEntity() {
		return true;
	}
	
	@Override
	public String buildReadableId() {
		if (getReadableId() == null) {
			ComplexEntityNameBuilder ce = ComplexEntityNameBuilder.instance();
			assembleReadableId(ce, this);
			setReadableId(ce.build());
		}
		return getReadableId();
	}

	protected void assembleReadableId(ComplexEntityNameBuilder ce, VWMLEntity entity) {
		ce.startProgress();
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
			if (le.isMarkedAsComplexEntity()) {
				assembleReadableId(ce, le);
			}
		}
		ce.stopProgress();
	}
}
