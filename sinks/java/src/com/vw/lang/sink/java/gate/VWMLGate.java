package com.vw.lang.sink.java.gate;

import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNodeAutomataInputs;

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
	private VWMLInterpreterImpl blockedInterpreter;

	public static final String s_blockedMode = "blocked";	
	
	public VWMLGate(VWMLConflictRing ring, String registrationKey, boolean blockedMode) {
		super();
		this.ring = ring;
		this.registrationKey = registrationKey;
		this.blockedMode = blockedMode;
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

	public synchronized void blockActivity(VWMLInterpreterImpl blockedInterpreter) {
		if (isBlockedMode()) {
			this.blockedInterpreter = blockedInterpreter;
			blockedInterpreter.getObserver().setBlockedByGate(this);
			blockedInterpreter.getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), VWMLConflictRingNodeAutomataInputs.IN_W);
			try {
				ring.incrementNumOfBlockedNodes(this);
			} catch (Exception e) {
				// nothing todo
			}
		}
	}

	// this method can be called from varios timer's callbacks, in particular from observer
	public synchronized void unblockActivity() {
		if (isBlockedMode() && blockedInterpreter != null) {
			blockedInterpreter.getObserver().setBlockedByGate(null);
			blockedInterpreter.getObserver().setConflictOperationalState(VWMLInterpreterObserver.getWaitContext(), null);
			blockedInterpreter = null;
			try {
				ring.decrementNumOfBlockedNodes();
			} catch (Exception e) {
				// nothing todo
			}
		}
	}
}
