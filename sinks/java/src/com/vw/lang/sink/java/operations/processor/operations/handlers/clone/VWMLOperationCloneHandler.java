package com.vw.lang.sink.java.operations.processor.operations.handlers.clone;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.processor.VWMLOperationHandler;
import com.vw.lang.sink.java.operations.processor.VWMLOperationStackInspector;

/**
 * Handler of 'OPCLONE' operation
 * @author ogibayev
 *
 */
public class VWMLOperationCloneHandler extends VWMLOperationHandler {

	@Override
	public void handle(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLStack stack = context.getStack();
		VWMLOperationStackInspector inspector = new VWMLOperationStackInspector();
		stack.inspect(inspector);
		List<VWMLEntity> entities = inspector.getReversedStack();
		if (entities.size() == 0 || entities.size() > 2) {
			throw new Exception("arguments != 2 for operation 'OPCLONE'");
		}
		if (entities.size() == 1) {
			if (!entities.get(0).isMarkedAsComplexEntity()) {
				throw new Exception("at least one entity should be complex; operation 'OPCLONE'");
			}
			if (entities.get(0).getLink().getLinkedObjectsOnThisTime() == 2) {
				handleCloneOperation(interpreter,
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(0),
									 (VWMLEntity)entities.get(0).getLink().getConcreteLinkedEntity(1));
			}
			else {
				throw new Exception("arguments != 2 for operation 'OPCLONE'");
			}
		}
		else
		if (entities.size() == 2) {
			handleCloneOperation(interpreter, entities.get(1), entities.get(0));
		}
		inspector.clear();
		entities.clear();
	}

	protected void handleCloneOperation(VWMLInterpreterImpl interpreter, VWMLEntity origEntity, VWMLEntity clonedObject) throws Exception {
		VWMLEntity cloned = clone(origEntity, clonedObject);
		if (cloned.getInterpreting() != null && interpreter.getRing() != null) {
			VWMLEntity clonedSourceLft = cloned.getInterpreting().getContext().findSourceLifeTerm();
			if (clonedSourceLft != null) {
				activateSourceLifeTerm(interpreter, cloned, clonedSourceLft);
			}
		}
	}
	
	private void activateSourceLifeTerm(VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		// lookup for original
		VWMLEntity p = clonedSourceLft;
		while(p.getClonedFrom() != null) {
			p = p.getClonedFrom();
		}
		VWMLConflictRingExecutionGroup group = interpreter.getRing().findGroupByEntityContext(p.getContext().getContext());
		if (group == null) {
			throw new Exception("couldn't ring find group by context '" + clonedSourceLft.getContext().getContext() + "'");
		}
		VWMLConflictRingNode ringMasterNode = group.findMasterNode();
		if (ringMasterNode == null) {
			throw new Exception("couldn't find ring node by context '" + clonedSourceLft.getContext().getContext() + "'");
		}
		//
		interpreter = (VWMLInterpreterImpl)ringMasterNode.peekInterpreter();
		VWMLInterpreterImpl clonedInterpreter = interpreter.clone();
		List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
		tl.add(clonedSourceLft);
		VWMLConflictRingNode clonedNode = ringMasterNode.clone(clonedInterpreter);
		clonedNode.markAsClone(true);
		// get real operated node (not - master)
		if (interpreter.getRtNode() != null) {
			// sigma's should be cloned also
			interpreter.getRtNode().cloneSigmaFor(clonedNode);
		}
		// interpreter was instantiated as result of cloning entity => cloned.getClonedFrom()
		// needed when resources should be released
		clonedInterpreter.setClonedFromEntity(cloned);
		clonedInterpreter.setCloned(true);
		clonedInterpreter.setTerms(tl);
		clonedNode.setMasterNode(ringMasterNode);
		clonedNode.setExecutionGroup(group);
		group.add(clonedNode);
		clonedInterpreter.start();
	}
	
	private VWMLEntity clone(VWMLEntity origEntity, VWMLEntity clonedObject) throws Exception {
		return VWMLCloneFactory.cloneContext(origEntity, clonedObject, clonedObject.getId());
	}
}
