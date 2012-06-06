package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.coordinates.Mesh;
import com.freedobjective.illuminare.framework.sprite.Sprite;

public class Cave extends Entity {
	private Mesh mesh;
	
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

	@Override
	public Sprite getSprite() {
		// TODO: Implement
		return null;
	}

	@Override
	public void saveSprite(Sprite s) {
		// TODO: implement
	}
}
