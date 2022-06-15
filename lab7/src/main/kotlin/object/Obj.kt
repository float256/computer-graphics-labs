package `object`

import primitives.Ray
import primitives.Vector

interface Obj{
    val color: Vector
    val emission: Double
    val material: Material
    fun computeDistance(ray: Ray): Double
    fun normal(v: Vector): Vector
}