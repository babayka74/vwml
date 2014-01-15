package com.vw.lang.sink.java.interpreter.slft;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;

/**
 * Interprets single threaded term only
 * @author ogibayev
 *
 */
public class VWMLSingleTermInterpreter extends VWMLInterpreterImpl {

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

	public static VWMLSingleTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term, VWMLContext context) {
		return new VWMLSingleTermInterpreter(linkage, term, context);
	}

	public void setTerm(VWMLEntity term) {
		if (term != null) {
			List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
			tl.add(term);
			super.setTerms(tl);
		}
	}
	
	/**
	 * Clones current interpreter
	 */
	public VWMLInterpreterImpl clone() {
		VWMLInterpreterImpl cloned = instance(super.getLinkage(), null, null);
		cloned.setConfig(this.getConfig());
		return cloned;
	}
	
	/**
	 * Resets interpreter's data
	 */
	public void reset() {
		
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
		setContext(entity.getContext());
		// marks context as lifeterm's context. It activates special behavior when entity's context
		// is pushed into special stack in order to guarantee entity's context after 'assemble' operation
		getContext().setLifeTermContext(true);
		// associates context of interpreted term with interpretation stack
		getContext().setEntityInterpretationHistorySize(entity.getInterpretationHistorySize());
		getContext().setLinkOperationVisitor(entity.getLink().getLinkOperationVisitor());
		// initializes operation processor
		getProcessor().init(this);
		try {
			activateComplexInterpretationProcess(getLinkage(), getContext(), entity);
		}
		catch(Exception e) {
			throw new Exception("Exception caught during interpretation life term on context '" + getContext().getContext() + "'; exception '" + e + "'");
		}
	}
	
	/**
	 * Starts interpretation on existed stack
	 * @param stack
	 * @throws Exception
	 */
	public VWMLEntity decomposeAndInterpret(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
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
					activateComplexInterpretationProcess(linkage, context, le);
				}
				else {
					// simple pushes non-term entity to stack
					activateSimpleInterpretationProcess(le, linkage, context);
				}
			}
		}
		return entity;
	}

	public void activateComplexInterpretationProcess(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		VWMLEntity entity = le;
		VWMLEntity exeEntity = null;
		while(entity != null) { // usually entity which is result of 'EXE' operation
			VWMLOperation opImplicitlyAddedRef = null;
			// working with lifeterm's context
			if (entity.getContext() == null) {
				throw new Exception("entity '" + le + "' doesn't belong to any context !");
			}
			// the 'true' means that entity is being processed now and potential recurse detected - as result stack should be unwind
			// and interpretation process starts from this entity again, but on 'unwinded' stack
			if (!context.isEntityMarkedAsObservableInsideContext(le)) {
				if (!entity.isTerm() && entity.isMarkedAsComplexEntity()) {
					// assemble operation is used if we need to collect result of entity interpretation process
					opImplicitlyAddedRef = new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE);
					entity.addOperation(opImplicitlyAddedRef);
				}
				else
				if (entity.isTerm() && ((VWMLTerm)entity).getAssociatedEntity() != null) {
					entity = ((VWMLTerm)entity).getAssociatedEntity();
				}
			}
			else {
				// push entity to special stack and unwind current stack until we find place where
				// entity was marked as 'observable' term
				context.pushRecurseEntity(le);
				// context is prepared for unwind operation
				context.prepareForUnwinding();
				return;
			}
			// marks entity as observable; used in order to detect recurse
			context.markEntityAsObservableInsideContext(le);
			context.getStack().pushEmptyMark();
			context.pushContext(entity.getContext());
			// entity is decomposed and interpreted
			decomposeAndInterpret(linkage, context, entity);
			// the 'defferredEntity' is result of EXE operation
			VWMLEntity defferredEntity = null;
			// term is interpreted only in case if unwinding process isn't in progress
			if (!context.isUnwinding()) {
				// the 'EXE' operations is specific operation - it starts to interpret term fetched from the stack not in recursing manner
				// this entity can be considered as 'defferred' since it is interpreted on next iteration
				defferredEntity = termInterpretation(linkage, context, le);
			}
			if (opImplicitlyAddedRef != null) {
				le.removeOperation(opImplicitlyAddedRef);
				if (le == exeEntity) {
					// consume ()
					VWMLEntity eEmptyToBeConsumed = (VWMLEntity)context.getStack().peek();
					if (eEmptyToBeConsumed != null && eEmptyToBeConsumed.isMarkedAsComplexEntity()) {
						context.getStack().popUntilEmptyMark();
					}
				}
			}
			if (le == context.peekRecurseEntity()) {
				// stack recurse detected and stack should be unwind 
				VWMLEntity unwindTill = context.getTopOfStackWhenEntityWasMarkedAsObservable(le);
				if (unwindTill != null) {
					context.unwindStackTill(unwindTill);
				}
				// stack unwind process finished - remove entity and starts interpretation again, in loop
				context.popRecurseEntity();
				context.finishUnwinding();
				// stack has been unwind and interpretation is started again
				defferredEntity = le;
			}
			// entity has been interpreted and should be unmarked
			context.unmarkEntityAsObservableInsideContext(le);
			// starts iteration of entity - result of 'EXE' operation
			exeEntity = le = entity = defferredEntity;
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
	protected VWMLEntity termInterpretation(VWMLLinkage linkage, VWMLContext context, VWMLEntity le) throws Exception {
		VWMLEntity exeEntity = null;
		if (le.isTerm()) { // means that entity has operations which should be executed on top of stack
			// executes operations and serves stack
			VWMLLinkIncrementalIterator itOps = le.acquireOperationsIterator();
			// executes set of operations on stack; all operations are performed on top of stack
			for(VWMLOperation op = le.getOperation(itOps); op != null; op = le.getOperation(itOps)) {
				// actually calls handle to process operation
				if (exeEntity == null) {
					handleOperation(linkage, context, op);
				}
				else {
					exeEntity.addOperation(op); // deferred operations
				}
				// EXE is a special operation and its implementation lies out of general rules for 
				// regular operations
				if (op.getOpCode() == VWMLOperationsCode.OPEXECUTE) {
					exeEntity = (VWMLEntity)context.getStack().pop();
				}
			}
			// removes 'empty mark' by propagating term's result to following term on stack
			context.getStack().consumeEmptyMark();
			context.popContext();
		}
		return exeEntity;
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
		context.getStack().push(le);
	}	
}
