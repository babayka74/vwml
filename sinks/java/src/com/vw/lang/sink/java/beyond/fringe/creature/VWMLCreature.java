package com.vw.lang.sink.java.beyond.fringe.creature;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * VWML entity which 'lives' on intersection of worlds; defined in section beyond.fringe
 * @author Oleg
 *
 */
public class VWMLCreature extends VWMLEntity {
	
	public VWMLCreature() {
		super();
		markAsCreature();
	}

	public VWMLCreature(Object id, String readableId) {
		super(id, readableId);
		markAsCreature();
	}
	
	public void markAsCreature() {
		this.isCreature = true;
	}
}
