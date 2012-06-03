package com.freedobjective.illuminare.storage;

import java.util.ArrayList;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class Level {
	private ArrayList<Block> blocks;
	private CoordinateSystem worldCoordinates;
	private String levelName;

	public Level(String levelName) {
		this.levelName = levelName;
		this.worldCoordinates = CoordinateSystem.createCoordinateSystem();
		this.blocks = new ArrayList<Block>();
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
		for (Block block: blocks) {
			block.setLevel(this);
		}
	}

	public CoordinateSystem getWorldCoordinates() {
		return worldCoordinates;
	}

	public String getLevelName() {
		return levelName;
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
		block.setLevel(this);
	}
	
	public void removeBlock(Block block) {
		blocks.remove(block);
		block.setLevel(null);
	}
}
