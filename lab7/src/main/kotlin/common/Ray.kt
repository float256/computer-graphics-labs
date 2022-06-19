package common

class Ray(
    val origin: Vector,
    direction: Vector
) {
    val direction = direction.normalize()
    override fun toString(): String = "origin: $origin, direction: $direction"
}