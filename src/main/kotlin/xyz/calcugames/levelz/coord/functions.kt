package xyz.calcugames.levelz.coord

// Coordiante2D

/**
 * Creates a 2D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: Double, y: Double): Coordinate2D = Coordinate2D(x, y)

/**
 * Creates a 2D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: Float, y: Float): Coordinate2D = Coordinate2D(x.toDouble(), y.toDouble())

/**
 * Creates a 2D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: Int, y: Int): Coordinate2D = Coordinate2D(x, y)

/**
 * Creates a 2D coordinate.
 * @param xy The x and y values in an array.
 * @return The 2D coordinate.
 */
fun coordinate2D(xy: IntArray) = Coordinate2D(xy[0], xy[1])

/**
 * Creates a 2D coordinate.
 * @param xy The x and y values in an array.
 * @return The 2D coordinate.
 */
fun coordinate2D(xy: FloatArray) = Coordinate2D(xy[0].toDouble(), xy[1].toDouble())

/**
 * Creates a 2D coordinate.
 * @param xy The x and y values in an array.
 * @return The 2D coordinate.
 */
fun coordinate2D(xy: DoubleArray) = Coordinate2D(xy[0], xy[1])

/**
 * Creates a 2D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: () -> Int, y: () -> Int): Coordinate2D = Coordinate2D(x(), y())

/**
 * Creates a 2D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: () -> Float, y: () -> Float): Coordinate2D = Coordinate2D(x().toDouble(), y().toDouble())

/**
 * Creates a 2D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @return The 2D coordinate.
 */
fun coordinate2D(x: () -> Double, y: () -> Double): Coordinate2D = Coordinate2D(x(), y())

// Coordinate3D

/**
 * Creates a 3D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @param z The z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: Double, y: Double, z: Double): Coordinate3D = Coordinate3D(x, y, z)

/**
 * Creates a 3D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @param z The z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: Float, y: Float, z: Float): Coordinate3D = Coordinate3D(x.toDouble(), y.toDouble(), z.toDouble())

/**
 * Creates a 3D coordinate.
 * @param x The x value.
 * @param y The y value.
 * @param z The z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: Int, y: Int, z: Int): Coordinate3D = Coordinate3D(x, y, z)

/**
 * Creates a 3D coordinate.
 * @param xyz The x, y, and z values in an array.
 * @return The 3D coordinate.
 */
fun coordinate3D(xyz: IntArray) = Coordinate3D(xyz[0], xyz[1], xyz[2])

/**
 * Creates a 3D coordinate.
 * @param xyz The x, y, and z values in an array.
 * @return The 3D coordinate.
 */
fun coordinate3D(xyz: FloatArray) = Coordinate3D(xyz[0].toDouble(), xyz[1].toDouble(), xyz[2].toDouble())

/**
 * Creates a 3D coordinate.
 * @param xyz The x, y, and z values in an array.
 * @return The 3D coordinate.
 */
fun coordinate3D(xyz: DoubleArray) = Coordinate3D(xyz[0], xyz[1], xyz[2])

/**
 * Creates a 3D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @param z The supplier to the z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: () -> Int, y: () -> Int, z: () -> Int): Coordinate3D = Coordinate3D(x(), y(), z())

/**
 * Creates a 3D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @param z The supplier to the z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: () -> Float, y: () -> Float, z: () -> Float): Coordinate3D = Coordinate3D(x().toDouble(), y().toDouble(), z().toDouble())

/**
 * Creates a 3D coordinate.
 * @param x The supplier to the x value.
 * @param y The supplier to the y value.
 * @param z The supplier to the z value.
 * @return The 3D coordinate.
 */
fun coordinate3D(x: () -> Double, y: () -> Double, z: () -> Double): Coordinate3D = Coordinate3D(x(), y(), z())