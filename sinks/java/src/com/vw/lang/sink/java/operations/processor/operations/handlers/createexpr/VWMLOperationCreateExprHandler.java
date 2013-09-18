package com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;

/**
 * Handler of 'OPCREATEEXPR' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCreateExprHandler extends VWMLOperationHandler {
	protected static class VWMLOperationCreateExprStackInspector extends VWMLStack.VWMLStackInspector {
		private List<VWMLEntity> reversedStack = new ArrayList<VWMLEntity>(); 
		
		@Override
		public boolean inspected(Object obj) {
			if (((VWMLEntity)obj).getId() == VWMLStack.s_specialMark) {
				return false;
			}
			reversedStack.add((VWMLEntity)obj);
			return true;
		}
		
		public List<VWMLEntity> getReversedStack() {
			return reversedStack;
		}
		
		public void clear() {
			reversedStack.clear();
		}
	}
	
	@Override
	public VWMLEntity handle(VWMLEntity e, VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLStack stack, VWMLOperation operation) throws Exception {
		VWMLOperationCreateExprStackInspector inspector = new VWMLOperationCreateExprStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 1) { // specific case where only one entity on stack
			new VWMLOperationHandlerCreateExprFromEntity().handle(entities.get(0), linkage, stack, operation);
		}
		else
		if (entities.size() == 2) { // specific case where only 2 entities on stack
			VWMLEntity interpretedEntity = (VWMLEntity)entities.get(1);
			VWMLEntity interpretingEntity = (VWMLEntity)entities.get(0);
			interpretedEntity.setInterpreting(interpretingEntity);
		}
		else {
			VWMLEntity entity = entities.get(entities.size() - 1);
			VWMLEntity newComplexEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(entities,
																											entities.size() - 2,
																											entity.getOriginalContext(),
																											entity.getInterpretationHistorySize(),
																											entity.getLink().getLinkOperationVisitor());
			entity.setInterpreting(newComplexEntity);
		}
		// clear stack
		stack.popUntilEmptyMark();
		entities.clear();
		return null;
	}

}