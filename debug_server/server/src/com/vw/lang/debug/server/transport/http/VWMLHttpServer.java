package com.vw.lang.debug.server.transport.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vw.lang.debug.server.VWMLDebugServerCommand;

/**
 * Simple HTTP server based on java HttpServer class
 * @author Oleg
 *
 */
public class VWMLHttpServer {
	
	/**
	 * Server's properties
	 * @author Oleg
	 *
	 */
	public static class VWMLHttpServerProps {
		// handlers' map
		private Map<String, VWMLDebugServerCommand> handlers = new HashMap<String, VWMLDebugServerCommand>();
		// server's listening port
		private int port;
		
		public void registerHandler(String context, VWMLDebugServerCommand handler) {
			handlers.put(context, handler);
		}
		
		public VWMLDebugServerCommand getHandlerByContext(String context) {
			return handlers.get(context);
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public Map<String, VWMLDebugServerCommand> getHandlers() {
			return handlers;
		}
	}

	/**
	 * Implemented by caller during commands implementation phase
	 * @author Oleg
	 *
	 */
	public static class VWMLDebugServerCommandHandler implements HttpHandler {

		private VWMLDebugServerCommand command = null;
		
		public VWMLDebugServerCommandHandler(VWMLDebugServerCommand command) {
			super();
			this.command = command;
		}

		public VWMLDebugServerCommand getCommand() {
			return command;
		}

		@Override
		public void handle(HttpExchange arg) throws IOException {
			throw new IOException("invalid command '" + arg + "'");
		}
	}
	
	private HttpServer httpServer = null;	
	private Logger logger = Logger.getLogger(VWMLHttpServer.class);
	
	private static int s_default_port = 8080;
	private static VWMLHttpServer s_instance = new VWMLHttpServer();
	
	private VWMLHttpServer() {
		
	}
	
	/**
	 * Returns initialized reference to http server
	 * @return
	 */
	public static synchronized VWMLHttpServer instance(VWMLHttpServerProps props) throws Exception {
		if (s_instance == null) {
			VWMLHttpServer t = new VWMLHttpServer();
			t.init(props);
			s_instance = t;
		}
		return s_instance;
	}

	/**
	 * Performs server's initialization steps
	 * @param props
	 * @throws Exception
	 */
	public void init(VWMLHttpServerProps props) throws Exception {
		httpServer = HttpServer.create(new InetSocketAddress((props.getPort() == 0) ? s_default_port : props.getPort()), 0);
		for(String context : props.getHandlers().keySet()) {
			httpServer.createContext(context, new VWMLDebugServerCommandHandler(props.getHandlers().get(context)));
		}
	}
	
	/**
	 * Starts server
	 */
	public void start() {
		httpServer.setExecutor(null); // creates a default executor
		httpServer.start();
		if (logger.isInfoEnabled()) {
			logger.info("VWML Http Server started and operational");
		}
	}
	
	public void stop() {
		httpServer.stop(0);
	}
}
