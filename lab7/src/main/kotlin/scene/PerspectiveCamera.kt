package scene

import common.Vector
import kotlin.math.PI
import kotlin.math.tan

class PerspectiveCamera(
    private val fov: Double,
    private val width: Int,
    private val height: Int
) : Camera {
    companion object {
        const val MinFOV = 0
        const val MaxFOV = 3 * PI / 2
    }

    init {
        if (fov < MinFOV || fov > MaxFOV) {
            throw IllegalArgumentException("Incorrect field of view")
        }
        if (width <= 0) {
            throw IllegalArgumentException("Incorrect width")
        }
        if (height <= 0) {
            throw IllegalArgumentException("Incorrect height")
        }
    }

    override fun transform(x: Int, y: Int): Vector {
        val fovX = fov
        val fovY = (height.toDouble() / width.toDouble()) * fov
        return Vector(
            ((2.0 * x - width) / width) * tan(fovX),
            -((2.0 * y - height) / height) * tan(fovY),
            -1.0
        )
    }
}