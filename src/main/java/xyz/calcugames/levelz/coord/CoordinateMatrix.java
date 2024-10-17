package xyz.calcugames.levelz.coord;

import org.jetbrains.annotations.NotNull;
import xyz.calcugames.levelz.Dimension;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents a matrix of coordinates.
 */
@SuppressWarnings("unchecked")
public abstract class CoordinateMatrix implements Iterable<Coordinate> {

    private final Dimension dimension;

    /**
     * Creates a new Coordinate Matrix.
     * @param dimension The dimension of the matrix.
     */
    protected CoordinateMatrix(@NotNull Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Gets the dimension of the matrix.
     * @return The dimension of the matrix.
     */
    @NotNull
    public final Dimension getDimension() {
        return dimension;
    }

    /**
     * Gets the coordinates in the matrix.
     * @return The coordinates in the matrix.
     */
    @NotNull
    public abstract Set<? extends Coordinate> getCoordinates();

    /**
     * Gets the starting coordinate of the matrix.
     * @return The starting coordinate of the matrix.
     */
    @NotNull
    public abstract Coordinate getStart();

    /**
     * Gets the string version of this coordinate matrix.
     * @return This Coordinate Matrix as a string, represented in LevelZ format.
     */
    @Override
    public abstract String toString();

    @NotNull
    @Override
    public final Iterator<Coordinate> iterator() {
        return (Iterator<Coordinate>) getCoordinates().iterator();
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        CoordinateMatrix that = (CoordinateMatrix) other;
        if (!getDimension().equals(that.getDimension())) return false;
        if (!getCoordinates().equals(that.getCoordinates())) return false;

        return getStart().equals(that.getStart());
    }

    @Override
    public final int hashCode() {
        int result = getDimension().hashCode();
        result = 31 * result + getCoordinates().hashCode();
        result = 31 * result + getStart().hashCode();
        return result;
    }

}
