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
operator fun Coordinate2D.plusAssign(other: Coordinate2D) { x += other.x; y += other.y }
operator fun Coordinate2D.plusAssign(other: Double) { x += other; y += other }
operator fun Coordinate2D.plusAssign(other: Int) { x += other; y += other }

operator fun Coordinate2D.minus(other: Coordinate2D): Coordinate2D = Coordinate2D(x - other.x, y - other.y)
operator fun Coordinate2D.minus(other: Double): Coordinate2D = Coordinate2D(x - other, y - other)
operator fun Coordinate2D.minus(other: Int): Coordinate2D = Coordinate2D(x - other, y - other)
operator fun Coordinate2D.minusAssign(other: Coordinate2D) { x -= other.x; y -= other.y }
operator fun Coordinate2D.minusAssign(other: Double) { x -= other; y -= other }
operator fun Coordinate2D.minusAssign(other: Int) { x -= other; y -= other }

operator fun Coordinate2D.times(other: Coordinate2D): Coordinate2D = Coordinate2D(x * other.x, y * other.y)
operator fun Coordinate2D.times(other: Double): Coordinate2D = Coordinate2D(x * other, y * other)
operator fun Coordinate2D.times(other: Int): Coordinate2D = Coordinate2D(x * other, y * other)
operator fun Coordinate2D.timesAssign(other: Coordinate2D) { x *= other.x; y *= other.y }
operator fun Coordinate2D.timesAssign(other: Double) { x *= other; y *= other }
operator fun Coordinate2D.timesAssign(other: Int) { x *= other; y *= other }

operator fun Coordinate2D.div(other: Coordinate2D): Coordinate2D = Coordinate2D(x / other.x, y / other.y)
operator fun Coordinate2D.div(other: Double): Coordinate2D = Coordinate2D(x / other, y / other)
operator fun Coordinate2D.div(other: Int): Coordinate2D = Coordinate2D(x / other, y / other)
operator fun Coordinate2D.divAssign(other: Coordinate2D) { x /= other.x; y /= other.y }
operator fun Coordinate2D.divAssign(other: Double) { x /= other; y /= other }
operator fun Coordinate2D.divAssign(other: Int) { x /= other; y /= other }

operator fun Coordinate2D.inc(): Coordinate2D { x++; y++; return this }
operator fun Coordinate2D.dec(): Coordinate2D { x--; y--; return this }

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
operator fun Coordinate3D.plusAssign(other: Coordinate3D) { x += other.x; y += other.y; z += other.z }
operator fun Coordinate3D.plusAssign(other: Double) { x += other; y += other; z += other }
operator fun Coordinate3D.plusAssign(other: Int) { x += other; y += other; z += other }

operator fun Coordinate3D.minus(other: Coordinate3D): Coordinate3D = Coordinate3D(x - other.x, y - other.y, z - other.z)
operator fun Coordinate3D.minus(other: Double): Coordinate3D = Coordinate3D(x - other, y - other, z - other)
operator fun Coordinate3D.minus(other: Int): Coordinate3D = Coordinate3D(x - other, y - other, z - other)
operator fun Coordinate3D.minusAssign(other: Coordinate3D) { x -= other.x; y -= other.y; z -= other.z }
operator fun Coordinate3D.minusAssign(other: Double) { x -= other; y -= other; z -= other }
operator fun Coordinate3D.minusAssign(other: Int) { x -= other; y -= other; z -= other }

operator fun Coordinate3D.times(other: Coordinate3D): Coordinate3D = Coordinate3D(x * other.x, y * other.y, z * other.z)
operator fun Coordinate3D.times(other: Double): Coordinate3D = Coordinate3D(x * other, y * other, z * other)
operator fun Coordinate3D.times(other: Int): Coordinate3D = Coordinate3D(x * other, y * other, z * other)
operator fun Coordinate3D.timesAssign(other: Coordinate3D) { x *= other.x; y *= other.y; z *= other.z }
operator fun Coordinate3D.timesAssign(other: Double) { x *= other; y *= other; z *= other }
operator fun Coordinate3D.timesAssign(other: Int) { x *= other; y *= other; z *= other }

operator fun Coordinate3D.div(other: Coordinate3D): Coordinate3D = Coordinate3D(x / other.x, y / other.y, z / other.z)
operator fun Coordinate3D.div(other: Double): Coordinate3D = Coordinate3D(x / other, y / other, z / other)
operator fun Coordinate3D.div(other: Int): Coordinate3D = Coordinate3D(x / other, y / other, z / other)
operator fun Coordinate3D.divAssign(other: Coordinate3D) { x /= other.x; y /= other.y; z /= other.z }
operator fun Coordinate3D.divAssign(other: Double) { x /= other; y /= other; z /= other }
operator fun Coordinate3D.divAssign(other: Int) { x /= other; y /= other; z /= other }

operator fun Coordinate3D.inc(): Coordinate3D { x++; y++; z++; return this }
operator fun Coordinate3D.dec(): Coordinate3D { x--; y--; z--; return this }