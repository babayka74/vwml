package com.vw.lang.sink.java.operations.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.vw.lang.beyond.java.fringe.entity.EWComplexEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.GateConstants;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLInterpreterObserver;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;
import com.vw.lang.sink.java.operations.processor.operations.handlers.activate.VWMLOperationActivateHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.activatectx.VWMLOperationActivateContextHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.applytoctx.VWMLOperationApplyToContextHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.begin.VWMLOperationBeginHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.born.VWMLOperationBornHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.bp.VWMLOperationBreakPointHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.callp.VWMLOperationCallPHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.cartesian.VWMLOperationCartesianHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.clone.VWMLOperationCloneHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.cloneon.VWMLOperationCloneOnHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.conflictend.VWMLOperationConflictSituationEndHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.conflictstart.VWMLOperationConflictSituationStartHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.copy.VWMLOperationCopyHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr.VWMLOperationCreateExprHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.dyncontext.VWMLOperationDynamicContextAddressingHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.equal.VWMLOperationEqualHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.exe.VWMLOperationExeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.existsi.VWMLOperationExistsIHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.find.VWMLOperationFindHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.first.VWMLOperationFirstHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.foreach.VWMLOperationForEachHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.gate.VWMLOperationGateHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.get.VWMLOperationGetHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.ident.VWMLOperationIdentHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.implicit.assemble.VWMLOperationImplicitAssembleHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.in.VWMLOperationInHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.include.VWMLOperationIncludeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interceptor.VWMLOperationFinishInterceptionHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interceptor.VWMLOperationStartInterceptionHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interpret.VWMLOperationInterpretHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interrupt.VWMLOperationInterruptHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.intersect.VWMLOperationIntersectHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.join.VWMLOperationJoinHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.last.VWMLOperationLastHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.ltt.VWMLOperationLTTHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.onfringe.VWMLOperationOnFringeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.random.VWMLOperationRandomHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.recall.VWMLOperationRecallHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.relax.VWMLOperationRelaxHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.release.VWMLOperationReleaseHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.repeat.VWMLOperationRepeatHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.rest.VWMLOperationRestHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.size.VWMLOperationSizeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.squeeze.VWMLOperationSqueezeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.substruct.VWMLOperationSubstructHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.unknown.VWMLOperationUnknownOperationHandler;

/**
 * Defines logic of executing operations
 * @author ogibayev
 *
 */
public class VWMLOperationProcessor {
	
