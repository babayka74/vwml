package com.vw.lang.sink.java.interpreter.slft;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.Stack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationProcessor;

/**
 * Interprets single threaded term only
 * @author ogibayev
 *
 */
public class VWMLSingleTermInterpreter {
	
	private VWMLLinkage linkage = null;
	// lifeterm to be interpreted
	private VWMLEntity term = null; 
	// internal worker thread
	private Stack stack = Stack.instance();
	// operating processor
	private VWMLOperationProcessor processor = VWMLOperationProcessor.instance();

	private VWMLSingleTermInterpreter() {
	}
	
	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term) {
		setTerm(term);
		setLinkage(linkage);
	}

	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term, Stack stack) {
		setTerm(term);
		setLinkage(linkage);
		setStack(stack);
	}
	
	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term) {
		return new VWMLSingleTermInterpreter(linkage, term);
	}

	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term, Stack stack) {
		return new VWMLSingleTermInterpreter(linkage, term, stack);
	}
	
	public VWMLEntity getTerm() {
		return term;
	}

	public void setTerm(VWMLEntity term) {
		this.term = term;
	}
	
	public VWMLLinkage getLinkage() {
		return linkage;
	}

	public void setLinkage(VWMLLinkage linkage) {
		this.linkage = linkage;
	}

	public Stack getStack() {
		return stack;
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
		startOnExistedStack(linkage, stack, entity);
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	public void startOnExistedStack(VWMLLinkage linkage, Stack stack, VWMLEntity entity) throws Exception {
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
				// executes set of operations on stack; all operations are performed on top of stack
				for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
					// actually calls handle to process operation
					handleOperation(linkage, stack, op);
				}
			}
		}
	}
	
	/**
	 * Executes operatoion on top of stack and serves stack
	 * @param linkage
	 * @param stack
	 * @param op
	 * @throws Exception
	 */
	protected void handleOperation(VWMLLinkage linkage, Stack stack, VWMLOperation op) throws Exception {
		// processor de-multiplexes this call
		processor.processOperation(linkage, stack, op);
	}

	protected void setStack(Stack stack) {
		this.stack = stack;
	}
	
	
}
