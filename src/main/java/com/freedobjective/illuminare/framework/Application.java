package com.freedobjective.illuminare.framework;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

/**
 * @author dhatch
 * Main class to manage application run cycle.  Requires a class implementing the Game interface containing all game specific logic.
 */
public class Application {
	
	private int width;
	private int height;
	private Game game;
	private long lastFrame;
	
	/**
	 * @param width 
	 * @param height
	 */
	public Application(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		run();
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	 
	    return delta;
	}
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private void run() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), new ContextAttribs(3, 2).withProfileCore(true));
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		game.init(this);
		game.resize(1152, 768);
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			if (delta < 0) {
				delta = 0;
			}
			
			game.update(delta);
			if (Display.wasResized()) {
//				Call resize as needed. Likely will not occur too often.
				game.resize(Display.getWidth(), Display.getHeight());
			}
			game.render();
			
			Display.update();
//			cap frame rate at 60 fps
			Display.sync(60);
		}
		
		game.exit();
		Display.destroy();
	}
	
	public float getAspectRatio() {
		return Display.getWidth()/(float)Display.getHeight();
	}
}
