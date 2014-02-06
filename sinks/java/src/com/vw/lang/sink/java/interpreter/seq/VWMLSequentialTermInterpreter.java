package com.vw.lang.sink.java.interpreter.seq;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLDynamicEntityProperties;
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
public class VWMLSequentialTermInterpreter extends VWMLInterpreterImpl {

	private static final VWMLOperation opImplicitlyAddedRef = new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE);

	// interpreter's temprary states
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
		setStatus(continueProcessingOfCurrentEntity);
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
		cloned.setMasterInterpreter(getMasterInterpreter());
		cloned.setTimerManager(getTimerManager());
		cloned.setRing(getRing());
		return cloned;
	}
		
	/**
	 * Resets interpreter's data
	 */
	public void reset() {
		lastInterpretedTerm = null;
		lastInterpretedEntity = null;
		if (getContext() != null) {
			getContext().clear();
		}
		setListener(null);
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
			setStatus(continueProcessingOfCurrentEntity);
			lastInterpretedTerm = getContext().getCurrentCodeStackFrame().getTerm();
			lastInterpretedEntity = getContext().getCurrentCodeStackFrame().getAssociatedEntity();
			if (!getConfig().isStepByStepInterpretation()) {
				startCompleteInterpretationProcess(getLinkage(), getContext());
			}
		}
		catch(Exception e) {
			throw new Exception("Exception caught during interpretation life term on context '" + getContext().getContext() + "'; exception '" + e + "'");
		}
	}

	public boolean step() throws Exception {
		boolean stopped = false;
		VWMLInterpreterImpl ii = peekInterpreterFromChildStack();
		if (ii != null) {
			ii.setRtNode(getRtNode());
			stopped = ii.step();
			if (stopped) { // means stopped
				popInterpreterFromChildStack();
			}
		}
		else {
			stepImpl(getLinkage(), getContext());
			stopped = (getStatus() == stopProcessing);
		}
		return stopped;
	}	
	
	protected void startCompleteInterpretationProcess(VWMLLinkage linkage, VWMLContext context) throws Exception {
		boolean stopped = false;
		while(!stopped) {
			stopped = step();
		}
	}

	protected void stepImpl(VWMLLinkage linkage, VWMLContext context) throws Exception {
		if (getStatus() != finishedEntityProcessing) {
			// working with lifeterm's context
			if (lastInterpretedEntity.getContext() == null) {
				throw new Exception("entity '" + lastInterpretedTerm + "' doesn't belong to any context !");
			}
			boolean pushEmptyMark = false;
			if (!lastInterpretedEntity.isTerm() && lastInterpretedEntity.isMarkedAsComplexEntity()) {
				// assemble operation is used if we need to collect result of entity interpretation process
				VWMLDynamicEntityProperties props = context.getEntityDynamicProperties(lastInterpretedEntity, true);
				props.setMarkedAsArtificalTerm(true);
				pushEmptyMark = true;
			}
			else
			if (lastInterpretedEntity.isTerm() && ((VWMLTerm)lastInterpretedEntity).getAssociatedEntity() != null) {
				lastInterpretedEntity = ((VWMLTerm)lastInterpretedEntity).getAssociatedEntity();
				pushEmptyMark = true;
			}
			if (pushEmptyMark) {
				// associates empty mark with processed entity (used when entity is assembled)
				context.getStack().pushEmptyMark();
				context.pushContext(lastInterpretedEntity.getContext());
			}
			// push stack frame
			pushCurrentStackFrame(context, lastInterpretedTerm, lastInterpretedEntity, null);				
			if (checkIfDecompositionNeeded(linkage, context, lastInterpretedEntity)) {
				// entity is decomposed and next entity is returned
				setStatus(decomposeAndGetNext(linkage, context));
				if (getStatus() == nextEntityToProcess && context.getNextProcessedEntity() != null) {
					// process next entity
					lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
					return;
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
		VWMLDynamicEntityProperties props = context.getEntityDynamicProperties(lastInterpretedEntity, false);
		VWMLEntity defferredEntity = termInterpretation(linkage, context,
														lastInterpretedTerm,
														(props == null) ? false : props.isMarkedAsArtificalTerm());
		if (defferredEntity != null) {
			if (checkVWMLRecursion(context, defferredEntity)) {
				// removes until recursive entity and resets its iterator
				resolveVWMLRecursion(context, defferredEntity);
			}
			else {
				VWMLSequentialTermInterpreterCodeStackFrame f = (VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame();
				unmarkVWMLEntityAsProbableRecursion(context, f.getTerm());
				context.popStackFrame();
			}
			context.getEntityDynamicProperties(defferredEntity, true).setOperatesByExe(true);
			markVWMLEntityAsProbableRecursion(context, defferredEntity);
			context.setCurrentCodeStackFrame((VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame());
			context.setNextProcessedEntity(defferredEntity);
			lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
			resetArtificialEntityProperty(context, lastInterpretedEntity, null);				
			setStatus(nextEntityToProcess);
			return;
		}
		VWMLDynamicEntityProperties termProps = context.getEntityDynamicProperties(lastInterpretedTerm, false);
		if (termProps != null && termProps.isOperatesByExe()) {
			termProps.setOperatesByExe(false);
			// context.getStack().popUntilEmptyMark();
			// consume ()
			VWMLEntity eEmptyToBeConsumed = (VWMLEntity)context.getStack().peek();
			if (VWMLObjectsRepository.shouldEntityBeConsumed(eEmptyToBeConsumed)) { 
				context.getStack().popUntilEmptyMark();
			}
		}
		resetArtificialEntityProperty(context, lastInterpretedEntity, null);
		// un-mark probable entity's recursion
		unmarkVWMLEntityAsProbableRecursion(context, lastInterpretedTerm);			
		context.popStackFrame();
		if (defferredEntity == null) {
			// pops stack frame
			setStatus(decomposeAndGetNext(linkage, context));
			context.setCurrentCodeStackFrame((VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame());
			if (getStatus() == nextEntityToProcess && context.getNextProcessedEntity() != null) {
				// process next entity
				lastInterpretedEntity = lastInterpretedTerm = context.getNextProcessedEntity();
			}
			else
			if (getStatus() == continueProcessingOfCurrentEntity) {
				throw new Exception("invalid state of interpreter; state is '" + getStatus() + "'; last interpreted entity '" + lastInterpretedEntity.getId() + "'");
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

	protected void resetArtificialEntityProperty(VWMLContext context, VWMLEntity entity, VWMLDynamicEntityProperties props) {
		if (props == null) {
			props = context.getEntityDynamicProperties(entity, false);
			if (props != null && props.isMarkedAsArtificalTerm()) {
				props.setMarkedAsArtificalTerm(false);
			}
		}
		else {
			props.setMarkedAsArtificalTerm(false);
		}
	}
	
	protected void markVWMLEntityAsProbableRecursion(VWMLContext context, VWMLEntity le) {
		context.markEntityAsRecursiveInsideContext(le);
	}

	protected void unmarkVWMLEntityAsProbableRecursion(VWMLContext context, VWMLEntity le) {
		context.unmarkEntityAsRecursiveInsideContext(le);
	}

	protected void resolveVWMLRecursion(VWMLContext context, VWMLEntity le) {
		boolean found = false;
		while(context.peekStackFrame() != null && !found) {
			VWMLSequentialTermInterpreterCodeStackFrame frame = (VWMLSequentialTermInterpreterCodeStackFrame)context.peekStackFrame();
			boolean pop = false;
			VWMLDynamicEntityProperties propsOfEntity = context.getEntityDynamicProperties(frame.getAssociatedEntity(), false);
			if (frame.getTerm().isTerm() || (propsOfEntity != null && propsOfEntity.isMarkedAsArtificalTerm())) {
				pop = true;
			}
			unmarkVWMLEntityAsProbableRecursion(context, frame.getTerm());
			resetArtificialEntityProperty(context, frame.getAssociatedEntity(), propsOfEntity);
			if (propsOfEntity != null && propsOfEntity.isOperatesByExe()) {
				propsOfEntity.setOperatesByExe(false);
			}
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
		return context.isEntityMarkedAsRecursiveInsideContext(le);
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
	protected VWMLEntity termInterpretation(VWMLLinkage linkage, VWMLContext context, VWMLEntity le, boolean artificialTerm) throws Exception {
		VWMLEntity exeEntity = null;
		if (le.isTerm() || artificialTerm) { // means that entity has operations which should be executed on top of stack
			// executes operations and serves stack
			VWMLLinkIncrementalIterator itOps = le.acquireOperationsIterator();
			if (itOps == null && artificialTerm) {
				// executes __assemble__ operation
				handleOperation(linkage, context, opImplicitlyAddedRef);
			}
			else {
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
