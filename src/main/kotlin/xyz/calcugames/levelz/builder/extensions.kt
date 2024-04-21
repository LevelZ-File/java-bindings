package xyz.calcugames.levelz.builder

import xyz.calcugames.levelz.Scroll
import xyz.calcugames.levelz.coord.Coordinate
import xyz.calcugames.levelz.coord.Coordinate2D
import xyz.calcugames.levelz.coord.Coordinate3D

// LevelBuilder
var LevelBuilder.spawn: Coordinate
    /**
     * Gets the spawn point of the level.
     * @return The spawn point of the level.
     */
    get() {
        if (dimension.is2D) return Coordinate2D.fromString(headers["spawn"])
        if (dimension.is3D) return Coordinate3D.fromString(headers["spawn"])

        throw IllegalStateException("Invalid Dimension")
    }
    /**
     * Sets the spawn point of the level.
     * @param value The spawn point of the level.
     */
    set(value) {
        headers["spawn"] = value.toString()
    }

var LevelBuilder.scroll: Scroll
    /**
     * Gets the scroll direction of the level.
     * @return The scroll direction of the level.
     */
    get() = Scroll.valueOf(headers["scroll"] ?: "NONE")
    /**
     * Sets the scroll direction of the level.
     * @param value The scroll direction of the level.
     */
    set(value) {
        if (!dimension.is2D) throw IllegalStateException("Only 2D levels can have a scroll direction")
        headers["scroll"] = value.name
    }