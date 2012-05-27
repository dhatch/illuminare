package com.freedobjective.illuminare.framework;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

import com.freedobjective.illuminare.framework.sprite.Sprite;

/**
 * @author dhatch
 * Manages the rendering of sprites.  Contains a program with which to render its sprites.
 */
public class RenderGroup {
	private ArrayList<Sprite> sprites;
	private Program program;
	private String groupID;
	private World world;
	
	public RenderGroup(String groupID, ArrayList<Sprite> sprites, Program prog) {
		this.groupID = groupID;
		program = prog;
		this.sprites = sprites;
	}
	
	/**
	 * @throws IOException if the program cannot be compiled because it cannot be loaded from disk.
	 */
	public void init() throws IOException {
		if (!program.isCompiled()) {
			program.compileProgram();
		}
		initSprites();
	}
	
	private void initSprites() {
		for (Sprite s: sprites) {
			s.init();
		}
	}
	
	/**
	 * Call the update method on all sprites.
	 * @param delta time since last update
	 */
	public void update(int delta) {
		for (Sprite s: sprites) {
			s.update(delta);
		}
	}
	
	/**
	 * Render this group.
	 */
	public void render() {
		program.enable();
		int camClipMatLoc = glGetUniformLocation(program.getProgramID(), "cameraToClipTransform");
		FloatBuffer matBuff = BufferUtils.createFloatBuffer(16);
		world.getCamera().getCameraToClipTransform().store(matBuff);
		matBuff.flip();
		glUniformMatrix4(camClipMatLoc, false, matBuff);
		
		for (Sprite s: sprites) {
			s.render(new RenderContext(world.getCamera()));
		}
		
		program.disable();
	}
	
	public void destroy() {
		for (Sprite s: sprites) {
			s.destroy();
		}
		program.destroy();
	}
	
	public void addSprite(Sprite s) {
		sprites.add(s);
	}
	
	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
	
	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public String getGroupID() {
		return groupID;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
