package com.freedobjective.illuminare.framework.coordinates;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class BaseCoordinateSystem extends CoordinateSystem {
	
	protected BaseCoordinateSystem() {
		super(new Vector2f(0, 0));
	}

	@Override
	public Matrix4f getTransformMatrix() {
		return null;
	}
	
	@Override
	public Matrix4f getReverseTransformMatrix() {
		return null;
	}

	@Override
	public Vector4f transform(Vector4f pos) {
		return null;
	}

	@Override
	public Vector4f reverseTransform(Vector4f pos) {
		return null;
	}
}
