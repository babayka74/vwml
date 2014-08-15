package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Part of conflict ring 
 * @author Oleg
 *
 */
public class VWMLConflictRingNode extends VWMLObject {
	
	// node's automata
	private VWMLConflictRingNodeAutomata nodeAutomata = VWMLConflictRingNodeAutomata.build();
	private boolean clone = false;
	// marked when node is created due to conflict definition + lifeterm, but initially it is not
	// placed to ring, so we may guess about its ability to be cloned
	private boolean markAsCandidatOnClone = false;
	// sets 'true' in case if conflict model loops itself
	private boolean looped = false;
	// true in case if node is in conflict situation; the conflict marked by operation '[' and 
	// finished by ']' operation
	private boolean nodeInConflict = false;
	// index of conflict fragment 
	private int sigma = 0;
	// true in case if node belongs to any group
	private boolean grouped = false;
	private int groupedCount = 0;
	private VWMLConflictRingExecutionGroup executionGroup = null;
	private VWMLConflictRingNode masterNode = null;
	// internal group (see below)
	private VWMLConflictRingNode groupOwner = null;
	// group of nodes (each node is considered as conflict fragment) which belong to the same source lifeterm
	private List<VWMLConflictRingNode> group = new ArrayList<VWMLConflictRingNode>();
	// the node can operate on more than 1 interpreter; more than 1 interpreters are used 
	// by operations which require additional term interpretation in runtime (used by reactive and parallel interpreters)
	private VWMLStack operationalInterpreters = VWMLStack.instance();
	private int activeInterpreters = 0;
	// sets to 'true' in case if node requires arbitration during lock event
	private boolean requireArbitrationOnLock = false;
	
	public VWMLConflictRingNode(Object hashId) {
		super(hashId);
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return VWMLResourceHostManagerFactory.hostManagerInstance().buildConflictRingNode(id, readableId);
	}

	/**
	 * Clones current node with specified interpreter
	 * @param relatedInterpreter
	 * @return
	 */
	public VWMLConflictRingNode clone(VWMLInterpreterImpl relatedInterpreter) {
		VWMLConflictRingNode n = build(this.getId(), this.getReadableId());
		n.markAsClone(true);
		n.setMasterNode(getMasterNode());
		n.setExecutionGroup(getExecutionGroup());
		if (relatedInterpreter != null) {
			n.pushInterpreter(relatedInterpreter);
			relatedInterpreter.setRtNode(n);
		}
		return n;
	}

	/**
	 * Deep cloning including node's linkage for specified group
	 * @return
	 */
	public VWMLConflictRingNode deepClone(VWMLConflictRingExecutionGroup forGroup) {
		List<VWMLConflictRingNode> alreadyCloned = new ArrayList<VWMLConflictRingNode>();
		VWMLConflictRingNode n = deepCloneImpl(forGroup, alreadyCloned);
		n.setMasterNode(n);
		alreadyCloned.clear();
		alreadyCloned = null;
		return n;
	}

	/**
	 * Resets internal structures, allowing us to 'resurrect' node
	 */
	public void reset() throws Exception {
	}

	/**
	 * Executes node's operational logic
	 * @throws Exception
	 */
	public void operate() throws Exception {
		VWMLInterpreterImpl i = peekInterpreter();
		if (i == null) {
			return;
		}
		if (i.getStatus() != VWMLInterpreterImpl.stopProcessing && i.getStatus() != VWMLInterpreterImpl.stopped) {
			i.setRtNode(this);
			operateOnNode();
		}
		if (i.getStatus() == VWMLInterpreterImpl.stopProcessing) {
			i.setStatus(VWMLInterpreterImpl.stopped);
			if (i.isCloned() || i.isReleaseClonedResource()) {
				VWMLCloneFactory.releaseClonedContext(i.getClonedFromEntity(), i.getContext());
			}
			i.reset();
		}
	}
	
	public boolean isClone() {
		return clone;
	}

	public void markAsClone(boolean clone) {
		this.clone = clone;
	}

	public boolean isMarkAsCandidatOnClone() {
		return markAsCandidatOnClone;
	}

	public void setMarkAsCandidatOnClone(boolean markAsCandidatOnClone) {
		this.markAsCandidatOnClone = markAsCandidatOnClone;
	}

