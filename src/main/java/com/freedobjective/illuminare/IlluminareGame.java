package com.freedobjective.illuminare;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import com.freedobjective.illuminare.framework.Application;
import com.freedobjective.illuminare.framework.Game;
import com.freedobjective.illuminare.framework.Program;
import com.freedobjective.illuminare.framework.RenderGroup;
import com.freedobjective.illuminare.framework.Shader;
import com.freedobjective.illuminare.framework.World;
import com.freedobjective.illuminare.framework.coordinates.Mesh;
import com.freedobjective.illuminare.framework.sprite.MeshSprite;
import com.freedobjective.illuminare.framework.sprite.RectSprite;
import com.freedobjective.illuminare.framework.sprite.Sprite;
import com.freedobjective.illuminare.framework.sprite.TextureSprite;
import com.freedobjective.illuminare.sprites.CaveSprite;
import com.freedobjective.illuminare.storage.Level;
import com.freedobjective.illuminare.storage.LevelManager;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

public class IlluminareGame implements Game {
	public static final Vector2f CAMERA_SIZE = new Vector2f(1000.0f, 1000.f);
	
	private World world;
	private Application app;
	private LoadManager loadManager;
	
	@Override
	public void init(Application app) {
		this.app = app;
		
		// Load the level from disk
		// TODO: in the future this should use a name for the level string
		Level level = LevelManager.loadLevel(null);
		
		// Load Shaders
		Program caveProgram = new Program(
			new Shader("src/main/shaders/meshshad.v.glsl", GL_VERTEX_SHADER),
			new Shader("src/main/shaders/meshshad.f.glsl", GL_FRAGMENT_SHADER)
		);
				
		// Initialize the world
		// RenderGroup defs
		RenderGroup caveGroup = new RenderGroup("cave", caveProgram);
		
		this.world = new World(this.CAMERA_SIZE, level.getCameraPosition(),
			caveGroup
		);
		
		// Initialize the LoadManager
		Map spriteRenderGroupMapping = new HashMap<Class, String>();
		spriteRenderGroupMapping.put(CaveSprite.class, "cave");
		LoadManager loader = new LoadManager(world, level, 50.0f, spriteRenderGroupMapping);
		this.loadManager = loader;
		
		
		try {
			world.init();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	@Override
	public void render() {
//		Sets background color.
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		
		world.render();
	}

	@Override
	public void update(int delta) {
		this.loadManager.update();
		world.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		world.getCamera().resize(width, height);
	}

	@Override
	public void exit() {
		world.destroy();
	}
	
}