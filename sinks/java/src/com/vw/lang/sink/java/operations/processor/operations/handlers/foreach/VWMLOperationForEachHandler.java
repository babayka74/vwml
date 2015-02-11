package com.vw.lang.sink.java.operations.processor.operations.handlers.foreach;

import java.util.List;

import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPFOREACH' operation
 * @author ogibayev
 *
 */
public class VWMLOperationForEachHandler extends VWMLOperationHandler {

	private static final int s_numOfOperationArgs = 2;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 0) {
			if (entities.size() == 1) {
				handleForEachOnComplexEntity(interpreter, entities.get(0), context);
			}
			else {
				VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack( entities,
																									   entities.size() - 1,
																									   context.peekContext(),
																									   context,
																									   context.getEntityInterpretationHistorySize(),
																									   context.getLinkOperationVisitor(),
																									   VWMLOperationUtils.s_dontAddIfUnknown);
				handleForEachOnComplexEntity(interpreter, entity, context);
				entity.getLink().clear();
				entity = null;
			}
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleForEachOnComplexEntity(VWMLInterpreterImpl interpreter, VWMLEntity entity, VWMLContext context) throws Exception {
		if (!entity.isMarkedAsComplexEntity()) {
			return;
		}
		if (((VWMLComplexEntity)entity).getLink().getLinkedObjectsOnThisTime() < s_numOfOperationArgs) {
			throw new Exception("Operation 'ForEach' requires 2 arguments (arguments and term)");
		}
		VWMLEntity argsO = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity termO = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		VWMLEntity args = VWMLOperationUtils.lazyEntityLookup(interpreter.getContext(), argsO.getContext(), argsO);
		VWMLEntity term = VWMLOperationUtils.lazyEntityLookup(interpreter.getContext(), termO.getContext(), termO);
		if (args == null) {
			throw new Exception("couldn't find entity '" + argsO.getId() + "' on context '" + argsO.getContext().getContext() + "'");
		}
		if (term == null) {
			throw new Exception("couldn't find entity '" + termO.getId() + "' on context '" + termO.getContext().getContext() + "'");
		}
		if (args.isMarkedAsComplexEntity()) {
			VWMLLinkIncrementalIterator it = ((VWMLComplexEntity)args).getLink().acquireLinkedObjectsIterator();
			if (it != null) {
				for(; it.isCorrect(); it.next()) {
					VWMLEntity e = (VWMLEntity)((VWMLComplexEntity)args).getLink().getConcreteLinkedEntity(it.getIt());
					if (!forEach(interpreter, e, term)) {
						break;
					}
				}
			}
		}
	}
	
	protected boolean forEach(VWMLInterpreterImpl interpreter, VWMLEntity component, VWMLEntity term) throws Exception {
		return VWMLOperationUtils.activateTerm(interpreter, component, false, term, "forEach_", "ForEach", null);
	}
}
