package xyz.calcugames.levelz;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a class that exports a LevelZ Level to a file.
 */
public final class LevelExporter {

    private final Level level;

    /**
     * Whether to include headers in the export.
     */
    public boolean includeHeaders = true;

    /**
     * Whether to include data in the export.
     */
    public boolean includeData = true;

    /**
     * Whether to include a section separator in the export.
     */
    public String fileExtension = "lvlz";

    /**
     * The line separator to use in the export. Default is {@code \n}.
     */
    public String lineSeparator = "\n";

    private LevelExporter(Level level) {
        this.level = level;
    }

    /**
     * Exports the Level to a file.
     * @param level Level to Export
     * @return Level Exporter
     */
    @NotNull
    public static LevelExporter export(@NotNull Level level) {
        if (level == null) throw new IllegalArgumentException("Level cannot be null");

        return new LevelExporter(level);
    }

    /**
     * Exports the Level to a string.
     * @return Level String
     */
    @NotNull
    public String writeToString() {
        StringBuilder builder = new StringBuilder();

        if (includeHeaders) {
            for (Map.Entry<String, String> key : level.getHeaders().entrySet())
                builder.append("@").append(key.getKey()).append(" ").append(key.getValue()).append(lineSeparator);

            builder.append("---").append(lineSeparator);
        }

        if (includeData) {
            Map<Block, String> blockMap = new HashMap<>();
            List<LevelObject> blocks = level.getBlocks().stream()
                .sorted()
                .collect(Collectors.toList());
            
            for (LevelObject block : blocks)
                if (blockMap.containsKey(block.getBlock()))
                    blockMap.put(block.getBlock(), blockMap.get(block.getBlock()) + "*" + block.getCoordinate().toString());
                else
                    blockMap.put(block.getBlock(), block.getCoordinate().toString());
            
            for (Map.Entry<Block, String> entry : blockMap.entrySet())
                builder.append(entry.getKey()).append(": ").append(entry.getValue()).append(lineSeparator);
        }

        builder.append("end");
        return builder.toString();
    }

    /**
     * Writes to a file. This will create the file if it does not exist.
     * @param file File
     */
    public void writeToFile(@NotNull File file) {
        if (file == null) throw new IllegalArgumentException("File cannot be null");

        if (!file.exists())
            try {
                if (!file.createNewFile()) throw new IOException("Failed to create file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        writeToPath(file.toPath());
    }

    /**
     * Writes to a file path.
     * @param file File Path
     */
    public void writeToPath(@NotNull Path file) {
        if (file == null) throw new IllegalArgumentException("Path cannot be null");

        String data = writeToString();
        try {
            Files.write(file, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
