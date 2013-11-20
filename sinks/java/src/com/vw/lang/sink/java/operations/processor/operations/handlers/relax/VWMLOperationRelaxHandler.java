package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import java.util.List;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPRELAX' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRelaxHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		int timeToRelax = 0;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			// get first entity which indicates rest time
			VWMLEntity relaxEntity = entities.get(0);
			if (relaxEntity.isMarkedAsComplexEntity()) {
				relaxEntity = (VWMLEntity)relaxEntity.getLink().getConcreteLinkedEntity(0);
			}
			try {
				timeToRelax = Integer.parseInt((String)relaxEntity.getId());
			}
			finally {
			}
			if (interpreter.getConfig().getInterpretationMtStrategy() != VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.REACTIVE) {
				internalDelayImpl(timeToRelax);
			}
			else { // reactive implementation
				reactiveDelayImpl(interpreter, timeToRelax);
			}
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
	}
	
	protected void internalDelayImpl(int delay) throws Exception {
		Thread.sleep(delay);
	}
	
	protected void reactiveDelayImpl(VWMLIterpreterImpl interpreter, int delay) throws Exception {
		if (delay != 0) {
			IVWMLGate fringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName());
			EWEntity e = fringeGate.invokeEW(IVWMLGate.builtInTimeCommandId, null);
			VWMLOperationRelaxTimerCallback callback = new VWMLOperationRelaxTimerCallback();
			if (interpreter.getObserver() != null) {
				VWMLConflictRingNodeAutomataInputs conflictRingNodeAutomataInput = interpreter.getObserver().getConflictOperationalState();
				callback.setConflictRingNodeAutomataInput(conflictRingNodeAutomataInput);
				interpreter.getObserver().setConflictOperationalState(VWMLConflictRingNodeAutomataInputs.IN_W);
			}
			if (interpreter.getTimerManager() != null) {
				interpreter.getTimerManager().addTimer(e.getId(), delay, Long.valueOf((String)e.getId()), interpreter, callback);
			}
		}
	}
}
