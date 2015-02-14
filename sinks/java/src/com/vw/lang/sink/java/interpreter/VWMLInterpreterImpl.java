package com.vw.lang.sink.java.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingExecutionGroup;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.processor.VWMLOperationProcessor;

/**
 * Abstract interpreter's functionality
 * @author ogibayev
 *
 */
public abstract class VWMLInterpreterImpl extends VWMLObject {

	public static final int continueProcessingOfCurrentEntity = 0;
	public static final int nextEntityToProcess = 1;
	public static final int stopProcessing = 2;
	public static final int finishedEntityProcessing = 3;
	public static final int stopped = 4;
	
	private int status = stopProcessing;
	// list of terms to be interpreted
	private List<VWMLEntity> terms;
	// linkage (usually linkage module of the main module)
	private VWMLLinkage linkage;
	// interpreter's configuration
	private VWMLInterpreterConfiguration config = null;
	// this interpreter was cloned as result of cloning operation of entity
	private VWMLEntity clonedFromEntity = null;
	// 'true' in case if interpreter was instantiated during 'clone' operation
	private boolean cloned = false;
	// 'true' in case if interpreter was pushed to interpreter's stack during CallP or ForEach operations
	// any operation which required own interpreter
	private boolean pushed = false;
	// interpreter's run-time node (the same interpreter may belong to some nodes in the same time - nut all nodes must be in the same node's group)
	private VWMLConflictRingNode rtNode = null;
	// interpreter's conflict ring
	private VWMLConflictRing ring = null;
	// internal worker thread
	private VWMLContext context = VWMLContext.instance("interpreter_context");
	// stack of child interpreters; the interpreter is interpreted as child when it is instantiated during
	// 'activate context' operation
	private VWMLStack childInterpreters = VWMLStack.instance();
	// operating processor
	private VWMLOperationProcessor processor = VWMLOperationProcessor.instance();
	// observer
	private VWMLInterpreterObserver observer = new VWMLInterpreterObserver(this);
	// reactive timer manager
	private VWMLInterpreterTimerManager timerManager = VWMLInterpreterTimerManager.instance();
	// interpreting entity (or component in terms of ForEach operation) of synthetic entity
	private VWMLEntity interpretingEntityForSyntheticEntity = null;
	// interpreting entity which is used for passing arguments for CallP command
	private VWMLEntity interpretingEntityForArgEntity = null;
	// interpreter's state listener
	private VWMLInterpreterListener listener = null;
	// for example 'reactive' interpreter instantiates 'sequential' interpreters for each life term
	// and sometimes (for 'ForEach') operations need to know master interpreter since it may
	// implement specific logic which is needed for operation
	private VWMLInterpreterImpl masterInterpreter = null;
	// forced context is used on child interpreters, which are activated during some types of operations like forEach and ':'
	private VWMLContext forcedContext = null;
	// delayed tasks can be executed by interpreter on next iteration step
	private List<VWMLInterpreterDeferredTask> delayedTasks = new ArrayList<VWMLInterpreterDeferredTask>();
	// in case 'true' interpreter's data should be normalized.
	// Parallel interpreter itself normalizes data and passes them to underlied reactive interpreters
	private boolean normalization = true;
	// used when new ring is created and new node is associated with cloned term
	private boolean releaseClonedResource = false;
	// clones master in case if implicit master is available only (usually used by parallel interpreter when new ring is activated)
	private boolean cloneMasterOnSLFTermActivation = false;
	
	public VWMLInterpreterImpl() {
		super("interpreter");
	}

	/**
	 * Starts interpretation logic
	 * @throws Exception
	 */
	public abstract void start() throws Exception;

	/**
	 * Clones current interpreter
	 */
	public abstract VWMLInterpreterImpl clone();
	
	/**
	 * Resets interpreter's data
	 */
	public abstract void reset();
	
	public void setForcedContext(VWMLContext context) {
		forcedContext = context;
	}

	public VWMLContext getForcedContext() {
		return forcedContext;
	}

