package com.vw.lang.sink.java.operations.processor;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Binds operation execution handler and operation
 * @author ogibayev
 *
 */
public abstract class VWMLOperationHandler {
	
	private VWMLEntity nilEntity = (VWMLEntity)VWMLObjectsRepository.instance().getNilEntity();
	private VWMLEntity emptyEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
	
	/**
	 * Abstract operation handler
	 * @param interpreter
	 * @param linkage
	 * @param context
	 * @param operation
	 * @throws Exception
	 */
	public abstract void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception;
	
	/**
	 * Reports to observer interpreter's internal state
	 * @param context 
	 * @param interpreter
	 */
	protected void reportInterpreterInternalState(String context, VWMLInterpreterImpl interpreter) {
		if (interpreter.getObserver() !=  null) {
			interpreter.getObserver().setConflictOperationalState(context, VWMLConflictRingNodeAutomataInputs.IN_N);
		}
	}
	
	/**
	 * Rebuilds entity's credentials and 
	 * @param result
	 * @return
	 */
	protected VWMLEntity finalizeAfterSetOrientedOperations(VWMLEntity result) {
		if (result != emptyEntity && result != nilEntity) {
			if (result.getLink().getLinkedObjectsOnThisTime() == 0) {
				result = emptyEntity;
			}
			else {
				VWMLObjectsRepository.instance().lateBinding(result, null);
			}
		}
		return result;
	}
	
}
