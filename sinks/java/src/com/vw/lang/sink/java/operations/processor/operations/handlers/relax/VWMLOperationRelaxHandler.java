package com.vw.lang.sink.java.operations.processor.operations.handlers.relax;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPRELAX' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRelaxHandler  extends VWMLOperationHandler {

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
			Thread.sleep(timeToRelax);
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
	}
}
