package com.airbnb.eloquentelksbackend.entity;

import lombok.Getter;

/**
 * Represents a bounding box. For further details, see @link{https://wiki.openstreetmap.org/wiki/Bounding_Box}
 */
@Getter
public final class BoundingBox {
    /**
     * Northern boundary of the box
     */
    private double north;

    /**
     * Eastern boundary of the box
     */
    private double east;

    /**
     * Southern boundary of the box
     */
    private double south;

    /**
     * Western boundary of the box
     */
    private double west;

    /**
     * Constructor
     * @param north Northern boundary of the box
     * @param east Eastern boundary of the box
     * @param south Southern boundary of the box
     * @param west Western boundary of the box
     */
    public BoundingBox(double north, double east, double south, double west){
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
}
