package com.vw.lang.sink.java.entity;

import com.vw.lang.sink.java.VWMLObject;

public class ArgPair {
	// argument's place inside the complex entity (CallP operation)
	private String argPlaceNumber;
	// reference to real argument which is indicated by argNumber and passed by CallP operation
	private VWMLObject argAsRef;
	
	public String getPlaceNumber() {
		return argPlaceNumber;
	}
	
	public void setPlaceNumber(String argPlaceNumber) {
		this.argPlaceNumber = argPlaceNumber;
	}
	
	public VWMLObject getArgAsRef() {
		return argAsRef;
	}

	public void setArgAsRef(VWMLObject argAsRef) {
		this.argAsRef = argAsRef;
	}
}
