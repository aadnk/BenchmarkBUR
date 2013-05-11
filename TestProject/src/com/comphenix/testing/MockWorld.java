package com.comphenix.testing;

/**
 * Represents a world of constant size with no lighting information.
 * 
 * @author Kristian
 */
public class MockWorld {
	private final int width; // x 
	private final int height; // y	
	private final int depth; // z
	private final int area;
	private short[] data;
	
	public MockWorld(int width, int height, int depth) {
		if (width <= 0 || height <= 0 || depth <= 0)
			throw new IllegalArgumentException("Dimensions must be one or higher.");
		
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.area = width * height;
		
		this.data = new short[width * height * depth];
	}
	
	/**
	 * Set the block ID and data value of a given block.
	 * @param x - the x coordinate.
	 * @param y - the y coordinate.
	 * @param z - the z coordinate.
	 * @param blockID - the block ID (up to 8 bits)
	 * @param metadata - the metadata (up to 8 bits)
	 */
	public void setBlock(int x, int y, int z, int blockID, int metadata) {
		data[x + y * height + z * area] = (short) (blockID | (metadata << 8));
	}
	
	/**
	 * Retrieve the block ID of a particular block.
	 * @param x - the x coordinate.
	 * @param y - the y coordinate.
	 * @param z - the z coordinate.
	 * @return The block ID.
	 */
	public int getBlockID(int x, int y, int z) {
		return data[x + y * height + z * area] & 0xFF;
	}
	
	/**
	 * Retrieve the metadata value of a particular block.
	 * @param x - the x coordinate.
	 * @param y - the y coordinate.
	 * @param z - the z coordinate.
	 * @return The metadata value.
	 */
	public int getMetadata(int x, int y, int z) {
		return data[x + y * height + z * area] >> 8;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDepth() {
		return depth;
	}
}
