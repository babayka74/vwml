package com.vw.lang.sink.java.operations.processor.operations.handlers.callp;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPCALLP' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCallPHandler extends VWMLOperationHandler {

	private static int s_numOfObligatoryOperationArgs = 2;
	private static int s_numOfCompleteOperationArgs   = 3;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			if (entities.size() == 1) {
				handleCallPOnComplexEntity(interpreter, entities.get(0), context);
			}
			else {
				VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																									   entities.size() - 1,
																									   context,
																									   context,
																									   context.getEntityInterpretationHistorySize(),
																									   context.getLinkOperationVisitor(),
																									   VWMLOperationUtils.s_dontAddIfUnknown);
				handleCallPOnComplexEntity(interpreter, entity, context);
				entity.getLink().clear();
				entity = null;
			}
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleCallPOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfObligatoryOperationArgs) {
			throw new Exception("Operation 'CallP' requires at least 2 arguments (arguments and term and completition term which is optional)");
		}
		VWMLEntity completitionTerm = null;
		VWMLEntity args = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity term = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() == s_numOfCompleteOperationArgs) {
			completitionTerm = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(2);
		}
		if (args.isMarkedAsComplexEntity()) {
			callP(interpreter, args, term, completitionTerm);
		}
	}
	
	protected boolean callP(VWMLInterpreterImpl interpreter,
							VWMLEntity component,
							VWMLEntity term,
							VWMLEntity completitionTerm) throws Exception {
		String forcedContextName = term.getContext().getContext();
		boolean b = VWMLOperationUtils.activateTerm(interpreter, component, true, term, "CallP_", "CallP", forcedContextName);
		if (completitionTerm != null) {
			VWMLOperationUtils.activateTerm(interpreter, null, false, completitionTerm, "CallPCbk_", "CallPCbk", forcedContextName);
		}
		return b;
	}
	
}
