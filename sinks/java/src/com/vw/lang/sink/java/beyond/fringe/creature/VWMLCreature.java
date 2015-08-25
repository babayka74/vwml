package com.vw.lang.sink.java.beyond.fringe.creature;

import java.util.UUID;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.entity.EWObject;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * VWML entity which 'lives' on intersection of worlds; defined in section beyond.fringe
 * @author Oleg
 *
 */
public class VWMLCreature extends VWMLEntity {

	// used by transformToEW && transformToVWML
	public static boolean s_transformAlwaysAsSimple = true;
	// used by transformToEW && transformToVWML
	public static boolean s_transformAsIs = false;
	
	public VWMLCreature(Object hashId) {
		super(hashId);
		markAsCreature();
	}
	
	public VWMLCreature(Object hashId, Object id, String readableId) {
		super(hashId, id, readableId);
		markAsCreature();
	}
	
	/**
	 * Transforms EW entity to VWML entity
	 * @param context
	 * @param ewEntity
	 * @return
	 * @throws Exception
	 */
	public static VWMLEntity transformToVWML(VWMLContext context, EWEntity ewEntity, boolean transformationFlag) throws Exception {
		VWMLEntity e = null;
		if (transformationFlag == s_transformAlwaysAsSimple || !ewEntity.isMarkedAsComplexEntity()) {
			e = transformSimpleEWEntityToVWML(context, null, ewEntity);
		}
		else {
			e = transformComplexEWEntityToVWML(context, null, ewEntity);
		}
		if (e != null) {
			e.setReadableId(null);
			e.buildReadableId();
			e.setId(e.getReadableId());
		}
		return e; 
	}

	/**
	 * Transforms VWML entity to EW entity
	 * @param vwmlEntity
	 * @param transformationFlag
	 * @return
	 * @throws Exception
	 */
	public static EWEntity transformToEW(VWMLEntity vwmlEntity, boolean transformationFlag) throws Exception {
		EWEntity e = null;
		if (transformationFlag == s_transformAlwaysAsSimple || !vwmlEntity.isMarkedAsComplexEntity()) {
			e = transformSimpleVWMLEntityToEW(null, vwmlEntity);
		}
		else {
			e = transformComplexVWMLEntityToEW(null, vwmlEntity);
		}
		e.setReadableId(vwmlEntity.buildReadableId());
		return e; 
	}
	
	public void markAsCreature() {
		this.isCreature = true;
	}
	
	private static VWMLEntity transformSimpleEWEntityToVWML(VWMLContext context, VWMLEntity parent, EWEntity ewEntity) throws Exception {
		VWMLEntity e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY,
														   context.getContext(),
														   ewEntity.getId(),
														   context,
														   0,
														   null);
		if (!ewEntity.isRegeneratable()) {
			e.incrementRefCounter();
		}
		e.setRegeneratable(ewEntity.isRegeneratable());
		if (parent != null) {
			parent.getLink().link(e);
		}
		return e;
	}
	
	private static VWMLEntity transformComplexEWEntityToVWML(VWMLContext context, VWMLEntity parent, EWEntity ewEntity) throws Exception {
		VWMLEntity e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
														   context.getContext(),
														   ewEntity.getId(),
														   context,
														   0,
														   null);
		if (!ewEntity.isRegeneratable()) {
			e.incrementRefCounter();
		}
		e.setRegeneratable(ewEntity.isRegeneratable());
		for(EWObject ewo : ewEntity.getLink().getLinkedObjects()) {
			EWEntity ewe = (EWEntity)ewo;
			if (!ewe.isMarkedAsComplexEntity()) {
				transformSimpleEWEntityToVWML(context, e, ewe);
			}
			else {
				VWMLEntity r = transformComplexEWEntityToVWML(context, e, ewe);
				e.getLink().link(r);
			}
		}
		if (ewEntity.getLink().getLinkedObjects().size() == 0) {
			if (parent != null) {
				parent.getLink().link(e);
			}
		}
		return e;
	}
	
	private static EWEntity transformSimpleVWMLEntityToEW(EWEntity parent, VWMLEntity vwmlEntity) throws Exception {
		EWEntity e = EWEntityBuilder.buildSimpleEntity(vwmlEntity.getId(), vwmlEntity.getContext().getContext());
		if (parent != null) {
			parent.getLink().link(e);
		}
		return e;
	}
	
	private static EWEntity transformComplexVWMLEntityToEW(EWEntity parent, VWMLEntity vwmlEntity) throws Exception {
		EWEntity ewe = EWEntityBuilder.buildComplexEntity(UUID.randomUUID().toString(),
				                                          (vwmlEntity.getContext() != null) ? vwmlEntity.getContext().getContext() : null);
		VWMLLinkIncrementalIterator it = vwmlEntity.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(VWMLObject o = vwmlEntity.getLink().peek(it); o != null; o = vwmlEntity.getLink().peek(it)) {
				VWMLEntity e = (VWMLEntity)o;
				if (!e.isMarkedAsComplexEntity()) {
					transformSimpleVWMLEntityToEW(ewe, e);
				}
				else {
					EWEntity r = transformComplexVWMLEntityToEW(ewe, e);
					ewe.getLink().link(r);
				}
			}
		}
		return ewe;
	}
}
