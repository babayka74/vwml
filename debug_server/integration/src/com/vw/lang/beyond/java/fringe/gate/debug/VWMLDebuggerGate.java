package com.vw.lang.beyond.java.fringe.gate.debug;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.GateCommandHandler;
import com.vw.lang.beyond.java.fringe.gate.GateConstants;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.beyond.java.fringe.gate.debug.commands.VWML2DebuggerCheckBpActionCommand;
import com.vw.lang.beyond.java.fringe.gate.debug.commands.VWML2DebuggerReportExceptionOnOPCommand;
import com.vw.lang.debug.client.transport.http.VWMLHttpClient;
import com.vw.lang.debug.client.transport.http.VWMLHttpClient.VWMLHttpClientProps;
import com.vw.lang.debug.commands.ccontinue.VWMLDebugCommandContinue;
import com.vw.lang.debug.commands.listofbps.VWMLDebugCommandGetListOfBreakPoints;
import com.vw.lang.debug.commands.notify.VWMLDebugCommandNotify;
import com.vw.lang.debug.commands.removeallbps.VWMLDebugCommandRemoveAllBreakPoints;
import com.vw.lang.debug.commands.removebp.VWMLDebugCommandRemoveBreakPoint;
import com.vw.lang.debug.commands.setbp.VWMLDebugCommandSetBreakPoint;
import com.vw.lang.debug.commands.test.VWMLDebugCommandTest;
import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResponseData;
import com.vw.lang.debug.common.VWMLDebugCommandResult;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer.VWMLHttpServerProps;

/**
 * VWML Debugger's gate. Process commands which are sent by command line tool
 * @author Oleg
 *
 */
public class VWMLDebuggerGate implements IVWMLGate, IVWMLDebuggerCommandInterface {

	private IEW2VWMLGate vwmlGate = null;
	private VWMLHttpServer server = null;
	private VWMLHttpClient client = null;
	
	private Map<VWMLDebuggerBreakPoint, VWMLDebuggerBreakPoint> bpStorage = Collections.synchronizedMap(new HashMap<VWMLDebuggerBreakPoint, VWMLDebuggerBreakPoint>());
	private Map<String, VWMLDebuggerStopReason> contextStopExecutionReason = Collections.synchronizedMap(new HashMap<String, VWMLDebuggerStopReason>());
	
	private static final VWMLDebugCommand s_debugCommands[] = {
		new VWMLDebugCommandContinue(),
		new VWMLDebugCommandSetBreakPoint(),
		new VWMLDebugCommandRemoveBreakPoint(),
		new VWMLDebugCommandRemoveAllBreakPoints(),
		new VWMLDebugCommandGetListOfBreakPoints(),
		new VWMLDebugCommandTest()
	};
	
	private Map<String, VWML2DebuggerCommandHandler> s_vwml2DebuggerInternalCommands = new HashMap<String, VWML2DebuggerCommandHandler>() {
		{
			put(GateConstants.debugCheckBpActionCommand, new VWML2DebuggerCheckBpActionCommand());
			put(GateConstants.debugCaughtExceptionOnOperationProcessorActionCommand, new VWML2DebuggerReportExceptionOnOPCommand());
		}
	};
	
	private static VWMLDebuggerGate s_instance = null;
	public static final String propDebugClientListeningPort = "debug.client.listening.port";
	public static final String propDebugServerURL = "debug.server.url";
	
	private VWMLDebuggerGate() {
		
	}
	
	public static synchronized IVWMLGate instance() {
		if (s_instance == null) {
			s_instance = new VWMLDebuggerGate();
			try {
				s_instance.init();
			}
			catch(Exception ex) {
				s_instance = null;
				System.out.println("caught exception during initialization of debugger's gate; exception '" + ex + "'");
			}
		}
		return s_instance;
	}
	
	@Override
	public void init() throws Exception {
	}

	@Override
	public void done() throws Exception {
		client = null;
		if (server != null) {
			server.stop();
		}
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
		vwmlGate = gate;
	}

	@Override
	public void activateConfiguration(Properties props) throws Exception {
		int clientListeningPort = 0;
		String listeningPort = props.getProperty(propDebugClientListeningPort);
		if (listeningPort != null) {
			try {
				clientListeningPort = Integer.parseInt(listeningPort);
			}
			catch(NumberFormatException e) {
				// simple swallow it
			}
		}
		String serverURL = props.getProperty(propDebugServerURL);
		if (serverURL == null) {
			throw new Exception("VWMLDebuggerGate has invalid configuration; server URL must be set");
		}
		// sets default active breakpoint for all exceptions on all contexts
		setBreakPoint(IVWMLDebuggerCommandInterface.exceptionCaughtContext, IVWMLDebuggerCommandInterface.exceptionCaughtCommand, false, false);
		server = initializeHttpServer(clientListeningPort);
		client = initializeHttpClient(serverURL);
	}
	
