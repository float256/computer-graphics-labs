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
        val Red = Color(1.0, 0.0, 0.0)
        val Green = Color(0.0, 1.0, 0.0)
        val Blue = Color(0.0, 0.0, 1.0)
        val White = Color(1.0, 1.0, 1.0)
        val Black = Color(0.0, 0.0, 0.0)
    }
}