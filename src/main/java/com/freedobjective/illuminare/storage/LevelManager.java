package com.freedobjective.illuminare.storage;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.coordinates.Mesh;

public class LevelManager {
	public static final Vector2f blockSize = new Vector2f(1000.0f, 1000.0f);
	
	public Level loadLevel(String levelName) {
		Level l = new Level("default");
		l.addBlock(new Block(new Vector2f(0.0f, 0.0f), blockSize));
		Block main = l.getBlocks().get(0).get(0);
		Cave c = new Cave(new Mesh(new ArrayList<Vector2f>(Arrays.asList(
				new Vector2f[]{
					new Vector2f(0.0f, 0.0f),
					new Vector2f(0.0f, 50.0f),
					new Vector2f(100.0f, 50.0f),
					new Vector2f(100.0f, 0.0f),
					new Vector2f(0.0f, 0.0f)
				}
		))));
		main.addEntity(c);
		return l;
	}
}
