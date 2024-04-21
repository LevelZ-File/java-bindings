package xyz.calcugames.levelz;

import xyz.calcugames.levelz.coord.Coordinate;
import xyz.calcugames.levelz.coord.Coordinate3D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a 3D level.
 */
public class Level3D extends Level {

    private final Set<LevelObject> blocks = new HashSet<>();
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
     * Creates a new 3D Level with the given headers and blocks.
     * @param headers Level Headers
     * @param blocks Level Blocks
     */
    public Level3D(@NotNull Map<String, String> headers, @NotNull Collection<LevelObject> blocks) {
        this(headers);
        this.blocks.addAll(blocks);
    }

    @Override
    public Coordinate3D getSpawn() {
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
    public Level3D clone() {
        return (Level3D) super.clone();
    }

}
