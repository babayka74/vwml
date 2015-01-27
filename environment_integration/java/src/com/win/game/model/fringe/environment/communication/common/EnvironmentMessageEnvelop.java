package com.win.game.model.fringe.environment.communication.common;

public class EnvironmentMessageEnvelop {
	private String clazz;
	private String payload;
	
	public EnvironmentMessageEnvelop() {
		super();
	}

	public EnvironmentMessageEnvelop(EnvironmentMessage message, String payload) {
		this.clazz = message.getClass().getName();
		this.payload = payload;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
}
