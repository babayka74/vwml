package com.win.game.model.fringe.gate.algorithm.graphbuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.win.game.model.fringe.gate.algorithm.graphbuilder.items.Coordinate2D;
import com.win.game.model.fringe.gate.algorithm.graphbuilder.items.Obstacle;

/**
 * Builds VWML graph from grid map which represented by set of cells
 * @author Oleg
 *
 */
public class VWMLGraphBuilder {
	
	protected static class Los {
		/**
		 * Checks visibility between two points
		 * @param x0 - start point 
		 * @param y0 - start point
		 * @param x1 - end point
		 * @param y1 - end point
		 * @param shadowMap - obstacles' map
		 * @return
		 */
		public static boolean los(int x0, int y0, int x1, int y1, int[][] shadowMap) {
            int sx, sy;
            int dx = x1 - x0;
            int dy = y1 - y0;
            if (x0 < x1) {
	            sx = 1;
            } 
            else {
                sx = -1;
            }
            if (y0 < y1) {
	            sy = 1;
            }
            else {
	            sy = -1;
            }
            // sx and sy are switches that enable us to compute the LOS in a single
            // quarter of x/y plan
            int xnext = x0;
            int ynext = y0;
            double denom = Math.sqrt(dx * dx + dy * dy);
            while (xnext != x1 || ynext != y1) {
	            // check map bounds here if needed
	            if (shadowMap[xnext][ynext] == 1) {
	                return false;
	            }
	            // Line-to-point distance formula < 0.5
	            if (Math.abs(dy * (xnext - x0 + sx) - dx * (ynext - y0)) / denom < 0.5f) {
	                xnext += sx;
	            }
	            else
	            if (Math.abs(dy * (xnext - x0) - dx * (ynext - y0 + sy)) / denom < 0.5f) {
	                ynext += sy;
	            }
	            else {
	                xnext += sx;
	                ynext += sy;
	            }
            }
            return true;
		}
	}
	
	private int mapSizeX;
	private int mapSizeY;
	private int[][] map;
	private List<Obstacle> obstaclesMap = new ArrayList<Obstacle>();
	
	private VWMLGraphBuilder() {
		
	}
	
	private VWMLGraphBuilder(int mapSizeX, int mapSizeY) {
		super();
		this.mapSizeX = mapSizeX;
		this.mapSizeY = mapSizeY;
	}

	public static VWMLGraphBuilder instance() {
		return new VWMLGraphBuilder();
	}

	public static VWMLGraphBuilder instance(int mapSizeX, int mapSizeY) {
		return new VWMLGraphBuilder(mapSizeX, mapSizeY);
	}
	
	public void addObstacle(Obstacle obstacle) throws Exception {
		if (!obstaclesMap.contains(obstacle)) {
			obstaclesMap.add(obstacle);
		}
		if (map != null) {
			obstacle.putOn(mapSizeX, mapSizeY, map, true);
		}
	}

	public void removeObstacle(Obstacle obstacle) throws Exception {
		if (obstaclesMap.contains(obstacle)) {
			obstaclesMap.remove(obstacle);
		}
		if (map != null) {
			obstacle.putOn(mapSizeX, mapSizeY, map, false);
		}
	}
	
	public String calculateAndBuildCode(Coordinate2D[] startPoints, boolean packedCode) throws Exception {
		calculateGraphOnly();
		linkStartPoints(startPoints);
		return generateVWMLCodeFromCalculatedGraph(startPoints, packedCode);
	}
	
	public void calculateGraphOnly() throws Exception {
		for(int i = 0; i < obstaclesMap.size(); i++) {
			Obstacle from = obstaclesMap.get(i);
			for(int j = i + 1; j < obstaclesMap.size(); j++) {
				Obstacle to = obstaclesMap.get(j);
				calculateSubGraph(from, to);
			}
		}
	}
	
