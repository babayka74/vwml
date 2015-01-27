package com.win.game.model.fringe.environment.communication.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Environment server which is used to delegate incoming/outgoing events from/to IDE 
 * @author Oleg
 *
 */
public class CommunicationServer {
	
	public static class Configuration {
		private String url;
		private int port;
		
		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
		
		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}
	
	// concrete server implementation
	private Server serverImpl = new Server();
	// external supplied server's configuration
	private Configuration extConfiguration = null;
	
	private CommunicationServer() {
		
	}
	
	public static CommunicationServer instance() {
		return new CommunicationServer();
	}
	
	public void init(Configuration extConfiguration) {
		this.extConfiguration = extConfiguration;
		SocketCreator.build(this);
		ServerConnector connector = new ServerConnector(serverImpl);
		connector.setPort(extConfiguration.getPort());
		serverImpl.addConnector(connector);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		serverImpl.setHandler(context);
		ServletHolder holderEvents = new ServletHolder(extConfiguration.getUrl(), CommunicationServlet.class);
        context.addServlet(holderEvents, "/events/*");
	}

	public void start() throws Exception {
		 serverImpl.start();
         serverImpl.dump(System.err);
         serverImpl.join();
	}

	public void stop() throws Exception {
		if (serverImpl.isRunning()) {
			serverImpl.stop();
		}
	}
	
	public Configuration getExtConfiguration() {
		return extConfiguration;
	}
}
