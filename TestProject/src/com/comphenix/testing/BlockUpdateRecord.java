package com.comphenix.testing;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Represents a single block update.
 */
public class BlockUpdateRecord {
    private final Location loc;
    private int materialId;
    private byte data;

    /**
     * Construct a new block update record for the given location
     * and material.
     * <p>
     * If the new block data is known in advance, consider using
     * the {@link BlockUpdateRecord(Location, Material, byte)}
     * constructor instead.
     *
     * @param loc the location at which the update should be applied
     * @param material the new material to apply to the location
     */
    public BlockUpdateRecord(Location loc, Material material) {
        this(loc, material, loc.getBlock().getData());
    }

    /**
     * Construct a new block update record for the given location,
     * material and data.
     *
     * @param loc the location at which the update should be applied
     * @param material the new block id to apply to the location.
     * @param data the new data to apply to the location
     */
    public BlockUpdateRecord(Location loc, int blockID, byte data) {
        this.loc = loc.clone();
        this.materialId = blockID;
        this.data = data;
    }
    
    /**
     * Construct a new block update record for the given location,
     * material and data.
     *
     * @param loc the location at which the update should be applied
     * @param material the new material to apply to the location
     * @param data the new data to apply to the location
     */
    public BlockUpdateRecord(Location loc, Material material, byte data) {
        this.loc = loc.clone();
        this.materialId = material.getId();
        this.data = data;
    }

    /**
     * Get the location for this update record.
     *
     * @return
     */
    public Location getLocation() {
        return loc;
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
