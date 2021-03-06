package com.vw.lang.sink.java.interpreter.datastructure.ring;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Action is run by node's automata (root class; must be inherited)
 * @author Oleg
 *
 */
public abstract class VWMLConflictRingNodeAutomataAction {
	
	public abstract void action(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) throws Exception;
	
	/**
	 * Increases node's index according to automata's specification
	 * @param node
	 */
	public void incIndex(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) {
		// increase 'index' on linked nodes
		VWMLLinkIncrementalIterator it = node.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode n = (VWMLConflictRingNode)node.getLink().getConcreteLinkedEntity(it.getIt());
				n.incSigma();
				if (n == node) {
					n.setLooped(interpreter.getRtNode());
				}
				//VWMLEntity term = interpreter.getRtNode().findInitialTerm();
				//System.out.println("<" + term.getId() + ">" + node.getReadableId() + "(" + node.getSigma() + ")" + " -> " + n.getReadableId() + "(" + n.getSigma() + ")");
				VWMLResourceHostManagerFactory.hostManagerInstance().remoteLock(interpreter, node, n);
			}
		}
	}

	/**
	 * Decreases node's index according to automata's specification
	 * @param node
	 */
	public void decIndex(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) {
		// increase 'index' on linked nodes
		VWMLLinkIncrementalIterator it = node.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode n = (VWMLConflictRingNode)node.getLink().getConcreteLinkedEntity(it.getIt());
				n.decSigma();
				if (n == node) {
					n.setLooped(null);
				}
				//VWMLEntity term = interpreter.getRtNode().findInitialTerm();
				//System.out.println("<" + term.getId() + ">" + node.getReadableId() + "(" + node.getSigma() + ")" + " <- " + n.getReadableId() + "(" + n.getSigma() + ")");
				VWMLResourceHostManagerFactory.hostManagerInstance().remoteUnlock(interpreter, node, n);
			}
		}
	}
	
	/**
	 * Activates next interpreter's step
	 * @param interpreter
	 * @return
	 * @throws Exception
	 */
	public boolean nextStep(VWMLInterpreterImpl interpreter) throws Exception {
		boolean r = false;
		if (interpreter != null && interpreter.getStatus() != VWMLInterpreterImpl.stopProcessing) {
			r = interpreter.step();
		}		
		return r;
	}
	
	/**
	 * Resets input signal to IN_N	
	 * @param interpreter
	 * @param node
	 */
	public void resetInput(VWMLInterpreterImpl interpreter, VWMLConflictRingNode node) {
		if (interpreter.getObserver() != null) {
			interpreter.getObserver().setConflictOperationalState((String)node.getId(), VWMLConflictRingNodeAutomataInputs.IN_N);
			interpreter.getObserver().setActiveConflictContext(null);
		}
	}
	
	/**
	 * Resets observer's active conflict context
	 * @param interpreter
	 */
	public void resetActiveConflictContext(VWMLInterpreterImpl interpreter) {
		if (interpreter.getObserver() != null) {
			interpreter.getObserver().setActiveConflictContext(null);
		}
	}
	
}
