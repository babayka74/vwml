package com.vw.lang.sink.java.operations.processor.operations.handlers.recycle;

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
 * Handler of 'OPRECYCLE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRecycleHandler extends VWMLOperationHandler {
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) {
			entity = entities.get(0);
		}
		else {
			throw new Exception("Invalid number of arguments > 1");
		}
		entity.decrementRefCounter();
		// System.out.println("---> " + entity.buildReadableId() + "' " + entity.getRefCounter() + " '" + entity.getOldReadableId() + "'");
		entity.releaseByRefCounter(null);
		inspector.clear();
		entities.clear();
	}
}
