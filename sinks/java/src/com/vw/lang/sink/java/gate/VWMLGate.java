package com.vw.lang.sink.java.gate;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Live entity's gate
 * @author Oleg
 *
 */
public class VWMLGate {
	private VWMLConflictRing ring;
	private String registrationKey;
	// blocked mode
	private boolean blockedMode;
	private VWMLConflictRingNode node;
	// gates starts interpretation process of dockingTerm in case if 'Ready' operation returns 'false'
	// usually dockingTerm is interpreted as 'fringe' operation
	private VWMLEntity dockingTerm;
	
	public static final String s_blockedMode = "blocked";	
	
	public VWMLGate(VWMLConflictRing ring, VWMLConflictRingNode node, String registrationKey, boolean blockedMode, VWMLEntity dockingTerm) {
		super();
		this.ring = ring;
		this.registrationKey = registrationKey;
		this.blockedMode = blockedMode;
		this.dockingTerm = dockingTerm;
		this.node = node;
	}

	public VWMLConflictRing getRing() {
		return ring;
	}
	
	public void setRing(VWMLConflictRing ring) {
		this.ring = ring;
	}
	
	public String getRegistrationKey() {
		return registrationKey;
	}
	
	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

	public boolean isBlockedMode() {
		return blockedMode;
	}

	public void setBlockedMode(boolean blockedMode) {
		this.blockedMode = blockedMode;
	}

	public VWMLEntity getDockingTerm() {
		return dockingTerm;
	}

	public void setDockingTerm(VWMLEntity dockingTerm) {
		this.dockingTerm = dockingTerm;
	}

	public void blockActivity() {
		if (isBlockedMode()) {
			VWMLConflictRing.sleepNode(node);
		}
	}

	// this method can be called from varios timer's callbacks, in particular from observer
	public void unblockActivity() {
		if (isBlockedMode()) {
			VWMLConflictRing.wakeupNode(node);
		}
	}
}