	@Override
	public EWEntity invokeVW(String commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public EWEntity invokeEW(String commandId, EWEntity commandArgs) {
		EWEntity e = null;
		VWML2DebuggerCommandHandler ch = s_vwml2DebuggerInternalCommands.get((String)commandId);
		if (ch != null) {
			ch.setDebuggerInterface(this);
			e = ch.handler(commandArgs);
		}
		return e;
	}
	
	@Override
	// called on server's side context (different thread)
	public void setBreakPoint(String context, String command, boolean after, boolean before) {
		VWMLDebuggerBreakPoint bp = VWMLDebuggerBreakPoint.build(context, command, after, before);
		if (bpStorage.containsKey(bp)) {
			bpStorage.remove(bp);
		}
		bpStorage.put(bp, bp);
	}

	@Override
	// called on server's side context (different thread)
	public void removeBreakPoint(String context, String command) {
		bpStorage.remove(VWMLDebuggerBreakPoint.build(context, command, false, false));
	}

	@Override
	// called on server's side context (different thread)
	public void removeAllBreakPoints() {
		bpStorage.clear();
		// sets default active breakpoint for all exceptions on all contexts
		setBreakPoint(IVWMLDebuggerCommandInterface.exceptionCaughtContext, IVWMLDebuggerCommandInterface.exceptionCaughtCommand, false, false);
	}
	
	@Override
	public boolean checkIfOperationBreakPointIsActive(String context, String command, boolean beforeOp) {
		boolean r = false;
		VWMLDebuggerBreakPoint bp = bpStorage.get(VWMLDebuggerBreakPoint.build(context, command, false, false));
		if (bp != null && ((beforeOp && bp.isBefore()) || (!beforeOp && bp.isAfter()))) {
			r = true;
		}
		return r;
	}
	
	@Override
	public boolean checkIfExceptionBreakPointIsActive(String context) {
		VWMLDebuggerBreakPoint bp = bpStorage.get(VWMLDebuggerBreakPoint.build(context, IVWMLDebuggerCommandInterface.exceptionCaughtCommand, false, false));
		return bp == null;
	}
	
	@Override
	public void waitOnBreakPoint(String context, String command) {
		VWMLDebuggerBreakPoint bp = bpStorage.get(VWMLDebuggerBreakPoint.build(context, command, false, false));
		if (bp != null) {
			synchronized(bp) {
				try {
					contextStopExecutionReason.put(context, VWMLDebuggerStopReason.build(VWMLDebuggerStopReason.Reason.BREAKPOINT, command));
					// to notify debug tool client about activated bp
					bp.wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}

	@Override
	public void resumeExecutionFlow(String context) {
		if (context == null || context.length() == 0) {
			context = IVWMLDebuggerCommandInterface.exceptionCaughtContext;
		}
		VWMLDebuggerStopReason stopReason = contextStopExecutionReason.get(context);
		if (stopReason != null && stopReason.getReason() == VWMLDebuggerStopReason.Reason.BREAKPOINT) {
			VWMLDebuggerBreakPoint bp = bpStorage.get(VWMLDebuggerBreakPoint.build(context,
																				   (String)stopReason.getIdentificator(),
																				   false,
																				   false));
			if (bp != null) {
				synchronized(bp) {
					bp.notify();
				}
			}				
		}
		contextStopExecutionReason.remove(context);
	}
	
	@Override
	public void delegate(VWMLDebugCommandResponseData notification) {
		VWMLDebugCommandNotify notify = new VWMLDebugCommandNotify();
		notify.setData(notification);
		try {
			client.send(notify);
		} catch (Exception e) {
			// swallow it - not critical at all
		}
		notify = null;
	}
	
	private VWMLHttpServer initializeHttpServer(int port) throws Exception {
		VWMLHttpServerProps props = new VWMLHttpServerProps();
		props.setPort(port);
		for(VWMLDebugCommand dbgCommand : s_debugCommands) {
			props.registerHandler(dbgCommand.getName(), dbgCommand.getClass());
		}
		VWMLHttpServer srv = VWMLHttpServer.instance(props);
		srv.start(this);
		return srv;
	}
	
	private VWMLHttpClient initializeHttpClient(String serverURL) throws Exception {
		VWMLHttpClientProps props = new VWMLHttpClientProps();
		props.setTargetUrl(serverURL);
		VWMLHttpClient client = VWMLHttpClient.instance();
		client.init(props);
		return client;
	}
}
