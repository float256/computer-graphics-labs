package `object`

import primitives.Intersection
import primitives.Ray
import primitives.Vector

interface Material {
    fun computeReflection(ray: Ray, intersection: Intersection): Ray
    fun computeColor(color: Vector, intersection: Intersection): Vector
}