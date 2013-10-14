package com.vw.lang.beyond.java.fringe.test;

import junit.framework.Assert;

import org.junit.Test;

import com.vw.lang.beyond.java.fringe.EWEntity;
import com.vw.lang.beyond.java.fringe.EWEntityBuilder;

public class EWEntityBuilderTest {

	@Test
	public void test() throws Exception {
		EWEntity e = null;
		e = EWEntityBuilder.buildFromString("a");
		Assert.assertTrue(e != null);
		e = EWEntityBuilder.buildFromString("(a)");
		Assert.assertTrue(e != null && e.isMarkedAsComplexEntity());
		e = EWEntityBuilder.buildFromString("(a b)");
		Assert.assertTrue(e != null && e.isMarkedAsComplexEntity());
		e = EWEntityBuilder.buildFromString("(a b (c d (e f)))");
		Assert.assertTrue(e != null && e.isMarkedAsComplexEntity());
	}
}
