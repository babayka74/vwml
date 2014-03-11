package com.vw.lang.sink.java.operations;

import com.vw.lang.sink.java.VWMLObject;

/**
 * VWML's operation (see language specification)
 * @author ogibayev
 *
 */
public class VWMLOperation extends VWMLObject {
	private VWMLOperationsCode opCode;

	public VWMLOperation() {
		super(VWMLOperationsCode.getDefault(), VWMLOperationsCode.getDefault(), null);
	}

	public VWMLOperation(VWMLOperationsCode opCode) {
		super(opCode.toValue(),opCode.toValue(), null);
		this.opCode = opCode;
	}

	public VWMLOperationsCode getOpCode() {
		return opCode;
	}

	public void setOpCode(VWMLOperationsCode opCode) {
		super.setId(opCode.toValue());
		this.opCode = opCode;
	}
}
