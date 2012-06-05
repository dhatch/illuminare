package com.freedobjective.illuminare;

import java.util.ArrayList;
import java.util.Map;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.World;
import com.freedobjective.illuminare.framework.sprite.Sprite;
import com.freedobjective.illuminare.storage.Block;
import com.freedobjective.illuminare.storage.Level;


/**
 * @author dhatch
 * Manages the loading of sprites which are within (or near) the camera viewing area in the block.
 */
public class LoadManager {
	private World world;
	private Level level;
	/**
	 * Sets the extra area around the camera view to load sprites from.
	 */
	private float extraDims;
	/**
	 * Should be initialized with the mapping of spriteClass strings used in entities within the level to 
	 * the classes to be used for those entities.
	 */
	private Map<String, Class> spriteClassMapping;
	/**
	 * Control which sprites are placed in which render group (by name)
	 */
	private Map<Sprite, String> spriteRenderGroupMapping;
	/**
	 * Stores the blocks which were loaded during the last frame.
	 */
	private ArrayList<Block> lastBlocks;
	
	public LoadManager(World world, Level level, float extraDims,
			Map<String, Class> spriteClassMapping,
			Map<Sprite, String> spriteRenderGroupMapping) {
		
		this.world = world;
		this.level = level;
		this.extraDims = extraDims;
		this.spriteClassMapping = spriteClassMapping;
		this.spriteRenderGroupMapping = spriteRenderGroupMapping;
		this.lastBlocks = new ArrayList<Block>();
	}
	
	/**
	 * Should be called every frame.  References camera positioning to determine which sprites to load into the render
	 * groups.  Removes unneeded sprites.
	 */
	public void update() {
		Block camBlock = level.getBlock(world.getCamera().getWorldPos());
		// Sprites within this block should be loaded
		
		for (Entity e: camBlock.getEntities()) {
			Class spriteClass = null;
			try {
				spriteClass = Class.forName(e.getSpriteClassName());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			if (spriteClass != null) {
				// TODO: Time to initialize this.				
			}
		}
		// TODO: check for other blocks within extraDims of the camera and load those as well to speed up process.
		
		
		// Remove sprites from blocks which have exited since the last frame.
	}
	
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public float getExtraDims() {
		return extraDims;
	}
	public void setExtraDims(float extraDims) {
		this.extraDims = extraDims;
	}
	public Map<String, Class> getSpriteClassMapping() {
		return spriteClassMapping;
	}
	public void setSpriteClassMapping(Map<String, Class> spriteClassMapping) {
		this.spriteClassMapping = spriteClassMapping;
	}
	public Map<Sprite, String> getSpriteRenderGroupMapping() {
		return spriteRenderGroupMapping;
	}
	public void setSpriteRenderGroupMapping(
			Map<Sprite, String> spriteRenderGroupMapping) {
		this.spriteRenderGroupMapping = spriteRenderGroupMapping;
	}
}
