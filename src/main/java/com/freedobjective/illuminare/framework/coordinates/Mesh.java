package com.freedobjective.illuminare.framework.coordinates;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

/**
 * @author dhatch
 * A list of coordinates for a polygon to be drawn.
 */
public class Mesh {
	
	private ArrayList<Vector2f> positions;
	
	public Mesh(ArrayList<Vector2f> positions) {
		positions = positions;
	}
	
	public Mesh() {
		this(new ArrayList<Vector2f>());
	}

	public ArrayList<Vector2f> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Vector2f> positions) {
		this.positions = positions;
	}
	
	public void addPosition(Vector2f pos) {
		positions.add(pos);
	}
	
	public void removePosition(Vector2f pos) {
		positions.remove(pos);
	}
}
