package com.freedobjective.illuminare.framework.sprite;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.freedobjective.illuminare.framework.RenderContext;
import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

public class TextureSprite extends RectSprite {

	Texture texture;
	String textureName;
	
	public TextureSprite(CoordinateSystem coordSys, Vector2f pos, Vector2f dimensions, String name) {
		super(coordSys, dimensions, pos);
		textureName = name;
	}

	@Override
	public void init() {
		super.init();
		glBindVertexArray(getVao());
		glActiveTexture(GL_TEXTURE0+1);
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/"+textureName), true);
		} catch (IOException e) {
			System.err.println("Error loading texture!");
			e.printStackTrace();
			System.exit(1);
		}
		glBindVertexArray(0);
	}

	@Override
	public void update(int delta) {
		super.update(delta);
	}

	@Override
	public void render(RenderContext context) {
		glBindVertexArray(getVao());
		glActiveTexture(GL_TEXTURE0+1);
		texture.bind();
		glUniform1i(glGetUniformLocation(glGetInteger(GL_CURRENT_PROGRAM), "textureSampler"), 1);
		glBindVertexArray(0);
		super.render(context);
	}

	@Override
	protected float[] getVertexAttribArray() {
		float[] pos = super.getVertexAttribArray();
		float[] texMap = {
			0.0f, 0.0f,
			0.0f, 1.0f,
			1.0f, 0.0f,
			1.0f, 1.0f
		};
		float[] result = Arrays.copyOf(pos, pos.length + texMap.length);
		System.arraycopy(texMap, 0, result, pos.length, texMap.length);
		return result;
	}
	
	@Override
	protected void bindVertexAttribArray(int vbo) {
		super.bindVertexAttribArray(vbo);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 64);
	}
	
	@Override
	public void destroy() {
		texture.release();
	}

}
