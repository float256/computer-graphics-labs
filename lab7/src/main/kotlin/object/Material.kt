package `object`

import common.Intersection
import common.Ray
import common.Vector

interface Material {
    fun computeReflection(ray: Ray, intersection: Intersection): Ray
    fun computeColor(nextColor: Vector, intersection: Intersection): Vector
}