package com.vw.lang.sink.java.link;

import com.vw.lang.sink.java.entity.VWMLEntity;

public class VWMLLinkParentPair {
	private String contextId;
	private VWMLEntity parent;
	
	public VWMLLinkParentPair(String contextId, VWMLEntity parent) {
		super();
		this.contextId = contextId;
		this.parent = parent;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public VWMLEntity getParent() {
		return parent;
	}

	public void setParent(VWMLEntity parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contextId == null) ? 0 : contextId.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		VWMLLinkParentPair other = (VWMLLinkParentPair) obj;
		if (contextId == null) {
			if (other.contextId != null) {
				return false;
			}
		} else if (!contextId.equals(other.contextId)) {
			return false;
		}
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		return true;
	}
}
