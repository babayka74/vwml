package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	// node's controlled interpreter
	private VWMLIterpreterImpl interpreter;
	// node's automata
	private VWMLConflictRingNodeAutomata nodeAutomata = VWMLConflictRingNodeAutomata.build();
	// index of conflict fragment 
	private int sigma;
	
	public VWMLConflictRingNode() {
		
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}

	public VWMLIterpreterImpl getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(VWMLIterpreterImpl interpreter) {
		this.interpreter = interpreter;
	}

	public int getSigma() {
		return sigma;
	}

	public void setSigma(int sigma) {
		this.sigma = sigma;
	}
	
	public void incSigma() {
		sigma++;
	}
	
	public void decSigma() {
		if (sigma > 0) {
			sigma--;
		}
	}
}
