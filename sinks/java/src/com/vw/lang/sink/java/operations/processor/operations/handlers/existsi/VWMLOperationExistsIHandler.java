package com.vw.lang.sink.java.operations.processor.operations.handlers.existsi;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interpret.VWMLOperationInterpretHandler;

/**
 * Handler of 'OPEXISTSI' operation
 * @author ogibayev
 *
 */
public class VWMLOperationExistsIHandler extends VWMLOperationHandler {

	private VWMLEntity trueEntity = (VWMLEntity)VWMLObjectsRepository.instance().getTrueEntity();
	private VWMLEntity falseEntity = (VWMLEntity)VWMLObjectsRepository.instance().getFalseEntity();
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = trueEntity;
		VWMLContext originalContext = context.peekContext();
		VWMLStack stack = context.getStack();
		VWMLEntity interpretingEntity = null;
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		inspector.setAssemblyEntity(true);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		List<VWMLEntity> entities = inspector.getReversedStack();
		VWMLOperationInterpretHandler interpretHandler = (VWMLOperationInterpretHandler)interpreter.getProcessor().exportOperationHandler(VWMLOperationsCode.OPINTERPRET);
		try {
			interpretingEntity = interpretHandler.getInterpretationOf(entities, interpreter, linkage, context, originalContext, operation);
			if (interpretingEntity == null) {
				result = falseEntity;
			}
		}
		catch(Exception e) {
			result = falseEntity;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}
}
