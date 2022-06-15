package primitives

class Ray(
    val origin: Vector,
    direction: Vector
) {
    val direction = direction.normalize()
}