package com.vw.lang.sink.java.operations.processor.operations.handlers.dyncontext;

import java.util.List;
import java.util.UUID;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
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
 * Handler of 'OPDYNCONTEXT' operation
 * @author ogibayev
 *
 */
public class VWMLOperationDynamicContextAddressingHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity entity = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector(interpreter, context);
		inspector.setOperationalContext(context);
		inspector.setAssemblyEntity(true);
		stack.inspect(inspector);
		// since inspector reads until empty mark we should read entity's original context
		VWMLContext originalContext = context.peekContext();
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("the dynamic context can't be empty");
		}
		else
		if (entities.size() == 1) {
			entity = entities.get(0);
			entity.buildReadableId();
			if (!VWMLContext.isDynamicContextPointsToSelf((String)entity.getId())) {
				VWMLEntity e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY,
												 null,
												 entity.getId() + "_mimic_" + UUID.randomUUID().toString(),
												 entity.getContext(),
												 context.getEntityInterpretationHistorySize(),
												 context.getLinkOperationVisitor());
				e.setReadableId(entity.getReadableId());
				e.rebuildHashId(entity.getId());
				entity = e;
			}
		}
		else {
			entity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
										  entities,
										  entities.size() - 1,
										  originalContext,
										  originalContext,
										  context.getEntityInterpretationHistorySize(),
										  context.getLinkOperationVisitor(),
										  VWMLOperationUtils.s_dontAddIfUnknown);
		}
		// this entity, can be considered as phantom, since it isn't used for interpreting purposes (can't be interpreted, etc),
		// is used for creating dynamic context only, where context is formed from terms
		entity.setReadableId((String)entity.buildReadableId());
		entity.setPartOfDynamicContext(true);
		if (entity.getContext() != null) {
			try {
				VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.instance().get(entity.getReadableId(), entity.getContext());
				if (e != null) {
					entity.setContext(e.getContext());
				}
			}
			catch(Exception ex) {
				// nothing todo
			}
		}
		// clear stack
		entities.clear();
		inspector.clear();
		stack.push(entity);
	}

}
