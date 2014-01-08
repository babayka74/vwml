package com.vw.lang.sink.java.operations.processor.operations.handlers.include;

import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
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
 * Handler of 'OPINCL' operation
 * @author ogibayev
 *
 */
public class VWMLOperationIncludeHandler extends VWMLOperationHandler {

	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	private VWMLEntity trueEntity = (VWMLEntity)VWMLObjectsRepository.instance().getTrueEntity();
	private VWMLEntity falseEntity = (VWMLEntity)VWMLObjectsRepository.instance().getFalseEntity();
	
	private static final int s_num_args = 2;
	
	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLEntity result = null;
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0) {
			throw new Exception("operation 'Include' requires at least 2 arguments; check code");
		}
		if (entities.size() == 1) {
			result = handleIncludeOnComplexEntity(entities.get(0), context);
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
			result = handleIncludeOnComplexEntity(entity, context);
			entity = null;
		}
		inspector.clear();
		entities.clear();
		stack.push(result);
	}

	public VWMLEntity include(VWMLEntity e1, VWMLEntity e2) throws Exception {
		if (!e1.isMarkedAsComplexEntity() || !e2.isMarkedAsComplexEntity()) {
			return nilEntity;
		}
		if (e1.equals(emptyEntity) || e2.equals(emptyEntity)) {
			return trueEntity;
		}
		VWMLEntity result = trueEntity;
		VWMLLinkIncrementalIterator itE1 = ((VWMLComplexEntity)e1).getLink().acquireLinkedObjectsIterator();
		VWMLLinkIncrementalIterator itE2 = ((VWMLComplexEntity)e2).getLink().acquireLinkedObjectsIterator();
		for(; itE1.isCorrect(); itE1.next()) {
			VWMLEntity eItE1 = (VWMLEntity)((VWMLComplexEntity)e1).getLink().getConcreteLinkedEntity(itE1.getIt());
			itE2.setIt(0);
			boolean found = false;
			for(; itE2.isCorrect(); itE2.next()) {
				VWMLEntity eItE2 = (VWMLEntity)((VWMLComplexEntity)e2).getLink().getConcreteLinkedEntity(itE2.getIt());
				if (eItE1.equals(eItE2)) {
					found = true;
					break;
				}
			}
			if (!found) {
				result = falseEntity;
				break;
			}
		}
		return result;
	}
	
	protected VWMLEntity handleIncludeOnComplexEntity(VWMLEntity entity, VWMLContext context) throws Exception {
		VWMLEntity result = nilEntity;
		if (!entity.isMarkedAsComplexEntity()) {
			return result;
		}
		if (entity.getLink().getLinkedObjectsOnThisTime() < s_num_args) {
			return result;
		}
		VWMLEntity e1 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(0);
		VWMLEntity e2 = (VWMLEntity)((VWMLComplexEntity)entity).getLink().getConcreteLinkedEntity(1);
		result = include(e1, e2);
		return result;
	}	
}
