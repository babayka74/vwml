package com.vw.lang.sink.java.beyond.fringe.creature;

import java.util.UUID;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.entity.EWObject;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
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
	
	public VWMLCreature() {
		super();
		markAsCreature();
	}
	
	public VWMLCreature(Object id, String readableId) {
		super(id, readableId);
		markAsCreature();
	}
	
	/**
	 * Transforms EW entity to VWML entity
	 * @param ewEntity
	 * @return
	 * @throws Exception
	 */
	public static VWMLEntity transformToVWML(EWEntity ewEntity, boolean transformationFlag) throws Exception {
		VWMLEntity e = null;
		if (transformationFlag == s_transformAlwaysAsSimple || !ewEntity.isMarkedAsComplexEntity()) {
			e = transformSimpleEWEntityToVWML(null, ewEntity);
		}
		else {
			e = transformComplexEWEntityToVWML(null, ewEntity);
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
	
	private static VWMLEntity transformSimpleEWEntityToVWML(VWMLEntity parent, EWEntity ewEntity) throws Exception {
		VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.SIMPLE_ENTITY,
											   ewEntity.getId(),
											   ewEntity.getContext(),
											   0,
											   VWMLObjectsRepository.notAsOriginal,
											   null);
		if (parent != null) {
			parent.getLink().link(e);
		}
		return e;
	}
	
	private static VWMLEntity transformComplexEWEntityToVWML(VWMLEntity parent, EWEntity ewEntity) throws Exception {
		VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.COMPLEX_ENTITY,
				   ewEntity.getId(),
				   ewEntity.getContext(),
				   0,
				   VWMLObjectsRepository.notAsOriginal,
				   null);
		for(EWObject ewo : ewEntity.getLink().getLinkedObjects()) {
			EWEntity ewe = (EWEntity)ewo;
			if (!ewe.isMarkedAsComplexEntity()) {
				transformSimpleEWEntityToVWML(e, ewe);
			}
			else {
				if (parent != null) {
					parent.getLink().link(e);
				}
				VWMLEntity r = transformComplexEWEntityToVWML(e, ewe);
				if (parent == null) {
					parent = e;
					parent.getLink().link(r);
				}									
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
		EWEntity ewe = EWEntityBuilder.buildComplexEntity(UUID.randomUUID().toString(), vwmlEntity.getContext().getContext());
		VWMLLinkIncrementalIterator it = vwmlEntity.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(VWMLObject o = vwmlEntity.getLink().peek(it); o != null; o = vwmlEntity.getLink().peek(it)) {
				VWMLEntity e = (VWMLEntity)o;
				if (!e.isMarkedAsComplexEntity()) {
					transformSimpleVWMLEntityToEW(ewe, e);
				}
				else {
					if (parent != null) {
						parent.getLink().link(ewe);
					}
					EWEntity r = transformComplexVWMLEntityToEW(ewe, e);
					if (parent == null) {
						parent = ewe;
						parent.getLink().link(r);
					}					
				}
			}
		}
		else {
			if (parent != null) {
				parent.getLink().link(ewe);
			}
		}
		return ewe;
	}
}
