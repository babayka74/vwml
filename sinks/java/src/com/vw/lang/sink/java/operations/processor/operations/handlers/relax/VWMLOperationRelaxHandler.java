package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import java.util.List;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
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

	private static final int s_extended_num_args = 3;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		int timeToRelax = 0;
		VWMLEntity runOnRegularCompletition = null, runOnTerminatedCompletition = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		VWMLContext originalContext = context.peekContext();
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			// get first entity which indicates rest time
			VWMLEntity relaxEntity = entities.get(0);
			if (entities.size() == s_extended_num_args) { // extended version
				relaxEntity = (VWMLEntity)entities.get(2);
				// activated on independent interpreter delay completed
				runOnRegularCompletition = (VWMLEntity)entities.get(1);
				// activated on independent interpreter when delay terminated
				runOnTerminatedCompletition = (VWMLEntity)entities.get(0);
			}
			try {
				timeToRelax = Integer.parseInt((String)relaxEntity.getId());
			}
			finally {
			}
			if (interpreter.getConfig().getInterpretationMtStrategy() == VWMLInterpreterConfiguration.INTERPRETER_MT_STRATEGY.SINGLE_SEQUENTIAL) {
				internalDelayImpl(timeToRelax);
			}
			else { // reactive implementation
				reactiveDelayImpl(originalContext, interpreter, timeToRelax, runOnRegularCompletition, runOnTerminatedCompletition);
			}
		}
		inspector.clear();
		entities.clear();
	}
	
	protected void internalDelayImpl(int delay) throws Exception {
		Thread.sleep(delay);
	}
	
	protected void reactiveDelayImpl(VWMLContext activeContext,
									 VWMLInterpreterImpl interpreter,
									 int delay,
									 VWMLEntity runOnRegularCompletition,
									 VWMLEntity runOnTerminatedCompletition) throws Exception {
		if (delay != 0) {
			IVWMLGate fringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName());
			EWEntity e = fringeGate.invokeEW(IVWMLGate.builtInTimeCommandId, null);
			VWMLOperationRelaxTimerCallback callback = new VWMLOperationRelaxTimerCallback(runOnRegularCompletition, runOnTerminatedCompletition);
			if (interpreter.getObserver() != null) {
				interpreter.getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), VWMLConflictRingNodeAutomataInputs.IN_W);
			}
			if (interpreter.getTimerManager() != null) {
				interpreter.getTimerManager().addTimer(e.getId(), delay, Long.valueOf((String)e.getId()), interpreter, callback);
				interpreter.getObserver().associateTimerWithContext(VWMLInterpreterObserver.getWaitContext(), e.getId());
			}
		}
	}
}
