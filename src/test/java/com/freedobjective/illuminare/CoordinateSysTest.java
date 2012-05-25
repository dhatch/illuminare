package com.freedobjective.illuminare;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import com.freedobjective.illuminare.framework.coordinates.*;

public class CoordinateSysTest {
	
	public static void main(String[] args) {
		CoordinateSystem world = CoordinateSystem.createCoordinateSystem();
		CoordinateSystem camera = CoordinateSystem.createCoordinateSystem(world, 1.0f, new Vector2f(1.0f, 1.0f));
		Vector4f worldPos = new Vector4f(0.0f, 10.0f, 0.0f, 1.0f);
		System.out.println(camera.reverseTransform(worldPos));
		camera.translate(-1.0f, -1.0f);
		System.out.println(camera.getTransformMatrix());
		System.out.println(camera.reverseTransform(worldPos));
	}
}
