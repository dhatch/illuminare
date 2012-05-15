package com.freedobjective.illuminare;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class IlluminareDesktopStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Illuminare";
		config.width = 1152;
		config.height = 720;
		config.useGL20 = true;
		config.fullscreen = false;
		new LwjglApplication(new IlluminareGame(), config);
	}

}
