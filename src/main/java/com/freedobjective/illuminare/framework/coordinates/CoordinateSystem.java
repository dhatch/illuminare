package com.freedobjective.illuminare.framework.coordinates;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public abstract class CoordinateSystem {
	
	public static CoordinateSystem createCoordinateSystem() {
		return new BaseCoordinateSystem();
	}
	
	public static CoordinateSystem createCoordinateSystem(
			CoordinateSystem rel, float scale, Vector2f relOriginPos) {
		return new RelativeCoordinateSystem(rel, scale, relOriginPos);
	}

	/**
	 * The offset of the origin from the bottom left corner of the system.
	 */
	private Vector2f originPos;

	protected CoordinateSystem(Vector2f originPos) {
		this.originPos = originPos;
	}
	
	public Vector2f getOriginPos() {
		return originPos;
	}

	public void setOriginPos(Vector2f originPos) {
		this.originPos = originPos;
	}
	
	public void translate(float x, float y) {
		this.originPos.translate(x, y);
	}
	
	public abstract Matrix4f getTransformMatrix();
	public abstract Matrix4f getReverseTransformMatrix();
	public abstract Vector4f transform(Vector4f pos);
	public abstract Vector4f reverseTransform(Vector4f pos);
	public abstract CoordinateSystem getRelativeCoordinateSystem();
	
	public boolean isRelative() {
		return false;
	}
	
	public float getScale() {
		return 1.0f;
	}
	
	public void setScale(float scale) {
		
	}
}
