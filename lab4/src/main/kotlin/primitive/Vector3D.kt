package primitive

import kotlin.math.sqrt

data class Vector3D(
    val x: Double,
    val y: Double,
    val z: Double
) {
    val length: Double = sqrt(x * x + y * y + z * z)

    operator fun plus(vector3D: Vector3D): Vector3D = Vector3D(
        x + vector3D.x,
        y + vector3D.y,
        z + vector3D.z
    )

    operator fun minus(vector3D: Vector3D): Vector3D = Vector3D(
        x - vector3D.x,
        y - vector3D.y,
        z - vector3D.z
    )

    fun normalize(): Vector3D {
        return Vector3D(
            x / length,
            y / length,
            z / length
        )
    }

    companion object {
        fun cross(a: Vector3D, b: Vector3D): Vector3D = Vector3D(
            a.y * b.z - a.z * b.y,
            a.z * b.x - a.x * b.z,
            a.x * b.y - a.y * b.x
        )
    }
}