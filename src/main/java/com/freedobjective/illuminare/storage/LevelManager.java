package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class LevelManager {
	public static final Vector2f blockSize = new Vector2f(1000.0f, 1000.0f);
	
	//public Level loadLevel(String levelName) throws IOException{
	
	//}
	
	/**
	 * @param levelName
	 */
	public void saveLevel(String levelName){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(levelName + ".info");
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}	
	}
	public Level loadLevel(String fileName){
		Level level = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			level = (Level)in.readObject();
			in.close();
		} catch (IOException ex){
			ex.printStackTrace();
		} 
		return level;
	}

}
