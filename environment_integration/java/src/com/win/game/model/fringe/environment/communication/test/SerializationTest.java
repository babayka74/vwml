package com.win.game.model.fringe.environment.communication.test;

import org.junit.Assert;
import org.junit.Test;

import com.win.game.model.fringe.environment.communication.common.EnvironmentCommunicationSerializer;
import com.win.game.model.fringe.environment.communication.common.EnvironmentMessage;

public class SerializationTest {

	protected static class S1 implements EnvironmentMessage {
		private Integer i1 = 5;
		private Integer i2 = 7;
		
		public Integer getI1() {
			return i1;
		}
		
		public void setI1(Integer i1) {
			this.i1 = i1;
		}
		
		public Integer getI2() {
			return i2;
		}

		public void setI2(Integer i2) {
			this.i2 = i2;
		}
	}
	
	@Test
	public void serialize() throws Exception {
		String str = EnvironmentCommunicationSerializer.serialize(new S1());
		System.out.println(str);
		Assert.assertTrue(str != null);
		S1 s1 = (S1)EnvironmentCommunicationSerializer.deSerialize(str);
		Assert.assertTrue(s1 != null);
	}
}
