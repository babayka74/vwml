package com.vw.lang.sink.java.test;

import junit.framework.Assert;

import org.junit.Test;

import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

public class VWMLJavaCodeGeneratorTest {
/*
	@Test
	public void testSourceGenerationProcess() {
		IVWMLLinkVisitor dotPreprocessorDebug = VWMLLinkDebugPreprocessorDotVisitor.instance();
		JavaCodeGenerator g = JavaCodeGenerator.instance();
		g.setVisitor(dotPreprocessorDebug);
		JavaCodeGenerator.JavaModuleStartProps props = new JavaCodeGenerator.JavaModuleStartProps();
		props.setAuthor("ogibayev");
		props.setDate("01-01-01");
		props.setDescription("Test of source generation process");
		props.setModuleName("testSourceGeneration");
		props.setSrcPath("src");
		props.setModulePackage("com.vw.lang.sink.java.test.generated.srcs.test");
		props.setVisitorDataPath("src/com/vw/lang/sink/java/test/generated/data/" + props.getModuleName() + ".dot");
		try {
			g.startModule(props);
			g.declareSimpleEntity("a");
			g.declareComplexEntity("(b c d)");
			g.declareSimpleEntity("x");
			g.declareComplexEntity("(f)");

			g.declareComplexEntity("(a1 a2 a3)");
			g.declareComplexEntity("(x1 x2 x3)");

			g.declareSimpleEntity("true");
			g.declareSimpleEntity("false");
			
			g.linkObjects("a", "(b c d)");
			g.linkObjects("a", "x");
			g.linkObjects("(f)", "x");
			g.linkObjects("(f)", "(b c d)");
			g.linkObjects("a", "(f)");
			
			g.interpretObjects("a", "(a1 a2 a3)");
			g.interpretObjects("x", "(x1 x2 x3)");
			g.interpretObjects("(a1 a2 a3)", "true");
			g.interpretObjects("(x1 x2 x3)", "false");
			
			g.associateOperation("a", "~");
			g.associateOperation("a", "Join");
			g.associateOperation("(f)", "~");
			g.associateOperation("(f)", "First");
			
			g.generate(props);
			g.finishModule(props);
			
		} catch (Exception e) {
			Assert.assertFalse(true);
		}
	}
	
	@Test
	public void testGeneratedCode() throws Exception {
		com.vw.lang.sink.java.test.generated.srcs.test.VWMLModuleTestSourceGeneration module = new com.vw.lang.sink.java.test.generated.srcs.test.VWMLModuleTestSourceGeneration();
		module.build();
	}
*/	

	@Test
	public void complexEntityNameBuilderTest() {
		ComplexEntityNameBuilder nb = ComplexEntityNameBuilder.instance();
		nb.startProgress();
		nb.addObjectId("a");
		nb.startProgress();
		nb.addObjectId("x");
		nb.addObjectId("y");
		nb.stopProgress();
		//nb.addObjectId("e");
		nb.stopProgress();		
		String s = nb.build();
		Assert.assertTrue(s.length() > 2);
	}
}
