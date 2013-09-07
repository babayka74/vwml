package com.vw.lang.sink.java.interpreter.slft;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Interprets single thread life term only
 * @author ogibayev
 *
 */
public class VWMLSingleThreadTermInterpreter {
	
	// lifeterm to be interpreted
	private VWMLEntity term = null; 
	private Stack stack = Stack.instance();

	private VWMLSingleThreadTermInterpreter() {
	}
	
	private VWMLSingleThreadTermInterpreter(VWMLEntity term) {
		setTerm(term);
	}
	
	public static VWMLSingleThreadTermInterpreter instance(VWMLEntity term) {
		return new VWMLSingleThreadTermInterpreter(term);
	}

	public VWMLEntity getTerm() {
		return term;
	}

	public void setTerm(VWMLEntity term) {
		this.term = term;
	}
	
	/**
	 * Starts interpretation logic
	 * @throws Exception
	 */
	public void start() throws Exception {
		if (getTerm() == null) {
			throw new Exception("term should be set before method is called");
		}
		VWMLEntity entity = getTerm();
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		// iterate through linked object
		for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
			// check relation type; if it has IAS relation than we don't add it. (IAS relation isn't term - it is interpreting expression)
			if (le.getInterpreting() != null) {
				// IAS relation is interpreted by executing operation '~'
				continue;
			}
			// put it to stack
			stack.push(le);			
			if (le.isTerm()) { // means that entity has operations which should be executed on top of stack
				// executes operations and serves stack
				VWMLLinkIncrementalIterator itOps = entity.getLink().acquireLinkedObjectsIterator();
				for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
					handleOperation(stack, op);
				}
			}
		}
	}
	
	/**
	 * Executes operatoion on top of stack and serves stack
	 * @param stack
	 * @param op
	 * @throws Exception
	 */
	protected void handleOperation(Stack stack, VWMLOperation op) throws Exception {
		
	}
}
