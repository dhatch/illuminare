package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.coordinates.Mesh;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelManager {
	public static final Vector2f blockSize = new Vector2f(1000.0f, 1000.0f);
	
	//public Level loadLevel(String levelName) throws IOException{
	
	//}
	
	/**
	 * @param levelName
	 */
//	public static void saveLevel(String levelName) {
//		FileOutputStream fos = null;
//		ObjectOutputStream out = null;
//		try{
//			fos = new FileOutputStream(levelName + ".info");
//			out = new ObjectOutputStream(fos);
//			out.writeObject(this);
//			out.close();
//		} catch (IOException ex){
//			ex.printStackTrace();
//		}	
//	}


	// Actual implementation...
//	public Level loadLevel(String fileName){
//		Level level = null;
//		FileInputStream fis = null;
//		ObjectInputStream in = null;
//		try{
//			fis = new FileInputStream(fileName);
//			in = new ObjectInputStream(fis);
//			level = (Level)in.readObject();
//			in.close();
//		} catch (IOException ex){
//			ex.printStackTrace();
//		} 
//		return level;
//	}
	// ........
	
	// Stubbed for convenience until LevelGenerator exists.
	public static Level loadLevel(String levelName) {
		Level l = new Level("default");
		l.addBlock(new Block(new Vector2f(0.0f, 0.0f), blockSize));
		Block main = l.getBlocks().get(0).get(0);
		Cave c = new Cave(new Mesh(new ArrayList<Vector2f>(Arrays.asList(
				new Vector2f[]{
					new Vector2f(0.0f, 0.0f),
					new Vector2f(0.0f, 50.0f),
					new Vector2f(50.0f, 50.0f),
					new Vector2f(0.0f, 0.0f),
					new Vector2f(50.0f, 0.0f),
					new Vector2f(50.0f, 50.0f),
				}
		))), new Vector2f(0.0f, 0.0f));
		main.addEntity(c);
		l.setCameraPosition(new Vector2f(500.0f, 500.0f));
		return l;
	}
}
