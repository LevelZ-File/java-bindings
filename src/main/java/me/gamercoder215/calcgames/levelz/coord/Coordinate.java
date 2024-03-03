package me.gamercoder215.calcgames.levelz.coord;

import me.gamercoder215.calcgames.levelz.Dimension;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    Dimension getDimension();

    @NotNull
    String toString();

    int hashCode();

}
