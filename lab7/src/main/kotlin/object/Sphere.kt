package `object`

import common.Ray
import common.Vector
import kotlin.math.sqrt

class Sphere(
    private val center: Vector,
    private val radius: Double,
    override val color: Vector,
    override val emission: Double,
    override val material: Material,
) : Obj {
    override fun computeDistance(ray: Ray): Double {
        val b: Double = ((ray.origin - center) * 2.0).dot(ray.direction)
        val c: Double = (ray.origin - center).dot(ray.origin - center) - radius * radius
        var discriminant = b * b - 4 * c
        if (discriminant < 0) {
            return Double.MAX_VALUE
        } else {
            discriminant = sqrt(discriminant)
        }

        val firstSolution = (-b - discriminant) / 2
        val secondSolution = (-b + discriminant) / 2

        return if (firstSolution > 0) {
            firstSolution
        } else {
            if (secondSolution > 0) {
                secondSolution
            } else {
                Double.MAX_VALUE
            }
        }
    }

    override fun normal(v: Vector): Vector {
        return (v - center).normalize()
    }
}