package com.vw.lang.sink.java.operations.processor.operations.handlers.onfringe;

import java.util.List;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.beyond.fringe.creature.VWMLCreature;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.java.operations.processor.operations.handlers.applytoctx.VWMLOperationApplyToContextHandler;

/**
 * Handler of 'OPDO' operation
 * @author ogibayev
 *
 */
public class VWMLOperationOnFringeHandler extends VWMLOperationHandler {

	private static final int s_numberOfArgs = 2;
	private static final int s_numberOfArgs_Context = 3;
	private static final int s_numberOfArgs_PurgeMsg = 4;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity answerFromEW = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Do' requires at least 2 arguments; check code");
		}
		int offset = -2;
		boolean purgeMsg = false;
		VWMLContext applyToContext = originalContext;
		if (entities.size() == 1) {
			if (entities.get(0).getLink().getLinkedObjectsOnThisTime() >= s_numberOfArgs) {
				if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == s_numberOfArgs_Context) {
					VWMLContext ctx = getContextFromEntity((VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0));
					if (ctx != null) {
						applyToContext = ctx;
					}
					offset = -1;
				}
				else
				if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == s_numberOfArgs_PurgeMsg) {
					VWMLContext ctx = getContextFromEntity((VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(1));
					if (ctx != null) {
						applyToContext = ctx;
					}
					purgeMsg = ((VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0)).getId().equals(VWMLEntity.s_trueEntityId);
					offset = 0;
				}
				answerFromEW = sendMsgToExternalWorld((VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(3 + offset),
													  (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(2 + offset),
													  applyToContext,
													  purgeMsg);
			}
		}
		else {
			if (entities.size() == s_numberOfArgs_Context) {
				VWMLContext ctx = getContextFromEntity(entities.get(0));
				if (ctx != null) {
					applyToContext = ctx;
				}
				offset = -1;
			}
			else
			if (entities.size() == s_numberOfArgs_PurgeMsg) {
				VWMLContext ctx = getContextFromEntity(entities.get(1));
				if (ctx != null) {
					applyToContext = ctx;
				}
				purgeMsg = entities.get(0).getId().equals(VWMLEntity.s_trueEntityId);
				offset = 0;
			}
			answerFromEW = sendMsgToExternalWorld(entities.get(2 + offset), entities.get(3 + offset), applyToContext, purgeMsg);
		}
		inspector.clear();
		entities.clear();
		if (answerFromEW != null) {
			stack.push(answerFromEW);
		}
	}

	protected VWMLContext getContextFromEntity(VWMLEntity e) {
		if (e.equals(VWMLEntity.s_NullEntityId)) {
			return null;
		}
		String c = VWMLOperationApplyToContextHandler.buildAbsoluteContext(e);
		return VWMLContextsRepository.instance().get(c);
	}
	
	protected IVWMLGate findGate(VWMLEntity onFringe) {
		String fringeContextName = onFringe.getContext().getContext();
		return VWMLFringesRepository.getGateByFringeName(fringeContextName);
	}
	
	protected VWMLEntity sendMsgToExternalWorld(VWMLEntity onFringe, VWMLEntity msg, VWMLContext resContext, boolean purgeMsg) throws Exception {
		VWMLEntity answerFromEW = null;
		IVWMLGate fringeGate = findGate(onFringe);
		if (fringeGate != null) {
			EWEntity ewEntity = VWMLCreature.transformToEW(msg, VWMLCreature.s_transformAsIs);
			EWEntity ewResponseEntity = fringeGate.invokeEW(getActionOnFringe(onFringe), ewEntity);
			if (ewResponseEntity != null) {
				answerFromEW = VWMLCreature.transformToVWML(resContext, ewResponseEntity, VWMLCreature.s_transformAsIs);
				answerFromEW.setContext(resContext);
				ewResponseEntity.release();
			}
			msg.releaseProtocolEntity();
/*			
			if (purgeMsg) {
				msg.releaseYourselfOnly();
			}
*/			
		}
		else {
			throw new Exception("couldn't find fringe '" + onFringe.getId() + "'; check VWML code");
		}
		return answerFromEW;
	}
	
	protected String getActionOnFringe(VWMLEntity onFringe) {
		String action = null;
		String id = (String)onFringe.getId();
		int l = id.lastIndexOf(".");
		if (l != -1) {
			action = id.substring(l + 1);
		}
		else {
			action = id;
		}
		return action;
	}
}
