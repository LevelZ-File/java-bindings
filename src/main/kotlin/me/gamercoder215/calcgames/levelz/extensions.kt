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