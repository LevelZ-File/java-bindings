package xyz.calcugames.levelz;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import xyz.calcugames.levelz.parser.Errors;
import xyz.calcugames.levelz.parser.LevelParser;
import xyz.calcugames.levelz.parser.ParseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Block in a Level.
 */
public final class Block {

    private final String name;
    private final Map<String, Object> properties;

    /**
     * Creates a new Block with the given name and empty properties.
     * @param name Block Name
     */
    public Block(@NotNull String name) {
        this(name, Map.of());
    }

    /**
     * Creates a new Block with the given name and properties.
     * @param name Block Name
     * @param properties Block Properties
     */
    public Block(@NotNull String name, @NotNull Map<String, Object> properties) {
        this.name = name;
        this.properties = properties;
    }

    /**
     * Gets the name of this block.
     * @return Block Name
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Gets an immutable copy of the properties for this block.
     * @return Block Properties
     */
    @Unmodifiable
    @NotNull
    public Map<String, Object> getProperties() {
        return Map.copyOf(properties);
    }

    /**
     * Creates a new Block with the given name and these properties.
     * @param name Block Name
     * @return New Block
     */
    @NotNull
    public Block withName(@NotNull String name) {
        return new Block(name, properties);
    }

    /**
     * Creates a new Block with this name and the given properties.
     * @param properties Block Properties
     * @return New Block
     */
    @NotNull
    public Block withProperties(@NotNull Map<String, Object> properties) {
        return new Block(name, properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(name, block.name) && Objects.equals(properties, block.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, properties);
    }

    @Override
    public String toString() {
        if (properties.isEmpty()) return name;
        return name + "<" + properties.toString().replaceAll("[{}]", "") + ">";
    }

    /**
     * Converts a string into a Block.
     * @param string The string to convert.
     * @return The Block, or null if the string is empty.
     */
    @Nullable
    public static Block fromString(@NotNull String string) {
        if (string.isEmpty()) return null;

        String[] split = string.replaceAll("[\\s>]", "").split("<");
        String name = split[0].trim();

        if (split.length < 2) return new Block(name, Map.of());

        Map<String, Object> properties = new HashMap<>();
        String[] props = split[1].split(",");

        for (String entry : props) {
            String[] kv = entry.split("=");
            if (kv.length < 2) throw new ParseException(String.format(Errors.INVALID_BLOCK, string));

            properties.put(kv[0], LevelParser.value(kv[1]));
        }

        return new Block(name, properties);
    }
}
