package xyz.calcugames.levelz.coord;

import org.jetbrains.annotations.NotNull;
import xyz.calcugames.levelz.Dimension;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a 3D matrix of coordinates.
 */
public final class CoordinateMatrix3D extends CoordinateMatrix {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int minZ;
    private final int maxZ;
    private final Coordinate3D start;

    public CoordinateMatrix3D(int x, int y, int z, Coordinate3D start) {
        this(0, x, 0, y, 0, z, start);
    }

    /**
     * Creates a new 3D matrix of coordinates.
     * @param minX The minimum x value.
     * @param maxX The maximum x value.
     * @param minY The minimum y value.
     * @param maxY The maximum y value.
     * @param minZ The minimum z value.
     * @param maxZ The maximum z value.
     * @param start The starting coordinate.
     */
    public CoordinateMatrix3D(int minX, int maxX, int minY, int maxY, int minZ, int maxZ, Coordinate3D start) {
        if (minX > maxX) throw new IllegalArgumentException("minX must be less than or equal to maxX");
        if (minY > maxY) throw new IllegalArgumentException("minY must be less than or equal to maxY");
        if (minZ > maxZ) throw new IllegalArgumentException("minZ must be less than or equal to maxZ");
        if (start == null) throw new IllegalArgumentException("start cannot be null");

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.start = start;
    }

    @Override
    public Dimension getDimension() {
        return Dimension.THREE;
    }

    @Override
    public Set<Coordinate3D> getCoordinates() {
        Set<Coordinate3D> coordinates = new HashSet<>();

        for (int x = min(minX, maxX); x <= max(minX, maxX); x++)
            for (int y = min(minY, maxY); y <= max(minY, maxY); y++)
                for (int z = min(minZ, maxZ); z <= max(minZ, maxZ); z++)
                    coordinates.add(new Coordinate3D(start.getX() + x, start.getY() + y, start.getZ() + z));

        return coordinates;
    }

    @Override
    public Coordinate3D getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "(" + minX + ", " + maxX + ", " + minY + ", " + maxY + ", " + minZ + ", " + maxZ + ")^[" + start.getX() + ", " + start.getY() + ", " + start.getZ() + "]";
    }

    /**
     * Parses a string into a 3D matrix of coordinates.
     * @param string The string to parse.
     * @return The parsed 3D matrix of coordinates.
     * @throws IllegalArgumentException if the string is invalid
     */
    @NotNull
    public static CoordinateMatrix3D fromString(@NotNull String string) throws IllegalArgumentException {
        String[] split = string.split("\\^");

        String[] coords = split[1].replaceAll("[\\[\\]\\s]", "").split(",");
        String[] matrix = split[0].replaceAll("[()\\s]", "").split(",");

        if (coords.length != 3) throw new IllegalArgumentException("Invalid start coordinate string");
        if (matrix.length != 6) throw new IllegalArgumentException("Invalid matrix bounds string");

        double cx = Double.parseDouble(coords[0]), cy = Double.parseDouble(coords[1]), cz = Double.parseDouble(coords[2]);

        int x1 = Integer.parseInt(matrix[0]), x2 = Integer.parseInt(matrix[1]);
        int y1 = Integer.parseInt(matrix[2]), y2 = Integer.parseInt(matrix[3]);
        int z1 = Integer.parseInt(matrix[4]), z2 = Integer.parseInt(matrix[5]);

        if (x1 > x2) throw new IllegalArgumentException("minX must be less than or equal to maxX");
        if (y1 > y2) throw new IllegalArgumentException("minY must be less than or equal to maxY");
        if (z1 > z2) throw new IllegalArgumentException("minZ must be less than or equal to maxZ");

        return new CoordinateMatrix3D(x1, x2, y1, y2, z1, z2, new Coordinate3D(cx, cy, cz));
    }

}
