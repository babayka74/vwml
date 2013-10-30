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
			ce.clear();
		}
		return getReadableId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		VWMLEntity e = (VWMLEntity)obj;
		if (!e.isMarkedAsComplexEntity()) {
			return false;
		}
		int linkedObjsOnCurrent = getLink().getLinkedObjectsOnThisTime();
		int linkedObjsOnForeign = ((VWMLComplexEntity)e).getLink().getLinkedObjectsOnThisTime();
		if (linkedObjsOnCurrent != linkedObjsOnForeign) {
			return false;
		}
		for(int i = 0; i < linkedObjsOnCurrent; i++) {
			VWMLEntity e1 = (VWMLEntity)getLink().getConcreteLinkedEntity(i);
			VWMLEntity e2 = (VWMLEntity)((VWMLComplexEntity)e).getLink().getConcreteLinkedEntity(i);
			if (!e1.equals(e2)) {
				return false;
			}
		}
		return true;
	}
	
	protected void assembleReadableId(ComplexEntityNameBuilder ce, VWMLEntity entity) {
		ce.startProgress();
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (le.isMarkedAsComplexEntity()) {
					assembleReadableId(ce, le);
				}
				else {
					ce.addObjectId(le.getId());
				}
			}
		}
		ce.stopProgress();
	}
}
