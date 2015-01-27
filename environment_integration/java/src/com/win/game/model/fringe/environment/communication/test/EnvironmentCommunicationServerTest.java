package com.win.game.model.fringe.environment.communication.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.win.game.model.fringe.environment.communication.server.CommunicationServer;

public class EnvironmentCommunicationServerTest {

	private CommunicationServer server;
	
	@Before
	public void initServer() {
		CommunicationServer.Configuration conf = new CommunicationServer.Configuration();
		conf.setPort(9999);
		conf.setUrl("ws-events");
		server = CommunicationServer.instance();
		server.init(conf);
	}
	
	@Test
	public void startServer() throws Exception {
		server.start();
	}
	
	@After
	public void stopServer() throws Exception {
		server.stop();
	}
}
