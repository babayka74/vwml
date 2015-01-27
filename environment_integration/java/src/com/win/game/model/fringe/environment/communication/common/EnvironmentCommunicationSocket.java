package com.win.game.model.fringe.environment.communication.common;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

/**
 * Communication socket
 * @author Oleg
 *
 */
public class EnvironmentCommunicationSocket extends WebSocketAdapter {

	private EnvironmentCommunicationListener listener = null;
	
	public EnvironmentCommunicationListener getListener() {
		return listener;
	}

	public void setListener(EnvironmentCommunicationListener listener) {
		this.listener = listener;
	}

	@Override
	public void onWebSocketConnect(Session sess) {
		super.onWebSocketConnect(sess);
		if (listener != null) {
			listener.onConnect(sess);
		}
	}
	    
	@Override
	public void onWebSocketText(String message) {
	    super.onWebSocketText(message);
		if (listener != null) {
			try {
				listener.onReceived((EnvironmentMessage)EnvironmentCommunicationSerializer.deSerialize(message));
			} catch (Exception e) {
				listener.onError(e);
			}
		}
	}
	
    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        super.onWebSocketBinary(payload, offset, len);
		if (listener != null) {
			try {
				listener.onReceived((EnvironmentMessage)EnvironmentCommunicationSerializer.deSerialize(new String(payload, offset, len)));
			} catch (Exception e) {
				listener.onError(e);
			}
		}
    }
	    
	@Override
	public void onWebSocketClose(int statusCode, String reason) {
	    super.onWebSocketClose(statusCode,reason);
		if (listener != null) {
			listener.onClose(statusCode, reason);
		}
	}
	    
	@Override
	public void onWebSocketError(Throwable cause) {
	    super.onWebSocketError(cause);
	    cause.printStackTrace(System.err);
		if (listener != null) {
			listener.onError(cause);
		}
	}
}
