package me.gamercoder215.calcgames.levelz;

/**
 * Utility class for LevelZ File Keywords
 */
public interface Keywords {

    /**
     * Marks the end of the header section
     */
    String HEADER_END = "---";

    /**
     * Marks the end of the file
     */
    String END = "end";

    /**
     * The default coordinates for a 2D Level
     */
    String DEFAULT_COORDINATE_2D = "[0, 0]";

    /**
     * The default coordinates for a 3D Level
     */
    String DEFAULT_COORDINATE_3D = "[0, 0, 0]";

}