	public boolean isLooped() {
		return looped;
	}

	public void setLooped(boolean looped) {
		this.looped = looped;
	}

	/**
	 * Returns master (not cloned) interpreter
	 * @return
	 */
	public VWMLInterpreterImpl peekInterpreter() {
		return (VWMLInterpreterImpl)operationalInterpreters.peek();
	}

	public void pushInterpreter(VWMLInterpreterImpl interpreter) {
		activeInterpreters++;
		operationalInterpreters.push(interpreter);
	}
	
	public void popInterpeter() {
		operationalInterpreters.pop();
		activeInterpreters--;
	}

	/**
	 * Returns first interpreter in operational stack
	 * @return
	 */
	public VWMLInterpreterImpl firstPushedInterpreter() {
		return (VWMLInterpreterImpl)operationalInterpreters.bottom();
	}
	
	/**
	 * Returns context of node's original term
	 * @param interpreter
	 * @return
	 */
	public String getContextOfNodeOriginalTerm(VWMLInterpreterImpl interpreter) {
		String termContext = interpreter.getContext().getContext();
		if (interpreter.getRtNode() != null) {
			VWMLEntity initialTerm = interpreter.getRtNode().findInitialTerm();
			if (initialTerm.getClonedFrom() != null) {
				initialTerm	= initialTerm.getClonedFrom();
			}
			termContext = initialTerm.getContext().getContext();
		}
		return termContext;
	}
	
	/**
	 * Returns 'true' in case if main and all cloned interpreters finished interpretation process
	 * @return
	 */
	public boolean isStopped() {
		VWMLInterpreterImpl i = peekInterpreter();
		if (i == null) {
			return true;
		}
		return activeInterpreters == 1 && (i.getStatus() == VWMLInterpreterImpl.stopProcessing || i.getStatus() == VWMLInterpreterImpl.stopped);
	}

	public boolean isRequireArbitrationOnLock() {
		return requireArbitrationOnLock;
	}

	public void setRequireArbitrationOnLock(boolean requireArbitrationOnLock) {
		this.requireArbitrationOnLock = requireArbitrationOnLock;
	}

	public boolean isNodeOnSendingLockEvent() {
		return false;
	}

	public void markNodeAsSendingLockEvent(boolean nodeOnSendingLockEvent) {
	}

	public boolean isNodeInConflict() {
		return nodeInConflict;
	}

	public void setNodeInConflict(boolean nodeInConflict) {
		this.nodeInConflict = nodeInConflict;
	}
	
	public int getSigma() {
		return sigma;
	}

	public void setSigma(int sigma) {
		this.sigma = sigma;
	}
	
	public void incSigma() {
		incSigmaImpl();
	}
	
	public void decSigma() {
		decSigmaImpl();
	}	
	
	/**
	 * Adds node to group
	 * @param node
	 */
	public void addToGroup(VWMLConflictRingNode node) {
		group.add(node);
		node.setGrouped(true);
		node.incGroupedCounter();
		node.setGroupOwner(this);
	}
	
	/**
	 * Removes node from group
	 * @param node
	 */
	public void removeFromGroup(VWMLConflictRingNode node) {
		if (group.contains(node)) {
			group.remove(node);
			node.decGroupedCounter();
		}
	}
	
	public VWMLConflictRingNode getMasterNode() {
		return masterNode;
	}

	public void setMasterNode(VWMLConflictRingNode masterNode) {
		this.masterNode = masterNode;
	}

	public VWMLConflictRingNode getGroupOwner() {
		return groupOwner;
	}
	
	public VWMLConflictRingNode findGroupedNodeByConflictName(String conflictName) {
		VWMLConflictRingNode groupedNode = null;
		for(VWMLConflictRingNode n : group) {
			if (((String)n.getId()).equals(conflictName)) {
				groupedNode = n;
				break;
			}
		}
		return groupedNode;
	}
	
	/**
	 * Returns true in case if node represents group
	 * @return
	 */
	public boolean isGroup() {
		return (group.size() != 0);
	}

	public boolean isGrouped() {
		return grouped;
	}

	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	public void incGroupedCounter() {
		groupedCount++;
	}
	
	public void decGroupedCounter() {
		if (groupedCount > 0) {
			groupedCount--;
		}
		else {
			setGrouped(false);
		}
	}
	
