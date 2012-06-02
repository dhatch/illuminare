package com.freedobjective.illuminare.framework.sprite;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;

import com.freedobjective.illuminare.framework.RenderContext;
import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class RectSprite extends AbstractSprite {

	private FloatBuffer vertexPositions;
	private int vao;
	private Vector2f dimensions;
	private float[] positions;
	
	public RectSprite(CoordinateSystem coordSys, Vector2f dimensions, Vector2f pos) {
		super(coordSys, pos);
		this.dimensions = dimensions;
	}
	
	@Override
	public void init() {
		// Calculate vertex positions.
		positions = getVertexAttribArray();
		FloatBuffer posBuffer = BufferUtils.createFloatBuffer(positions.length);
		posBuffer.put(positions);
		posBuffer.flip();
		
		// Set up vertex buffer.
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		final short[] indexOrder = {
			0, 1, 3, 0, 2, 3
		};
		ShortBuffer indexBuffer = BufferUtils.createShortBuffer(indexOrder.length);
		indexBuffer.put(indexOrder);
		indexBuffer.flip();
		
		int ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
        // Set up VAO for rendering.
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		bindVertexAttribArray(vbo);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	protected float[] getVertexAttribArray() {
		return new float[]{
				0.0f, 0.0f, 0.0f, 1.0f,
				0.0f, dimensions.y, 0.0f, 1.0f,
				dimensions.x, 0.0f, 0.0f, 1.0f,
				dimensions.x, dimensions.y, 0.0f, 1.0f
			};
	}
	
	protected void bindVertexAttribArray(int vbo) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
	}
	
	@Override
	public void update(int delta) {

	}

	@Override
	public void render(RenderContext context) {
		int prog = glGetInteger(GL_CURRENT_PROGRAM);
		int transUniform = glGetUniformLocation(prog, "modelToCameraTransform");
		FloatBuffer mat = BufferUtils.createFloatBuffer(16);
		Matrix4f matrix = new Matrix4f();
		CoordinateSystem coord = getModelCoordinateSystem();
		while (coord.isRelative()) {
			matrix = Matrix4f.mul(coord.getReverseTransformMatrix(), matrix, null);
			coord = coord.getRelativeCoordinateSystem();
		}
		matrix = Matrix4f.mul(context.getCamera().getWorldToCameraTransform(), matrix, null);
		matrix.store(mat);
		mat.flip();

		glUniformMatrix4(transUniform, false, mat);
		
		glBindVertexArray(vao);
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, 0);
		glBindVertexArray(0);
	}
	

	@Override
	public void destroy() {
		
	}

	public Vector2f getDimensions() {
		return dimensions;
	}

	public void setDimensions(Vector2f dimensions) {
		this.dimensions = dimensions;
	}

	public int getVao() {
		return vao;
	}

}