	/**
	 * Moves interpreter to another master
	 */
	public void move(VWMLInterpreterImpl master) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}
	
	/**
	 * Adds term to the same interpreter in runtime; used for reactive and parallel interpreters only
	 * @param g
	 * @param masterInterpreter
	 * @param term
	 * @param forcedContext
	 * @param listener
	 * @throws Exception
	 */
	public VWMLInterpreterImpl addTermInRunTime(VWMLConflictRingExecutionGroup g, VWMLInterpreterImpl masterInterpreter, VWMLEntity term, VWMLContext forcedContext, VWMLInterpreterListener listener, boolean considerTermAsLifeTerm) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}
	
	/**
	 * Releases interpreter's allocated resources
	 * @param node
	 * @param interpreter
	 * @param term
	 */
	public void releaseTermResourcesAfterInterpretationDone(VWMLConflictRingNode node, VWMLInterpreterImpl interpreter, VWMLEntity term) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}
	
	/**
	 * Runs interpreting loop until listener's decision to stop it (used by reactive and parallel interpreters, see operation ForEach as example and reactive interpreter)
	 * @param listener
	 * @throws Exception
	 */
	public void conditionalLoop(VWMLInterpreterListener listener) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}

	/**
	 * Asks interpreter for new activity
	 * @param ringTerms
	 * @param clonedFrom
	 */
	public void newActivity(List<VWMLEntity> ringTerms, VWMLEntity clonedFrom) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}

	/**
	 * Runs one step execution process
	 * @return
	 * @throws Exception
	 */
	public boolean oneStep() throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}

	/**
	 * Given node is be blocked on interpretation during oneStep
	 * @param node
	 */
	public void addBlockedOnInterpretation(VWMLConflictRingNode node) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}

	/**
	 * Given node is be unblocked on interpretation during oneStep
	 * @param node
	 */
	public void removeBlockedOnInterpretation(VWMLConflictRingNode node) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}
	
	/**
	 * Hidden activity is not visible for ring's search operation and used for LTT operation only
	 * @param ringTerms
	 * @param clonedFrom
	 * @throws Exception
	 */
	public void hiddenActivity(List<VWMLEntity> ringTerms, VWMLEntity clonedFrom) throws Exception {
		throw new Exception("Must be implemented by concrete interpreter");
	}
	
	public VWMLConflictRingNode getRtNode() {
		return rtNode;
	}

	public void setRtNode(VWMLConflictRingNode rtNode) {
		this.rtNode = rtNode;
	}

	public VWMLConflictRing getRing() {
		return ring;
	}

	public void setRing(VWMLConflictRing ring) {
		this.ring = ring;
	}

	public List<VWMLEntity> getTerms() {
		return terms;
	}

	public void setTerms(List<VWMLEntity> terms) {
		this.terms = terms;
	}

	public VWMLLinkage getLinkage() {
		return linkage;
	}

	public void setLinkage(VWMLLinkage linkage) {
		this.linkage = linkage;
	}

	public VWMLInterpreterConfiguration getConfig() {
		return config;
	}

	public void setConfig(VWMLInterpreterConfiguration config) {
		this.config = config;
	}

	public VWMLContext getContext() {
		return context;
	}

	public VWMLOperationProcessor getProcessor() {
		return processor;
	}

	public VWMLInterpreterObserver getObserver() {
		return observer;
	}

	public void setObserver(VWMLInterpreterObserver observer) {
		this.observer = observer;
		// delegates it to processor
		processor.setObserver(observer);
	}

	public VWMLInterpreterTimerManager getTimerManager() {
		return timerManager;
	}

	public void setTimerManager(VWMLInterpreterTimerManager timerManager) {
		this.timerManager = timerManager;
	}

	public VWMLEntity getInterpretingEntityForSyntheticEntity() {
		return interpretingEntityForSyntheticEntity;
	}

	public void setInterpretingEntityForSyntheticEntity(VWMLEntity interpretingEntityForSyntheticEntity) {
		this.interpretingEntityForSyntheticEntity = interpretingEntityForSyntheticEntity;
	}

	public void setInterpretingEntityForArgEntity(VWMLEntity interpretingEntityForArgEntity) {
		this.interpretingEntityForArgEntity = interpretingEntityForArgEntity;
	}

	public VWMLEntity getInterpretingEntityForArgEntity() {
		return this.interpretingEntityForArgEntity;
	}
	
	public VWMLInterpreterListener getListener() {
		return listener;
	}

	public void setListener(VWMLInterpreterListener listener) {
		this.listener = listener;
	}

	public VWMLInterpreterImpl getMasterInterpreter() {
		return masterInterpreter;
	}

	public void setMasterInterpreter(VWMLInterpreterImpl masterInterpreter) {
		this.masterInterpreter = masterInterpreter;
	}

	public VWMLInterpreterDeferredTask getDeferredTask() {
		VWMLInterpreterDeferredTask task = null;
		if (delayedTasks.size() != 0) {
			task = delayedTasks.get(0);
			delayedTasks.remove(task);
		}
		return task;
	}

	public void setDeferredTask(VWMLInterpreterDeferredTask delayedTask) {
		delayedTasks.add(delayedTask);
	}

	public void pushInterpreterToChildStack(VWMLInterpreterImpl interpreter) {
		childInterpreters.push(interpreter);
	}
	
	public VWMLInterpreterImpl peekInterpreterFromChildStack() {
		return (VWMLInterpreterImpl)childInterpreters.peek();
	}
	
	public void popInterpreterFromChildStack() {
		childInterpreters.pop();
	}
	
	/**
	 * Step-by-step interpretation
	 * @return 'false' in case if interpretation finished, otherwise 'true' is returned
	 * @throws Exception
	 */
	public boolean step() throws Exception {
		throw new Exception("not implemented");
	}
		
	protected void setContext(VWMLContext context) {
		this.context = context;
	}
	
	/**
	 * Returns last interpreter's status
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets status
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
		if (listener != null) {
			listener.hanldeStatus(this);
		}
	}

	public boolean isCloned() {
		return cloned;
	}

	public void setCloned(boolean cloned) {
		this.cloned = cloned;
	}

	public VWMLEntity getClonedFromEntity() {
		return clonedFromEntity;
	}

	public void setClonedFromEntity(VWMLEntity clonedFromEntity) {
		this.clonedFromEntity = clonedFromEntity;
	}

	public boolean isPushed() {
		return pushed;
	}

	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}

	public boolean isNormalization() {
		return normalization;
	}

	public void setNormalization(boolean normalization) {
		this.normalization = normalization;
	}

	public boolean isReleaseClonedResource() {
		return releaseClonedResource;
	}

	public void setReleaseClonedResource(boolean releaseClonedResource) {
		this.releaseClonedResource = releaseClonedResource;
	}

	public boolean isCloneMasterOnSLFTermActivation() {
		return cloneMasterOnSLFTermActivation;
	}

	public void setCloneMasterOnSLFTermActivation(boolean cloneMasterOnSLFTermActivation) {
		this.cloneMasterOnSLFTermActivation = cloneMasterOnSLFTermActivation;
	}
}
