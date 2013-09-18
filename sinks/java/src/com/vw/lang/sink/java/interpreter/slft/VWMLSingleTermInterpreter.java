package com.vw.lang.sink.java.interpreter.slft;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Interprets single threaded term only
 * @author ogibayev
 *
 */
public class VWMLSingleTermInterpreter extends VWMLIterpreterImpl {
	
	private VWMLSingleTermInterpreter() {
	}
	
	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term) {
		setTerm(term);
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
		activateComplexInterpretationProcess(getLinkage(), getStack(), entity);
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	public VWMLEntity startOnExistedStack(VWMLLinkage linkage, VWMLStack stack, VWMLEntity entity) throws Exception {
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		// check if entity has at least one term
		if (!isEntityListOfTerms(entity, it)) {
			// simple pushes entity to stack, no operations performed
			activateSimpleInterpretationProcess(entity, linkage, stack);
			return entity;
		}
		// iterate through linked object, if exist
		if (it != null) { // complex entity is interpreted
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (le.isTerm() && ((VWMLTerm)le).getAssociatedEntity() != null) {
					activateComplexInterpretationProcess(linkage, getStack(), le);
				}
				else {
					activateSimpleInterpretationProcess(le, linkage, stack);
				}
			}
		}
		else {
			activateComplexInterpretationProcess(linkage, stack, entity);
		}
		return entity;
	}

	public void termInterpretation(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		if (le.isTerm()) { // means that entity has operations which should be executed on top of stack
			// executes operations and serves stack
			VWMLLinkIncrementalIterator itOps = le.acquireOperationsIterator();
			// executes set of operations on stack; all operations are performed on top of stack
			for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
				// actually calls handle to process operation
				handleOperation(null, linkage, stack, op);
			}
		}
	}

	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		startOnExistedStack(linkage, stack, ((VWMLTerm)le).getAssociatedEntity());
		termInterpretation(linkage, stack, le);
	}
	
	/**
	 * Executes operatoion on top of stack and serves stack
	 * @param linkage
	 * @param stack
	 * @param op
	 * @throws Exception
	 */
	protected VWMLEntity handleOperation(VWMLEntity entity, VWMLLinkage linkage, VWMLStack stack, VWMLOperation op) throws Exception {
		// processor de-multiplexes this call
		return getProcessor().processOperation(entity, this, linkage, stack, op);
	}
	
	private void activateSimpleInterpretationProcess(VWMLEntity le, VWMLLinkage linkage, VWMLStack stack) throws Exception {
		if (le.isTerm() && ((VWMLTerm)le).getAssociatedEntity() != null) {
			termInterpretation(linkage, stack, le);
		}
		else {
			// adds non-term to stack
			stack.push(le);
		}
	}	
	
	private boolean isEntityListOfTerms(VWMLEntity entity, VWMLLinkIncrementalIterator it) {
		if (entity.isTerm()) {
			return true;
		}
		boolean termFound = false, resetItAfter = false;
		if (it == null) {
			it = entity.getLink().acquireLinkedObjectsIterator();
		}
		else {
			resetItAfter = true;
		}
		if (it != null) {
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (le.isTerm()) {
					termFound = true;
					break;
				}
			}
		}
		if (resetItAfter) {
			it.setIt(0);
		}
		return termFound;
	}
}
