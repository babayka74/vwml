package com.vw.lang.sink.java.interpreter.parallel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.VWMLFringesRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.reactive.VWMLReactiveTermInterpreter;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Uses pool of threads in order to schedule terms interpretation
 * @author ogibayev
 *
 */
public class VWMLParallelTermInterpreter extends VWMLInterpreterImpl {

	protected static class VWMLReactiveActivity implements Runnable {

		protected static final boolean masterRing = true;
		protected static final boolean secondaryRing = false;
		
		private VWMLParallelTermInterpreter masterInterpreter = null;
		private List<VWMLEntity> terms = null;
		private VWMLReactiveTermInterpreter interpreter = null;
		private VWMLEntity clonedFrom = null;
		private Integer signalOnStart = null;
		private Integer signalOnStop = null;
		private AtomicBoolean started = new AtomicBoolean(false);
		private boolean stopped = false;
		private boolean ringCopyAsSecondary = VWMLReactiveActivity.masterRing;
		private boolean cloneMasterNode = false;
		private boolean waitTillStart = false;
		
		public boolean isStopped() {
			return stopped;
		}

		public VWMLEntity getClonedFrom() {
			return clonedFrom;
		}

		public void setClonedFrom(VWMLEntity clonedFrom) {
			this.clonedFrom = clonedFrom;
		}

		public Integer getSignalOnStart() {
			return signalOnStart;
		}

		public void setSignalOnStart(Integer signalOnStart) {
			this.signalOnStart = signalOnStart;
		}

		public Integer getSignalOnStop() {
			return signalOnStop;
		}

		public void setSignalOnStop(Integer signalOnStop) {
			this.signalOnStop = signalOnStop;
		}
		
		public VWMLParallelTermInterpreter getMasterInterpreter() {
			return masterInterpreter;
		}

		public void setMasterInterpreter(VWMLParallelTermInterpreter masterInterpreter) {
			this.masterInterpreter = masterInterpreter;
		}

		public List<VWMLEntity> getTerms() {
			return terms;
		}

		public void setTerms(List<VWMLEntity> terms) {
			this.terms = terms;
		}

		public VWMLReactiveTermInterpreter getInterpreter() {
			return interpreter;
		}

		public boolean isRingCopyAsSecondary() {
			return ringCopyAsSecondary;
		}

		public void setRingCopyAsSecondary(boolean ringCopyAsSecondary) {
			this.ringCopyAsSecondary = ringCopyAsSecondary;
		}

		public boolean isCloneMasterNode() {
			return cloneMasterNode;
		}

		public void setCloneMasterNode(boolean cloneMasterNode) {
			this.cloneMasterNode = cloneMasterNode;
		}

		public boolean isWaitTillStart() {
			return waitTillStart;
		}

		public void setWaitTillStart(boolean waitTillStart) {
			this.waitTillStart = waitTillStart;
		}

		public void start() {
			Thread t = new Thread(this);
			t.start();
		}
		
		@Override
		public void run() {
			interpreter = VWMLReactiveTermInterpreter.instance(getMasterInterpreter().getLinkage(), getTerms());
			interpreter.setConfig(getMasterInterpreter().getConfig());
			interpreter.setMasterInterpreter(getMasterInterpreter());
			interpreter.getRing().copyFrom(getMasterInterpreter().getRing());
			interpreter.setNormalization(false);
			interpreter.setCloneMasterOnSLFTermActivation(this.isCloneMasterNode());
			if (getClonedFrom() != null) {
				interpreter.setClonedFromEntity(getClonedFrom());
				interpreter.setReleaseClonedResource(true);
			}
			if (isRingCopyAsSecondary() == VWMLReactiveActivity.secondaryRing) {
				interpreter.getRing().ringAsSecondaryCopy();
			}
			String activityName = "reactive_" + String.valueOf(interpreter.getRing().getId());
			try {
				if (isWaitTillStart()) {
					started.getAndSet(true);
					synchronized(signalOnStart) {
						signalOnStart.notifyAll();
					}
				}
				masterInterpreter.notifyActivityBroker(IVWMLGate.builtInCreateActivityCommandId, Thread.currentThread().getId(), activityName);
				System.out.println("Ring started '" + interpreter.getRing() + "'");
				interpreter.start();
				System.out.println("Ring finished '" + interpreter.getRing() + "'");
				// notifies master interpreter about finishing
				synchronized(signalOnStop) {
					signalOnStop.notifyAll();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Ring finished with exception '" + interpreter.getRing() + "'");
			}
			masterInterpreter.notifyActivityBroker(IVWMLGate.builtInRemoveActivityCommandId, Thread.currentThread().getId(), activityName);
			stopped = true;
		}
		
		protected void markAsStarted() {
			started.getAndSet(true);
		}
		
		protected boolean isStarted() {
			return started.get();
		}
	}
	
	private static final int waitPeriodForCompletition = 10;
	private List<VWMLReactiveActivity> activities = Collections.synchronizedList(new ArrayList<VWMLReactiveActivity>());
	private Integer signalOnStop = new Integer(0x1235);
	private IVWMLGate activityFringeGate = VWMLFringesRepository.getGateByFringeName(VWMLFringesRepository.getActivityBrokerFringeName());
	
