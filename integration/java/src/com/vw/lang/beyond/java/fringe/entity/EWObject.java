package com.vw.lang.beyond.java.fringe.entity;

import java.util.List;

/**
 * Root object for all EW's items; used as class marker at this time
 * @author ogibayev
 *
 */
public class EWObject implements Cloneable, Comparable<EWObject> {
	private Object id;
	private String readableId;
	// entity can be linked with another entity or command
	private EWLink link = new EWLink(this);
	
	public EWObject() {
		super();
	}

	public EWObject(Object id, String readableId) {
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
		EWObject other = (EWObject) obj;
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

	/**
	 * Links current entity with another VWML object which can be entity or command
	 * @param linked
	 */
	public void link(EWObject linked) {
		link.link(linked);
	}
	
	/**
	 * Unlinks objects: current and given 
	 * @param obj
	 */
	public void unlink(EWObject obj) {
		link.unlinkFrom(obj);
	}
	
	/**
	 * Returns 'true' in case if objects were linked
	 * @param obj
	 * @return
	 */
	public boolean isLinked(EWObject obj) {
		return link.isLinked(obj);
	}

	/**
	 * Shows all dependency of given object
	 * @param startObj
	 */
	public void iterate(List<EWObject> dependencyList) {
		link.iterate(this, dependencyList);
	}
	
	/**
	 * Returns VWML object linked with current entity 
	 * @return
	 */
	public EWLink getLink() {
		return link;
	}
	
	public String buildReadableId() {
		setReadableId(null);
		return getReadableId();
	}

	@Override
	public String toString() {
		return "VWMLObject [id=" + id + ", readableId=" + readableId + "]";
	}

	@Override
	public int compareTo(EWObject o) {
		int r = 0;
		if (!equals(o)) {
			r = getId().toString().compareTo(o.getId().toString());
		}
		return r;
	}
}
