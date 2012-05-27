package com.freedobjective.illuminare.framework.sprite;

import com.freedobjective.illuminare.framework.RenderContext;

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
	 * Contains all code for rendering sprite.
	 */
	public void render(RenderContext context);
	
	/**
	 * Called to clean up sprite.  Clean up resources etc.
	 */
	public void destroy();
}