	public void linkStartPoints(Coordinate2D[] startPoints) throws Exception {
		for(Coordinate2D startPoint : startPoints) {
			EWEntity eF = getAssociatedEntity(startPoint);
			for(Obstacle obstacle : obstaclesMap) {
				for(Coordinate2D nextPoint : obstacle.getCorners()) {
					if (checkLos(startPoint, nextPoint)) {
						EWEntity eT = getAssociatedEntity(nextPoint);
						crossLinkEntities(eF, eT);
					}
				}
			}
		}
	}
	
	public String generateVWMLCodeFromCalculatedGraph(Coordinate2D[] startPoints, boolean packed) {
		String codeBuffer = "";
		if (packed) {
			codeBuffer += "(";
		}
		Set<EWEntity> processedEntities = new HashSet<EWEntity>();
		for(Coordinate2D startPoint : startPoints) {
			EWEntity e = getAssociatedEntity(startPoint);
			codeBuffer = generateVWMLCodeFromCalculatedGraph(processedEntities, codeBuffer, e, packed);
		}
		if (packed) {
			codeBuffer += ")";
		}
		return codeBuffer;
	}
	
	public int getMapSizeX() {
		return mapSizeX;
	}

	public void setMapSizeX(int mapSizeX) {
		this.mapSizeX = mapSizeX;
	}

	public int getMapSizeY() {
		return mapSizeY;
	}

	public void setMapSizeY(int mapSizeY) {
		this.mapSizeY = mapSizeY;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	protected void calculateSubGraph(Obstacle from, Obstacle to) throws Exception {
		Coordinate2D[] cornersFrom = from.getCorners();
		Coordinate2D[] cornersTo = to.getCorners();
		for(int i = 0; i < cornersFrom.length; i++) {
			EWEntity eF = getAssociatedEntity(cornersFrom[i]);
			for(int j = 0; j < cornersTo.length; j++) {
				if (checkLos(cornersFrom[i], cornersTo[j])) {
					EWEntity eT = getAssociatedEntity(cornersTo[j]);
					crossLinkEntities(eF, eT);
				}
			}
		}
	}
	
	protected boolean checkLos(Coordinate2D from, Coordinate2D to) throws Exception {
		if (getMap() == null) {
			throw new Exception("Map should be set before method is called");
		}
		return Los.los(from.getX(), from.getY(), to.getX(), to.getY(), getMap());
	}
	
	private EWEntity getAssociatedEntity(Coordinate2D coord) {
		EWEntity e = coord.getAsEntity();
		if (e == null) {
			e = EWEntityBuilder.buildComplexEntity(coord.buildVWMLId(), null);
			coord.setAsEntity(e);
		}
		return e;
	}
	
	private String generateVWMLCodeFromCalculatedGraph(Set<EWEntity> processed, String codeBuffer, EWEntity fromEntity, boolean packed) {
		if (processed.contains(fromEntity)) {
			return codeBuffer;
		}
		processed.add(fromEntity);
		if (packed) {
			codeBuffer += " (" + fromEntity.getId() + " (";
		}
		else {
			codeBuffer += "\r\n" + fromEntity.getId() + " ias (";
		}
		for(int i = 0; i < fromEntity.getLink().getLinkedObjectsOnThisTime(); i++) {
			EWEntity e = (EWEntity)fromEntity.getLink().getConcreteLinkedEntity(i);
			codeBuffer += e.getId() + " ";
		}
		codeBuffer = codeBuffer.trim();
		if (packed) {
			codeBuffer += ")) ";
		}
		else {
			codeBuffer += ");";
		}
		for(int i = 0; i < fromEntity.getLink().getLinkedObjectsOnThisTime(); i++) {
			EWEntity e = (EWEntity)fromEntity.getLink().getConcreteLinkedEntity(i);
			codeBuffer = generateVWMLCodeFromCalculatedGraph(processed, codeBuffer, e, packed);
		}
		return codeBuffer;
	}
	
	private void crossLinkEntities(EWEntity e1, EWEntity e2) {
		if (!e1.isLinked(e2)) {
			e1.getLink().link(e2);
		}
		if (!e2.isLinked(e1)) {
			e2.getLink().link(e1);
		}
	}
}
