package com.vw.lang.sink.java.operations.processor.operations.handlers.recall;

import java.util.List;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
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
 * Handler of 'OPRECALL' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRecallHandler extends VWMLOperationHandler {

	private static final int s_numOfArgs = 3;
	private static final int s_numOfExArgs = 4;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Recall' requires 2 arguments; check code");
		}
		if (entities.size() == 1) {
			handleRecallOnComplexEntity(entities.get(0), context, interpreter);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
					   entities,
					   entities.size() - 1,
					   context.peekContext(),
					   context,
					   context.getEntityInterpretationHistorySize(),
					   context.getLinkOperationVisitor(),
					   VWMLOperationUtils.s_dontAddIfUnknown);
			handleRecallOnComplexEntity(entity, context, interpreter);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
	}
	
	protected void handleRecallOnComplexEntity(VWMLEntity entity, VWMLContext context, VWMLInterpreterImpl interpreter) throws Exception {
		if (!entity.isMarkedAsComplexEntity() || ((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfArgs) {
			throw new Exception("operation 'Recall' requires 2 arguments; check code");
		}
		int time = 0;
		VWMLEntity argComponent = null;
		VWMLEntity runOnTerminatedCompletition = null;
		VWMLEntity timeEntity = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
		VWMLEntity recallIdEntity = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(1);
		VWMLEntity runOnRegularCompletition = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(2);
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() >= s_numOfExArgs) {
			runOnTerminatedCompletition = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(3);
			if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() > s_numOfExArgs) {
				argComponent = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(4);
			}
		}
		try {
			time = Integer.parseInt((String)timeEntity.getNativeId());
		}
		finally {
		}
		recallImpl(context, interpreter, time, recallIdEntity, runOnRegularCompletition, runOnTerminatedCompletition, argComponent);
	}
	
	protected void recallImpl(VWMLContext activeContext, VWMLInterpreterImpl interpreter, int time, VWMLEntity recallEntityId, VWMLEntity runOnRegularCompletition, VWMLEntity runOnTerminatedCompletition, VWMLEntity argComponent) throws Exception {
		if (time != 0) {
			IVWMLGate fringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName());
			EWEntity e = fringeGate.invokeEW(IVWMLGate.builtInTimeCommandId, null);
			if (interpreter.isPushed()) {
				if (interpreter.getRtNode() == null) {
					throw new Exception("Pushed interpreter on 'Recall' doesn't have associated RT node; context '" + activeContext.getContext() + "'");
					
				}
				interpreter = interpreter.getRtNode().firstPushedInterpreter();
				if (interpreter == null) {
					throw new Exception("Didn't find first pushed interpreter on 'Recall'; context '" + activeContext.getContext() + "'");
				}
			}
			VWMLOperationRecallTimerCallback callback = new VWMLOperationRecallTimerCallback(runOnRegularCompletition, runOnTerminatedCompletition);
			callback.setRing(interpreter.getRtNode().getExecutionGroup().getRing());
			if (interpreter.getTimerManager() != null) {
				callback.setArgComponent(argComponent);
				recallEntityId.setReadableId(null);
				if (time != -1) {
					interpreter.getTimerManager().addTimer(recallEntityId.buildReadableId(), time, Long.valueOf((String)e.getId()), interpreter, callback);
				}
				else {
					interpreter.getTimerManager().removeTimer(recallEntityId.buildReadableId());
				}
			}
		}
	}
}
