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
	private Object hashId;
	private String readableId;
	private String simpleName;
	private String[] parsedName;
	// complex name includes absolute or relative path delimited by '.'
	private boolean compoundName = false;
	// context path consists from set of entity ids
	// VWML object which takes responsibility of superviser of any changes on given object
	private VWMLObject superviser = null;
	// entity can be linked with another entity or command
	private VWMLLink link = new VWMLLink(this);
	// simple ref. counter
	private int refCounter = 0;
	
	public VWMLObject(Object hashId) {
		super();
		setHashId(hashId);
	}

	public VWMLObject(Object hashId, Object id, String readableId) {
		super();
		setHashId(hashId);
		setId(id);
		setReadableId(readableId);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VWMLObject other = (VWMLObject) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
		if (id != null && ((String)id).contains(".")) {
			setCompoundName(true);
			int le = ((String)id).lastIndexOf(".");
			simpleName = ((String)id).substring(le + 1);
			parsedName = ((String)id).split(".");			
		}
		else {
			simpleName = ((String)id);
		}
	}

	public String[] getParsedName() {
		return parsedName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public String getReadableId() {
		return readableId;
	}
	
	public void setReadableId(String readableId) {
		this.readableId = readableId;
	}
	
	public boolean isCompoundName() {
		return compoundName;
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
	
	public void setLink(VWMLLink link) {
		this.link = link;
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
	 * Following methods implement object's reference counting mechanism
	 * Used in case if object should be cached and released in case if counter is 0
	 * @return
	 */
	public int getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(int refCounter) {
		this.refCounter = refCounter;
	}
	
	public int incrementRefCounter() {
		this.refCounter++;
		return getRefCounter();
	}
	
	public int decrementRefCounter() {
		if (refCounter > 0) {
			refCounter--;
		}
		return getRefCounter();
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
	
	public String buildReadableId() {
		setReadableId(null);
		return getReadableId();
	}

	public void rebuildHashId(Object hashId) {
		setHashId(hashId);
		hashId = buildCompleteHashId();
		setHashId(hashId);
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

	protected void setHashId(Object hashId) {
		this.hashId = hashId;
	}
	
	protected Object buildCompleteHashId() {
		if (getId() != null && getHashId() != null) {
			return getHashId() + "." + getId();
		}
		return (getId() == null) ? getHashId() : getId();
	}
	
	protected void setCompoundName(boolean compoundName) {
		this.compoundName = compoundName;
	}

	protected Object getHashId() {
		return hashId;
	}
}
