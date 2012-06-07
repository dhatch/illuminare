package com.freedobjective.illuminare.framework;

import java.io.Serializable;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.sprite.Sprite;
import com.freedobjective.illuminare.storage.Block;

public abstract class Entity implements Serializable {

	/**
	 * This position of the entity inside the block.
	 */
	private Vector2f pos;
	/**
	 * The block this entity exists in.
	 */
	private Block block;
	
	public Entity(Vector2f pos) {
		this.pos = pos;
	}
	
	protected Entity() {
		
	}
	
	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	/**
	 * @return a new instance of the associated sprite loaded with the stored data.
	 */
	public abstract Sprite getSprite();
	/**
	 * Save this sprite's data to myself.
	 */
	public abstract void saveSprite(Sprite s);
}
