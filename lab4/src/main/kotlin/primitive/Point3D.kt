package primitive

data class Point3D(
    val x: Double,
    val y: Double,
    val z: Double
) {
    fun toVector(): Vector3D {
        return Vector3D(x, y, z)
    }
}