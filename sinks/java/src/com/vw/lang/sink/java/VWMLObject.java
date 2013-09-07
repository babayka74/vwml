package com.vw.lang.sink.java;

import java.util.List;

import com.vw.lang.sink.java.link.VWMLLink;

/**
 * Root object for all VWML's items; used as class marker at this time
 * @author ogibayev
 *
 */
public class VWMLObject implements Cloneable, Comparable<VWMLObject> {
	private Object id;
	private String readableId;
	// context path consists from set of entity ids
	private String[] contextPath;
	// VWML object which takes responsibility of superviser of any changes on given object
	private VWMLObject superviser = null;
	// entity can be linked with another entity or command
	private VWMLLink link = new VWMLLink(this);
	
	public VWMLObject() {
		super();
	}

	public VWMLObject(Object id, String readableId) {
		super();
		this.id = id;
		this.readableId = readableId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VWMLObject other = (VWMLObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
	}
	
	public String getReadableId() {
		return readableId;
	}
	
	public void setReadableId(String readableId) {
		this.readableId = readableId;
	}

	public VWMLObject getSuperviser() {
		return superviser;
	}

	public void setSuperviser(VWMLObject superviser) {
		this.superviser = superviser;
	}

	/**
	 * Links current entity with another VWML object which can be entity or command
	 * @param linked
	 */
	public void link(VWMLObject linked) {
		link.link(linked);
	}
	
	/**
	 * Unlinks objects: current and given 
	 * @param obj
	 */
	public void unlink(VWMLObject obj) {
		link.unlinkFrom(obj);
	}
	
	/**
	 * Returns 'true' in case if objects were linked
	 * @param obj
	 * @return
	 */
	public boolean isLinked(VWMLObject obj) {
		return link.isLinked(obj);
	}
	
	/**
	 * Shows all dependency of given object
	 * @param startObj
	 */
	public void iterate(List<VWMLObject> dependencyList) {
		link.iterate(this, dependencyList);
	}
	
	/**
	 * Returns VWML object linked with current entity 
	 * @return
	 */
	public VWMLLink getLink() {
		return link;
	}
	
	public String[] getContextPath() {
		return contextPath;
	}

	public void setContextPath(String[] contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public String toString() {
		return "VWMLObject [id=" + id + ", readableId=" + readableId + "]";
	}

	@Override
	public int compareTo(VWMLObject o) {
		int r = 0;
		if (!equals(o)) {
			r = getId().toString().compareTo(o.getId().toString());
		}
		return r;
	}
}
