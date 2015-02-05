package com.vw.lang.sink.java.operations.processor.operations.handlers.get;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
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
 * Handler of 'OPGET' operation
 * @author ogibayev
 *
 */
public class VWMLOperationGetHandler extends VWMLOperationHandler {

	private static int s_numOfArgs = 2;
	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Get' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleGetOnComplexEntity(entities.get(0), context);
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
			result = handleGetOnComplexEntity(entity, context);
			entity.getLink().clear();
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	protected VWMLEntity handleGetOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity() || ((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfArgs) {
			return nilEntity;
		}
		VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		if (!e.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		VWMLEntity eIndex = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		eIndex.setReadableId(null);
		eIndex.buildReadableId();
		if (eIndex.getReadableId() == null) {
			return nilEntity;
		}
		int index = Integer.valueOf(eIndex.getReadableId()).intValue();
		if (index >= e.getLink().getLinkedObjectsOnThisTime() || index < 0) {
			return nilEntity;
		}
		VWMLEntity result = (VWMLEntity)((VWMLComplexEntity)e).getLink().getConcreteLinkedEntity(index);
		return result;
	}
}
