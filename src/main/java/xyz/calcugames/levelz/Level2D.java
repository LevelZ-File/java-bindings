package xyz.calcugames.levelz;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.calcugames.levelz.coord.Coordinate;
import xyz.calcugames.levelz.coord.Coordinate2D;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a 2D level.
 */
public class Level2D extends Level {

    private final Set<LevelObject> blocks = new HashSet<>();
    private final Coordinate2D spawn;
    private final Scroll scroll;

    /**
     * Creates an empty 2D Level.
     */
    public Level2D() {
        this.spawn = new Coordinate2D(0, 0);
        this.scroll = Scroll.NONE;
    }

    /**
     * Creates a new 2D Level with the given headers.
     * @param headers Level Headers
     */
    public Level2D(@NotNull Map<String, String> headers) {
        super(headers);
        this.spawn = Coordinate2D.fromString(headers.getOrDefault("spawn", "[0, 0]"));
        this.scroll = Scroll.valueOf(headers.getOrDefault("scroll", "NONE").replace('-', '_').toUpperCase());
    }

    /**
     * Creates a new 2D Level with the given headers and blocks.
     * @param headers Level Headers
     * @param blocks Level Blocks
     */
    public Level2D(@NotNull Map<String, String> headers, @NotNull Collection<LevelObject> blocks) {
        this(headers);
        this.blocks.addAll(blocks);
    }

    /**
     * Gets the scroll direction for this 2D Level.
     * @return Level Scroll Direction
     */
    @NotNull
    public Scroll getScroll() {
        return scroll;
    }

    @Override
    public Coordinate2D getSpawn() {
        return spawn;
    }

    @Override
    public Set<Coordinate> getCoordinates() {
        return blocks.stream()
                .map(LevelObject::getCoordinate)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    @NotNull
    @Unmodifiable
    public Set<LevelObject> getBlocks() {
        return Set.copyOf(blocks);
    }

    @Override
    public Level2D clone() {
        return (Level2D) super.clone();
    }

}
