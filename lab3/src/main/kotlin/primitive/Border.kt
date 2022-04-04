package primitive

data class Border(
    val color: Color,
    val width: Double
) {
    init {
        if (width <= 0) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }
}