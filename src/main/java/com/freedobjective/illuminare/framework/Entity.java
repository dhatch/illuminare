package com.freedobjective.illuminare.framework;

import java.io.Serializable;

import org.lwjgl.util.vector.Vector2f;

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
	/**
	 * Maps this entity to the sprite with which it represents.
	 */
	protected static String spriteClass;
	
	public Entity(Block block, Vector2f pos) {
		this.block = block;
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

	public String getSpriteClassName() {
		return spriteClass;
	}
	
	public Entity person(Block block, Vector2f pos, int health, int lives){
		this.block = block
		this.pos = pos;
		this.health = health;
		this.lives = lives;
		return this.person();
	}

	public Entity ghost(Block block, Vector2f pos){
		this.block = block
		this.pos = pos;
		return this.ghost();
	}
	
	public Entity savePoint(Block block, Vector2f pos){
		this.block = block
		this.pos = pos;
		return this.savePoint(); 
	}
	
	
}
