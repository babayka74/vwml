package com.vw.lang.sink.java.interpreter.datastructure.ring;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLCloneFactory;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
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
	// sets 'true' when node is inside conflict area, used during clone operation when new node's sigma
	// should be set in explicit way (without involving ring's sigma updater)
	private boolean inConflictAreaNow = false;
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
	
	public VWMLConflictRingNode(Object hashId) {
		super(hashId);
	}
	
	public VWMLConflictRingNode(Object id, String readableId) {
		super(id, id, readableId);
	}
	
	public static VWMLConflictRingNode build(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}

	/**
	 * Clones current node with specified interpreter
	 * @param relatedInterpreter
	 * @return
	 */
	public VWMLConflictRingNode clone(VWMLInterpreterImpl relatedInterpreter) {
		VWMLConflictRingNode n = build(this.getId(), this.getReadableId());
		VWMLLinkIncrementalIterator it = getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLConflictRingNode nc = (VWMLConflictRingNode)getLink().getConcreteLinkedEntity(it.getIt());
				if (this != nc) {
					n.getLink().link(nc);
				}
				else {
					n.getLink().link(n);
				}
			}
		}
		for(VWMLConflictRingNode ng : group) {
			VWMLConflictRingNode ngCloned = ng.clone(relatedInterpreter);
			n.addToGroup(ngCloned);
		}
		n.setSigma(getSigma());
		n.setExecutionGroup(getExecutionGroup());
		n.pushInterpreter(relatedInterpreter);
		return n;
	}
	
	/**
	 * Clones node's sigma which tells how many nodes pretends on shared resource on this time (called during 'clone' operation)
	 * @param rtNode
	 */
	public void cloneSigmaFor(VWMLConflictRingNode clonedNode) {
		for(int i = 0; i < group.size(); i++) {
			VWMLConflictRingNode g1 = group.get(i);
			VWMLConflictRingNode g2 = clonedNode.getGroup().get(i);
			g2.setSigma(g1.getSigma());
		}
		clonedNode.setSigma(getSigma());
		if (isInConflictAreaNow() && group.size() == 0) {
			clonedNode.setSigma(getSigma() + 1);
		}
	}
	
	/**
	 * Resets internal structures, allowing us to 'resurrect' node
	 */
	public void reset() throws Exception {
		sigma = 0;
		for(int i = 0; i < group.size(); i++) {
			group.get(i).reset();
		}
		VWMLInterpreterImpl i = peekInterpreter();
		if (!isGrouped() && i != null && !i.getConfig().isStepByStepInterpretation()) {
			i.reset();
		}
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
			if (i.isCloned()) {
				for(VWMLEntity t : i.getTerms()) {
					if (t.getClonedFrom() != null) {
						VWMLCloneFactory.releaseClonedContext(i.getClonedFromEntity(), t.getContext());
					}
				}
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

	public boolean isInConflictAreaNow() {
		return inConflictAreaNow;
	}

	public void setInConflictAreaNow(boolean inConflictAreaNow) {
		this.inConflictAreaNow = inConflictAreaNow;
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

	/**
	 * Called by execution group when group on cloned nodes' 'sigmas' should be updated
	 * @param initiator
	 * @param inc
	 */
	public void updateSigmaOnGrouped(VWMLConflictRingNode initiator, boolean inc) {
		for(VWMLConflictRingNode grouped : group) {
			if (initiator.getId().equals(grouped.getId())) {
				if (inc) {
					grouped.incSigma();
					//System.out.println("(G) Node '" + initiator.getId() + "'; context '" + initiator.getInterpreter().getContext().getContext() + "' inc on node '" + grouped.getId() + "'; context '" + grouped.getInterpreter().getContext().getContext());
				}
				else {
					grouped.decSigma();
					//System.out.println("(G) Node '" + initiator.getId() + "'; context '" + initiator.getInterpreter().getContext().getContext() + "' dec on node '" + grouped.getId() + "'; context '" + grouped.getInterpreter().getContext().getContext());
				}
			}
		}
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

	protected void operateOnNode() throws Exception {
		VWMLConflictRingNode operationalNode = null;
		VWMLConflictRingNodeAutomataInputs input = null;
		VWMLConflictRingNodeAutomataStates state = null;
		VWMLInterpreterImpl i = peekInterpreter();
		if (!isGroup()) {
			operationalNode = this;
		}
		else {
			String activeConflictContext = i.getObserver().getActiveConflictContext();
			if (activeConflictContext == null) {
				operationalNode = this;
			}
			else {
				// looking for a node inside the group
				if (activeConflictContext.equals(getId())) { // lead node
					operationalNode = this;
				}
				else {
					for(VWMLConflictRingNode n : group) {
						if (((String)n.getId()).equals(activeConflictContext)) {
							operationalNode = n;
							break;
						}
					}
				}
				if (operationalNode == null) {
					operationalNode = this;
				}
			}
		}
		if (operationalNode != null) {
			input = i.getObserver().getConflictOperationalState((String)operationalNode.getId());
			state = VWMLConflictRingNodeAutomataStates.STATE_PAS;
			if (operationalNode.getSigma() == 0) {
				state = VWMLConflictRingNodeAutomataStates.STATE_ACT;
			}
			//System.out.println("Node of '" + operationalNode.getInterpreter().getContext().getContext() + "'; sigma '" + operationalNode.getSigma() + "'; state '" + state + "'; input '" + input + "'");
		}
		nodeAutomata.runAction(operationalNode, input, state);
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
}
