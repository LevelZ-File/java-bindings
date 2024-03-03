package me.gamercoder215.calcgames.levelz;

import me.gamercoder215.calcgames.levelz.coord.Coordinate;
import me.gamercoder215.calcgames.levelz.coord.Coordinate2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a 2D level.
 */
public class Level2D extends Level {

    private final Map<Block, Coordinate2D[]> blocks = new HashMap<>();
    private final Coordinate2D spawn;

    /**
     * Creates an empty 2D Level.
     */
    public Level2D() {
        this.spawn = new Coordinate2D(0, 0);
    }

    /**
     * Creates a new 2D Level with the given headers.
     * @param headers Level Headers
     */
    public Level2D(@NotNull Map<String, String> headers) {
        super(headers);
        this.spawn = Coordinate2D.fromString(headers.get("spawn"));
    }

    /**
     * Creates a new 2D Level with the given headers and blocks.
     * @param headers Level Headers
     * @param blocks Level Blocks
     */
    public Level2D(@NotNull Map<String, String> headers, @NotNull Map<Block, Coordinate2D[]> blocks) {
        this(headers);
        this.blocks.putAll(blocks);
    }

    @Override
    public Coordinate2D getSpawn() {
        return spawn;
    }

    @Override
    public Set<Coordinate> getCoordinates() {
        return blocks.values()
                .stream()
                .flatMap(Stream::of)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    @NotNull
    @Unmodifiable
    public Map<Block, Coordinate2D[]> getBlocks() {
        return Map.copyOf(blocks);
    }

    @Override
    public Level2D clone() {
        return (Level2D) super.clone();
    }

}
