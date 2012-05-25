package com.freedobjective.illuminare.framework.sprite;

public interface Sprite {
	
	/**
	 * Load sprite resources etc.
	 */
	public void init();
	
	/**
	 * @param delta time passed since last update
	 */
	public void update(int delta);
	
	/**
	 * Contain all code for rendering sprite.
	 */
	public void render();
	
	/**
	 * Called to clean up sprite.  Clean up resources etc.
	 */
	public void destroy();
}
