package com.vw.lang.sink.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.debug.visitor.dot.VWMLLinkDebugPreprocessorDotVisitor;

public class VWMLObjectsRepositoryTest {

	private AbstractVWMLLinkVisitor dotPreprocessorDebug = VWMLLinkDebugPreprocessorDotVisitor.instance();

	private static String s_schemaPath = "./output/debug/model/graph/dot";
	
	@Test
	public void repositoryAndSchemaPreprocessorTest() throws Exception {
		// generates objects
		VWMLObject vA = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, "a", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);
		VWMLObject vBC = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, "(b c)", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);
		vBC = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, "(b c)", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);
		VWMLObject vE = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, "e", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);
		VWMLObject vF = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, "f", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);
		VWMLObject vG = VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, "g", null, 2, VWMLObjectsRepository.notAsOriginal, dotPreprocessorDebug);		
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
		VWMLObject[] objs =  {vA,       vF,       vBC      };
		String[] schemas  =  {"test_a", "test_f", "test_bc"};
		int i = 0;
		for(VWMLObject o : objs) {
			dotPreprocessorDebug.init(schemas[i], buildPath(schemas[i]));
			o.iterate(null);
			dotPreprocessorDebug.done(schemas[i]);
			i++;
		}
	}
	
	private String buildPath(String schemaName) {
		return s_schemaPath + "/" + schemaName + ".dot";
	}
}
