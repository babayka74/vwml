package com.vw.lang.sink.java.test.interpreter.datastructure;

import junit.framework.Assert;

import org.junit.Test;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;

/**
 * Interpreter's stack simple test
 * @author ogibayev
 *
 */
public class VWMLInterpreterStackUnitTest {

	public static class StackInspectorTest extends VWMLStack.VWMLStackInspector {
		public void inspected(Object obj) {
			VWMLObject o = (VWMLObject)obj;
			Assert.assertNotNull(obj);
			System.out.println("inspected object '" + o + "'");
		}
	}
	
	@Test
	public void testBasicFunctionality() {
		VWMLObject obj = null;
		VWMLStack s = VWMLStack.instance();
		obj = s.pop();
		Assert.assertNull(obj);
		obj = s.pop();
		Assert.assertNull(obj);
		for(int i = 0; i < 7; i++) {
			obj = new VWMLObject(i, Integer.toString(i));
			s.push(obj);
			System.out.println("pushed object '" + obj + "'");
		}
		for(int i = 0; i < 7; i++) {
			obj = s.pop();
			Assert.assertNotNull(obj);
			System.out.println("popped object '" + obj + "'");
		}
		obj = s.pop();
		Assert.assertNull(obj);
	}
	
	@Test
	public void testInspectorFunctionality() {
		VWMLObject obj = null;
		VWMLStack s = VWMLStack.instance();
		for(int i = 0; i < 7; i++) {
			obj = new VWMLObject(i, Integer.toString(i));
			s.push(obj);
			System.out.println("pushed object '" + obj + "'");
		}
		s.inspect(new StackInspectorTest());
	}
}
