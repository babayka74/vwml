package com.vw.lang.sink.java.operations.processor.operations.handlers.ident;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLIterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of operation 'OPIDENT'
 * @author ogibayev
 *
 */
public class VWMLOperationIdentHandler extends VWMLOperationHandler {

	private static int s_arg_numbers = 2;
	
	@Override
	public void handle(VWMLIterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		boolean r = false;
		if (entities.size() == 1 && entities.get(0).isMarkedAsComplexEntity()) {
			if (((VWMLComplexEntity)entities.get(0)).getLink().getLinkedObjectsOnThisTime() == s_arg_numbers) {
				r = checkEntitiesIdenticProperty((VWMLEntity)((VWMLComplexEntity)entities.get(0)).getLink().getConcreteLinkedEntity(0),
						                         (VWMLEntity)((VWMLComplexEntity)entities.get(0)).getLink().getConcreteLinkedEntity(1));
			}
			else {
				errorReport();
			}
		}
		else
		if (entities.size() == s_arg_numbers) {
			r = checkEntitiesIdenticProperty(entities.get(0), entities.get(1));
		}
		else {
			errorReport();
		}
		inspector.clear();
		entities.clear();
		stack.popUntilEmptyMark();
		VWMLEntity entity = (r) ? (VWMLEntity)VWMLObjectsRepository.instance().get(VWMLEntity.s_trueEntityId, VWMLContextsRepository.instance().getDefaultContext()) : 
            					  (VWMLEntity)VWMLObjectsRepository.instance().get(VWMLEntity.s_falseEntityId, VWMLContextsRepository.instance().getDefaultContext());
		stack.push(entity);
	}

	protected boolean checkEntitiesIdenticProperty(VWMLEntity e, VWMLEntity e1) {
		return e.getId().equals(e1.getId());
	}
	
	protected void errorReport() throws Exception {
		throw new Exception("invalid number of arguments for operation 'Ident'; should be 2 entities");
	}
}
