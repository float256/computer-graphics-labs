package `object`

import primitives.Constants
import primitives.Ray
import primitives.Vector

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
            val t = -1 * (((normal.dot(ray.origin)) + distance) / dotProduct)
            return if (t > Constants.Epsilon) t else 0.0
        } else {
            0.0
        }
    }

    override fun normal(v: Vector): Vector {
        return normal
    }
}