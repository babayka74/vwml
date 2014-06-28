package com.win.game.model.fringe.gate.algorithm.graphbuilder.items;

/**
 * Some abstract obstacle which is located on map
 * @author Oleg
 *
 */
public class Obstacle {
	public static final int LBCORNER = 0x0;
	public static final int CLCORNER = 0x1;
	public static final int LTCORNER = 0x2;
	public static final int CTCORNER = 0x3;
	public static final int RTCORNER = 0x4;
	public static final int CRCORNER = 0x5;
	public static final int RBCORNER = 0x6;
	public static final int CBCORNER = 0x7;
	public static final int CORNERS  = 0x8;

	private int id;
	private Coordinate2D[] corners;
	
	private Obstacle() {
		
	}
	
	public static Obstacle instance() {
		return new Obstacle();
	}
	
	public int getNumberOfAnalayzedCorners() {
		return CORNERS;
	}
	
	public void setCorners(	Coordinate2D lb, Coordinate2D cl, Coordinate2D lt,
							Coordinate2D ct, Coordinate2D rt, Coordinate2D cr,
							Coordinate2D rb, Coordinate2D cb
							) {

		Coordinate2D[] cornerCoords = {lb, cl, lt, ct, rt, cr, rb, cb};
		corners = new Coordinate2D[CORNERS];
		for(int i = 0; i < CORNERS; i++) {
			corners[i] = cornerCoords[i];
		}
	}
	
	public void setCorners(Coordinate2D[] corners) throws Exception {
		if (corners.length != CORNERS) {
			throw new Exception("invalid corners size; must be'" + CORNERS + "'");
		}
		this.corners = corners;
	}

	public Coordinate2D[] getCorners() {
		return corners;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coordinate2D getCorner(int corner) {
		if (corners == null || corner < LTCORNER || corner > CORNERS) {
			return null;
		}
		return corners[corner];
	}
	
	/**
	 * Fills area, which is map, by yourself 
	 * @param areaSizeX
	 * @param areaSizeY
	 * @param area
	 */
	public void putOn(int areaSizeX, int areaSizeY, int[][] area, boolean put) throws Exception {
		if (corners == null || corners[RTCORNER] == null || corners[LTCORNER] == null || corners[LBCORNER] == null
							|| areaSizeX < corners[RTCORNER].getX() - corners[LTCORNER].getX() 
		                    || areaSizeY < corners[RBCORNER].getY() - corners[RTCORNER].getY()) {
			throw new Exception("Invalid arguments or corners is null");
		}
		for(int x = corners[LTCORNER].getX() + 1; x <= corners[RTCORNER].getX() - 1; x++) {
			for(int y = corners[LBCORNER].getY() + 1; y <= corners[LTCORNER].getY() - 1; y++) {
				area[x][y] = (put) ? 1 : 0;
			}
		}
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Obstacle other = (Obstacle) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
