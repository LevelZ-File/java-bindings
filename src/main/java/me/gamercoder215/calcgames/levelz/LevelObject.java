package me.gamercoder215.calcgames.levelz;

import me.gamercoder215.calcgames.levelz.coord.Coordinate;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Utility Object for representing a Level Block and its Coordinate.
 */
public final class LevelObject implements Comparable<LevelObject> {

    private final Block block;
    private final Coordinate coordinate;

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param block Block
     * @param coordinate Coordinate
     */
    public LevelObject(@NotNull Block block, @NotNull Coordinate coordinate) {
        this.block = block;
        this.coordinate = coordinate;
    }

    /**
     * Gets the block of this LevelObject.
     * @return LevelObject Block
     */
    @NotNull
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the coordinate of this LevelObject.
     * @return LevelObject Coordinate
     */
    @NotNull
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelObject that = (LevelObject) o;
        return Objects.equals(block, that.block) && Objects.equals(coordinate, that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, coordinate);
    }

    @Override
    public String toString() {
        return block + ": " + coordinate;
    }

    @Override
    public int compareTo(LevelObject o) {
        return coordinate.compareTo(o.coordinate);
    }
}
