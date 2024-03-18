package me.gamercoder215.calcgames.levelz.builder

import me.gamercoder215.calcgames.levelz.Level2D
import me.gamercoder215.calcgames.levelz.Level3D
import me.gamercoder215.calcgames.levelz.as2D
import me.gamercoder215.calcgames.levelz.as3D

/**
 * Creates a 2D level.
 * @param init The level builder.
 * @return The 2D level.
 */
fun create2DLevel(init: LevelBuilder.() -> Unit): Level2D {
    val level = LevelBuilder.create2D()
    level.init()

    return level.build().as2D
}

/**
 * Creates a 3D level.
 * @param init The level builder.
 * @return The 3D level.
 */
fun create3DLevel(init: LevelBuilder.() -> Unit): Level3D {
    val level = LevelBuilder.create3D()
    level.init()

    return level.build().as3D
}