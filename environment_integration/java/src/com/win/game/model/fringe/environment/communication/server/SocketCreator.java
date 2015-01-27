package com.win.game.model.fringe.environment.communication.server;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import com.win.game.model.fringe.environment.communication.common.EnvironmentCommunicationSocket;

public class SocketCreator implements WebSocketCreator {

	private EnvironmentCommunicationSocket socket = null;
	
	private static SocketCreator socketCreator = null;
	
	private SocketCreator() {
		
	}
	
	public static synchronized SocketCreator build(CommunicationServer server) {
		if (socketCreator != null) {
			return socketCreator;
		}
		SocketCreator sc = new SocketCreator();
		ServerCommunicationSocket s = new ServerCommunicationSocket();
		s.setServerExtConfig(server.getExtConfiguration());
		sc.setSocket(s);
		socketCreator = sc;
		return socketCreator;
	}
	
	public static synchronized SocketCreator instance() {
		return socketCreator;
	}
	
	@Override
	public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
		return socket;
	}

	public EnvironmentCommunicationSocket getSocket() {
		return socket;
	}

	public void setSocket(EnvironmentCommunicationSocket socket) {
		this.socket = socket;
	}
}
