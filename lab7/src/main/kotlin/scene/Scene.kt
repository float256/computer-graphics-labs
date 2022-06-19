package scene

import `object`.Obj
import common.Intersection
import common.Ray

class Scene(
    private val objects: List<Obj>
) {
    fun intersect(ray: Ray): Intersection? {
        var closestIntersection: Intersection? = null
        objects.forEach { obj ->
            val distance = obj.computeDistance(ray)
            if (isClosestIntersection(closestIntersection, distance)) {
                closestIntersection = Intersection(distance, obj)
            }
        }
        return closestIntersection
    }

    private fun isClosestIntersection(prevClosestIntersection: Intersection?, distance: Double): Boolean {
        return (distance != Double.MAX_VALUE) && ((prevClosestIntersection == null) ||
                (prevClosestIntersection.distance > distance))
    }
}