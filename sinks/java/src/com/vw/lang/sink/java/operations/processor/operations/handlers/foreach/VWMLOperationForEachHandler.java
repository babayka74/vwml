package com.vw.lang.sink.java.operations.processor.operations.handlers.foreach;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterListener;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPFOREACH' operation
 * @author ogibayev
 *
 */
public class VWMLOperationForEachHandler extends VWMLOperationHandler {

	private static int s_numOfOperationArgs = 2;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			if (entities.size() == 1) {
				handleForEachOnComplexEntity(interpreter, entities.get(0), context);
			}
			else {
				VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																									   entities.size() - 1,
																									   context,
																									   context,
																									   context.getEntityInterpretationHistorySize(),
																									   context.getLinkOperationVisitor(),
																									   VWMLOperationUtils.s_dontAddIfUnknown);
				handleForEachOnComplexEntity(interpreter, entity, context);
			}
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleForEachOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfOperationArgs) {
			throw new Exception("Operation 'ForEach' requires 2 arguments (arguments and term)");
		}
		VWMLEntity args = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity term = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		if (args.isMarkedAsComplexEntity()) {
			VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)args).getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)args).getLink().getConcreteLinkedEntity(it.getIt());
					if (!forEach(interpreter, e, term)) {
						break;
					}
				}
			}
		}
	}
	
	protected boolean forEach(VWMLInterpreterImpl interpreter, VWMLEntity component, VWMLEntity term) throws Exception {
		boolean continueForEach = true;
		VWMLConflictRingExecutionGroup g = null;
		VWMLInterpreterImpl activeInterpreter = interpreter;
		VWMLInterpreterListener listener = new VWMLInterpreterListenerForOperationForEach();
		if (interpreter.getRtNode() != null) {
			g = interpreter.getRtNode().getExecutionGroup();
		}
		if (interpreter.getMasterInterpreter() != null) {
			interpreter = interpreter.getMasterInterpreter();
		}
		// term is interpreted by own interpreter
		VWMLContext forcedContext = VWMLContext.instance("forEach_" + term.getContext().getContext());
		forcedContext.setContext(term.getContext().getContext());
		VWMLInterpreterImpl i = interpreter.addTermInRunTime(g, activeInterpreter, term, forcedContext, listener);
		if (i != null) {
			// the synthetic entity '$' will be interpreted as component
			i.setInterpretingEntityForSyntheticEntity(component);
			if (i.getConfig().isStepByStepInterpretation()) {
				interpreter.conditionalLoop(listener);
			}
			continueForEach = !listener.isForcedStop();
			interpreter.releaseTermResourcesAfterInterpretationDone(g, i, term);
		}
		else {
			continueForEach = false;
			throw new Exception("Couldn't activate interpreter for term '" + term + "'; operation 'ForEach'");
		}
		return continueForEach;
	}
}
