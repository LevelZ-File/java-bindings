package xyz.calcugames.levelz.builder;

import xyz.calcugames.levelz.coord.Coordinate;
import xyz.calcugames.levelz.coord.Coordinate2D;
import xyz.calcugames.levelz.coord.Coordinate3D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import xyz.calcugames.levelz.*;

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
     * Sets the scroll direction for the level.
     * @param scroll Scroll Direction
     * @return this class, for chaining
     * @throws IllegalArgumentException If the level is not 2D
     */
    @NotNull
    public LevelBuilder scroll(@Nullable Scroll scroll) throws IllegalArgumentException {
        if (!dimension.is2D())
            throw new IllegalArgumentException("Scroll is only supported for 2D levels");
        
        if (scroll == null) 
            headers.remove("scroll");
        else 
            headers.put("scroll", scroll.name().toLowerCase());
        
        return this;
    }

    /**
     * Sets a header value.
     * @param key Header Key
     * @param value Header Value, can be null
     * @return this class, for chaining
     * @throws IllegalArgumentException If the key is null
     */
    @NotNull
    public LevelBuilder header(@NotNull String key, @Nullable String value) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        if (value == null)
            headers.remove(key);
        else 
            headers.put(key, value);
        return this;
    }

    /**
     * Adds a block to the level.
     * @param block Level Block
     * @return this class, for chaining
     * @throws IllegalArgumentException If the block coordinate is not the same dimension as the level
     */
    @NotNull
    public LevelBuilder block(@NotNull LevelObject block) throws IllegalArgumentException {
        if (dimension.is2D())
            if (!(block.getCoordinate() instanceof Coordinate2D)) throw new IllegalArgumentException("Block Coordinate must be 2D");

        if (dimension.is3D())
            if (!(block.getCoordinate() instanceof Coordinate3D)) throw new IllegalArgumentException("Block Coordinate must be 3D");

        blocks.add(block);
        return this;
    }

    /**
     * Adds a block to the level.
     * @param block Block
     * @param coordinate Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull Block block, @NotNull Coordinate coordinate) {
        return block(new LevelObject(block, coordinate));
    }

    /**
     * Adds a block to the level.
     * @param block Block
     * @param x X Coordinate
     * @param y Y Coordinate
     * @return this class, for chaining
     * @throws IllegalArgumentException If the dimension is not 2D
     */
    @NotNull
    public LevelBuilder block(@NotNull Block block, int x, int y) throws IllegalArgumentException {
        if (!dimension.is2D()) throw new IllegalArgumentException("Block Coordinate cannot be 2D");
        return block(block, new Coordinate2D(x, y));
    }

    /**
     * Adds a block to the level.
     * @param block Block
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     * @return this class, for chaining
     * @throws IllegalArgumentException If the dimension is not 3D
     */
    @NotNull
    public LevelBuilder block(@NotNull Block block, int x, int y, int z) throws IllegalArgumentException {
        if (!dimension.is3D()) throw new IllegalArgumentException("Block Coordinate cannot be 3D");
        return block(block, new Coordinate3D(x, y, z));
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
     * Adds a block with no properties to the level.
     * @param name Block Name
     * @param x X Coordinate
     * @param y Y Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, int x, int y) {
        return block(name, new Coordinate2D(x, y));
    }

    /**
     * Adds a block with no properties to the level.
     * @param name Block Name
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param z Z Coordinate
     * @return this class, for chaining
     */
    @NotNull
    public LevelBuilder block(@NotNull String name, int x, int y, int z) {
        return block(name, new Coordinate3D(x, y, z));
    }

    /**
     * Performs a block matrix operation.
     * @param block Block
     * @param cx Center X
     * @param cy Center Y
     * @param x1 First X Coordinate
     * @param x2 Second X Coordinate
     * @param y1 First Y Coordinate
     * @param y2 Second Y Coordinate
     * @return this class, for chaining
     * @throws IllegalArgumentException If the dimension is not 2D, or first is bigger than second
     */
    @NotNull
    public LevelBuilder matrix(@NotNull Block block, int cx, int cy, int x1, int x2, int y1, int y2) throws IllegalArgumentException {
        if (!dimension.is2D()) throw new IllegalArgumentException("2D Matrix is only supported for 2D levels");
        if (x1 > x2 || y1 > y2) throw new IllegalArgumentException("First coordinate must be smaller than second");

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                block(block, cx + x, cy + y);

        return this;
    }

    /**
     * Performs a block matrix operation.
     * @param block Block
     * @param cx Center X
     * @param cy Center Y
     * @param cz Center Z
     * @param x1 First X Coordinate
     * @param x2 Second X Coordinate
     * @param y1 First Y Coordinate
     * @param y2 Second Y Coordinat
     * @param z1 First Z Coordinate
     * @param z2 Second Z Coordinate
     * @return this class, for chaining
     * @throws IllegalArgumentException If the dimension is not 2D, or first is bigger than second
     */
    @NotNull
    public LevelBuilder matrix(@NotNull Block block, int cx, int cy, int cz, int x1, int x2, int y1, int y2, int z1, int z2) throws IllegalArgumentException {
        if (!dimension.is3D()) throw new IllegalArgumentException("3D Matrix is only supported for 3D levels");
        if (x1 > x2 || y1 > y2 || z1 > z2) throw new IllegalArgumentException("First coordinate must be smaller than second");

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                for (int z = z1; z <= z2; z++)
                    block(block, cx + x, cy + y, cz + z);

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
