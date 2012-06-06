package com.freedobjective.illuminare.storage;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

public class Level implements Serializable {
	/**
	 * Stored as 2D array. blocks.get(a).get(b) -> (a, b) (0,0) is bottom left, (1, 0) is one block above this, (0,1) one to the right
	 * etc..
	 * 
	 * Will confirm that all blocks are of consistent sizing.  First block added will set the expected block size for level.
	 */
	private ArrayList<ArrayList<Block>> blocks;
	private CoordinateSystem worldCoordinates;
	private String levelName;
	private Vector2f blockSize;
	
	public Level(String levelName) {
		this.levelName = levelName;
		this.worldCoordinates = CoordinateSystem.createCoordinateSystem();
		this.blocks = new ArrayList<ArrayList<Block>>();
	}
	
	protected Level() {
		
	}
	
	public ArrayList<ArrayList<Block>> getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks
	 * Clears and sets blocks.
	 */
	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = new ArrayList<ArrayList<Block>>();
		for (Block block: blocks) {
			addBlock(block);
		}
	}

	public CoordinateSystem getWorldCoordinates() {
		return worldCoordinates;
	}

	public String getLevelName() {
		return levelName;
	}
	
	public void addBlock(Block block) {
		if (blockSize == null) {
			blockSize = block.getDims();
		} else {
			assert blockSize.equals(block.getDims()) : "Block sizes must be consistent";
		}
		
		// Calculate where this block should be placed in our 2D array.
		float r = block.getPos().y / blockSize.y;
		float c = block.getPos().x / blockSize.x;
		if ((int)r != r || (int)c != c) {
			// There should be no remainder...
			throw new BlockPositionError();
		}
		
		// Make sure there is no block in this position
		boolean correct = false;
		try {
			blocks.get((int)r).get((int)c);
		} catch (IndexOutOfBoundsException e) {
			correct = true;
		} 
		if (!correct) {
			throw new BlockPositionError();
		}
		
		ArrayList<Block> col = new ArrayList<Block>();
		try {
			col = blocks.get((int)r);
		} catch (IndexOutOfBoundsException e) {
			while (blocks.size() < r - 1) {
				blocks.add(new ArrayList<Block>());
			}
			blocks.add(col);
		}
		
		try {
			col.set((int)c, block);
		} catch (IndexOutOfBoundsException e) {
			// fill with nulls
			for (int x = 0; x < (int)c; x++) {
				col.set(x, null);
			}
		}
		
		block.setLevel(this);
	}
	
	public void removeBlock(Block block) {
		blocks.get((int)(block.getPos().y/blockSize.y)).remove((int)(block.getPos().x/blockSize.x));
		block.setLevel(null);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return block containing this position
	 * @throws IndexOutOfBoundsException if this block does not exist
	 */
	public Block getBlock(float x, float y) {
		if (blockSize == null) {
			throw new IndexOutOfBoundsException();
		}
		
		return blocks.get((int)(y/blockSize.y)).get((int)(x/blockSize.x));
	}
	
	public Block getBlock(Vector2f pos) {
		return getBlock(pos.x, pos.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Level) {
			return ((Level)obj).getLevelName().equals(this.levelName);
		}
		return super.equals(obj);
	}
}
