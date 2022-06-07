package primitive

data class Size<T: Number>(
    val width: T,
    val height: T
) {
    init {
        if (width.toDouble() <= 0 || height.toDouble() <= 0) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }
}