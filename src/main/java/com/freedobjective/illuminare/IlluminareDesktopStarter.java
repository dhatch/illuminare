package com.freedobjective.illuminare;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class IlluminareDesktopStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(new IlluminareGame(), "Illuminare", 1024, 768, false);
	}

}
