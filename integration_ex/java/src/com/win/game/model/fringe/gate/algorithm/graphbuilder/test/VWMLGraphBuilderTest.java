package com.win.game.model.fringe.gate.algorithm.graphbuilder.test;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

import com.win.game.model.fringe.gate.algorithm.graphbuilder.VWMLGraphBuilder;
import com.win.game.model.fringe.gate.algorithm.graphbuilder.items.Coordinate2D;
import com.win.game.model.fringe.gate.algorithm.graphbuilder.items.Obstacle;

public class VWMLGraphBuilderTest {

	static int MAPSIZEX = 38;
	static int MAPSIZEY = 38;
	
	@Test
	public void test1() throws Exception {
		int[][] map = new int[MAPSIZEX][MAPSIZEY];
		VWMLGraphBuilder gb = VWMLGraphBuilder.instance(MAPSIZEX, MAPSIZEY);
		gb.setMap(map);
		Obstacle o1 = Obstacle.instance();
		o1.setId(1);
		o1.setCorners(new Coordinate2D(2, 1), new Coordinate2D(2, 2), new Coordinate2D(2, 4),
					  new Coordinate2D(4, 4), new Coordinate2D(6, 4), new Coordinate2D(6, 2),
					  new Coordinate2D(6, 1), new Coordinate2D(4, 1));
		Obstacle o2 = Obstacle.instance();
		o2.setId(2);
		o2.setCorners(new Coordinate2D(16, 1), new Coordinate2D(16, 3), new Coordinate2D(16, 6),
					  new Coordinate2D(17, 6), new Coordinate2D(18, 6), new Coordinate2D(18, 3),
					  new Coordinate2D(18, 1), new Coordinate2D(17, 1));
		Obstacle o3 = Obstacle.instance();
		o3.setId(3);
		o3.setCorners(new Coordinate2D(10, 3), new Coordinate2D(10, 5), new Coordinate2D(10, 7),
					  new Coordinate2D(11, 7), new Coordinate2D(13, 7), new Coordinate2D(13, 5),
					  new Coordinate2D(13, 3), new Coordinate2D(11, 3));
		Obstacle o4 = Obstacle.instance();
		o4.setId(4);
		o4.setCorners(new Coordinate2D(6, 7), new Coordinate2D(6, 8), new Coordinate2D(6, 9),
					  new Coordinate2D(7, 9), new Coordinate2D(8, 9), new Coordinate2D(8, 8),
					  new Coordinate2D(8, 7), new Coordinate2D(7, 7));
		Obstacle o5 = Obstacle.instance();
		o5.setId(5);
		o5.setCorners(new Coordinate2D(6, 9), new Coordinate2D(6, 12), new Coordinate2D(6, 14),
					  new Coordinate2D(9, 14), new Coordinate2D(11, 14), new Coordinate2D(11, 12),
					  new Coordinate2D(11, 9), new Coordinate2D(9, 9));
		Obstacle o6 = Obstacle.instance();
		o6.setId(6);
		o6.setCorners(new Coordinate2D(13, 12), new Coordinate2D(13, 13), new Coordinate2D(13, 14),
					  new Coordinate2D(14, 14), new Coordinate2D(15, 14), new Coordinate2D(15, 13),
					  new Coordinate2D(15, 12), new Coordinate2D(14, 12));
		Obstacle obstacles[] = {o1, o2, o3, o4, o5, o6};
		for(Obstacle obstacle : obstacles) {
			gb.addObstacle(obstacle);
		}
		String vwmlCode = gb.calculateAndBuildCode(new Coordinate2D[] {new Coordinate2D(8, 1), new Coordinate2D(8, 2)}, true);
		System.out.println(vwmlCode);
		FileOutputStream fos = new FileOutputStream(new File("/tmp/battleField.graph"));
		fos.write(vwmlCode.getBytes(), 0, vwmlCode.length());
		fos.flush();
		fos.close();
	}
}
