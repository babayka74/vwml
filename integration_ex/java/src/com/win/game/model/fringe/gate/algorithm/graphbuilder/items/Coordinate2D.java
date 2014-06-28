package com.win.game.model.fringe.gate.algorithm.graphbuilder.items;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;

/**
 * 2D coordinates
 * @author Oleg
 *
 */
public class Coordinate2D {
	private int x;
	private int y;
	private EWEntity asEntity;
	
	public Coordinate2D() {
		super();
	}

	public Coordinate2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public EWEntity getAsEntity() {
		return asEntity;
	}

	public void setAsEntity(EWEntity asEntity) {
		this.asEntity = asEntity;
	}

	public String buildVWMLId() {
		return "(" + getX() + " " + getY() + ")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate2D other = (Coordinate2D) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate2D [x=" + x + ", y=" + y + "]";
	}
}
