package xyz.calcugames.levelz.parser;

import xyz.calcugames.levelz.Level;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Represents a LevelZ level parser.
 */
public abstract class LevelParser {

    private final Random seed;

    /**
     * Creates a new Level Parser.
     * @param seed Seed
     */
    protected LevelParser(@NotNull Random seed) {
        if (seed == null) throw new IllegalArgumentException("Seed cannot be null");

        this.seed = seed;
    }

    /**
     * Gets the randomizer seed for this parser.
     * @return Randomizer
     */
    @NotNull
    public final Random getSeed() {
        return seed;
    }

    /**
     * Gets the input stream for this parser.
     * @return Input Stream
     */
    @NotNull
    public abstract InputStream getInputStream();

    /**
     * Parses the level from the input stream. This will close the input stream.
     * @return Level
     */
    @NotNull
    public abstract Level parse();

    // Builders

    /**
     * Creates a new Level Parser Builder.
     * @return Level Parser Builder
     */
    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Represents a Level Parser Builder.
     */
    public static final class Builder {
        Random seed;
        InputStream inputStream;
        Logger logger;

        private Builder() {
            this.seed = new SecureRandom();
            this.inputStream = null;
            this.logger = Logger.getGlobal();
        }

        /**
         * Sets the randomizer seed for this parser.
         * @param seed Seed
         * @return this class, for chaining
         */
        @NotNull
        public Builder seed(@NotNull Random seed) {
            if (seed == null) throw new IllegalArgumentException("Seed cannot be null");

            this.seed = seed;
            return this;
        }

        /**
         * Sets the input for this parser.
         * @param inputStream Input Stream
         * @return this class, for chaining
         */
        public Builder input(@NotNull InputStream inputStream) {
            if (inputStream == null) throw new IllegalArgumentException("Input Stream cannot be null");

            this.inputStream = inputStream;
            return this;
        }

        /**
         * Sets the input for this parser.
         * @param file File to read
         * @return this class, for chaining
         */
        public Builder input(@NotNull File file) {
            if (file == null) throw new IllegalArgumentException("File cannot be null");

            try {
                return input(new FileInputStream(file));
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to open file", e);
            }
        }

        /**
         * Sets the logger for the parser.
         * @param logger Logger
         * @return this class, for chaining
         */
        @NotNull
        public Builder logger(@NotNull Logger logger) {
            if (logger == null) throw new IllegalArgumentException("Logger cannot be null");

            this.logger = logger;
            return this;
        }

        /**
         * Builds the parser.
         * @return Level Parser
         */
        @NotNull
        public LevelParser build() {
            if (inputStream == null) throw new IllegalArgumentException("Input Stream cannot be null");

            return new LevelParser(seed) {
                @Override
                public InputStream getInputStream() {
                    return inputStream;
                }

                @Override
                public @NotNull Level parse() {
                    String[] lines = InternalParser.lines(new InputStreamReader(inputStream), logger);
                    return InternalParser.parse(lines, seed);
                }
            };
        }
    }

}