	public VWMLConflictRingExecutionGroup getExecutionGroup() {
		return executionGroup;
	}

	public void setExecutionGroup(VWMLConflictRingExecutionGroup executionGroup) {
		this.executionGroup = executionGroup;
		// all grouped nodes should belong to the same group
		for(VWMLConflictRingNode n : group) {
			n.setExecutionGroup(executionGroup);
		}
	}

	/**
	 * Returns term which belongs to initial (first in interpreter's stack) interpreter 
	 * @return
	 */
	public VWMLEntity findInitialTerm() {
		VWMLEntity e = null;
		VWMLInterpreterImpl i = this.firstPushedInterpreter();
		if (i != null) {
			e = i.getClonedFromEntity();
		}
		return e;
	}
	
	/**
	 * Add deferred unlock operation; used when forced lock is required and need to wakeup node which was slept by force; the forced lock 
	 * is activated during circular conflict resolution (see VWMLConflictRingMT -> lock)
	 * @param node
	 */
	public void addDeferredWakeupOnUnlock(VWMLConflictRingNode node) throws Exception {
		throw new Exception("For MT strategy only");
	}
	
	/**
	 * Processes deferred unlock for nodes added by 'addDeferredWakeupOnUnlock'
	 * @throws Exception
	 */
	public void processDeferredWakeupsOnUnlock() throws Exception {
		throw new Exception("For MT strategy only");
	}
	
	protected void operateOnNode() throws Exception {
		VWMLConflictRingNode operationalNode = null;
		VWMLConflictRingNodeAutomataInputs input = null;
		VWMLConflictRingNodeAutomataStates state = null;
		VWMLInterpreterImpl i = peekInterpreter();
		
		// looking for model (each node has reference to conflict model)
		VWMLConflictRingNode masterModelNode = getMasterNode();
		operationalNode = masterModelNode;
		String activeConflictContext = i.getObserver().getActiveConflictContext();
		if (activeConflictContext != null) {
			// looking for a node inside the group
			if (!activeConflictContext.equals(masterModelNode.getId())) { // not lead node
				VWMLConflictRingNode n = masterModelNode.findGroupedNodeByConflictName(activeConflictContext);
				if (n != null) {
					operationalNode = n;
				}
			}
		}
		if (operationalNode != null) {
			input = i.getObserver().getConflictOperationalState((String)operationalNode.getId());
			state = VWMLConflictRingNodeAutomataStates.STATE_PAS;
			if (operationalNode.getSigma() == 0 || operationalNode.isLooped()) {
				state = VWMLConflictRingNodeAutomataStates.STATE_ACT;
			}
			//System.out.println("Node of '" + operationalNode.getInterpreter().getContext().getContext() + "'; sigma '" + operationalNode.getSigma() + "'; state '" + state + "'; input '" + input + "'");
		}
		nodeAutomata.runAction(i, operationalNode, input, state);
	}
	
	protected void incSigmaImpl() {
		sigma++;
	}
	
	protected void decSigmaImpl() {
		if (sigma > 0) {
			sigma--;
		}
	}
	
	protected void setGroupOwner(VWMLConflictRingNode groupOwner) {
		this.groupOwner = groupOwner;
	}

	protected List<VWMLConflictRingNode> getGroup() {
		return group;
	}
	
	protected VWMLConflictRingNode deepCloneImpl(VWMLConflictRingExecutionGroup forGroup, List<VWMLConflictRingNode> alreadyCloned) {
		VWMLConflictRingNode n = build(this.getId(), this.getReadableId());
		n.setExecutionGroup(forGroup);
		if (!alreadyCloned.contains(n)) {
			alreadyCloned.add(n);
		}
		VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode nc = (VWMLConflictRingNode)getLink().getConcreteLinkedEntity(it.getIt());
				if (!alreadyCloned.contains(nc)) {
					nc = nc.deepCloneImpl(forGroup, alreadyCloned);
				}
				else {
					for(VWMLConflictRingNode rn : alreadyCloned) {
						if (rn.equals(nc)) {
							nc = rn;
							break;
						}
					}
				}
				if (!n.isLinked(nc)) {
					n.getLink().link(nc);
				}
			}
		}
		return n;
		
	}
}
