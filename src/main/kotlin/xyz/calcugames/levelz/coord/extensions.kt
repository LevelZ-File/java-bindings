package xyz.calcugames.levelz.coord

// Coordiante2D

/**
 * Returns the x value of the coordinate
 */
operator fun Coordinate2D.component1(): Double = x

/**
 * Returns the y value of the coordinate
 */
operator fun Coordinate2D.component2(): Double = y

operator fun Coordinate2D.plus(other: Coordinate2D): Coordinate2D = Coordinate2D(x + other.x, y + other.y)
operator fun Coordinate2D.plus(other: Double): Coordinate2D = Coordinate2D(x + other, y + other)
operator fun Coordinate2D.plus(other: Int): Coordinate2D = Coordinate2D(x + other, y + other)

operator fun Coordinate2D.minus(other: Coordinate2D): Coordinate2D = Coordinate2D(x - other.x, y - other.y)
operator fun Coordinate2D.minus(other: Double): Coordinate2D = Coordinate2D(x - other, y - other)
operator fun Coordinate2D.minus(other: Int): Coordinate2D = Coordinate2D(x - other, y - other)

operator fun Coordinate2D.times(other: Coordinate2D): Coordinate2D = Coordinate2D(x * other.x, y * other.y)
operator fun Coordinate2D.times(other: Double): Coordinate2D = Coordinate2D(x * other, y * other)
operator fun Coordinate2D.times(other: Int): Coordinate2D = Coordinate2D(x * other, y * other)

operator fun Coordinate2D.div(other: Coordinate2D): Coordinate2D = Coordinate2D(x / other.x, y / other.y)
operator fun Coordinate2D.div(other: Double): Coordinate2D = Coordinate2D(x / other, y / other)
operator fun Coordinate2D.div(other: Int): Coordinate2D = Coordinate2D(x / other, y / other)

// Coordinate3D

/**
 * Returns the x value of the coordinate
 */
operator fun Coordinate3D.component1(): Double = x

/**
 * Returns the y value of the coordinate
 */
operator fun Coordinate3D.component2(): Double = y

/**
 * Returns the z value of the coordinate
 */
operator fun Coordinate3D.component3(): Double = z

operator fun Coordinate3D.plus(other: Coordinate3D): Coordinate3D = Coordinate3D(x + other.x, y + other.y, z + other.z)
operator fun Coordinate3D.plus(other: Double): Coordinate3D = Coordinate3D(x + other, y + other, z + other)
operator fun Coordinate3D.plus(other: Int): Coordinate3D = Coordinate3D(x + other, y + other, z + other)

operator fun Coordinate3D.minus(other: Coordinate3D): Coordinate3D = Coordinate3D(x - other.x, y - other.y, z - other.z)
operator fun Coordinate3D.minus(other: Double): Coordinate3D = Coordinate3D(x - other, y - other, z - other)
operator fun Coordinate3D.minus(other: Int): Coordinate3D = Coordinate3D(x - other, y - other, z - other)

operator fun Coordinate3D.times(other: Coordinate3D): Coordinate3D = Coordinate3D(x * other.x, y * other.y, z * other.z)
operator fun Coordinate3D.times(other: Double): Coordinate3D = Coordinate3D(x * other, y * other, z * other)
operator fun Coordinate3D.times(other: Int): Coordinate3D = Coordinate3D(x * other, y * other, z * other)

operator fun Coordinate3D.div(other: Coordinate3D): Coordinate3D = Coordinate3D(x / other.x, y / other.y, z / other.z)
operator fun Coordinate3D.div(other: Double): Coordinate3D = Coordinate3D(x / other, y / other, z / other)
operator fun Coordinate3D.div(other: Int): Coordinate3D = Coordinate3D(x / other, y / other, z / other)