package com.vw.lang.sink.java.operations.processor.operations.handlers.tstate;

import java.util.List;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager.TimerState;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Handler of 'OPTSTATE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationTStateHandler extends VWMLOperationHandler {

	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity e = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'TState' requires 1 argument; check code");
		}
		if (entities.size() == 1) {
			e = handleTStateOnComplexEntity(entities.get(0), context, interpreter);
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
			e = handleTStateOnComplexEntity(entity, context, interpreter);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(e);
	}

	protected VWMLEntity handleTStateOnComplexEntity(VWMLEntity entity, VWMLContext context, VWMLInterpreterImpl interpreter) throws Exception {
		VWMLEntity result = null;
		TimerState ts = interpreter.getTimerManager().getTimerState(entity.buildReadableId());
		if (ts == null) {
			result = nilEntity;
		}
		else {
			// timer's remained time
			VWMLObject time = VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, String.valueOf(ts.getTime()), String.valueOf(ts.getTime()), context, 0, null);
			// start time
			VWMLObject origStartTime = VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, String.valueOf(ts.getOrigTime()), String.valueOf(ts.getOrigTime()), context, 0, null);
			// last time stamp
			VWMLObject lastTimeStamp = VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, String.valueOf(ts.getTimeStamp()), String.valueOf(ts.getTimeStamp()), context, 0, null);
			result = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
													      entity.getContext().getContext(),
														  ComplexEntityNameBuilder.generateRandomName(),
														  entity.getContext(),
														  entity.getInterpretationHistorySize(),
														  entity.getLink().getLinkOperationVisitor());
			result.getLink().link(time);
			result.getLink().link(origStartTime);
			result.getLink().link(lastTimeStamp);
			result.buildReadableId();
		}
		return result;
	}	
}
