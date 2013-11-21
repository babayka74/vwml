package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	// node's controlled interpreter
	private VWMLInterpreterImpl interpreter;
	// node's automata
	private VWMLConflictRingNodeAutomata nodeAutomata = VWMLConflictRingNodeAutomata.build(this);
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

	/**
	 * Executes node's operational logic
	 * @throws Exception
	 */
	public void operate() throws Exception {
		VWMLConflictRingNodeAutomataInputs input = interpreter.getObserver().getConflictOperationalState();
		VWMLConflictRingNodeAutomataStates state = VWMLConflictRingNodeAutomataStates.STATE_PAS;
		if (getSigma() == 0) {
			state = VWMLConflictRingNodeAutomataStates.STATE_ACT;
		}
		nodeAutomata.runAction(this, input, state);
	}
	
	public VWMLInterpreterImpl getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(VWMLInterpreterImpl interpreter) {
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
