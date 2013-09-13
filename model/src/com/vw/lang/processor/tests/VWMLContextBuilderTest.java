package com.vw.lang.processor.tests;

import junit.framework.Assert;

import org.junit.Test;

import com.vw.lang.processor.context.builder.VWMLContextBuilder;

public class VWMLContextBuilderTest {
	@Test
	public void test() {
		VWMLContextBuilder cb = VWMLContextBuilder.instance();
		for(int i = 0; i < 10; i++) {
			cb.push(String.valueOf(i));
		}
		String context = cb.buildContext();
		Assert.assertNotNull(context);
		Assert.assertTrue(context.length() > 1);
	}
}
