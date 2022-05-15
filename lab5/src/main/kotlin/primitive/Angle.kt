package primitive

import kotlin.math.PI

data class Angle(
    val radianValue: Double
) {
    val degreeValue: Double = radianValue / (2 * PI) * 360
}