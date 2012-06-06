package com.freedobjective.illuminare.framework.sprite;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.RenderContext;
import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public abstract class AbstractSprite implements Sprite {

	private CoordinateSystem coordinateSystem;
	private CoordinateSystem modelCoordinates;
	private Entity entity;
	
	public AbstractSprite(CoordinateSystem coordSys, Vector2f pos) {
		coordinateSystem = coordSys;
		modelCoordinates = CoordinateSystem.createCoordinateSystem(coordinateSystem, 1.0f, pos);
	}
	
	@Override
	public abstract void init();

	@Override
	public abstract void update(int delta);
	
	@Override
	public abstract void render(RenderContext context);
	
	@Override
	public abstract void destroy();
	
	public void translate(float x, float y) {
		modelCoordinates.translate(x, y);
	}
	
	public void scale(float scale) {
		modelCoordinates.setScale(modelCoordinates.getScale()*scale);
	}
	
	public void setScale(float scale) {
		modelCoordinates.setScale(scale);
	}
	
	public float getScale() {
		return modelCoordinates.getScale();
	}
	
	public CoordinateSystem getCoordinateSystem() {
		return coordinateSystem;
	}
	
	public CoordinateSystem getModelCoordinateSystem() {
		return modelCoordinates;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Save myself to entity.
	 */
	public abstract void save();
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sprite) {
			// No real case where sprites will be equal besides them being the exact same object in memory.
			return obj == this;
		}
		return false;
	}
}
