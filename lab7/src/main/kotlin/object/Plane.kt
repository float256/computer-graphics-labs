package `object`

import common.Ray
import common.Vector

class Plane(
    private val distance: Double,
    private val normal: Vector,
    override val color: Vector,
    override val emission: Double,
    override val material: Material
) : Obj {
    override fun computeDistance(ray: Ray): Double {
        val dotProduct = normal.dot(ray.direction)
        return if (dotProduct != 0.0) {
            val result = -1 * ((normal.dot(ray.origin) + distance) / dotProduct)
            if (result > 0) {
                result
            } else {
                Double.MAX_VALUE
            }
        } else {
            Double.MAX_VALUE
        }
    }

    override fun normal(v: Vector): Vector {
        return normal
    }
}