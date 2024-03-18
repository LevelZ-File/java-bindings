package me.gamercoder215.calcgames.levelz

// Level

/**
 * Casts this Level as a Level2D.
 */
val Level.as2D: Level2D
    get() = this as Level2D

/**
 * Casts this Level as a Level3D.
 */
val Level.as3D: Level3D
    get() = this as Level3D

// Block

/**
 * Gets the name of this Block.
 * @return The name of this Block.
 */
operator fun Block.component1() = name

/**
 * Gets the properties of this Block.
 * @return The properties of this Block.
 */
operator fun Block.component2(): Map<String, Any> = properties

// LevelObject

/**
 * Gets the block of this LevelObject.
 * @return The block of this LevelObject.
 */
operator fun LevelObject.component1() = block

/**
 * Gets the coordinate for this LevelObject.
 * @return The coordinate for this LevelObject.
 */
operator fun LevelObject.component2() = coordinate