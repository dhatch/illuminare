package com.freedobjective.illuminare;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.BufferUtils;

import com.freedobjective.illuminare.framework.Game;
import com.freedobjective.illuminare.framework.Program;
import com.freedobjective.illuminare.framework.Shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

public class IlluminareGame implements Game {
	
	final float[] vertexPositions = {
			0.75f,  0.75f, 0.0f, 1.0f,
			0.75f, -0.75f, 0.0f, 1.0f,
		   -0.75f, -0.75f, 0.0f, 1.0f,
	};
	
	private int vao;
	private Program prog;
	
	@Override
	public void init() {
		System.out.println(glGetString(GL_SHADING_LANGUAGE_VERSION));
		System.out.println(glGetString(GL_VERSION));

		Shader vertex = new Shader("target/test.v.glsl", GL_VERTEX_SHADER);
		Shader frag = new Shader("target/test.f.glsl", GL_FRAGMENT_SHADER);
		
		prog = new Program(new ArrayList<Shader>(Arrays.asList(new Shader[]{vertex,frag})));
		try {
			prog.compileProgram();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
	}

	@Override
	public void render() {
		
		FloatBuffer vertexPositionsBuffer = BufferUtils.createFloatBuffer(vertexPositions.length);
		vertexPositionsBuffer.put(vertexPositions);
		vertexPositionsBuffer.flip();
		
		int positionBufferObject = glGenBuffers();	       
		glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
	    glBufferData(GL_ARRAY_BUFFER, vertexPositionsBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);

		prog.enable();

		glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);

		glDrawArrays(GL_TRIANGLES, 0, 3);

		glDisableVertexAttribArray(0);
		prog.disable();
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void exit() {
		
	}
	
}