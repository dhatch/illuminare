package com.freedobjective.illuminare.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class Block implements Serializable {
	private ArrayList<Entity> entities;
	private CoordinateSystem coordSystem;
	private Level level;
	private Vector2f pos;
	private Vector2f dims;
	
	public Block(Vector2f pos, Vector2f dimensions, Entity... entities) {
		this.pos = pos;
		this.dims = dimensions;
		this.entities = new ArrayList<Entity>(Arrays.asList(entities));
	}
	
	public Block(Vector2f pos, Vector2f dimensions) {
		this(pos, dimensions, new Entity[]{});
	}
	
	protected Block() {
		
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
		for (Entity e: entities) {
			checkEntity(e);
			e.setBlock(this);
		}
	}

	private void checkEntity(Entity e) {
		if (!(e.getPos().x >= pos.x && e.getPos().x <= pos.x + dims.x) || !(e.getPos().y >= pos.y && e.getPos().y <= pos.y + dims.y)) {
			throw new EntityPositionError();
		}
	}
	
	public void addEntity(Entity e) {
		e.setBlock(this);
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		e.setBlock(null);
		entities.remove(e);
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
		this.coordSystem = CoordinateSystem.createCoordinateSystem(level.getWorldCoordinates(), 1.0f, pos);
	}

	public CoordinateSystem getCoordinateSystem() {
		return coordSystem;
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public Vector2f getDims() {
		return dims;
	}

	public void setDims(Vector2f dims) {
		this.dims = dims;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Block) {
			Block b = (Block)obj;
			return b.getLevel().equals(level) && b.getPos().equals(pos);
		}
		return super.equals(obj);
	}
}
