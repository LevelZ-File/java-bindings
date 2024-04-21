package xyz.calcugames.levelz;

import xyz.calcugames.levelz.coord.Coordinate;
import org.jetbrains.annotations.NotNull;
import xyz.calcugames.levelz.coord.Coordinate2D;
import xyz.calcugames.levelz.coord.Coordinate3D;

import java.util.Map;
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
     * Creates a new LevelObject with the given block and coordinate.
     * @param block Block
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public LevelObject(@NotNull Block block, int x, int y) {
        this(block, new Coordinate2D(x, y));
    }

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param name Block Name
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public LevelObject(@NotNull String name, int x, int y) {
        this(new Block(name), new Coordinate2D(x, y));
    }

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param name Block Name
     * @param properties Block Properties
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public LevelObject(@NotNull String name, Map<String, Object> properties, int x, int y) {
        this(new Block(name, properties), new Coordinate2D(x, y));
    }

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param block Block
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     */
    public LevelObject(@NotNull Block block, int x, int y, int z) {
        this(block, new Coordinate3D(x, y, z));
    }

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param name Block Name
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     */
    public LevelObject(@NotNull String name, int x, int y, int z) {
        this(new Block(name), new Coordinate3D(x, y, z));
    }

    /**
     * Creates a new LevelObject with the given block and coordinate.
     * @param name Block Name
     * @param properties Block Properties
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     */
    public LevelObject(@NotNull String name, Map<String, Object> properties, int x, int y, int z) {
        this(new Block(name, properties), new Coordinate3D(x, y, z));
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
