package com.vw.lang.sink.java.operations.processor.operations.handlers.clone;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
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
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0 || entities.size() > 2) {
			throw new Exception("arguments != 2 for operation 'OPCLONE'");
		}
		if (entities.size() == 1) {
			if (!entities.get(0).isMarkedAsComplexEntity()) {
				throw new Exception("at least one entity should be complex; operation 'OPCLONE'");
			}
			if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == 2) {
				handleCloneOperation((VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0),
									 entities.get(0).getLink().getConcreteLinkedEntity(1).getId());
			}
			else {
				throw new Exception("arguments != 2 for operation 'OPCLONE'");
			}
		}
		else
		if (entities.size() == 2) {
			handleCloneOperation(entities.get(0), entities.get(1).getId());
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
	}

	protected void handleCloneOperation(VWMLEntity origEntity, Object clonedObjectId) throws Exception {
		origEntity.clone(clonedObjectId);
	}
}
