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
import com.vw.lang.sink.java.operations.VWMLOperationsCode;

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
		// associates context of interpreted term with interpretation stack
		getStack().setContext(entity.getOriginalContext());
		getStack().setContextPath(entity.getContextPath());
		activateComplexInterpretationProcess(getLinkage(), getStack(), entity);
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	@Override
	public VWMLEntity startOnExistedStack(VWMLLinkage linkage, VWMLStack stack, VWMLEntity entity) throws Exception {
		if (!entity.isTerm() && !entity.isMarkedAsComplexEntity()) {
			activateSimpleInterpretationProcess(entity, linkage, stack);
			return entity;
		}
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		// iterate through linked object, if exist
		if (it != null) { // complex entity is interpreted
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (le.isTerm() || le.isMarkedAsComplexEntity()) {
					// recursive call
					activateComplexInterpretationProcess(linkage, getStack(), le);
				}
				else {
					// simple pushes non-term entity to stack
					activateSimpleInterpretationProcess(le, linkage, stack);
				}
			}
		}
		else {
			// interprets term associated with simple entity
			activateComplexInterpretationProcess(linkage, stack, entity);
		}
		return entity;
	}

	@Override
	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		VWMLEntity entity = le;
		VWMLOperation opImplicitlyAdded = null;
		stack.pushEmptyMark();
		if (!le.isTerm() && le.isMarkedAsComplexEntity()) {
			opImplicitlyAdded = new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE);
			le.addOperation(opImplicitlyAdded);
		}
		else
		if (le.isTerm() && ((VWMLTerm)le).getAssociatedEntity() != null) {
			entity = ((VWMLTerm)le).getAssociatedEntity();
		}
		startOnExistedStack(linkage, stack, entity);
		termInterpretation(linkage, stack, le);
		if (opImplicitlyAdded != null) {
			le.removeOperation(opImplicitlyAdded);
		}
	}
	
	/**
	 * Executes operation on top of stack and serves stack
	 * @param linkage
	 * @param stack
	 * @param op
	 * @throws Exception
	 */
	protected void handleOperation(VWMLLinkage linkage, VWMLStack stack, VWMLOperation op) throws Exception {
		// processor de-multiplexes this call
		getProcessor().processOperation(this, linkage, stack, op);
	}

	/**
	 * Executes term's operations on stack; interpreted entity should be already on top of stack
	 */
	protected void termInterpretation(VWMLLinkage linkage, VWMLStack stack, VWMLEntity le) throws Exception {
		if (le.isTerm()) { // means that entity has operations which should be executed on top of stack
			// executes operations and serves stack
			VWMLLinkIncrementalIterator itOps = le.acquireOperationsIterator();
			// executes set of operations on stack; all operations are performed on top of stack
			for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
				// actually calls handle to process operation
				handleOperation(linkage, stack, op);
			}
			// removes 'empty mark' by propagating term's result to following term on stack
			stack.consumeEmptyMark();
		}
	}

	/**
	 * Simple pushes non-term entity to stack
	 * @param le
	 * @param linkage
	 * @param stack
	 * @throws Exception
	 */
	private void activateSimpleInterpretationProcess(VWMLEntity le, VWMLLinkage linkage, VWMLStack stack) throws Exception {
		// adds non-term to stack
		stack.push(le);
	}	
}
