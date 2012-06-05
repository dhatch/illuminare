package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.coordinates.Mesh;

public class Cave extends Entity {
	private Mesh mesh;
	protected static String spriteClass = "CaveSprite";
	
	public Cave(Mesh mesh) {
		super(new Vector2f(0.0f, 0.0f));
		this.mesh = mesh;
	}
	
	protected Cave() {
		
	}
	
	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	@Override
	public void setPos(Vector2f pos) {
		// no op
	}
}
