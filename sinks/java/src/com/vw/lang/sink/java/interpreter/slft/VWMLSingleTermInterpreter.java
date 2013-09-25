package com.vw.lang.sink.java.interpreter.slft;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
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

	private VWMLSingleTermInterpreter(VWMLLinkage linkage, VWMLEntity term, VWMLContext context) {
		setTerm(term);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term) {
		return new VWMLSingleTermInterpreter(linkage, term);
	}

	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term, VWMLContext stack) {
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
		getContext().setContext(entity.getOriginalContext());
		getContext().setContextPath(entity.getContextPath());
		getContext().setEntityInterpretationHistorySize(entity.getInterpretationHistorySize());
		getContext().setLinkOperationVisitor(entity.getLink().getLinkOperationVisitor());
		activateComplexInterpretationProcess(getLinkage(), getContext(), entity);
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	@Override
	public VWMLEntity startOnExistedContext(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
		if (!entity.isTerm() && !entity.isMarkedAsComplexEntity()) {
			activateSimpleInterpretationProcess(entity, linkage, context);
			return entity;
		}
		VWMLLinkIncrementalIterator it = entity.getLink().acquireLinkedObjectsIterator();
		// iterate through linked object, if exist
		if (it != null) { // complex entity is interpreted
			for(VWMLEntity le = (VWMLEntity)entity.getLink().peek(it); le != null; le = (VWMLEntity)entity.getLink().peek(it)) {
				if (le.isTerm() || le.isMarkedAsComplexEntity()) {
					// recursive call
					activateComplexInterpretationProcess(linkage, getContext(), le);
				}
				else {
					// simple pushes non-term entity to stack
					activateSimpleInterpretationProcess(le, linkage, context);
				}
			}
		}
		else {
			// interprets term associated with simple entity
			activateComplexInterpretationProcess(linkage, context, entity);
		}
		return entity;
	}

	@Override
	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		VWMLEntity entity = le;
		VWMLOperation opImplicitlyAdded = null;
		context.getStack().pushEmptyMark();
		if (!le.isTerm() && le.isMarkedAsComplexEntity()) {
			opImplicitlyAdded = new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE);
			le.addOperation(opImplicitlyAdded);
		}
		else
		if (le.isTerm() && ((VWMLTerm)le).getAssociatedEntity() != null) {
			entity = ((VWMLTerm)le).getAssociatedEntity();
		}
		startOnExistedContext(linkage, context, entity);
		termInterpretation(linkage, context, le);
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
	protected void handleOperation(VWMLLinkage linkage, VWMLContext context, VWMLOperation op) throws Exception {
		// processor de-multiplexes this call
		getProcessor().processOperation(this, linkage, context, op);
	}

	/**
	 * Executes term's operations on stack; interpreted entity should be already on top of stack
	 */
	protected void termInterpretation(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		if (le.isTerm()) { // means that entity has operations which should be executed on top of stack
			// executes operations and serves stack
			VWMLLinkIncrementalIterator itOps = le.acquireOperationsIterator();
			// executes set of operations on stack; all operations are performed on top of stack
			for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
				// actually calls handle to process operation
				handleOperation(linkage, context, op);
			}
			// removes 'empty mark' by propagating term's result to following term on stack
			context.getStack().consumeEmptyMark();
		}
	}

	/**
	 * Simple pushes non-term entity to stack
	 * @param le
	 * @param linkage
	 * @param stack
	 * @throws Exception
	 */
	private void activateSimpleInterpretationProcess(VWMLEntity le, VWMLLinkage linkage, VWMLContext context) throws Exception {
		// adds non-term to stack
		VWMLStack stack = context.getStack();
		stack.push(le);
	}	
}
