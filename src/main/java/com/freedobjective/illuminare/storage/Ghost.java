package com.freedobjective.illuminare.storage;

import com.freedobjective.illuminare.framework.Entity;

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
		else if(health <=0)
			return false;
	}

	public void loseHealth(int loss){
		this.loss = loss;
		health += health - loss;
	}
}
