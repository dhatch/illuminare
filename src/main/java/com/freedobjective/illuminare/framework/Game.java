package com.freedobjective.illuminare.framework;

import org.lwjgl.opengl.Display;

public interface Game {
	/**
	 * Called to initialize the game system.  Perform resource loading etc.
	 */
	public void init(Application app);
	public void render();
	public void resize(int width, int height);
	/**
	 * @param delta The time since the last loop.
	 */
	public void update(int delta);
	/**
	 * Called when a game exit is requested.  Perform saving etc.
	 */
	public void exit();
	
}
