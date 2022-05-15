package primitive

data class RGB(
    val red: Double,
    val green: Double,
    val blue: Double
) {
    init {
        if (!isParameterInCorrectBounds(red) ||
            !isParameterInCorrectBounds(green) ||
            !isParameterInCorrectBounds(blue)
        ) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }

    private fun isParameterInCorrectBounds(parameter: Double): Boolean {
        return (parameter >= 0) && (parameter <= 1)
    }

    companion object {
        val White = RGB(1.0, 1.0, 1.0)
        val Black = RGB(0.0, 0.0, 0.0)
        val Yellow = RGB(231 / 255.0, 192 / 255.0, 11 / 255.0)
        val LightGray = RGB(217 / 255.0, 216 / 255.0, 221 / 255.0)
        val DarkGray = RGB(101 / 255.0, 106 / 255.0, 118 / 255.0)
        val DarkGreen = RGB(28 / 255.0, 147 / 255.0, 27 / 255.0)
        val LightGreen = RGB(181 / 255.0, 207 / 255.0, 75 / 255.0)
        val Red = RGB(201 / 255.0, 14 / 255.0, 13 / 255.0)
    }
}