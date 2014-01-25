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
import com.vw.lang.sink.java.operations.processor.operations.handlers.activatectx.VWMLOperationActivateContextHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.applytoctx.VWMLOperationApplyToContextHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.bp.VWMLOperationBreakPointHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.clone.VWMLOperationCloneHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.conflictend.VWMLOperationConflictSituationEndHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.conflictstart.VWMLOperationConflictSituationStartHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.createexpr.VWMLOperationCreateExprHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.dyncontext.VWMLOperationDynamicContextAddressingHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.equal.VWMLOperationEqualHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.exe.VWMLOperationExeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.first.VWMLOperationFirstHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.foreach.VWMLOperationForEachHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.ident.VWMLOperationIdentHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.implicit.assemble.VWMLOperationImplicitAssembleHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.in.VWMLOperationInHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.include.VWMLOperationIncludeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.interpret.VWMLOperationInterpretHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.intersect.VWMLOperationIntersectHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.join.VWMLOperationJoinHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.last.VWMLOperationLastHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.onfringe.VWMLOperationOnFringeHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.random.VWMLOperationRandomHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.relax.VWMLOperationRelaxHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.rest.VWMLOperationRestHandler;
import com.vw.lang.sink.java.operations.processor.operations.handlers.size.VWMLOperationSizeHandler;
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
			put(new VWMLOperation(VWMLOperationsCode.OPAPPLYTOCONTEXT),         new VWMLOperationApplyToContextHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPCLONE),   				new VWMLOperationCloneHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPFOREACH),   				new VWMLOperationForEachHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPDYNCONTEXT),   			new VWMLOperationDynamicContextAddressingHandler());
			put(new VWMLOperation(VWMLOperationsCode.OPSIZE),   				new VWMLOperationSizeHandler());
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
			reportException(context, operation, e);
			throw new Exception("Operation processor caught exception '" + e + "' on context '" + context.getContext() + "'; operation '" + operation + "'");
		}
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
