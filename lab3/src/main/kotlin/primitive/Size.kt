package primitive

data class Size(
    val width: Double,
    val height: Double
) {
    init {
        if (width <=0 || height <= 0) {
            throw IllegalArgumentException("Incorrect parameters")
        }
    }
}