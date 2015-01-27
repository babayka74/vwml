package com.win.game.model.fringe.environment.communication.server;

import com.win.game.model.fringe.environment.communication.common.EnvironmentCommunicationSocket;

public class ServerCommunicationSocket extends EnvironmentCommunicationSocket {

	private CommunicationServer.Configuration serverExtConfig = null;
	
	public CommunicationServer.Configuration getServerExtConfig() {
		return serverExtConfig;
	}

	public void setServerExtConfig(CommunicationServer.Configuration serverExtConfig) {
		this.serverExtConfig = serverExtConfig;
	}
	
}
