package com.vw.lang.sink.java.test;

import org.junit.Test;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.beyond.fringe.creature.VWMLCreature;


public class VWMLCreatureTest {

	@Test
	public void creatureTransformTest() throws Exception {
		EWEntity ew = EWEntityBuilder.buildFromString("((p1 c1)(p2 c2)(p3 c3)(p4 c4)(p5 c5)(p6 c6)(p7 c7)(p8 c8)(p9 c9))");
		VWMLCreature.transformToVWML(VWMLContextsRepository.instance().getRootContext(), ew, VWMLCreature.s_transformAsIs);
		
		ew = EWEntityBuilder.buildFromString("(p1 p2 p3 p4 p5 p6 p7 p8 p9)");
		VWMLCreature.transformToVWML(VWMLContextsRepository.instance().getRootContext(), ew, VWMLCreature.s_transformAsIs);

		ew = EWEntityBuilder.buildFromString("(p1 p2)");
		VWMLCreature.transformToVWML(VWMLContextsRepository.instance().getRootContext(), ew, VWMLCreature.s_transformAsIs);
		
	}
}
