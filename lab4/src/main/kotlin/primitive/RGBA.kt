package primitive

data class RGBA(
    val red: Double,
    val green: Double,
    val blue: Double,
    val alpha: Double,
) {
    init {
        if (!isParameterInCorrectBounds(red) ||
            !isParameterInCorrectBounds(green) ||
            !isParameterInCorrectBounds(blue) ||
            !isParameterInCorrectBounds(alpha)
        ) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }

    private fun isParameterInCorrectBounds(parameter: Double): Boolean {
        return (parameter >= 0) && (parameter <= 1)
    }

    fun toFloatArray(): FloatArray {
        val array = FloatArray(4)
        array[0] = red.toFloat()
        array[1] = red.toFloat()
        array[2] = red.toFloat()
        array[3] = red.toFloat()
        return array
    }
}