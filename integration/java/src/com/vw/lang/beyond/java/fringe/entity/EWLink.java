package com.vw.lang.beyond.java.fringe.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EW's link; it describes how EW objects are linked
 * @author ogibayev
 *
 */
public class EWLink {
	
	private EWObject itself;
	private EWObject parent;
	private List<EWObject> linkedObjects = Collections.synchronizedList(new ArrayList<EWObject>());
	
	public EWLink(EWObject itself) {
		super();
		this.itself = itself;
	}

	public EWObject getItself() {
		return itself;
	}

	public void setItself(EWObject itself) {
		this.itself = itself;
	}

	public List<EWObject> getLinkedObjects() {
		return linkedObjects;
	}
	
	public EWObject getParent() {
		return parent;
	}

	public void setParent(EWObject parent) {
		this.parent = parent;
	}

	/**
	 * Returns 'true' in case if objects have been linked
	 * @param obj
	 * @return
	 */
	public boolean isLinked(EWObject obj) {
		return itself.getLink().getLinkedObjects().contains(obj);
	}
	
	/**
	 * Links object to object which is referenced by field 'itself' according to type's rule
	 * @param linked object to be linked
	 */
	public void link(EWObject linked) {
		// links: itself -> linked
		itself.getLink().getLinkedObjects().add(linked);
		linked.getLink().setParent(itself);
	}

	/**
	 * Current object is unlinked from the given object
	 * @param obj
	 */
	public void unlinkFrom(EWObject obj) {
		if (isLinked(obj)) {
			itself.getLink().getLinkedObjects().remove(obj);
			obj.getLink().setParent(itself.getLink().getParent());
		}
	}
	
	/**
	 * Shows all dependency of given object
	 * @param startObj
	 */
	public void iterate(EWObject startObj, List<EWObject> dependencyList) {
		List<EWObject>  linkedObjects = startObj.getLink().getLinkedObjects();
		for(EWObject obj : linkedObjects) {
			if (dependencyList != null) {
				dependencyList.add(obj);
			}
			iterate(obj, dependencyList);
		}
	}
	
	
	/**
	 * Returns number of linked objects on given VWMLObject 
	 * @return
	 */
	public int getLinkedObjectsOnThisTime() {
		return linkedObjects.size();
	}
	
	/**
	 * Returns concrete entity by its number
	 * @param number
	 * @return
	 */
	public EWObject getConcreteLinkedEntity(int number) {
		if (number >= getLinkedObjectsOnThisTime()) {
			return null;
		}
		return linkedObjects.get(number);
	}
}
