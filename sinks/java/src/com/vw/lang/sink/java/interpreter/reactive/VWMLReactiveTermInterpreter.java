package com.vw.lang.sink.java.interpreter.reactive;

import java.util.List;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterListener;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;
import com.vw.lang.sink.java.interpreter.seq.VWMLSequentialTermInterpreter;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Interprets multiple terms in one thread; simulating parallel interpreting (using reactor pattern)
 * @author ogibayev
 *
 */
public class VWMLReactiveTermInterpreter extends VWMLInterpreterImpl {
	private VWMLConflictRing ring = VWMLConflictRing.instance();
	private IVWMLGate timeFringeGate = null;
	
	private VWMLReactiveTermInterpreter() {
		setRing(ring);
	}
	
	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
		setRing(ring);
	}

	private VWMLReactiveTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		setTerms(terms);
		setLinkage(linkage);
		setContext(context);
		setRing(ring);
	}
	
	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLReactiveTermInterpreter(linkage, terms);
	}

	public static VWMLReactiveTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		return new VWMLReactiveTermInterpreter(linkage, terms, context);
	}
	
	@Override
	public void start() throws Exception {
		if (getConfig() == null) {
			// default configuration is used
			setConfig(VWMLInterpreterConfiguration.instance());
		}
		if (getTerms() == null  || getTerms().size() == 0) {
			throw new Exception("term should be set before method is called");
		}
		VWMLConflictRing.instance().setRingVisitor(getConfig().getRingVisitor());
		getConfig().setStepByStepInterpretation(true);
		// iterates through the conflict ring and associates ring node with reactive sequential interpreter
		// looking for ring node by source lifeterm's context 
		for(VWMLEntity e : getTerms()) {
			VWMLEntity p = e;
			while(p.getClonedFrom() != null) {
				p = p.getClonedFrom();
			}
			VWMLConflictRingExecutionGroup g = ring.findGroupByEntityContext(p.getContext().getContext(), true);
			if (g == null) {
				throw new Exception("couldn't find ring group by context '" + p.getContext().getContext() + "'");
			}
			activateSourceLifeTerm(g, this, e, null, null, false);
		}
		if (isNormalization()) {
			normalizeInterpreterData();
		}
		timeFringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getTimerManagerFringeName());
		// starts reactive interpretation activity
		setStatus(continueProcessingOfCurrentEntity);
		conditionalLoop(null);
		setStatus(stopped);
		getTimerManager().stop();
	}

	/**
	 * Runs interpreting loop until listener's decision to stop it
	 * @param listener
	 * @throws Exception
	 */
	@Override
	public void conditionalLoop(VWMLInterpreterListener listener) throws Exception {
		while((listener == null) ? true : listener.getInterpreterStatus() != VWMLInterpreterImpl.stopped) {
			VWMLConflictRingNode node = ring.next();
			if (node == null) {
				// no operational nodes - root interpreter is going to be stopped, so we have to clear resources
				VWMLResourceHostManagerFactory.hostManagerInstance().clearResource();
				break;
			}
			spinTimerManager(getTimerManager(), timeFringeGate);
			node.operate();
		}
	}
	
	/**
	 * Resets interpreter's data
	 */
	public void reset() {
		try {
			ring.reset();
		} catch (Exception e) {
			// nothing interested...
		}
	}	
	
	@Override
	public VWMLInterpreterImpl clone() {
		VWMLInterpreterImpl cloned = instance(super.getLinkage(), null, null);
		cloned.setConfig(this.getConfig());
		return cloned;
	}
	
	@Override
	public VWMLInterpreterImpl addTermInRunTime(VWMLConflictRingExecutionGroup g, VWMLInterpreterImpl masterInterpreter,
			                                    VWMLEntity term, VWMLContext forcedContext, VWMLInterpreterListener listener,
			                                    boolean considerTermAsLifeTerm) throws Exception {
		term.setLifeTermAsSource(true);
		VWMLInterpreterImpl impl = activateSourceLifeTerm(g, masterInterpreter, term, forcedContext, listener, true);
		term.setLifeTermAsSource(false);
		return impl;
	}

	@Override
	public void releaseTermResourcesAfterInterpretationDone(VWMLConflictRingNode node, VWMLInterpreterImpl interpreter, VWMLEntity term) throws Exception {
		interpreter.reset();
		if (node != null) {
			node.popInterpeter();
		}
	}
	
	protected void spinTimerManager(VWMLInterpreterTimerManager timerManager, IVWMLGate fringeGate) throws Exception {
		EWEntity e = fringeGate.invokeEW(IVWMLGate.builtInTimeCommandId, null);
		if (e != null) {
			timerManager.processReactive(Long.valueOf((String)e.getId()));
		}
	}
	
	protected void normalizeInterpreterData() throws Exception {
		// ring's normalization process
		ring.normalize();
	}
	
	protected VWMLInterpreterImpl activateSourceLifeTerm(VWMLConflictRingExecutionGroup g, VWMLInterpreterImpl activeInterpreter, VWMLEntity term, VWMLContext forcedContext, VWMLInterpreterListener listener, boolean addAdditionalInterpreterToNode) throws Exception {
		VWMLConflictRingNode n = (activeInterpreter != null) ? activeInterpreter.getRtNode() : null;
		if (n == null && g != null) {
			n = g.findMasterNode();
			if (n == null) {
				n = g.findMasterInAnyCase();
				if (isCloneMasterOnSLFTermActivation()) {
					n = n.clone(null);
					g.add(n);
				}
			}
			if (n.peekInterpreter() != null && !addAdditionalInterpreterToNode) { // already processed
				return null;
			}
		}
		VWMLSequentialTermInterpreter impl = null;
		if (!term.isLifeTermAsSource()) {
			n.setMarkAsCandidatOnClone(true);
		}
		else {
			// instantiates new sequential interpreter
			impl = VWMLSequentialTermInterpreter.instance(getLinkage(), term);
			impl.setForcedContext(forcedContext);
			impl.setListener(listener);
			impl.setConfig(getConfig());
			impl.setTimerManager(getTimerManager());
			impl.setRing(ring);
			impl.setMasterInterpreter(this);
			impl.setClonedFromEntity(term);
			if (n != null) {
				// associating interpreter and ring node
				n.pushInterpreter(impl);
			}
			else {
				getConfig().setStepByStepInterpretation(false);
			}
			// 'lazy' start (initializes interpreter's internal structures only; the execution phase is managed by ring)
			impl.start();
		}
		return impl;
	}
}
