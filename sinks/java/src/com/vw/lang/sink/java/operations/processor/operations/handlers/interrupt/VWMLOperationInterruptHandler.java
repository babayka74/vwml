package com.vw.lang.sink.java.operations.processor.operations.handlers.interrupt;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPINTERRUPT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationInterruptHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Interrupt' requires at least 1 argument; check code");
		}
		VWMLEntity entity = entities.get(0);
		if (entity.isMarkedAsComplexEntity()) {
			if (entity.getLink().getLinkedObjectsOnThisTime() == 0) {
				throw new Exception("operation 'Interrupt' can't be applied on '()' entity; check code");
			}
			else {
				entity = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
			}
		}
		handleInterruptOperation(interpreter, entity);
		inspector.clear();
		entities.clear();
	}

	protected void handleInterruptOperation(VWMLInterpreterImpl interpreter, VWMLEntity entity) throws Exception {
		VWMLEntity masterEntity = entity;
		if (entity.getClonedFrom() != null) {
			masterEntity = entity.getClonedFrom();
		}
		VWMLContext masterOfInterruptedContext = VWMLContextsRepository.instance().get(masterEntity.getNativeId());
		if (masterOfInterruptedContext == null) {
			throw new Exception("Coudn't find context to interrupt; context '" + masterEntity.getNativeId() + "'");
		}
		VWMLConflictRingExecutionGroup g = interpreter.getRing().findGroupByEntityContext(masterOfInterruptedContext.getContext(), true);
		VWMLInterpreterImpl interruptedInterpreter = g.findInterpreterByContext((String)entity.getNativeId());
		if (interruptedInterpreter != null) {
			Object timerId = interruptedInterpreter.getObserver().getAssociatedTimerWithContext(VWMLInterpreterObserver.getWaitContext());
			if (timerId != null) {
				interruptedInterpreter.getTimerManager().removeTimer(timerId);
			}
		}
	}
}
