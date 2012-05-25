package com.freedobjective.illuminare.framework;

import java.io.IOException;
import java.util.ArrayList;

import com.freedobjective.illuminare.framework.sprite.Sprite;

/**
 * @author dhatch
 * Manages the rendering of sprites.  Contains a program with which to render its sprites.
 */
public class RenderGroup {
	private ArrayList<Sprite> sprites;
	private Program program;
	private String groupID;
	
	public RenderGroup(String groupID, ArrayList<Sprite> sprites, Program prog) {
		this.groupID = groupID;
		program = prog;
		this.sprites = sprites;
	}
	
	/**
	 * @throws IOException if the program cannot be compiled because it cannot be loaded from disk.
	 */
	public void init() throws IOException {
		if (!program.isCompiled()) {
			program.compileProgram();
		}
		initSprites();
	}
	
	private void initSprites() {
		for (Sprite s: sprites) {
			s.init();
		}
	}
	
	/**
	 * Call the update method on all sprites.
	 * @param delta time since last update
	 */
	public void update(int delta) {
		for (Sprite s: sprites) {
			s.update(delta);
		}
	}
	
	/**
	 * Render this group.
	 */
	public void render() {
		program.enable();
		
		for (Sprite s: sprites) {
			s.render();
		}
		
		program.disable();
	}
	
	public void destroy() {
		for (Sprite s: sprites) {
			s.destroy();
		}
		program.destroy();
	}
	
	public void addSprite(Sprite s) {
		sprites.add(s);
	}
	
	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
	
	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public String getGroupID() {
		return groupID;
	}
}
