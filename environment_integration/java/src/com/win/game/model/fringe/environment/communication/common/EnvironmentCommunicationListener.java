package com.win.game.model.fringe.environment.communication.common;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Implemented by user in order to get notification about communication events
 * @author Oleg
 *
 */
public abstract class EnvironmentCommunicationListener {

	public abstract void onConnect(Session sess);
	    
	public abstract void onReceived(EnvironmentMessage message);
	
	public abstract void onClose(int statusCode, String reason);
	    
	public abstract void onError(Throwable cause);
}
