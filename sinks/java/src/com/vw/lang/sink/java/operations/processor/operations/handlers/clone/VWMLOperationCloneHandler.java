package com.vw.lang.sink.java.operations.processor.operations.handlers.clone;

import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
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
 * Handler of 'OPCLONE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCloneHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0 || entities.size() > 3) {
			throw new Exception("arguments != 2 | 3 for operation 'OPCLONE/OPBORN'");
		}
		if (entities.size() == 1) {
			if (!entities.get(0).isMarkedAsComplexEntity()) {
				throw new Exception("at least one entity should be complex; operation 'OPCLONE/OPBORN'");
			}
			VWMLEntity deferExecution = null;
			if (entities.get(0).getLink().getLinkedObjectsOnThisTime() >= 2) {
				if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == 3) {
					deferExecution = (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(2);
				}
				handleCloneOperation(interpreter,
									 context,
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0),
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(1),
									 deferExecution);
			}
			else {
				throw new Exception("arguments != 2 | 3 for operation 'OPCLONE/OPBORN'");
			}
		}
		else
		if (entities.size() >= 2) {
			if (entities.size() == 3) {
				handleCloneOperation(interpreter, context, entities.get(2), entities.get(1), entities.get(0));
			}
			else {
				handleCloneOperation(interpreter, context, entities.get(1), entities.get(0), null);
			}
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleCloneOperation(VWMLInterpreterImpl interpreter, VWMLContext interpreterContext, VWMLEntity origEntity, VWMLEntity clonedObject, VWMLEntity deferExecution) throws Exception {
		if (VWMLContextsRepository.instance().get(VWMLContext.constructContextNameFromParts(origEntity.getContext().getContext(), (String)clonedObject.getNativeId())) != null) {
			throw new Exception("the context '" + clonedObject.getNativeId() + "' has already been cloned");
		}
		VWMLEntity cloned = VWMLCloneFactory.cloneContextLazy(origEntity, clonedObject);
		VWMLObjectsRepository.instance().remove(clonedObject);
		if (cloned.getInterpreting() != null && interpreter.getRing() != null && deferExecution == null) {
			VWMLEntity clonedSourceLft = cloned.getInterpreting().getContext().findSourceLifeTerm();
			if (clonedSourceLft != null) {
				activateSourceLifeTerm(interpreter, cloned, clonedSourceLft);
			}
			else {
				VWMLEntity clonedLft = cloned.getInterpreting().getContext().findLifeTerm();
				if (clonedLft != null) {
					clonedLft.setLifeTermAsSource(true);
					activateSourceLifeTerm(interpreter, cloned, clonedLft);
				}
			}
		}
	}
	
	private void activateSourceLifeTerm(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		VWMLOperationUtils.activateTerm(interpreter, cloned, clonedSourceLft);
	}	
}
