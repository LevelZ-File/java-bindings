package me.gamercoder215.calcgames.levelz;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Game Dimension.
 */
public enum Dimension {

    /**
     * Represents a 2D Plane.
     */
    TWO,

    /**
     * Represents a 3D Space.
     */
    THREE

    ;

    /**
     * Gets if this dimension is 2D.
     * @return true if 2D, false if 3D
     */
    public boolean is2D() {
        return this == TWO;
    }

    /**
     * Gets if this dimension is 3D.
     * @return true if 3D, false if 2D
     */
    public boolean is3D() {
        return this == THREE;
    }

    /**
     * Gets the default coordinate for this dimension in string form.
     * @return Default Coordinate
     */
    @NotNull
    public String getDefaultCoordinate() {
        return is2D() ? Keywords.DEFAULT_COORDINATE_2D : Keywords.DEFAULT_COORDINATE_3D;
    }

}
