package com.comphenix.testing;

public abstract class BlockUpdater {
	/**
	 * Update at most the given number of blocks in the current world.
	 * @param world - current world.
	 * @param maxBlockCount - maximum number of blocks to update.
	 * @return TRUE if we are done updating blocks, FALSE otherwise.
	 */
	public boolean update(MockWorld world, int maxBlockCount) {
		for (int i = 0; i < maxBlockCount; i++) {
			if (updateBlock(world))
				return true;
		}
		return false;
	}
	
	/**
	 * Update a single block in the current world.
	 * @param world - current world.
	 * @return TRUE if we are done updating blocks, FALSE otherwise.
	 */
	public abstract boolean updateBlock(MockWorld world);
}
