package com.vw.lang.sink.java.operations.processor.operations.handlers.release;

import java.util.List;

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
 * Handler of 'OPRELEASE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationReleaseHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Release' requires at least 1 argument; check code");
		}
		if (entities.size() == 1) {
			handleReleaseOnComplexEntity(entities.get(0), context);
		}
		else {
			VWMLEntity entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
					   entities,
					   entities.size() - 1,
					   context,
					   context,
					   context.getEntityInterpretationHistorySize(),
					   context.getLinkOperationVisitor(),
					   VWMLOperationUtils.s_dontAddIfUnknown);
			handleReleaseOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleReleaseOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		if (entity.getInterpreting() != null && (entity.getInterpreting().getContext().findSourceLifeTerm() != null || entity.getInterpreting().getContext().findLifeTerm() != null)) {
			return;
		}
		if (entity.getInterpreting() != null) {
			String dynContext = VWMLContext.constructContextNameInRunTime(null, entity);
			VWMLContext c = VWMLContextsRepository.instance().get(dynContext);
			if (c == null) {
				throw new Exception("coudln't find context '" + dynContext + "'");
			}
			if (c.getLink().getParent() != null) {
				dynContext = ((VWMLContext)c.getLink().getParent()).getContext();
			}
			VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.findObject(dynContext, entity.buildReadableId());
			if (e != null && e == entity && e.getClonedFrom() != null) {
				VWMLContextsRepository.releaseCloned(c);
			}
		}
		VWMLObjectsRepository.instance().remove(entity);
	}
}
