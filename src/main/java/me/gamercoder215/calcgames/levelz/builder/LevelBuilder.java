package me.gamercoder215.calcgames.levelz.builder;

import me.gamercoder215.calcgames.levelz.*;
import me.gamercoder215.calcgames.levelz.coord.Coordinate;
import me.gamercoder215.calcgames.levelz.coord.Coordinate2D;
import me.gamercoder215.calcgames.levelz.coord.Coordinate3D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a builder for creating LevelZ Levels.
 */
public final class LevelBuilder {

    private final Dimension dimension;

    private final Map<String, String> headers = new HashMap<>();
    private final Set<LevelObject> blocks = new HashSet<>();

    private LevelBuilder(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Creates a 2D Level Builder.
     * @return 2D Level Builder
     */
    @NotNull
    public static LevelBuilder create2D() {
        return new LevelBuilder(Dimension.TWO);
    }

    /**
     * Creates a 3D Level Builder.
     * @return 3D Level Builder
     */
    @NotNull
    public static LevelBuilder create3D() {
        return new LevelBuilder(Dimension.THREE);
    }

    /**
     * Sets the spawn point for the level.
     * @param spawn Spawn Point
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder spawn(@NotNull Coordinate spawn) {
        if (dimension.is2D())
            if (!(spawn instanceof Coordinate2D)) throw new IllegalArgumentException("Spawn must be 2D");

        if (dimension.is3D())
            if (!(spawn instanceof Coordinate3D)) throw new IllegalArgumentException("Spawn must be 3D");

        headers.put("spawn", spawn.toString());
        return this;
    }

    /**
     * Sets the spawn point for the level.
     * @param coords Spawn Point
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder spawn(int[] coords) {
        return spawn(Coordinate.fromArray(coords));
    }

    /**
     * Sets a header value.
     * @param key Header Key
     * @param value Header Value
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder header(@NotNull String key, @NotNull String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * Adds a block to the level.
     * @param block Level Block
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull LevelObject block) {
        if (dimension.is2D())
            if (!(block.getCoordinate() instanceof Coordinate2D)) throw new IllegalArgumentException("Block Coordinate must be 2D");

        if (dimension.is3D())
            if (!(block.getCoordinate() instanceof Coordinate3D)) throw new IllegalArgumentException("Block Coordinate must be 3D");

        blocks.add(block);
        return this;
    }

    /**
     * Adds a block to the level.
     * @param name Block Name
     * @param properties Block Properties
     * @param coordinate Block Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Map<String, Object> properties, @NotNull Coordinate coordinate) {
        return block(new LevelObject(new Block(name, properties), coordinate));
    }

    /**
     * Adds a block to the level.
     * @param name Block Name
     * @param properties Block Properties
     * @param coordinates Array of Block Coordinates
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Map<String, Object> properties, @NotNull Coordinate... coordinates) {
        for (Coordinate c : coordinates)
            block(name, properties, c);

        return this;
    }

    /**
     * Adds a block to the level.
     * @param name Block Name
     * @param properties Block Properties
     * @param coordinates Iterable of Block Coordinates
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Map<String, Object> properties, @NotNull Iterable<Coordinate> coordinates) {
        for (Coordinate c : coordinates)
            block(name, properties, c);

        return this;
    }

    /**
     * Adds a block with no properties to the level.
     * @param name Block Name
     * @param coordinate Block Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Coordinate coordinate) {
        return block(name, new HashMap<>(), coordinate);
    }

    /**
     * Adds a block with no properties to the level.
     * @param name Block Name
     * @param coordinate Block Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Coordinate... coordinate) {
        for (Coordinate c : coordinate)
            block(name, new HashMap<>(), c);

        return this;
    }

    /**
     * Adds a block with no properties to the level.
     * @param name Block Name
     * @param coordinates Iterable of Block Coordinates
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, @NotNull Iterable<Coordinate> coordinates) {
        for (Coordinate c : coordinates)
            block(name, new HashMap<>(), c);

        return this;
    }

    /**
     * Gets the current dimension of the level.
     * @return Level Dimension
     */
    @NotNull
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Gets an immutable copy of the current headers of the level.
     * @return Level Headers
     */
    @Unmodifiable
    @NotNull
    public Map<String, String> getHeaders() {
        return Map.copyOf(headers);
    }

    /**
     * Gets an immutable copy of the current blocks of the level.
     * @return Level Blocks
     */
    @Unmodifiable
    @NotNull
    public Set<LevelObject> getBlocks() {
        return Set.copyOf(blocks);
    }

    /**
     * Builds the level.
     * @return Level
     */
    @NotNull
    public Level build() {
        if (dimension.is2D()) return new Level2D(headers, blocks);
        if (dimension.is3D()) return new Level3D(headers, blocks);

        throw new IllegalStateException("Invalid Dimension: " + dimension);
    }

}
