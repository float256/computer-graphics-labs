package `object`

import primitives.Constants
import primitives.Ray
import primitives.Vector
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
        val c: Double = (ray.origin - center).dot(ray.origin - color) - radius * radius
        var discriminant = b * b - 4 * c
        if (discriminant < 0) {
            return 0.0
        } else {
            discriminant = sqrt(discriminant)
        }

        val firstSolution = -b + discriminant
        val secondSolution = -b - discriminant

        return if (secondSolution > Constants.Epsilon) {
            secondSolution / 2
        } else {
            if (firstSolution > Constants.Epsilon) {
                firstSolution / 2
            } else {
                0.0
            }
        }
    }

    override fun normal(v: Vector): Vector {
        return (v - center).normalize()
    }
}