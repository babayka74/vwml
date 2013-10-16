package com.vw.lang.debug.server.transport.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vw.lang.debug.commands.test.VWMLDebugCommandTest;
import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;
import com.vw.lang.debug.common.VWMLDebugCommandTranscoder;

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
		private Map<String, Class<? extends VWMLDebugCommand>> handlers = new HashMap<String, Class<? extends VWMLDebugCommand>>();
		// server's listening port
		private int port;
		
		public void registerHandler(String context, Class<? extends VWMLDebugCommand> handler) {
			handlers.put(context, handler);
		}
		
		public Class<? extends VWMLDebugCommand> getHandlerByContext(String context) {
			return handlers.get(context);
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public Map<String, Class<? extends VWMLDebugCommand>> getHandlers() {
			return handlers;
		}
	}

	/**
	 * Implemented by caller during commands implementation phase
	 * @author Oleg
	 *
	 */
	public static class VWMLDebugServerCommandHandler implements HttpHandler {

		private Class<?> debugCommandClass;
		
		public VWMLDebugServerCommandHandler(Class<?> debugCommandClass) {
			super();
			this.debugCommandClass = debugCommandClass;
		}

		@Override
		public void handle(HttpExchange arg) throws IOException {
			try {
				String result = null;
				InputStream is = arg.getRequestBody();
				if (is == null) {
					throw new IOException("request body has invalid format; request's class '" + debugCommandClass + "'");
				}
				String body = getStringFromInputStream(is);
				String commandTag = VWMLDebugCommand.getCommandTag() + "=";
				if (body.startsWith(commandTag)) {
					body = body.substring(commandTag.length());
				}
				else {
					throw new Exception("Invalid command received; command doesn't contain tag; command '" + body + "'");
				}
				VWMLDebugCommand command = (VWMLDebugCommand)VWMLDebugCommandTranscoder.fromJSON(body, debugCommandClass);
				VWMLDebugCommandResult res = command.handle();
				if (res != null) {
					result = VWMLDebugCommandTranscoder.toJSON(res);
				}
				else {
					result = VWMLDebugCommandTranscoder.toJSON(VWMLDebugCommandResult.defaultCommandResult());
				}
				arg.sendResponseHeaders(200, result.length());
				OutputStream os = arg.getResponseBody();
				os.write(result.getBytes());
				os.close();
			}
			catch(Exception e) {
				throw new IOException(e);
			}
		}
		
		private static String getStringFromInputStream(InputStream is) throws Exception {
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return URLDecoder.decode(sb.toString(), "UTF-8");
		}
	}
	
	private HttpServer httpServer = null;	
	private Logger logger = Logger.getLogger(VWMLHttpServer.class);
	
	private static int s_default_port = 8974;
	private static int s_attemptsToCreateServer = 5;
	private static VWMLHttpServer s_instance = null;
	
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
		int listeningPort = (props.getPort() == 0) ? s_default_port : props.getPort();
		httpServer = null;
		Exception le = null;
		for(int i = 0; i < s_attemptsToCreateServer; i++) {
			try {
				httpServer = HttpServer.create(new InetSocketAddress(listeningPort), 0);
				break;
			}
			catch(Exception e) {
				listeningPort++;
				le = e;
			}
		}
		if (httpServer == null) {
			throw new Exception("couldn't create http server; last exception is '" + le + "'");
		}
		// create 'test' command handler
		httpServer.createContext("/test", new VWMLDebugServerCommandHandler(VWMLDebugCommandTest.class));
		// installs handlers
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
