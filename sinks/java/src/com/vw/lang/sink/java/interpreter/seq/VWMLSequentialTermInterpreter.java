package com.vw.lang.sink.java.interpreter.seq;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLSequentialTermInterpreterCodeStackFrame;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;

/**
 * Sequential interpreter; the terms are interpreted sequentially in one thread
 * @author ogibayev
 *
 */
public class VWMLSequentialTermInterpreter extends VWMLIterpreterImpl {

	private static final int continueProcessingOfCurrentEntity = 0;
	private static final int nextEntityToProcess = 1;
	private static final int stopProcessing = 2;
	private static final int finishedEntityProcessing = 3;

	private static final VWMLOperation opImplicitlyAddedRef = new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE);

	// interpreter's temprary states
	// last step's result
	private int result = continueProcessingOfCurrentEntity;
	// last term
	private VWMLEntity lastInterpretedTerm = null;
	// last assoociated term's entity
	private VWMLEntity lastInterpretedEntity = null;
	
	
	private VWMLSequentialTermInterpreter() {
	}
	
	private VWMLSequentialTermInterpreter(VWMLLinkage linkage, VWMLEntity term) {
		setTerm(term);
		setLinkage(linkage);
	}

	private VWMLSequentialTermInterpreter(VWMLLinkage linkage, VWMLEntity term, VWMLContext context) {
		setTerm(term);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLSequentialTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term) {
		return new VWMLSequentialTermInterpreter(linkage, term);
	}

	public static VWMLSequentialTermInterpreter instance(VWMLLinkage linkage, VWMLEntity term, VWMLContext context) {
		return new VWMLSequentialTermInterpreter(linkage, term, context);
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
	public VWMLIterpreterImpl clone() {
		VWMLIterpreterImpl cloned = instance(super.getLinkage(), null, null);
		cloned.setConfig(this.getConfig());
		return cloned;
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
			getContext().setNextProcessedEntity(entity);
			VWMLSequentialTermInterpreterCodeStackFrame frame = buildCodeStackFrame(entity, entity, null);
			getContext().setCurrentCodeStackFrame(frame);
			startCompleteInterpretationProcess(getLinkage(), getContext());
		}
		catch(Exception e) {
			throw new Exception("Exception caught during interpretation life term on context '" + getContext().getContext() + "'; exception '" + e + "'");
		}
	}
	
	protected void startCompleteInterpretationProcess(VWMLLinkage linkage, VWMLContext context) throws Exception {
		result = continueProcessingOfCurrentEntity;
		lastInterpretedTerm = context.getCurrentCodeStackFrame().getTerm();
		lastInterpretedEntity = context.getCurrentCodeStackFrame().getAssociatedEntity();
		while(result != stopProcessing) {
			if (result != finishedEntityProcessing) {
				// working with lifeterm's context
				if (lastInterpretedEntity.getContext() == null) {
					throw new Exception("entity '" + lastInterpretedTerm + "' doesn't belong to any context !");
				}
				boolean pushEmptyMark = false;
				if (!lastInterpretedEntity.isTerm() && lastInterpretedEntity.isMarkedAsComplexEntity()) {
					// assemble operation is used if we need to collect result of entity interpretation process
					lastInterpretedEntity.addOperation(opImplicitlyAddedRef);
					lastInterpretedEntity.setMarkedAsArtificalTerm(true);
					pushEmptyMark = true;
				}
				else
				if (lastInterpretedEntity.isTerm() && ((VWMLTerm)lastInterpretedEntity).getAssociatedEntity() != null) {
					lastInterpretedEntity = ((VWMLTerm)lastInterpretedEntity).getAssociatedEntity();
					pushEmptyMark = true;
				}
				if (pushEmptyMark) {
					context.getStack().pushEmptyMark();
					context.pushContext(lastInterpretedEntity.getContext());
				}
				// push stack frame
				pushCurrentStackFrame(context, lastInterpretedTerm, lastInterpretedEntity, null);				
				if (checkIfDecompositionNeeded(linkage, context, lastInterpretedEntity)) {
					// entity is decomposed and next entity is returned
					result = decomposeAndGetNext(linkage, context);
					if (result == nextEntityToProcess && context.getNextProcessedEntity() != null) {
						// process next entity
						lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
						continue;
					} // else processing current entity					
				}
			} // entity processed - check its operations, if they are exist
			else {
				lastInterpretedTerm = context.getCurrentCodeStackFrame().getTerm();
				lastInterpretedEntity = context.getCurrentCodeStackFrame().getAssociatedEntity();
			}
			// the 'defferredEntity' is result of EXE operation
			// the 'EXE' operations is specific operation - it starts to interpret term fetched from the stack not in recursing manner
			// this entity can be considered as 'defferred' since it is interpreted on next iteration
			VWMLEntity defferredEntity = termInterpretation(linkage, context, lastInterpretedTerm);
			if (defferredEntity != null) {
				if (checkVWMLRecursion(context, defferredEntity)) {
					// removes until recursive entity and resets its iterator
					resolveVWMLRecursion(context, defferredEntity);
				}
				else {
					context.popStackFrame();
				}
				defferredEntity.setOperateByExe(true);
				markVWMLEntityAsProbableRecursion(context, defferredEntity);
				context.setCurrentCodeStackFrame((VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame());
				context.setNextProcessedEntity(defferredEntity);
				lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
				resetArticialTermProperty(lastInterpretedTerm);				
				result = nextEntityToProcess;
				continue;
			}
			if (lastInterpretedTerm.isOperateByExe()) {
				lastInterpretedTerm.setOperateByExe(false);
				// consume ()
				VWMLEntity eEmptyToBeConsumed = (VWMLEntity)context.getStack().peek();
				if (eEmptyToBeConsumed != null && eEmptyToBeConsumed.isMarkedAsComplexEntity() && ((VWMLComplexEntity)eEmptyToBeConsumed).getLink().getLinkedObjectsOnThisTime() == 0) {
					context.getStack().popUntilEmptyMark();
				}
			}
			resetArticialTermProperty(lastInterpretedTerm);
			// un-mark probable entity's recursion
			unmarkVWMLEntityAsProbableRecursion(context, lastInterpretedTerm);			
			context.popStackFrame();
			if (defferredEntity == null) {
				// pops stack frame
				result = decomposeAndGetNext(linkage, context);
				context.setCurrentCodeStackFrame((VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame());
				if (result == nextEntityToProcess && context.getNextProcessedEntity() != null) {
					// process next entity
					lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
				}
				else
				if (result == continueProcessingOfCurrentEntity) {
					throw new Exception("invalid state of interpreter; state is '" + result + "'; last interpreted entity '" + lastInterpretedEntity.getId() + "'");
				}
			}
		}
	}

	protected int decomposeAndGetNext(VWMLLinkage linkage, VWMLContext context) throws Exception {
		VWMLEntity nextProcessed = null;
		int result = continueProcessingOfCurrentEntity;
		
		VWMLSequentialTermInterpreterCodeStackFrame frame = (VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame();
		if (frame == null) {
			context.setNextProcessedEntity(nextProcessed);
			result = stopProcessing;
			return result;
		}
		VWMLLinkIncrementalIterator it = frame.getItEntity();
		if (it != null) {
			VWMLEntity le = (VWMLEntity)frame.getAssociatedEntity().getLink().peek(it);
			// iterate through linked object, if exist
			if (le != null) {
				result = nextEntityToProcess;
				nextProcessed = le;				
			}
			else {
				result = finishedEntityProcessing;
			}
		}
		context.setNextProcessedEntity(nextProcessed);
		return result;
	}

	protected void resetArticialTermProperty(VWMLEntity term) {
		if (term.isTerm() && term.isMarkedAsArtificalTerm()) {
			term.removeOperation(opImplicitlyAddedRef);
			term.setMarkedAsArtificalTerm(false);
		}
	}
	
	protected void markVWMLEntityAsProbableRecursion(VWMLContext context, VWMLEntity le) {
		context.markEntityAsObservableInsideContext(le);
	}

	protected void unmarkVWMLEntityAsProbableRecursion(VWMLContext context, VWMLEntity le) {
		context.unmarkEntityAsObservableInsideContext(le);
	}

	protected void resolveVWMLRecursion(VWMLContext context, VWMLEntity le) {
		boolean found = false;
		while(context.peekStackFrame() != null && !found) {
			VWMLSequentialTermInterpreterCodeStackFrame frame = (VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame();
			boolean pop = false;
			if (frame.getTerm().isTerm()) {
				pop = true;
			}
			unmarkVWMLEntityAsProbableRecursion(context, frame.getTerm());
			resetArticialTermProperty(frame.getTerm());
			frame.getTerm().setOperateByExe(false);
			if (le.equals(frame.getTerm())) {
				found = true;
			}
			else
			if (pop) {
				context.getStack().pop();
				context.popContext();
			}
			context.popStackFrame();
		}
	}
	
	protected boolean checkVWMLRecursion(VWMLContext context, VWMLEntity le) {
		// the 'true' means that entity is being processed now and potential recurse detected - as result stack should be unwind
		// and interpretation process starts from this entity again, but on 'unwinded' stack
		return context.isEntityMarkedAsObservableInsideContext(le);
	}

	protected boolean checkIfDecompositionNeeded(VWMLLinkage linkage, VWMLContext context, VWMLEntity entity) throws Exception {
		if (!entity.isTerm() && !entity.isMarkedAsComplexEntity()) {
			pushEntityToContextStack(entity, linkage, context);
			return false;
		}
		return true;
	}

	protected void pushCurrentStackFrame(VWMLContext context, VWMLEntity term, VWMLEntity entity, VWMLOperation opImplicitlyAddedRef) {
		context.pushStackFrame(buildCodeStackFrame(term, entity, opImplicitlyAddedRef));
	}
	
	protected VWMLSequentialTermInterpreterCodeStackFrame buildCodeStackFrame(VWMLEntity term, VWMLEntity entity, VWMLOperation opImplicitlyAddedRef) {
		VWMLSequentialTermInterpreterCodeStackFrame frame = new VWMLSequentialTermInterpreterCodeStackFrame();
		frame.setId("__code_stack_frame__");
		frame.setTerm(term);
		frame.setItEntity(entity.getLink().acquireLinkedObjectsIterator());
		frame.setAssociatedEntity(entity);
		frame.setOpImplicitlyAdded(opImplicitlyAddedRef);
		return frame;
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
	private void pushEntityToContextStack(VWMLEntity le, VWMLLinkage linkage, VWMLContext context) throws Exception {
		// adds non-term to stack
		context.getStack().push(le);
	}	
}
