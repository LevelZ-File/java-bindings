package xyz.calcugames.levelz.parser;

/**
 * Utility class for storing common LevelZ Errors
 */
public interface Errors {

    // Missing
    String MISSING_DIMENSION_TYPE = "Missing Dimension Type (@type)";
    String MISSING_SPAWNPOINT = "Missing Level Spawnpoint (@spawn)";

    // I/O
    String FAILED_TO_CLOSE_READER = "Failed to close LevelZ Reader";
    String INVALID_HEADER = "Invalid LevelZ Header: %s";
    String INVALID_POINT = "Invalid LevelZ Point: %s";
    String INVALID_BLOCK = "Invalid LevelZ Block: %s";
    String INVALID_PROBABILITIES = "LevelZ Block Probabilities exceeded 1.0, found %s";

}
