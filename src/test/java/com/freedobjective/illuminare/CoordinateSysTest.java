package com.freedobjective.illuminare;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import com.freedobjective.illuminare.framework.World;
import com.freedobjective.illuminare.framework.coordinates.*;

public class CoordinateSysTest {
	
	public static void main(String[] args) {
		World w = new World(new Vector2f(1000.0f, 1000.0f), new Vector2f(500.0f, 500.0f));
		Vector4f camCord = Matrix4f.transform(w.getCamera().getWorldToCameraTransform(), new Vector4f(1000.0f, 1000.0f, 0.0f, 1.0f), null);
		Vector4f clipCord = Matrix4f.transform(w.getCamera().getCameraToClipTransform(), camCord, null);
		System.out.println(clipCord);
	}
}
