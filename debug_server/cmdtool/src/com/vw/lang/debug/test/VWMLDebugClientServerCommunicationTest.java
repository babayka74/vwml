package com.vw.lang.debug.test;

import org.junit.Assert;
import org.junit.Test;

import com.vw.lang.debug.client.transport.http.VWMLHttpClient;
import com.vw.lang.debug.client.transport.http.VWMLHttpClient.VWMLHttpClientProps;
import com.vw.lang.debug.commands.test.VWMLDebugCommandTest;
import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer;
import com.vw.lang.debug.server.transport.http.VWMLHttpServer.VWMLHttpServerProps;

public class VWMLDebugClientServerCommunicationTest {
	
	@Test
	public void test() throws Exception {
		VWMLHttpServer srv = initializeHttpServer();
		VWMLHttpClient client = initializeHttpClient();
		VWMLDebugCommandResult res = client.send(new VWMLDebugCommandTest());
		srv.stop();
		Assert.assertTrue(res != null);
	}
	
	protected VWMLHttpServer initializeHttpServer() throws Exception {
		VWMLHttpServerProps props = new VWMLHttpServerProps();
		props.setPort(8974);
		VWMLHttpServer srv = VWMLHttpServer.instance(props);
		srv.start(null);
		return srv;
	}
	
	protected VWMLHttpClient initializeHttpClient() throws Exception {
		VWMLHttpClientProps props = new VWMLHttpClientProps();
		props.setTargetUrl("http://localhost:8974");
		VWMLHttpClient client = VWMLHttpClient.instance();
		client.init(props);
		return client;
	}
}
