package com.freedobjective.illuminare.storage;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.Entity;
import com.freedobjective.illuminare.framework.sprite.Sprite;

public class Ghost extends Entity {

	private float health;

	public Ghost(Vector2f pos, float health) {
		super(pos);
		this.health = health;
	}

	public float getHealth(){
		return health;
	}

	public boolean isAlive(){
		if(health > 0)
			return true;
		
		return false;
	}

	public void loseHealth(int loss){
		health -= loss;
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSprite(Sprite s) {
		// TODO Auto-generated method stub
		
	}
}
