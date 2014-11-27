package com.vw.lang.sink.java.operations.processor.operations.handlers.interceptor;

import java.util.List;

import com.vw.lang.sink.java.VWMLInterceptorsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interceptor.VWMLInterceptor;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPSTARTINTERCEPTION' operation
 * @author ogibayev
 *
 */
public class VWMLOperationStartInterceptionHandler extends VWMLOperationHandler {

	private static final int s_numOfOperationArgs = 3;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			if (entities.size() == 1) {
				handleStartInterceptionOnComplexEntity(interpreter, entities.get(0), context);
			}
			else {
				VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																									   entities.size() - 1,
																									   context,
																									   context,
																									   context.getEntityInterpretationHistorySize(),
																									   context.getLinkOperationVisitor(),
																									   VWMLOperationUtils.s_dontAddIfUnknown);
				handleStartInterceptionOnComplexEntity(interpreter, entity, context);
				entity.getLink().clear();
				entity = null;
			}
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleStartInterceptionOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (entity.isMarkedAsComplexEntity() && entity.getLink().getLinkedObjectsOnThisTime() == s_numOfOperationArgs) {
			VWMLEntity intercept = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
			VWMLEntity trigger = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(1);
			VWMLEntity term = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(2);
			VWMLInterceptorsRepository.instance().addInterceptor(VWMLInterceptor.instance(intercept, trigger, term));
		}
	}
}
