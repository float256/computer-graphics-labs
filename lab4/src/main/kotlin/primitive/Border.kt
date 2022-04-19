package primitive

data class Border(
    val color: RGB,
    val width: Double
) {
    init {
        if (width <= 0) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }
}