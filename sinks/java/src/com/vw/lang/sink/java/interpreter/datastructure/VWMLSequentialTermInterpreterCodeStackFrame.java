package com.vw.lang.sink.java.interpreter.datastructure;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.operations.VWMLOperation;

public class VWMLSequentialTermInterpreterCodeStackFrame extends VWMLObject {
	// entity associated with term
	private VWMLEntity associatedEntity;
	// entity is being processed (like ret. instruction)
	private VWMLEntity term;
	// entity's iterator
	private VWMLLinkIncrementalIterator itEntity;
	// added to non-term complex entity in order to unify term and non-term complex entity processing
	// (__assemble__)
	private VWMLOperation opImplicitlyAdded;
	
	private static long s_stackFrameNum = 0;
	
	public VWMLSequentialTermInterpreterCodeStackFrame() {
		super("stack_frame_" + s_stackFrameNum);
		s_stackFrameNum++;
	}
	
	public VWMLSequentialTermInterpreterCodeStackFrame(	VWMLEntity term,
														VWMLEntity associatedEntity,
														VWMLLinkIncrementalIterator itEntity,
														VWMLOperation opImplicitlyAdded) {
		super("stack_frame_" + s_stackFrameNum);
		s_stackFrameNum++;
		this.associatedEntity = associatedEntity;
		this.term = term;
		this.itEntity = itEntity;
		this.opImplicitlyAdded = opImplicitlyAdded;
	}

	public VWMLEntity getAssociatedEntity() {
		return associatedEntity;
	}
	
	public void setAssociatedEntity(VWMLEntity associatedEntity) {
		this.associatedEntity = associatedEntity;
	}
	
	public VWMLEntity getTerm() {
		return term;
	}
	
	public void setTerm(VWMLEntity term) {
		this.term = term;
	}
	
	public VWMLLinkIncrementalIterator getItEntity() {
		return itEntity;
	}
	
	public void setItEntity(VWMLLinkIncrementalIterator itEntity) {
		this.itEntity = itEntity;
	}
	
	public VWMLOperation getOpImplicitlyAdded() {
		return opImplicitlyAdded;
	}
	
	public void setOpImplicitlyAdded(VWMLOperation opImplicitlyAdded) {
		this.opImplicitlyAdded = opImplicitlyAdded;
	}
}
