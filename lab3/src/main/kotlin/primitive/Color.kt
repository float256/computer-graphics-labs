package primitive

data class Color(
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
        val White = Color(1.0, 1.0, 1.0)
        val Black = Color(0.0, 0.0, 0.0)
        val Yellow = Color(231 / 255.0, 192 / 255.0, 11 / 255.0)
        val LightGray = Color(217 / 255.0, 216 / 255.0, 221 / 255.0)
        val DarkGray = Color(101 / 255.0, 106 / 255.0, 118 / 255.0)
        val DarkGreen = Color(28 / 255.0, 147 / 255.0, 27 / 255.0)
        val LightGreen = Color(181 / 255.0, 207 / 255.0, 75 / 255.0)
        val Red = Color(201 / 255.0, 14 / 255.0, 13 / 255.0)
    }
}