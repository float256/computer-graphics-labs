package primitives

import kotlin.math.sqrt

class Vector(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0
) {
    operator fun plus(v: Vector): Vector = Vector(x + v.x, y + v.y, z + v.z)
    operator fun minus(v: Vector): Vector = Vector(x - v.x, y - v.y, z - v.z)
    operator fun times(num: Double): Vector = Vector(x * num, y * num, z * num)
    operator fun times(v: Vector): Vector = Vector(x * v.x, y * v.y, z * v.z)
    operator fun div(num: Double): Vector = Vector(x / num, y / num, z / num)

    fun cross(v: Vector) = Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
    fun dot(v: Vector) = x * v.x + y * v.y + z * v.z
    fun normalize(): Vector = this * (1 / length())
    fun length(): Double = sqrt(x * x + y * y + z * z)
}