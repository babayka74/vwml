package com.vw.lang.processor.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.vw.lang.grammar.preprocessor.VWMLPreprocessor;
import com.vw.lang.grammar.preprocessor.VWMLPreprocessor.VWMLPreprocessorIfDirective;

public class VWMLPreprocessorExpressionTest {
	
	@Before
	public void init() {
		VWMLPreprocessor.addDirective("1", "true");
		VWMLPreprocessor.addDirective("0", "false");
	}
	
	@Test
	public void test1() throws Exception {
		VWMLPreprocessor p = VWMLPreprocessor.instance();
		// (0 & 1)
		VWMLPreprocessorIfDirective d = p.startDirectiveIf();
		d.addRegularItem("0");
		d.addRegularItem("1");
		d.addOperation("&");
		p.processDirectiveIf();
		Assert.assertFalse(p.getResultOfProcessingDirectiveIf());
	}

	@Test
	public void test2() throws Exception {
		VWMLPreprocessor p = VWMLPreprocessor.instance();
		// (1 & (1 | 0))
		VWMLPreprocessorIfDirective d = p.startDirectiveIf();
		// 1
		d.addRegularItem("1");
		// (1 | 0)
		d.addExpressionItem();
		{
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("|");
			d.removeTop();
		}
		d.addOperation("&");
		p.processDirectiveIf();
		Assert.assertTrue(p.getResultOfProcessingDirectiveIf());
	}

	@Test
	public void test3() throws Exception {
		VWMLPreprocessor p = VWMLPreprocessor.instance();
		// (1 & (1 | 0) & 0)
		VWMLPreprocessorIfDirective d = p.startDirectiveIf();
		// 1
		d.addRegularItem("1");
		d.addOperation("&");
		// (1 | 0)
		d.addExpressionItem();
		{
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("|");
			d.removeTop();
		}
		// 0
		d.addRegularItem("0");
		d.addOperation("&");
		p.processDirectiveIf();
		Assert.assertFalse(p.getResultOfProcessingDirectiveIf());
	}

	@Test
	public void test4() throws Exception {
		VWMLPreprocessor p = VWMLPreprocessor.instance();
		// (1 & ((1 | 0) & (1 & 0)))
		VWMLPreprocessorIfDirective d = p.startDirectiveIf();
		// 1
		d.addRegularItem("1");
		d.addOperation("&");
		// ()
		d.addExpressionItem();
		d.addOperation("&");
		{
			// (1 | 0)
			d.addExpressionItem();
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("|");
			d.removeTop();
			// (1 & 0)
			d.addExpressionItem();
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("&");
			d.removeTop();
		}
		p.processDirectiveIf();
		Assert.assertFalse(p.getResultOfProcessingDirectiveIf());
	}

	@Test
	public void test5() throws Exception {
		VWMLPreprocessor p = VWMLPreprocessor.instance();
		// (1 & ((1 | 0) & (1 | 0)))
		VWMLPreprocessorIfDirective d = p.startDirectiveIf();
		// 1
		d.addRegularItem("1");
		d.addOperation("&");
		// ()
		d.addExpressionItem();
		d.addOperation("&");
		{
			// (1 | 0)
			d.addExpressionItem();
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("|");
			d.removeTop();
			// (1 & 0)
			d.addExpressionItem();
			d.addRegularItem("0");
			d.addRegularItem("1");
			d.addOperation("|");
			d.removeTop();
		}
		p.processDirectiveIf();
		Assert.assertTrue(p.getResultOfProcessingDirectiveIf());
	}
	
}
