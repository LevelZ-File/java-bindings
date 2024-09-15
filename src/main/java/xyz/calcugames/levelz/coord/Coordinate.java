package xyz.calcugames.levelz.coord;

import xyz.calcugames.levelz.Dimension;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Game Coordinate.
 */
public interface Coordinate extends Comparable<Coordinate> {

    /**
     * Gets the magnitude of the Coordinate.
     * @return Coordinate Magnitude
     */
    double getMagnitude();

    /**
     * Gets the distance between two Coordinates.
     * @param other Other Coordinate
     * @return Distance between Coordinates
     * @throws IllegalArgumentException if the Coordinates are not the same dimension
     */
    double getDistance(@NotNull Coordinate other) throws IllegalArgumentException;

    /**
     * Gets the dimension of the Coordinate.
     * @return Coordinate Dimension
     */
    @NotNull
    Dimension getDimension();

    @NotNull
    String toString();

    int hashCode();

    /**
     * Creates a Coordinate from an array of integers.
     * @param coords Coordinate Array
     * @return A {@linkplain Coordinate2D 2D} or {@linkplain Coordinate3D 3D} Coordinate, depending on array size
     */
    @NotNull
    static Coordinate fromArray(int[] coords) {
        if (coords.length == 2) return new Coordinate2D(coords);
        if (coords.length == 3) return new Coordinate3D(coords);

        throw new IllegalArgumentException("Invalid Coordinate Length");
    }

    /**
     * Creates a Coordinate from an array of doubles.
     * @param coords Coordinate Array
     * @return A {@linkplain Coordinate2D 2D} or {@linkplain Coordinate3D 3D} Coordinate, depending on array size
     */
    @NotNull
    static Coordinate fromArray(double[] coords) {
        if (coords.length == 2) return new Coordinate2D(coords);
        if (coords.length == 3) return new Coordinate3D(coords);

        throw new IllegalArgumentException("Invalid Coordinate Length");
    }

    default int compareTo(@NotNull Coordinate o) {
        return Double.compare(getMagnitude(), o.getMagnitude());
    }

}
