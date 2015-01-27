package com.win.game.model.fringe.environment.communication.common;

import org.codehaus.jackson.map.ObjectMapper;

public class EnvironmentCommunicationSerializer {
	
	/**
	 * Serializes message to string
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String serialize(EnvironmentMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EnvironmentMessageEnvelop e = new EnvironmentMessageEnvelop(message, mapper.writeValueAsString(message));
		return mapper.writeValueAsString(e);
	}
	
	/**
	 * Deserializes communication message
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static EnvironmentMessage deSerialize(String message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EnvironmentMessageEnvelop e = mapper.readValue(message, EnvironmentMessageEnvelop.class);
		@SuppressWarnings("unchecked")
		Class<? extends EnvironmentMessage> c = (Class<? extends EnvironmentMessage>)ClassLoader.getSystemClassLoader().loadClass(e.getClazz());
		return mapper.readValue(e.getPayload(), c);
	}
}
