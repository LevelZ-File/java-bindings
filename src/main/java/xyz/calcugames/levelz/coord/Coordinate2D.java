package xyz.calcugames.levelz.coord;

import org.jetbrains.annotations.NotNull;
import xyz.calcugames.levelz.Dimension;

import java.util.Objects;

/**
 * Represents a 2-Dimensional Coordinate.
 */
public final class Coordinate2D implements Coordinate {

    private double x, y;

    /**
     * Constructs a 2D Coordinate.
     * @param x X Value
     * @param y Y Value
     */
    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a 2D Coordinate.
     * @param xy Array of X and Y Values
     */
    public Coordinate2D(int[] xy) {
        this.x = xy[0];
        this.y = xy[1];
    }

    /**
     * Constructs a 2D Coordinate.
     * @param x X Value
     * @param y Y Value
     */
    public Coordinate2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a 2D Coordinate.
     * @param xy Array of X and Y Values
     */
    public Coordinate2D(double[] xy) {
        this.x = xy[0];
        this.y = xy[1];
    }

    /**
     * Gets the X Value of the Coordinate.
     * @return X Value
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the X Value of the Coordinate.
     * @param x X Value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the Y Value of the Coordinate.
     * @param x X Value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y Value of the Coordinate.
     * @return Y Value
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the Y Value of the Coordinate.
     * @param y Y Value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the Y Value of the Coordinate.
     * @param y Y Value
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y));
    }

    @Override
    public Dimension getDimension() {
        return Dimension.TWO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate2D that = (Coordinate2D) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        int xInt = (int) x, yInt = (int) y;
        String xs = x == xInt ? String.valueOf(xInt) : String.valueOf(x);
        String ys = y == yInt ? String.valueOf(yInt) : String.valueOf(y);

        return "[" + xs + ", " + ys + "]";
    }

    /**
     * Parses a 2D Coordinate from a string.
     * @param point String
     * @return 2D Coordinate
     */
    @NotNull
    public static Coordinate2D fromString(String point) {
        String point0 = point.trim().replaceAll("[\\[\\]\\s]", "");
        String[] parts = point0.split(",");
        double x = Double.parseDouble(parts[0]), y = Double.parseDouble(parts[1]);

        return new Coordinate2D(x, y);
    }
}
