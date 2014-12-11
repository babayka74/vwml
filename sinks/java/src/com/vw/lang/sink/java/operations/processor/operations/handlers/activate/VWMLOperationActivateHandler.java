package com.vw.lang.sink.java.operations.processor.operations.handlers.activate;

import java.util.List;

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
 * Handler of 'OPACTIVATE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationActivateHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Activate' requires 1 argument; check code");
		}
		if (entities.size() == 1) {
			handleActivateOperation(entities.get(0), context, interpreter);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
					   entities,
					   entities.size() - 1,
					   context,
					   context,
					   context.getEntityInterpretationHistorySize(),
					   context.getLinkOperationVisitor(),
					   VWMLOperationUtils.s_dontAddIfUnknown);
			handleActivateOperation(entity, context, interpreter);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
	}
	
	protected void handleActivateOperation(VWMLEntity entity, VWMLContext context, VWMLInterpreterImpl interpreter) throws Exception {
		VWMLEntity activateEntity = entity;
		if (activateEntity.getInterpreting() != null && interpreter.getRing() != null) {
			VWMLEntity lft = activateEntity.getInterpreting().getContext().findSourceLifeTerm();
			if (lft != null) {
				activateTerm(interpreter, activateEntity, lft);
			}
			else {
				lft = activateEntity.getInterpreting().getContext().findLifeTerm();
				if (lft != null) {
					lft.setLifeTermAsSource(true);
					activateTerm(interpreter, activateEntity, lft);
				}
				else {
					lft = activateEntity.getInterpreting();
					lft.setLifeTermAsSource(true);
					activateTerm(interpreter, activateEntity, lft);
				}
			}
		}
	}
	
	protected void activateTerm(VWMLInterpreterImpl interpreter, VWMLEntity context, VWMLEntity term) throws Exception {
		if (!term.isActivated()) {
			VWMLOperationUtils.activateTerm(interpreter, context, term);
		}
		else {
			throw new Exception("trying to activate active term '" + term.getId() + "' belongs to context '" + term.getContext().getContext() + "'on context '" + context.getContext() + "'");
		}
	}
}
