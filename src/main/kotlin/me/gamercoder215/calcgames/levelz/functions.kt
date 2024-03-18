package me.gamercoder215.calcgames.levelz

import me.gamercoder215.calcgames.levelz.coord.Coordinate
import me.gamercoder215.calcgames.levelz.coord.Coordinate2D
import me.gamercoder215.calcgames.levelz.coord.Coordinate3D

fun levelObject(block: Block, coordinate: Coordinate) = LevelObject(block, coordinate)
fun levelObject(block: Block, x: Int, y: Int) = LevelObject(block, Coordinate2D(x, y))
fun levelObject(block: Block, x: Int, y: Int, z: Int) = LevelObject(block, Coordinate3D(x, y, z))

fun levelObject(name: String, coordinate: Coordinate, properties: MutableMap<String, Any>.() -> Unit): LevelObject {
    val p = mutableMapOf<String, Any>()
    p.properties()

    return LevelObject(Block(name, p), coordinate)
}

fun levelObject(name: String, x: Int, y: Int, properties: MutableMap<String, Any>.() -> Unit): LevelObject {
    val p = mutableMapOf<String, Any>()
    p.properties()

    return LevelObject(Block(name, p), Coordinate2D(x, y))
}

fun levelObject(name: String, x: Int, y: Int, z: Int, properties: MutableMap<String, Any>.() -> Unit): LevelObject {
    val p = mutableMapOf<String, Any>()
    p.properties()

    return LevelObject(Block(name, p), Coordinate3D(x, y, z))
}

fun export(level: Level, exporter: LevelExporter.() -> Unit): LevelExporter {
    val e = LevelExporter.export(level)
    e.exporter()

    return e
}