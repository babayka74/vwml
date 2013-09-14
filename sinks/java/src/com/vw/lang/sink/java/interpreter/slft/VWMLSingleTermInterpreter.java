package com.vw.lang.sink.java.interpreter.slft;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationProcessor;

/**
 * Interprets single threaded term only
 * @author ogibayev
 *
 */
public class VWMLSingleTermInterpreter extends VWMLIterpreterImpl {
	
	private VWMLSingleTermInterpreter() {
	}
	
	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term) {
		setTerms(new ArrayList<VWMLEntity>());
		setLinkage(linkage);
	}

	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term, VWMLStack stack) {
		setTerm(term);
		setLinkage(linkage);
		setStack(stack);
	}
	
	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term) {
		return new VWMLSingleTermInterpreter(linkage, term);
	}

	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term, VWMLStack stack) {
		return new VWMLSingleTermInterpreter(linkage, term, stack);
	}

	public void setTerm(VWMLEntity term) {
		List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
		tl.add(term);
		super.setTerms(tl);
	}
	
	/**
	 * Starts interpretation logic
	 * @throws Exception
	 */
	public void start() throws Exception {
		if (getConfig() == null) {
			// default configuration is used
			setConfig(VWMLInterpreterConfiguration.instance());
		}
		if (getTerms() == null  || getTerms().size() == 0) {
			throw new Exception("term should be set before method is called");
		}
		VWMLEntity entity = getTerms().get(0);
		startOnExistedStack(getLinkage(), getStack(), entity);
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	public void startOnExistedStack(VWMLLinkage linkage, VWMLStack stack, VWMLEntity entity) throws Exception {
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
	protected void handleOperation(VWMLLinkage linkage, VWMLStack stack, VWMLOperation op) throws Exception {
		// processor de-multiplexes this call
		getProcessor().processOperation(linkage, stack, op);
	}
}
