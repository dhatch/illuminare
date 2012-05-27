package com.freedobjective.illuminare.framework;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class Camera {
	
	private Vector2f cameraDimensions;
	private Vector2f worldPos;
	private float scale;
	private World world;
	private CoordinateSystem coordinateSystem;
	
	public Camera(World world) {
		this.world = world;
		worldPos = new Vector2f();
		scale = 1.0f;
		this.cameraDimensions = new Vector2f(Display.getWidth(), Display.getHeight());
		coordinateSystem = CoordinateSystem.createCoordinateSystem(world.getCoordinateSystem(), scale, new Vector2f());
		transformCoordinateSystem();
	}
	
	private void transformCoordinateSystem() {
		coordinateSystem.setOriginPos(worldPos);
		coordinateSystem.setScale(scale);
	}

	public Vector2f getCameraDimensions() {
		return cameraDimensions;
	}

	public void setCameraDimensions(Vector2f windowDimensions) {
		this.cameraDimensions = windowDimensions;
		transformCoordinateSystem();
	}
	
	public void resize() {
		setCameraDimensions(new Vector2f(Display.getWidth(), Display.getHeight()));
	}
	
	public Vector2f getWorldPos() {
		return worldPos;
	}

	public void setWorldPos(Vector2f worldPos) {
		this.worldPos = worldPos;
		transformCoordinateSystem();
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
		transformCoordinateSystem();
	}
	
	public Matrix4f getWorldToCameraTransform() {
		return coordinateSystem.getReverseTransformMatrix();
	}
	
	public Matrix4f getCameraToClipTransform() {
		Vector2f translation = (Vector2f)(new Vector2f(cameraDimensions).negate().scale(0.5f));
		Matrix4f mat = new Matrix4f();
		mat.m00 = 2/cameraDimensions.x;
		mat.m11 = 2/cameraDimensions.y;
		mat.m30 = -1.0f;
		mat.m31 = -1.0f;

		return mat;
	}
}
