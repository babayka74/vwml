package com.vw.lang.debug.main;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLine;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer.VWMLHttpServerProps;

/**
 * Command line debug server which is used in order to debug model created using VWML language
 * @author Oleg
 *
 */
public class VWMLDebugServer {
	
	/**
	 * Command line arguments
	 * @author ogibayev
	 *
	 */
	public static class ServerArgs {
		@Option(name="-p", usage="server listening port")
		private int port;

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}
	
	private static Logger logger = Logger.getLogger(VWMLDebugServer.class);
	private static VWMLHttpServer s_HttpServer = null;
	private static VWMLDebugServerCmdLine s_CmdLine = null;
	
	public static void main(String[] args) throws Exception {
		logger.info("VWML Debug Server is being initialized");
		ServerArgs srvArgs = new ServerArgs();
		CmdLineParser cmdParser = new CmdLineParser(srvArgs);
		cmdParser.setUsageWidth(80);
		cmdParser.parseArgument(args);
		// initialization phase
		// initializeHttpServer(srvArgs);
		initializeCommandLine();
		logger.info("VWML Debug Server is initialized and operational");
    }
	
	protected static void initializeHttpServer(ServerArgs args) throws Exception {
		VWMLHttpServerProps props = new VWMLHttpServerProps();
		props.setPort(args.getPort());
		s_HttpServer.init(props);
	}
	
	protected static void initializeCommandLine() throws Exception {
		s_CmdLine = new VWMLDebugServerCmdLine();
		s_CmdLine.start();
	}
}
