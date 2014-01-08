package com.vw.lang.sink.java.operations.processor.operations.handlers.conflictend;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPCONFLICTSITUATIONEND' operation
 * @author ogibayev
 *
 */
public class VWMLOperationConflictSituationEndHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) {
			entity = entities.get(0);
		}
		else {
			entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																					   entities.size() - 1,
																					   originalContext,
																					   context,
																					   context.getEntityInterpretationHistorySize(),
																					   context.getLinkOperationVisitor(),
																					   VWMLOperationUtils.s_addIfUnknown);
		}
		if (entity.getClonedFrom() != null) {
			entity = entity.getClonedFrom();
		}
		reportInterpreterInternalState((String)entity.getId(), interpreter);
		inspector.clear();
		entities.clear();
	}

	/**
	 * Reports to observer interpreter's internal state
	 * @param interpreter
	 */
	@Override
	protected void reportInterpreterInternalState(String context, VWMLInterpreterImpl interpreter) {
		if (interpreter.getObserver() !=  null) {
			interpreter.getObserver().setActiveConflictContext(context);
			interpreter.getObserver().setConflictOperationalState(context, VWMLConflictRingNodeAutomataInputs.IN_E);
		}
	}
}
