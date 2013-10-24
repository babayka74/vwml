package com.vw.lang.beyond.java.fringe.gate.debug;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.debug.client.transport.http.VWMLHttpClient;
import com.vw.lang.debug.client.transport.http.VWMLHttpClient.VWMLHttpClientProps;
import com.vw.lang.debug.commands.ccontinue.VWMLDebugCommandContinue;
import com.vw.lang.debug.commands.listofbps.VWMLDebugCommandGetListOfBreakPoints;
import com.vw.lang.debug.commands.removeallbps.VWMLDebugCommandRemoveAllBreakPoints;
import com.vw.lang.debug.commands.removebp.VWMLDebugCommandRemoveBreakPoint;
import com.vw.lang.debug.commands.setbp.VWMLDebugCommandSetBreakPoint;
import com.vw.lang.debug.commands.test.VWMLDebugCommandTest;
import com.vw.lang.debug.common.VWMLDebugCommand;
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
	
	private Set<VWMLDebuggerBreakPoint> bpStorage = Collections.synchronizedSet(new HashSet<VWMLDebuggerBreakPoint>());
	
	private static final VWMLDebugCommand s_debugCommands[] = {
		new VWMLDebugCommandContinue(),
		new VWMLDebugCommandSetBreakPoint(),
		new VWMLDebugCommandRemoveBreakPoint(),
		new VWMLDebugCommandRemoveAllBreakPoints(),
		new VWMLDebugCommandGetListOfBreakPoints(),
		new VWMLDebugCommandTest()
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
		server = initializeHttpServer(clientListeningPort);
		client = initializeHttpClient(serverURL);
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		return null;
	}
	
	@Override
	// called on server's side context (different thread)
	public void setBreakPoint(String context, String command, boolean after, boolean before) {
		VWMLDebuggerBreakPoint bp = VWMLDebuggerBreakPoint.build(context, command, after, before);
		if (bpStorage.contains(bp)) {
			bpStorage.remove(bp);
		}
		bpStorage.add(VWMLDebuggerBreakPoint.build(context, command, after, before));
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
