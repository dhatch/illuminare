package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.coordinates.Mesh;
import com.freedobjective.illuminare.framework.sprite.Sprite;
import com.freedobjective.illuminare.sprites.CaveSprite;

public class Cave extends Entity {
	private Mesh mesh;
	
	public Cave(Mesh mesh, Vector2f pos) {
		super(pos);
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
	public Sprite getSprite() {
		return new CaveSprite(getBlock().getCoordinateSystem(), mesh, getPos(), new Vector4f(0.5f, 0.5f, 0.5f, 1.0f));
	}

	@Override
	public void saveSprite(Sprite s) {
		
	}
}
