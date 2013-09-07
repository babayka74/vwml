package com.vw.lang.sink.java.link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vw.lang.sink.java.VWMLObject;

/**
 * VWML's link; it describes how VWML objects are linked
 * For debug purposes links are visualized with help of DOT language => http://www.graphviz.org/Documentation.php
 * @author ogibayev
 *
 */
public class VWMLLink {
	
	private VWMLObject itself;
	private IVWMLLinkVisitor linkOperationVisitor = null;
	private List<VWMLObject> linkedObjects = Collections.synchronizedList(new ArrayList<VWMLObject>());
	
	public VWMLLink(VWMLObject itself) {
		super();
		this.itself = itself;
	}

	public VWMLObject getItself() {
		return itself;
	}

	public void setItself(VWMLObject itself) {
		this.itself = itself;
	}

	public List<VWMLObject> getLinkedObjects() {
		return linkedObjects;
	}
	
	public IVWMLLinkVisitor getLinkOperationVisitor() {
		return linkOperationVisitor;
	}

	public void setLinkOperationVisitor(IVWMLLinkVisitor linkOperationVisitor) {
		this.linkOperationVisitor = linkOperationVisitor;
	}

	/**
	 * Returns 'true' in case if objects have been linked
	 * @param obj
	 * @return
	 */
	public boolean isLinked(VWMLObject obj) {
		return itself.getLink().getLinkedObjects().contains(obj);
	}
	
	/**
	 * Links object to object which is referenced by field 'itself' according to type's rule
	 * @param linked object to be linked
	 */
	public void link(VWMLObject linked) {
		// links: itself -> linked
		itself.getLink().getLinkedObjects().add(linked);
		if (getLinkOperationVisitor() != null) {
			getLinkOperationVisitor().link(itself, linked);
		}
	}

	/**
	 * Current object is unlinked from the given object
	 * @param obj
	 */
	public void unlinkFrom(VWMLObject obj) {
		if (isLinked(obj)) {
			itself.getLink().getLinkedObjects().remove(obj);
			if (getLinkOperationVisitor() != null) {
				getLinkOperationVisitor().unlink(itself, obj);
			}
		}
	}
	
	/**
	 * Shows all dependency of given object
	 * @param startObj
	 */
	public void iterate(VWMLObject startObj, List<VWMLObject> dependencyList) {
		List<VWMLObject>  linkedObjects = startObj.getLink().getLinkedObjects();
		for(VWMLObject obj : linkedObjects) {
			if (dependencyList != null) {
				dependencyList.add(obj);
			}
			if (startObj.getLink().getLinkOperationVisitor() != null) {
				startObj.getLink().getLinkOperationVisitor().link(startObj, obj);
			}
			iterate(obj, dependencyList);
		}
	}
	
	/**
	 * Returns instance of iterator of container of linked objects
	 * @return
	 */
	public VWMLLinkIncrementalIterator acquireLinkedObjectsIterator() {
		return new VWMLLinkIncrementalIterator(getLinkedObjects().size());
	}
	
	/**
	 * Peeks next VWML object from the linked objects container
	 * @param it
	 * @return
	 */
	public VWMLObject peek(VWMLLinkIncrementalIterator it) {
		VWMLObject obj = null;
		if (it.isCorrect()) {
			obj = getLinkedObjects().get(it.getIt());
			it.next();
		}
		return obj;
	}
}
