package com.freedobjective.illuminare;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Game;
import com.freedobjective.illuminare.framework.Program;
import com.freedobjective.illuminare.framework.RenderGroup;
import com.freedobjective.illuminare.framework.Shader;
import com.freedobjective.illuminare.framework.World;
import com.freedobjective.illuminare.framework.sprite.RectSprite;
import com.freedobjective.illuminare.framework.sprite.Sprite;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

public class IlluminareGame implements Game {
	
	private World world;
	private Program main;
	
	@Override
	public void init() {
		System.out.println(glGetString(GL_SHADING_LANGUAGE_VERSION));
		System.out.println(glGetString(GL_VERSION));

		Shader vertex = new Shader("target/main.v.glsl", GL_VERTEX_SHADER);
		Shader frag = new Shader("target/test.f.glsl", GL_FRAGMENT_SHADER);
		
		Program main = new Program(new ArrayList<Shader>(Arrays.asList(new Shader[]{vertex,frag})));
		
		world = new World(new ArrayList<RenderGroup>());
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.add(new RectSprite(world.getCoordinateSystem(), new Vector2f(100.0f, 100.0f), new Vector2f(0.0f, 0.0f)));
		world.addGroup(new RenderGroup("base", sprites, main));
		try {
			world.init();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
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
		world.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		world.getCamera().resize();
	}

	@Override
	public void exit() {
		world.destroy();
	}
	
}