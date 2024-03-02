package me.gamercoder215.calcgames.levelz.coord;

import me.gamercoder215.calcgames.levelz.Dimension;

import java.util.Objects;

/**
 * Represents a 3-Dimensional Coordinate.
 */
public class Coordinate3D implements Coordinate {

    private double x, y, z;

    /**
     * Constructs a 3D Coordinate.
     * @param x X Value
     * @param y Y Value
     * @param z Z Value
     */
    public Coordinate3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a 3D Coordinate.
     * @param xyz Array of X, Y, and Z Values
     */
    public Coordinate3D(int[] xyz) {
        this.x = xyz[0];
        this.y = xyz[1];
        this.z = xyz[2];
    }

    /**
     * Constructs a 3D Coordinate.
     * @param x X Value
     * @param y Y Value
     * @param z Z Value
     */
    public Coordinate3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a 3D Coordinate.
     * @param xyz Array of X, Y, and Z Values
     */
    public Coordinate3D(double[] xyz) {
        this.x = xyz[0];
        this.y = xyz[1];
        this.z = xyz[2];
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
     * @return Y Value
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the Y Value of the Coordinate.
     * @param y Y Value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the Z Value of the Coordinate.
     * @return Z Value
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the Z Value of the Coordinate.
     * @param z Z Value
     */
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    @Override
    public Dimension getDimension() {
        return Dimension.THREE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate3D that = (Coordinate3D) o;
        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "3D{x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
