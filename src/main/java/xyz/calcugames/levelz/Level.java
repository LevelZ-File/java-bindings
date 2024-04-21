package xyz.calcugames.levelz;

import xyz.calcugames.levelz.coord.Coordinate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Represents a LevelZ level.
 */
public abstract class Level implements Iterable<LevelObject>, Cloneable {

    private final Map<String, String> headers = new HashMap<>();

    /**
     * Creates an empty Level.
     */
    protected Level() {}

    /**
     * Creates a new Level with the given headers.
     * @param headers Level Headers
     */
    protected Level(@NotNull Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    // Implementation

    /**
     * Gets the spawn point for this level.
     * @return Level Spawn
     */
    @NotNull
    public abstract Coordinate getSpawn();

    /**
     * Gets an immutable copy of all coordinates in this level.
     * @return Level Coordinates
     */
    @NotNull
    @Unmodifiable
    public abstract Set<Coordinate> getCoordinates();

    /**
     * Gets an immutable copy of all blocks to their coordinates in this level.
     * @return Level Blocks
     */
    @NotNull
    @Unmodifiable
    public abstract Set<LevelObject> getBlocks();

    // Default

    /**
     * Gets an immutable copy of the raw headers for this level.
     * @return Level Headers
     */
    @NotNull
    @Unmodifiable
    public final Map<String, String> getHeaders() {
        return Map.copyOf(headers);
    }

    @Override
    public Level clone() {
        try {
            return (Level) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public final Iterator<LevelObject> iterator() {
        return getBlocks().iterator();
    }
}
