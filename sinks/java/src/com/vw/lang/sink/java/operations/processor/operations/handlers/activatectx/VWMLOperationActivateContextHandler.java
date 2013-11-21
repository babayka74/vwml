package com.vw.lang.sink.java.operations.processor.operations.handlers.activatectx;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
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
 * Handler of 'OPACTIVATECTX' operation
 * @author ogibayev
 *
 */
public class VWMLOperationActivateContextHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		Object activatedContextId = null;
		if (entities.size() == 1) { // specific case where only one entity on stack
			entity = entities.get(0);
			activatedContextId = entity.getId();
		}
		else {
			// entity defines context which is activated
			entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																					   entities.size() - 1,
																					   (String)context.getContext(),
																					   (String)context.getContext(),
																					   context.getEntityInterpretationHistorySize(),
																					   context.getLinkOperationVisitor(),
																					   VWMLOperationUtils.s_dontAddIfUnknown);
			activatedContextId = entity.buildReadableId();
		}
		VWMLContext activatedContext = VWMLContextsRepository.instance().get(activatedContextId);
		if (activatedContext == null) {
			throw new Exception("couldn't find context identified by '" + activatedContextId + "'");
		}
		VWMLEntity lfTerm = activatedContext.findLifeTerm();
		if (lfTerm == null) {
			throw new Exception("couldn't find lifeterm on context identified by Id '" + activatedContextId + "'; the operation ':' couldn't be finished correctly");
		}
		// clones current interpreter and runs lifeterm on it
		exectuteLifeTerm(interpreter, lfTerm);
		// clear stack
		stack.popUntilEmptyMark();
		entities.clear();
		inspector.clear();
	}
	
	protected void exectuteLifeTerm(VWMLInterpreterImpl interpreter, VWMLEntity lfTerm) throws Exception {
		VWMLInterpreterImpl ii = interpreter.clone();
		List<VWMLEntity> terms = new ArrayList<VWMLEntity>();
		terms.add(lfTerm);
		ii.setTerms(terms);
		ii.start();
		if (interpreter.getConfig().isStepByStepInterpretation()) {
			interpreter.pushInterpreterToChildStack(ii);
		}
		terms = null; // marks all elements to be garbage collected
	}
}