	private IVWMLGate debuggerGate = null;
	private VWMLInterpreterObserver observer = null;
	/**
	 * maps VWML operation to its handler
	 */
	@SuppressWarnings("serial")
	private static Map<VWMLOperation, VWMLOperationHandler> s_processorMap = new HashMap<VWMLOperation, VWMLOperationHandler>() {
		{
			put(new VWMLOperation(VWMLOperationsCode.OPINTERPRET),        		new VWMLOperationInterpretHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCREATEEXPR),       		new VWMLOperationCreateExprHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPRANDOM),           		new VWMLOperationRandomHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPEXECUTE),          		new VWMLOperationExeHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPIMPLICITASSEMBLE), 		new VWMLOperationImplicitAssembleHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPIDENT),           	 	new VWMLOperationIdentHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPACTIVATECTX),      		new VWMLOperationActivateContextHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPDO),               		new VWMLOperationOnFringeHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPRELAX),            		new VWMLOperationRelaxHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPJOIN),             		new VWMLOperationJoinHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPINTERSECT),        		new VWMLOperationIntersectHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSUBSTRUCT),        		new VWMLOperationSubstructHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPIN),               		new VWMLOperationInHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPINCL),             		new VWMLOperationIncludeHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPEQ),               		new VWMLOperationEqualHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFIRST),            		new VWMLOperationFirstHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPREST),             		new VWMLOperationRestHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPLAST),             		new VWMLOperationLastHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPBEGIN),             		new VWMLOperationBeginHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPAPPLYTOCONTEXT),         new VWMLOperationApplyToContextHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCLONE),   				new VWMLOperationCloneHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFOREACH),   				new VWMLOperationForEachHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPDYNCONTEXT),   			new VWMLOperationDynamicContextAddressingHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSIZE),   				new VWMLOperationSizeHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPINTERRUPT),   			new VWMLOperationInterruptHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPBORN),   				new VWMLOperationBornHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCALLP),   				new VWMLOperationCallPHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPGET),   					new VWMLOperationGetHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFIND),   				new VWMLOperationFindHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPGATE),   				new VWMLOperationGateHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPRECALL),   				new VWMLOperationRecallHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPREPEAT),   				new VWMLOperationRepeatHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPACTIVATE),   			new VWMLOperationActivateHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCARTESIAN),   			new VWMLOperationCartesianHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSQU),   					new VWMLOperationSqueezeHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCOPY),   				new VWMLOperationCopyHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSTARTINTERCEPTION),   	new VWMLOperationStartInterceptionHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSTARTINTERCEPTION_S),   	new VWMLOperationStartInterceptionHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFINISHINTERCEPTION),   	new VWMLOperationFinishInterceptionHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFINISHINTERCEPTION_S),   new VWMLOperationFinishInterceptionHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPRELEASE),   				new VWMLOperationReleaseHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPEXISTSI),   				new VWMLOperationExistsIHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPEXISTSI_S),   			new VWMLOperationExistsIHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPLTT),   					new VWMLOperationLTTHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCLONEON),   				new VWMLOperationCloneOnHandler());
			// service commands
			put(new VWMLOperation(VWMLOperationsCode.OPBREAKPOINT),       		new VWMLOperationBreakPointHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCONFLICTSITUATIONSTART), new VWMLOperationConflictSituationStartHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCONFLICTSITUATIONEND),   new VWMLOperationConflictSituationEndHandler());
		}
	};
	// called when unknown/unsupported operation is going to be executed
	private VWMLOperationUnknownOperationHandler unknownOperationHandler = new VWMLOperationUnknownOperationHandler();

	private VWMLOperationProcessor() {
		
	}
	
	/**
	 * Constructs and initializes new operation processor
	 * @return
	 */
	public static VWMLOperationProcessor instance() {
		return new VWMLOperationProcessor();
	}
	
	/**
	 * Processor's initialization steps
	 */
	public void init(VWMLInterpreterImpl interpreter) {
		debuggerGate = interpreter.getConfig().getDebuggerGate();
	}
	
	/**
	 * Processes incoming command
	 * @param interpreter
	 * @param linkage
	 * @param stack
	 * @param operation
	 * @throws Exception
	 */
	public void processOperation(VWMLInterpreterImpl interpreter, VWMLLinkage linkage, VWMLContext context, VWMLOperation operation) throws Exception {
		VWMLOperationHandler handler = s_processorMap.get(operation);
		if (handler == null) {
			handler = unknownOperationHandler;
		}
		try {
			checkBpAction(context, operation, true);
			handler.handle(interpreter, linkage, context, operation);
			checkBpAction(context, operation, false);
		}
		catch(Exception e) {
			e.printStackTrace();
			reportException(context, operation, e);
			throw new Exception("Operation processor caught exception '" + e + "' on context '" + context.getContext() + "'; operation '" + operation + "'; debug info '" + operation.getDebugInfo() + "'");
		}
	}
	

	/**
	 * Exports operation handler
	 * @param opCode
	 * @return
	 */
	public VWMLOperationHandler exportOperationHandler(VWMLOperationsCode opCode) {
		return s_processorMap.get(new VWMLOperation(opCode));
	}
	
	public VWMLInterpreterObserver getObserver() {
		return observer;
	}

	public void setObserver(VWMLInterpreterObserver observer) {
		this.observer = observer;
	}

	protected void checkBpAction(VWMLContext context, VWMLOperation operation, boolean beforeOp) {
		if (debuggerGate != null) {
			EWComplexEntity ce = EWEntityBuilder.buildComplexEntity(UUID.randomUUID().toString(), null);
			ce.link(EWEntityBuilder.buildSimpleEntity(context.getContext(), null));
			ce.link(EWEntityBuilder.buildSimpleEntity(operation.getId(), null));
			ce.link(EWEntityBuilder.buildSimpleEntity(String.valueOf(beforeOp), null));
			debuggerGate.invokeEW(debugCheckBpCommand(), ce);
			ce = null; // makes all linked objects to be eligible for gc
		}
	}

	protected void reportException(VWMLContext context, VWMLOperation operation, Object exception) {
		if (debuggerGate != null) {
			EWComplexEntity ce = EWEntityBuilder.buildComplexEntity(UUID.randomUUID().toString(), null);
			ce.link(EWEntityBuilder.buildSimpleEntity(context.getContext(), null));
			ce.link(EWEntityBuilder.buildSimpleEntity(operation.getId(), null));
			ce.link(EWEntityBuilder.buildSimpleEntity(exception, null));
			debuggerGate.invokeEW(reportOnCaughtExceptionCommand(), ce);
			ce = null; // makes all linked objects to be eligible for gc
		}
	}
	
	private String debugCheckBpCommand() {
		return GateConstants.debugCheckBpActionCommand;
	}
	
	private String reportOnCaughtExceptionCommand() {
		return GateConstants.debugCaughtExceptionOnOperationProcessorActionCommand;
	}
}
