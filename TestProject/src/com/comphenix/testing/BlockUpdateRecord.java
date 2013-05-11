package com.comphenix.testing;

import org.bukkit.Material;

/**
 * Represents a single block update.
 */
public class BlockUpdateRecord {
    private int x;
    private int y;
    private int z;
    private int materialId;
    private byte data;

    /**
     * Construct a new block update record for the given location,
     * material and data.
     *
     * @param loc the location at which the update should be applied
     * @param material the new block id to apply to the location.
     * @param data the new data to apply to the location
     */
    public BlockUpdateRecord(int x, int y, int z, int blockID, byte data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.materialId = blockID;
        this.data = data;
    }

    public BlockUpdateRecord() {
		// TODO Auto-generated constructor stub
	}
    
    public int getX() {
		return x;
	}

    public void setX(int x) {
		this.x = x;
	}
    
    public int getY() {
		return y;
	}
    
    public void setY(int y) {
		this.y = y;
	}
    
    public int getZ() {
		return z;
	}
    
    public void setZ(int z) {
		this.z = z;
	}

    /**
     * Get the material for this update record.
     *
     * @return the material
     */
    public Material getMaterial() {
        return Material.getMaterial(materialId);
    }

    /**
     * Set the material for this update record.
     *
     * @param material the material
     */
    public void setMaterial(Material material) {
        materialId = material.getId();
    }

    /**
     * Get the material ID for this update record.
     *
     * @return the material ID
     */
    public int getMaterialId() {
        return materialId;
    }

    /**
     * Set the material ID for this update record.
     *
     * @param materialId the material ID
     */
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    /**
     * Get the block data for this update record.
     *
     * @return the block data
     */
    public byte getData() {
        return data;
    }

    /**
     * Set the block data for this update record.
     *
     * @param data the block data
     */
    public void setData(byte data) {
        this.data = data;
    }
}
