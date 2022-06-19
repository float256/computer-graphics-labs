package `object`

import common.Intersection
import common.Ray
import common.Vector

class SpecularMaterial: Material {
    override fun computeReflection(ray: Ray, intersection: Intersection): Ray {
        val hitPoint = ray.origin + ray.direction * intersection.distance
        val normal = intersection.obj.normal(hitPoint)
        val cost: Double = ray.direction.dot(normal)
        val rayDirection = (ray.direction - normal * (cost * 2)).normalize()
        return Ray(hitPoint, rayDirection)
    }

    override fun computeColor(nextColor: Vector, intersection: Intersection): Vector {
        return nextColor
    }
}