package com.freedobjective.illuminare;

import com.freedobjective.illuminare.framework.Application;


public class IlluminareDesktopStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Application(new IlluminareGame(), 800, 800);
	}

}
