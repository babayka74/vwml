package com.vw.lang.sink.java.link;

import com.vw.lang.sink.java.VWMLObject;

/**
 * VWML's link; it describes how VWML objects are linked
 * @author ogibayev
 *
 */
public class VWMLLink extends VWMLObject {
	
	// link types
	public static enum TYPE {
		CHILD,
		SIBLING
	}
	
	//strategy of unlink operation
	public static enum UNLINK_STRATEGY {
		FROM_PARENT(0x1),
		FROM_SIBLING(0x2),
		FROM_ALL(FROM_PARENT.ordinal() | FROM_SIBLING.ordinal());
		
		private int s;
		
		UNLINK_STRATEGY(int s) {
			this.s = s;
		}
		
		public int getValue() {
			return s;
		}
	}
	
	private VWMLObject itself;
	// points to parent object
	private VWMLObject parent;
	// siblings, objects of the same level
	private VWMLObject sibling;
	// first child; if link has more than 1 child then they can be accessed through sibling field
	private VWMLObject child;
	
	public VWMLLink(VWMLObject itself) {
		super();
		this.itself = itself;
	}

	public VWMLLink(VWMLObject parent, VWMLObject sibling, VWMLObject child) {
		super();
		this.parent = parent;
		this.sibling = sibling;
		this.child = child;
	}

	public VWMLObject getParent() {
		return parent;
	}

	public void setParent(VWMLObject parent) {
		this.parent = parent;
	}

	public VWMLObject getSibling() {
		return sibling;
	}

	public void setSibling(VWMLObject sibling) {
		this.sibling = sibling;
	}

	public VWMLObject getChild() {
		return child;
	}

	public void setChild(VWMLObject child) {
		this.child = child;
	}

	public VWMLObject getItself() {
		return itself;
	}

	public void setItself(VWMLObject itself) {
		this.itself = itself;
	}

	/**
	 * Links object to object which is referenced by field 'itself' according to type's rule
	 * @param type {CHILD | SIBLING}
	 * @param linked object to be linked
	 */
	public void link(VWMLLink.TYPE type, VWMLObject linked) {
		if (type == VWMLLink.TYPE.CHILD) {
			VWMLObject c = itself.getLink().getChild();
			if (c == null) {
				itself.getLink().setChild(linked);
			}
			else {
				// iterate over all children to find last sibling; last sbling will be last child of object
				// referenced by 'itself'
				VWMLObject p = null;
				while(c != null) {
					p = c;
					c = c.getLink().getSibling();
				}
				p.getLink().setSibling(linked);
			}
		}
		else
		if (type == VWMLLink.TYPE.SIBLING) {
			VWMLObject s = itself, p = null;
			while(s != null) {
				p = s;
				s = itself.getLink().getSibling();
			}
			p.getLink().setSibling(linked);
		}
		linked.getLink().setParent(itself);
	}

	/**
	 * Current object is unlinked from the given object
	 * @param obj
	 */
	public void unlinkFrom(VWMLObject obj) {
		
	}
	
	/**
	 * Unlinks given object
	 * @param obj
	 */
	public void unlink(VWMLLink.UNLINK_STRATEGY mixedStrategy) {
		int mixedStrategyMask = mixedStrategy.ordinal();
		int i = 1;
		while(mixedStrategyMask > 0) {
			int strategy = (mixedStrategyMask >> i) & 0x01;
			if (strategy == VWMLLink.UNLINK_STRATEGY.FROM_PARENT.ordinal()) {
				unlinkFromParent(itself);
			}
			else
			if (strategy == VWMLLink.UNLINK_STRATEGY.FROM_SIBLING.ordinal()) {
				unlinkFromSibling(itself);
			}
			i++;
		}
	}

	/**
	 * Unlinks object from both parent and siblings
	 * @param obj
	 */
	private void unlinkFromParent(VWMLObject obj) {
		VWMLObject parent = obj.getLink().getParent();
		if (parent != null) {
			obj.getLink().setParent(null);
			VWMLObject c = parent.getLink().getChild(), p = null;
			while(c != obj && c != null) {
				p = c;
				c = c.getLink().getSibling();
			}
			if (c != null) {
				if (p != null) {
					p.getLink().setSibling(c.getLink().getSibling());
				}
				else {
					parent.getLink().setChild(null);
				}
			}
		}
	}

	private void unlinkFromSibling(VWMLObject obj) {
		VWMLObject parent = obj.getLink().getParent();
		if (parent != null) {
			VWMLObject c = parent.getLink().getChild(), p = null;
			while(c != obj && c != null) {
				p = c;
				c = c.getLink().getSibling();
			}
			if (c != null && p != null) {
				p.getLink().setSibling(c.getLink().getSibling());
			}
		}
	}

	@Override
	public String toString() {
		return "VWMLLink [parent=" + parent + ", sibling=" + sibling
				+ ", child=" + child + "]";
	}
}
