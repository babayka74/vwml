package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.VWMLObject;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	public VWMLConflictRingNode() {
		
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}
}
