package com.vw.lang.sink.java.operations.processor.operations.handlers.applytoctx;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
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
 * Handler of 'OPAPPLYTOCONTEXT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationApplyToContextHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() != 2) {
			throw new Exception("arguments != 2 for operation 'OPAPPLYTOCONTEXT'");
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
																					   entities,
																					   entities.size() - 1,
																					   originalContext,
																					   context,
																					   context.getEntityInterpretationHistorySize(),
																					   context.getLinkOperationVisitor(),
																					   VWMLOperationUtils.s_addIfUnknown);
			if (entity.isMarkedAsComplexEntity()) {
				if (entity.getLink().getLinkedObjectsOnThisTime() == 2) {
					applyContext(entity);
				}
				else
				if (entity.getLink().getLinkedObjectsOnThisTime() == 1) {
					applyContext((VWMLEntity)entity.getLink().getConcreteLinkedEntity(0));
				}
				else {
					// exception
					throw new Exception("a lot of arguments > 2 for operation 'OPAPPLYTOCONTEXT'");
				}
			}
			else {
				throw new Exception("operation 'OPAPPLYTOCONTEXT' applied to complex entity only; entity '" + entity.getId() + "'");
			}
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
	}

	private void applyContext(VWMLEntity entity) throws Exception {
		if (entity.getLink().getLinkedObjectsOnThisTime() == 2) {
			VWMLEntity e = (VWMLEntity)entity.getLink().getConcreteLinkedEntity(0);
			String contextToFind = buildAbsoluteContext((VWMLEntity)entity.getLink().getConcreteLinkedEntity(1));
			VWMLContext ctx = VWMLContextsRepository.instance().get(contextToFind);
			if (ctx == null) {
				throw new Exception("couldn't find context identified by '" + contextToFind + "'; operation 'OPAPPLYTOCONTEXT'");
			}
			e.setContext(ctx);
		}
	}
	
	private String buildAbsoluteContext(VWMLEntity e) {
		return e.getContext().getContext() + "." + (e.getReadableId() != null ? e.getReadableId() : e.getId());
	}
}
