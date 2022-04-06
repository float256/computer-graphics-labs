package primitive

import kotlin.math.PI

data class Angle(
    val value: Double
) {
    init {
        if (!isCorrectAngle(value)) {
            throw IllegalArgumentException("Incorrect angle value")
        }
    }

    private fun isCorrectAngle(angle: Double): Boolean {
        return (angle >= 0) && (angle <= 2 * PI)
    }
}