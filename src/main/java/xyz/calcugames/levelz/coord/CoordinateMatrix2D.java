package xyz.calcugames.levelz.coord;

import org.jetbrains.annotations.NotNull;
import xyz.calcugames.levelz.Dimension;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a 2D matrix of coordinates.
 */
public final class CoordinateMatrix2D extends CoordinateMatrix {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final Coordinate2D start;

    /**
     * Creates a new 2D matrix of coordinates with minimum values of 0.
     * @param x The x value.
     * @param y The y value.
     * @param start The starting coordinate.
     */
    public CoordinateMatrix2D(int x, int y, @NotNull Coordinate2D start) {
        this(0, x, 0, y, start);
    }

    /**
     * Creates a new 2D matrix of coordinates.
     * @param minX The minimum x value.
     * @param maxX The maximum x value.
     * @param minY The minimum y value.
     * @param maxY The maximum y value.
     * @param start The starting coordinate.
     */
    public CoordinateMatrix2D(int minX, int maxX, int minY, int maxY, @NotNull Coordinate2D start) {
        super(Dimension.TWO);

        if (minX > maxX) throw new IllegalArgumentException("minX must be less than or equal to maxX");
        if (minY > maxY) throw new IllegalArgumentException("minY must be less than or equal to maxY");
        if (start == null) throw new IllegalArgumentException("start cannot be null");

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.start = start;
    }

    @Override
    @NotNull
    public Set<Coordinate2D> getCoordinates() {
        Set<Coordinate2D> coordinates = new HashSet<>();

        for (int x = min(minX, maxX); x <= max(minX, maxX); x++)
            for (int y = min(minY, maxY); y <= max(minY, maxY); y++)
                coordinates.add(new Coordinate2D(start.getX() + x, start.getY() + y));

        return coordinates;
    }

    @Override
    @NotNull
    public Coordinate2D getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "(" + minX + ", " + maxX + ", " + minY + ", " + maxY + ")^[" + start.getX() + ", " + start.getY() + "]";
    }

    /**
     * Parses a 2D matrix of coordinates from a string.
     * @param string The string to parse.
     * @return The parsed 2D matrix of coordinates.
     * @throws IllegalArgumentException if the string is invalid
     */
    @NotNull
    public static CoordinateMatrix2D fromString(@NotNull String string) throws IllegalArgumentException {
        String[] split = string.split("\\^");

        String[] coords = split[1].replaceAll("[\\[\\]\\s]", "").split(",");
        String[] matrix = split[0].replaceAll("[()\\s]", "").split(",");

        if (coords.length != 2) throw new IllegalArgumentException("Invalid start coordinate string");
        if (matrix.length != 4) throw new IllegalArgumentException("Invalid matrix bounds string");

        double cx = Double.parseDouble(coords[0]), cy = Double.parseDouble(coords[1]);

        int x1 = Integer.parseInt(matrix[0]), x2 = Integer.parseInt(matrix[1]);
        int y1 = Integer.parseInt(matrix[2]), y2 = Integer.parseInt(matrix[3]);

        if (x1 > x2) throw new IllegalArgumentException("minX must be less than or equal to maxX");
        if (y1 > y2) throw new IllegalArgumentException("minY must be less than or equal to maxY");

        return new CoordinateMatrix2D(x1, x2, y1, y2, new Coordinate2D(cx, cy));
    }
}
