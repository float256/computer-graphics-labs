package primitive

import org.joml.Vector3d

data class Point3D(
    val x: Double,
    val y: Double,
    val z: Double
) {
    fun toVector(): Vector3d {
        return Vector3d(x, y, z)
    }
}