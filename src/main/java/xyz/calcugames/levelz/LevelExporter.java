package xyz.calcugames.levelz;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
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
                    blockMap.put(block.getBlock(), blockMap.get(block.getBlock()) + "*" + block.getCoordinate());
                else
                    blockMap.put(block.getBlock(), block.getCoordinate().toString());
            
            for (Map.Entry<Block, String> entry : blockMap.entrySet())
                builder.append(entry.getKey()).append(": ").append(entry.getValue()).append(lineSeparator);
        }

        builder.append("end");
        return builder.toString();
    }

    /**
     * <p>Writes to a byte array using the default charset.</p>
     * <p>The exporter will only write to the byte buffer if there is enough space, stopping if the length if too small.</p>
     * @param data Byte Array to encode into
     * @throws IllegalArgumentException if the data is null
     */
    public void writeToByteArray(byte[] data) throws IllegalArgumentException {
        writeToByteArray(data, Charset.defaultCharset());
    }

    /**
     * <p>Writes to a byte array.</p>
     * <p>The exporter will only write to the byte buffer if there is enough space, stopping if the length if too small.</p>
     * @param data Byte Array to encode into
     * @param charset Charset to encode bytes with
     * @throws IllegalArgumentException if the data or charset is null
     */
    public void writeToByteArray(byte[] data, @NotNull Charset charset) throws IllegalArgumentException {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        if (charset == null) throw new IllegalArgumentException("Charset cannot be null");

        String string = writeToString();
        byte[] bytes = string.getBytes(charset);
        System.arraycopy(bytes, 0, data, 0, Math.min(bytes.length, data.length));
    }

    /**
     * Writes to a file using the default charset. This will create the file if it does not exist.
     * @param file File
     * @throws IllegalArgumentException if the file is null
     */
    public void writeToFile(@NotNull File file) throws IllegalArgumentException {
        writeToFile(file, Charset.defaultCharset());
    }

    /**
     * Writes to a file. This will create the file if it does not exist.
     * @param file File
     * @param charset Charset to encode bytes with
     * @throws IllegalArgumentException if the file or charset is null
     */
    public void writeToFile(@NotNull File file, @NotNull Charset charset) throws IllegalArgumentException {
        if (file == null) throw new IllegalArgumentException("File cannot be null");
        if (charset == null) throw new IllegalArgumentException("Charset cannot be null");

        if (!file.exists())
            try {
                if (!file.createNewFile()) throw new IOException("Failed to create file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        writeToPath(file.toPath(), charset);
    }

    /**
     * Writes to a file path using the default charset.
     * @param file File Path
     * @throws IllegalArgumentException if the path is null
     */
    public void writeToPath(@NotNull Path file) throws IllegalArgumentException {
        writeToPath(file, Charset.defaultCharset());
    }

    /**
     * Writes to a file path.
     * @param file File Path
     * @param charset Charset to encode bytes with
     * @throws IllegalArgumentException if the path or charset is null
     */
    public void writeToPath(@NotNull Path file, @NotNull Charset charset) throws IllegalArgumentException {
        if (file == null) throw new IllegalArgumentException("Path cannot be null");
        if (charset == null) throw new IllegalArgumentException("Charset cannot be null");

        String data = writeToString();
        try {
            Files.write(file, data.getBytes(charset));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes this level to an output stream using the default charset.
     * @param stream Output Stream
     * @throws IllegalArgumentException if the stream is null
     */
    public void writeToStream(@NotNull OutputStream stream) throws IllegalArgumentException {
        writeToStream(stream, Charset.defaultCharset());
    }

    /**
     * Writes this level to an output stream.
     * @param stream Output Stream
     * @param charset Charset to encode bytes with
     * @throws IllegalArgumentException if the stream or charset is null
     */
    public void writeToStream(@NotNull OutputStream stream, @NotNull Charset charset) throws IllegalArgumentException {
        if (stream == null) throw new IllegalArgumentException("Stream cannot be null");
        if (charset == null) throw new IllegalArgumentException("Charset cannot be null");

        String data = writeToString();
        try {
            stream.write(data.getBytes(charset));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
