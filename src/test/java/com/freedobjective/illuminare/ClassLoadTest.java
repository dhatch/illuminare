package com.freedobjective.illuminare;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;
import com.freedobjective.illuminare.framework.sprite.Sprite;


public class ClassLoadTest {
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<Sprite> sprite = (Class<Sprite>) Class.forName("com.freedobjective.illuminare.framework.sprite.RectSprite");
		Constructor<Sprite> construct = sprite.getConstructor(
				Class.forName("com.freedobjective.illuminare.framework.coordinates.CoordinateSystem"),
				Class.forName("org.lwjgl.util.vector.Vector2f"),
				Class.forName("org.lwjgl.util.vector.Vector2f")
		);
		Sprite s = construct.newInstance(CoordinateSystem.createCoordinateSystem(), new Vector2f(0.0f,0.0f), new Vector2f(0.0f, 0.0f));
		s.toString();
	}
}
