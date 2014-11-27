package com.vw.lang.sink.java.operations.processor.operations.handlers.repeat;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
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
 * Handler of 'OPREPEAT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationRepeatHandler extends VWMLOperationHandler {

	private static final int s_numOfOperationArgs = 2;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			if (entities.size() == 1) {
				handleRepeatOnComplexEntity(interpreter, entities.get(0), context);
			}
			else {
				VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																									   entities.size() - 1,
																									   context,
																									   context,
																									   context.getEntityInterpretationHistorySize(),
																									   context.getLinkOperationVisitor(),
																									   VWMLOperationUtils.s_dontAddIfUnknown);
				handleRepeatOnComplexEntity(interpreter, entity, context);
				entity.getLink().clear();
				entity = null;
			}
		}
		inspector.clear();
		entities.clear();
	}
	
	protected void handleRepeatOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfOperationArgs) {
			throw new Exception("Operation 'Repeat' requires 2 arguments (arguments and term)");
		}
		VWMLEntity counter = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity term = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		if (!counter.isMarkedAsComplexEntity()) {
			int c = 0;
			c = Integer.valueOf((String)counter.getId()).intValue();
			for(int i = 0; i < c; i++) {
				String id = String.valueOf(i);
				VWMLEntity e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, id, id, context, 0, null);
				if (!forEach(interpreter, e, term)) {
					break;
				}
			}
		}
	}
	
	protected boolean forEach(VWMLInterpreterImpl interpreter, VWMLEntity component, VWMLEntity term) throws Exception {
		return VWMLOperationUtils.activateTerm(interpreter, component, false, term, "Repeat_", "Repeat", null);
	}
}
