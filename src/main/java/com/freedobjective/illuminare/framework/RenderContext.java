package com.freedobjective.illuminare.framework;

public class RenderContext {
	private Camera camera;
	
	public RenderContext(Camera c) {
		camera = c;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
