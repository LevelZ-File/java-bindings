package me.gamercoder215.calcgames.levelz.coord;

import me.gamercoder215.calcgames.levelz.Dimension;

/**
 * Represents a Game Coordinate.
 */
public interface Coordinate {

    /**
     * Gets the magnitude of the Coordinate.
     * @return Coordinate Magnitude
     */
    double getMagnitude();

    /**
     * Gets the dimension of the Coordinate.
     * @return Coordinate Dimension
     */
    Dimension getDimension();

    String toString();

}
