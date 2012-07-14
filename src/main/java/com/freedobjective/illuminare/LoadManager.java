package com.freedobjective.illuminare;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.RenderGroup;
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
	 * Control which sprites are placed in which render group (by name)
	 */
	private Map<Class, String> spriteRenderGroupMapping;
	/**
	 * Stores the blocks which were loaded during the last frame.
	 */
	private HashSet<Block> inLevelBlocks;
	
	public LoadManager(World world, Level level, float extraDims,
			Map<Class, String> spriteRenderGroupMapping) {
		
		this.world = world;
		this.level = level;
		this.extraDims = extraDims;
		this.spriteRenderGroupMapping = spriteRenderGroupMapping;
		this.inLevelBlocks = new HashSet<Block>();
	}
	
	/**
	 * Should be called every frame.  References camera positioning to determine which sprites to load into the render
	 * groups.  Removes unneeded sprites.
	 */
	public void update() {
		Block camBlock = level.getBlock(world.getCamera().getWorldPos());
		ArrayList<Block> addedBlocks = new ArrayList<Block>();
		// Sprites within this block should be loaded
		if (!inLevelBlocks.contains(camBlock)) {
			for (Entity e: camBlock.getEntities()) {
				Sprite s = e.getSprite();
				RenderGroup g = world.getGroup(spriteRenderGroupMapping.get(s.getClass()));
				// TODO: this needs to happen. but this is not a good spot probably. not sure. this will take more resources than we'd like to happen during the render loop
				s.init();
				g.addSprite(s);
				System.out.println("Added sprite: "+s);
			}
			addedBlocks.add(camBlock);
		}
		// TODO: check for other blocks within extraDims of the camera and load those as well to speed up process.
		
		
		// TODO: Remove sprites from blocks which have exited since the last frame.
		
		inLevelBlocks.addAll(addedBlocks);
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
	public Map<Class, String> getSpriteRenderGroupMapping() {
		return spriteRenderGroupMapping;
	}
	public void setSpriteRenderGroupMapping(
			Map<Class, String> spriteRenderGroupMapping) {
		this.spriteRenderGroupMapping = spriteRenderGroupMapping;
	}
}
