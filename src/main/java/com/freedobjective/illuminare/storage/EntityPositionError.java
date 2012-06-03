package com.freedobjective.illuminare.storage;

public class EntityPositionError extends Error {
	
	@Override
	public String toString() {
		return "Entity is outside bounds of block";
	}
}
