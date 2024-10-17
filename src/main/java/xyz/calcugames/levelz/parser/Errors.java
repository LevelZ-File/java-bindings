package xyz.calcugames.levelz.parser;

/**
 * Utility class for storing common LevelZ Errors
 */
public interface Errors {

    // Missing
    /**
     * Error message for missing LevelZ Type Header (@type)
     */
    String MISSING_DIMENSION_TYPE = "Missing Dimension Type (@type)";

    /**
     * Error message for missing LevelZ Spawnpoint Header (@spawn)
     */
    String MISSING_SPAWNPOINT = "Missing Level Spawnpoint (@spawn)";

    // I/O
    /**
     * Error message for failed LevelZ Reader close
     */
    String FAILED_TO_CLOSE_READER = "Failed to close LevelZ Reader";
    /**
     * Error message for an invalid LevelZ Header
     */
    String INVALID_HEADER = "Invalid LevelZ Header: %s";
    /**
     * Error message for an invalid LevelZ Point
     */
    String INVALID_POINT = "Invalid LevelZ Point: %s";
    /**
     * Error message for an invalid LevelZ Block
     */
    String INVALID_BLOCK = "Invalid LevelZ Block: %s";
    /**
     * Error message for when LevelZ probabilities in a block exceed 1.0
     */
    String INVALID_PROBABILITIES = "LevelZ Block Probabilities exceeded 1.0, found %s";

}
