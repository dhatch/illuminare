package com.freedobjective.illuminare.framework.sprite;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class RectSprite extends AbstractSprite {

	FloatBuffer vertexPositions;
	
	public RectSprite(CoordinateSystem coordSys, Vector2f dimensions, Vector2f pos) {
		super(coordSys, pos);
		
		BufferUtils.createFloatBuffer(8);
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void render() {
		
	}

	@Override
	public void destroy() {
		
	}

}