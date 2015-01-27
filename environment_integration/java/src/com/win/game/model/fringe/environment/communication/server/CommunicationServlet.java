package com.win.game.model.fringe.environment.communication.server;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Implementation of web-socket communication servlet for integrated environment
 * @author Oleg
 *
 */
@SuppressWarnings("serial")
public class CommunicationServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(10000);
		factory.setCreator(SocketCreator.instance());
	}
}