	private VWMLParallelTermInterpreter() {
	}
	
	private VWMLParallelTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms) {
		setTerms(terms);
		setLinkage(linkage);
	}

	private VWMLParallelTermInterpreter(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		setTerms(terms);
		setLinkage(linkage);
		setContext(context);
	}
	
	public static VWMLParallelTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms) {
		return new VWMLParallelTermInterpreter(linkage, terms);
	}

	public static VWMLParallelTermInterpreter instance(VWMLLinkage linkage, List<VWMLEntity> terms, VWMLContext context) {
		return new VWMLParallelTermInterpreter(linkage, terms, context);
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
		// activates rings
		String activityName = "parallel_" + Thread.currentThread().getId();
		notifyActivityBroker(IVWMLGate.builtInCreateActivityCommandId, Thread.currentThread().getId(), activityName);
		// remember all execution groups belong to ring of parallel interpreter (this ring is not scheduled)
		VWMLConflictRing.instance().normalize();
		setRing(VWMLConflictRing.instance());
		getRing().setMaster(true);
		activateRings(getTerms(), null, VWMLReactiveActivity.masterRing, false, false);
		waitForAll();
		notifyActivityBroker(IVWMLGate.builtInRemoveActivityCommandId, Thread.currentThread().getId(), activityName);
	}

	@Override
	public VWMLInterpreterImpl clone() {
		return null;
	}

	@Override
	public void reset() {
		for(VWMLReactiveActivity activity : activities) {
			activity.getInterpreter().reset();
		}
		activities.clear();
	}

	@Override
	public void newActivity(List<VWMLEntity> ringTerms, VWMLEntity clonedFrom) throws Exception {
		activateRings(ringTerms, clonedFrom, VWMLReactiveActivity.secondaryRing, true, true);
	}
	
	protected void activateRings(List<VWMLEntity> terms, VWMLEntity clonedFrom, boolean masterRing, boolean cloneMaster, boolean waitTillStart) throws Exception {
		int nodesPerRing = getConfig().getNodesPerRing();
		int nodes = terms.size() / nodesPerRing;
		List<VWMLEntity> ringTerms = new ArrayList<VWMLEntity>();
		int i = 0;
		for(int n = 0; n < nodes; n++) {
			for(int j = 0; j < nodesPerRing; j++) {
				ringTerms.add(terms.get(j + i));
			}
			activateRing(ringTerms, clonedFrom, masterRing, cloneMaster, waitTillStart);
			ringTerms = new ArrayList<VWMLEntity>();
			i += nodesPerRing;
			masterRing = VWMLReactiveActivity.secondaryRing;
		}
		// last ring
		for(int j = i; j < terms.size(); j++) {
			ringTerms.add(terms.get(j));
		}
		activateRing(ringTerms, clonedFrom, masterRing, cloneMaster, waitTillStart);
	}
	
	protected void activateRing(List<VWMLEntity> ringTerms, VWMLEntity clonedFrom, boolean masterRing, boolean cloneMaster, boolean waitTillStart) throws Exception {
		if (ringTerms.size() != 0) {
			Integer signalOnStart = new Integer(0x1111);
			VWMLReactiveActivity activity = new VWMLReactiveActivity();
			activity.setMasterInterpreter(this);
			activity.setTerms(ringTerms);
			activity.setSignalOnStart(signalOnStart);
			activity.setSignalOnStop(signalOnStop);
			activity.setRingCopyAsSecondary(masterRing);
			activity.setClonedFrom(clonedFrom);
			activity.setCloneMasterNode(cloneMaster);
			activity.setWaitTillStart(waitTillStart);
			activities.add(activity);
			activity.start();
			if (waitTillStart) {
				while(!activity.isStarted()) {
					synchronized(signalOnStart) {
						signalOnStart.wait(waitPeriodForCompletition);
					}
				}
				System.out.println("activity started");
			}
		}
	}
	
	protected void waitForAll() throws Exception {
		for(int i = 0; i < activities.size(); i++) {
			if (!activities.get(i).isStopped()) {
				synchronized(signalOnStop) {
					signalOnStop.wait(waitPeriodForCompletition);
				}
				i = -1;
			}
		}
	}
	
	protected void notifyActivityBroker(String command, Long activityId, String activityName) {
		if (activityFringeGate != null) {
			activityFringeGate.invokeEW(command, buildActivityPropsArgsAsEntity(activityId, activityName));
		}
	}
	
	protected EWEntity buildActivityPropsArgsAsEntity(Long activityId, String activityName) {
		String name = String.valueOf(Thread.currentThread().getId());
		EWEntity activityPropsArgs = EWEntityBuilder.buildComplexEntity(name, null);
		EWEntity idProp = EWEntityBuilder.buildSimpleEntity(String.valueOf(activityId), null);
		EWEntity nameProp = EWEntityBuilder.buildSimpleEntity(activityName, null);
		activityPropsArgs.getLink().link(idProp);
		activityPropsArgs.getLink().link(nameProp);
		return activityPropsArgs;
	}
}
