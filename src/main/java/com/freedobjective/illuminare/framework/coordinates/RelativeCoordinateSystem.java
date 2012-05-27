package com.freedobjective.illuminare.framework.coordinates;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RelativeCoordinateSystem extends CoordinateSystem {

	private CoordinateSystem relative;
	private float scale;

	/**
	 * @param rel The coordinate system this is defined relative to.
	 * @param scale Scale of this system compared to the relative system.
	 * @param relOriginPos Origin pos of this system in other system's coordinates.
	 * @param originPos Offset of this system's origin in this system's coordinates.
	 * @param size (width, height) of this coordinate system.
	 */
	protected RelativeCoordinateSystem(CoordinateSystem rel, float scale, Vector2f originPos) {
		super(originPos);
		relative = rel;
		this.scale = scale;
	}

	@Override
	public Matrix4f getTransformMatrix() {
		Matrix4f mat = new Matrix4f();
		mat.translate((Vector2f)new Vector2f(getOriginPos()).negate())
			.scale(new Vector3f(1/scale, 1/scale, 1/scale));
		return mat;
	}
	
	public Matrix4f getReverseTransformMatrix() {
		return (Matrix4f)getTransformMatrix().invert();
	}
	
	public Vector4f transform(Vector4f pos) {
		Vector4f n = new Vector4f();
		Matrix4f.transform(getTransformMatrix(), pos, n);
		return n;
	}
	
	public Vector4f reverseTransform(Vector4f pos) {
		return Matrix4f.transform(getReverseTransformMatrix(), pos, null);
	}
	
	public boolean isRelative() {
		return true;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public CoordinateSystem getRelativeCoordinateSystem() {
		return relative;
	}
}
