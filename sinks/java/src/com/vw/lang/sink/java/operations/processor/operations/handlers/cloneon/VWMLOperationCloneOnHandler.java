package com.vw.lang.sink.java.operations.processor.operations.handlers.cloneon;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPCLONEON' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCloneOnHandler extends VWMLOperationHandler {
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0 || entities.size() > 3) {
			throw new Exception("arguments > 3  for operation 'OPCLONEON'");
		}
		boolean push = true;
		VWMLEntity cloned = null;
		if (entities.size() == 1) {
			if (!entities.get(0).isMarkedAsComplexEntity()) {
				throw new Exception("at least one entity should be complex; operation 'OPCLONEON'");
			}
			if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == 2) {
				cloned = handleCloneOnOperation(interpreter,
									 context,
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0),
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(1),
									 true
									 );
				VWMLEntity entityPushToStack = (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(2);
				if (entityPushToStack != null) {
					if (entityPushToStack == VWMLObjectsRepository.instance().getFalseEntity()) {
						push = false;
					}
				}
				else {
					push = false;
				}
			}
			else {
				throw new Exception("arguments != 2 for operation 'OPCLONEON'");
			}
		}
		else
		if (entities.size() >= 2) {
			if (entities.size() == 3) {
				cloned = handleCloneOnOperation(interpreter, context, entities.get(2), entities.get(1), true);
				if (entities.get(0) == VWMLObjectsRepository.instance().getFalseEntity()) {
					push = false;
				}
			}
			else {
				cloned = handleCloneOnOperation(interpreter, context, entities.get(1), entities.get(0), true);
			}
		}
		if (cloned == null) {
			cloned = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
		}
		inspector.clear();
		entities.clear();
		if (push) {
			stack.push(cloned);
		}
	}

	protected VWMLEntity handleCloneOnOperation(VWMLInterpreterImpl interpreter, VWMLContext interpreterContext, VWMLEntity entityToClone, VWMLEntity onContext, boolean cloneInterpreting) throws Exception {
		String ctxName = VWMLContext.constructContextNameFromParts(onContext.getContext().getContext(), (String)onContext.getNativeId());
		VWMLContext ctx = VWMLContextsRepository.instance().get(ctxName);
		if (ctx == null) {
			throw new Exception("Context '" + ctxName + "' not found");
		}
		VWMLEntity cloned = entityToClone.simpleCloneOnContext(ctx, cloneInterpreting);
		return cloned;
	}	
}

