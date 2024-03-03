package me.gamercoder215.calcgames.levelz;

import me.gamercoder215.calcgames.levelz.coord.Coordinate;
import me.gamercoder215.calcgames.levelz.coord.Coordinate3D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a 3D level.
 */
public class Level3D extends Level {

    private final Map<Block, Coordinate3D[]> blocks = new HashMap<>();
    private final Coordinate3D spawn;

    /**
     * Creates an empty 3D Level.
     */
    public Level3D() {
        this.spawn = new Coordinate3D(0, 0, 0);
    }

    /**
     * Creates a new 3D Level with the given headers.
     * @param headers Level Headers
     */
    public Level3D(@NotNull Map<String, String> headers) {
        super(headers);
        this.spawn = Coordinate3D.fromString(headers.get("spawn"));
    }

    /**
     * Creates a new 2D Level with the given headers and blocks.
     * @param headers Level Headers
     * @param blocks Level Blocks
     */
    public Level3D(@NotNull Map<String, String> headers, @NotNull Map<Block, Coordinate3D[]> blocks) {
        this(headers);
        this.blocks.putAll(blocks);
    }

    @Override
    public Coordinate3D getSpawn() {
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
    public Map<Block, Coordinate3D[]> getBlocks() {
        return Map.copyOf(blocks);
    }

    @Override
    public Level2D clone() {
        return (Level2D) super.clone();
    }

}
