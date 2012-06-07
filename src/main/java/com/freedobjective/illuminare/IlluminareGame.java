package com.freedobjective.illuminare;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

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

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

public class IlluminareGame implements Game {
	
	private World world;
	private Program main;
	private Application app;
	
	@Override
	public void init(Application app) {
		this.app = app;
		System.out.println(glGetString(GL_SHADING_LANGUAGE_VERSION));
		System.out.println(glGetString(GL_VERSION));

		Shader vertex = new Shader("target/main.v.glsl", GL_VERTEX_SHADER);
		Shader frag = new Shader("target/test.f.glsl", GL_FRAGMENT_SHADER);
		Shader tVertex = new Shader("target/tex.v.glsl", GL_VERTEX_SHADER);
		Shader tFrag = new Shader("target/tex.f.glsl", GL_FRAGMENT_SHADER);
		
		Program main = new Program(new ArrayList<Shader>(Arrays.asList(new Shader[]{vertex,frag})));
		Program textureProg = new Program(new ArrayList<Shader>(Arrays.asList(new Shader[]{tVertex, tFrag})));
		
		world = new World(new ArrayList<RenderGroup>(), new Vector2f((float)(app.getAspectRatio()*1000), 1000.0f));
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.add(new RectSprite(world.getCoordinateSystem(), new Vector2f(100.0f, 100.0f), new Vector2f(0.0f, 0.0f)));
		world.addGroup(new RenderGroup("base", sprites, main));

		System.out.println(""+app.getAspectRatio() +"\n" + world.getCamera().getCameraDimensions());
		
		sprites = new ArrayList<Sprite>();
		sprites.add(new TextureSprite(world.getCoordinateSystem(), new Vector2f(100.0f, 100.0f), new Vector2f(500.0f, 500.0f), "placeholder.png"));
		world.addGroup(new RenderGroup("texture", sprites, textureProg));
		
		Shader mVertex = new Shader("target/meshshad.v.glsl", GL_VERTEX_SHADER);
		Shader mFrag = new Shader("target/meshshad.f.glsl", GL_FRAGMENT_SHADER);
		Program mProg = new Program(new ArrayList<Shader>(Arrays.asList(new Shader[]{mVertex,mFrag})));
		
		sprites = new ArrayList<Sprite>();
		ArrayList<Vector2f> meshPositions = new ArrayList<Vector2f>();
		meshPositions.add(new Vector2f(0.0f, 0.0f));
		meshPositions.add(new Vector2f(100.0f, 100.0f));
		meshPositions.add(new Vector2f(200.0f, 100.0f));
		
		// TODO: subclass MeshSprite into CaveSprite to draw cave, reading from Cave in com.freedobjective.illuminare.storage
		sprites.add(
				new MeshSprite(
						world.getCoordinateSystem(),
						new Mesh(meshPositions),
						new Vector2f(500.0f, 500.0f),
						new Vector4f(0.5f, 0.5f, 0.5f, 1.0f)
				)
		);
		world.addGroup(new RenderGroup("mesh", sprites, mProg));
		
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