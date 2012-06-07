package com.freedobjective.illuminare.framework.sprite;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector4f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.RenderContext;
import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;
import com.freedobjective.illuminare.framework.coordinates.Mesh;

public class MeshSprite extends AbstractSprite {

	private FloatBuffer vertexPositions;
	private Mesh mesh;
	private int vao;
	private Vector2f dimensions;
	private float[] positions;
	private Vector4f color;
	
	public MeshSprite(CoordinateSystem coordSys, Mesh mesh, Vector2f pos, Vector4f col) {
		super(coordSys, pos);
		this.mesh = mesh;
		color = col;
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
		
		
        // Set up VAO for rendering.
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		bindVertexAttribArray(vbo);
		
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	protected float[] getVertexAttribArray() {
		float[] ret = new float[8*mesh.getPositions().size()];
		int i = 0;
		for (Vector2f p: mesh.getPositions()) {
			ret[i] = p.x;
			ret[i+1] = p.y;
			ret[i+2] = 0.0f;
			ret[i+3] = 1.0f;
			i += 4;
		}
		for (Vector2f p: mesh.getPositions()) {
			ret[i] = color.x;
			ret[i+1] = color.y;
			ret[i+2] = color.z;
			ret[i+3] = color.w;
			i += 4;
		}
		System.out.println(Arrays.toString(ret));
		return ret;
		
	}
	
	protected void bindVertexAttribArray(int vbo) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 16*mesh.getPositions().size());
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
		glDrawArrays(GL_TRIANGLES, 0, mesh.getPositions().size());
		glBindVertexArray(0);
	}

	@Override
	public void destroy() {
		
	}
}
