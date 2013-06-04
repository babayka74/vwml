package com.vw.lang.sink.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.debug.visitor.dot.VWMLLinkDebugPreprocessorDotVisitor;

public class VWMLObjectsRepositoryTest {

	private IVWMLLinkVisitor dotPreprocessorDebug = VWMLLinkDebugPreprocessorDotVisitor.instance();

	private static String s_schemaPath = "./output/debug/model/graph/dot";
	
	@Test
	public void repositoryAndSchemaPreprocessorTest() {
		// generates objects
		VWMLObject vA = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.SIMPLE_ENTITY, "a", dotPreprocessorDebug);
		VWMLObject vBC = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.COMPLEX_ENTITY, "(b c)", dotPreprocessorDebug);
		vBC = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.COMPLEX_ENTITY, "(b c)", dotPreprocessorDebug);
		VWMLObject vE = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.SIMPLE_ENTITY, "e", dotPreprocessorDebug);
		VWMLObject vF = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.SIMPLE_ENTITY, "f", dotPreprocessorDebug);
		VWMLObject vG = VWMLObjectsRepository.acquire(VWMLObjectBuilder.TYPE.SIMPLE_ENTITY, "g", dotPreprocessorDebug);		
		// start new schema
		String schemaName = "test_1"; 
		dotPreprocessorDebug.init(schemaName, buildPath(schemaName));
		vA.link(vBC);
		vA.link(vE);
		vF.link(vA);
		vF.link(vBC);
		vA.link(vG);
		// checks linkage...
		assertTrue(vA.isLinked(vBC));
		assertTrue(vA.isLinked(vE));
		assertTrue(vF.isLinked(vA));
		assertTrue(vF.isLinked(vBC));
		assertTrue(vA.isLinked(vG));
		assertFalse(vBC.isLinked(vA));
		// end of schema
		dotPreprocessorDebug.done(schemaName);		
	}
	
	private String buildPath(String schemaName) {
		return s_schemaPath + "/" + schemaName + ".dot";
	}
}
