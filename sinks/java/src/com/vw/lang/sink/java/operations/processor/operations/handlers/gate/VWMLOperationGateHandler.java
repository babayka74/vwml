package com.vw.lang.sink.java.operations.processor.operations.handlers.gate;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing.VWMLRingActivateGateEvent;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPGATE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationGateHandler extends VWMLOperationHandler {

	// simple transmission; transmitted data put to queue
	private static final String modeTx     = "Tx";
	// receiving
	private static final String modeRx     = "Rx";
	// checks if something is in queue
	private static final String modeReady  = "Ready";
	// registers itself on internal repository
	private static final String modeRegister  = "Register";
	// unregisters itself on internal repository
	private static final String modeUnregister  = "Unregister";
	
	// regular args (destination ring's term and mode)
	private static final int s_numOfArgs   = 2;
	// transmitted entity specified
	private static final int s_numOfArgsForTx = 3;
	// destination handler which is executed in case if transmitted data is ready
	private static final int s_numOfArgsForDestHandler = 4;
	// argument for destination handler
	private static final int s_numOfArgsForDestHandlerArg = 5;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Gate' requires 3 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleGateOnComplexEntity(interpreter, entities.get(0), context);
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
			result = handleGateOnComplexEntity(interpreter, entity, context);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
		if (result != null) {
			stack.push(result);
		}
	}
	
	protected VWMLEntity handleGateOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity() || ((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfArgs) {
			throw new Exception("operation 'Gate' requires 2 arguments at least; check code");
		}
		VWMLEntity result = null;
		VWMLEntity handlerDestTerm = null;
		VWMLEntity handlerDestTermArg = null;
		VWMLEntity transportedEntity = null;
		VWMLEntity ringDestTerm = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
		VWMLEntity mode = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(1);
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() == s_numOfArgsForTx) {
			transportedEntity = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(2);
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() == s_numOfArgsForDestHandler) {
			handlerDestTerm = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(3);
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() == s_numOfArgsForDestHandlerArg) {
			handlerDestTermArg = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(4);
		}
		if (mode.getId().equals(modeTx)) {
			VWMLResourceHostManagerFactory.hostManagerInstance().activateGate(getGate(ringDestTerm), getGate(ringDestTerm).getRing(), ringDestTerm, transportedEntity, handlerDestTerm);
		}
		else
		if (mode.getId().equals(modeRx)) {
			VWMLRingActivateGateEvent event = (VWMLRingActivateGateEvent)getGate(ringDestTerm).getRing().fromGate(ringDestTerm);
			if (event != null) {
				result = event.getTransportedEntity();
			}
			if (result == null) {
				result = (VWMLEntity)VWMLObjectsRepository.instance().get(VWMLEntity.s_NilEntityId, VWMLContextsRepository.instance().getDefaultContext());
			}
		}
		else
		if (mode.getId().equals(modeReady)) {
			VWMLGate gate = getGate(ringDestTerm);
			boolean b = gate.getRing().isGateOpened(ringDestTerm);
			if (!b && gate.getDockingTerm() != null) {
				// asking to interpret docking term in order to receive another data (for example from fringe)
				VWMLOperationUtils.activateTerm(interpreter, handlerDestTermArg, false, gate.getDockingTerm(), "Gate_", "Gate", null);
				// checking again
				b = gate.getRing().isGateOpened(ringDestTerm);
			}
			if (!b && gate.isBlockedMode()) {
				gate.blockActivity(interpreter);
			}
//			System.out.println("ringDestTerm '" + ringDestTerm.getId() + "' b '" + b + "'");
			result = (b) ? 	(VWMLEntity)VWMLObjectsRepository.instance().get(VWMLEntity.s_trueEntityId, VWMLContextsRepository.instance().getDefaultContext()) : 
				  			(VWMLEntity)VWMLObjectsRepository.instance().get(VWMLEntity.s_falseEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		else
		if (mode.getId().equals(modeRegister)) {
			VWMLConflictRing ring = null;
			if (interpreter.getRtNode() == null) {
				ring = VWMLResourceHostManagerFactory.hostManagerInstance().findRingByExecutingTerm(ringDestTerm);
			}
			else {
				ring = interpreter.getRtNode().getExecutionGroup().getRing();
			}
			boolean blocked = false;
			if (transportedEntity != null && transportedEntity.getId().equals(VWMLGate.s_blockedMode)) {
				blocked = true;
			}
			if (ring == null) {
				throw new Exception("Couldn't find ring by term '" + ringDestTerm.getId() + "'");
			}
			VWMLGate gate = new VWMLGate(ring, (String)(ringDestTerm.getId()), blocked, handlerDestTerm);
			ring.associateGate(gate);
			VWMLResourceHostManagerFactory.hostManagerInstance().requestGatesRepo().registerGate((String)ringDestTerm.getId(), gate);
		}
		else
		if (mode.getId().equals(modeUnregister)) {
			VWMLConflictRing ring = null;
			if (interpreter.getRtNode() == null) {
				ring = VWMLResourceHostManagerFactory.hostManagerInstance().findRingByExecutingTerm(ringDestTerm);
			}
			else {
				ring = interpreter.getRtNode().getExecutionGroup().getRing();
			}
			VWMLGate gate = unblockGate(ringDestTerm);
			ring.unAssociateGate(gate);
			VWMLResourceHostManagerFactory.hostManagerInstance().requestGatesRepo().unregisterGate((String)ringDestTerm.getId());
		}
		return result;
	}
	
	private VWMLGate getGate(VWMLEntity ringDestTerm) throws Exception {
		VWMLGate gate = VWMLResourceHostManagerFactory.hostManagerInstance().requestGatesRepo().getGate((String)ringDestTerm.getId());
		if (gate == null) {
			throw new Exception("Couldn't find gate by term '" + ringDestTerm.getId() + "'");
		}
		return gate;
	}
	
	private VWMLGate unblockGate(VWMLEntity ringDestTerm) throws Exception {
		VWMLGate gate = getGate(ringDestTerm);
		gate.unblockActivity();
		return gate;
	}
}
