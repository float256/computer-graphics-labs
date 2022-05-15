package graphics.model

data class Face(
    val elements: List<FaceElement>
) {
    init {
        if (elements.size < 3) {
            throw IllegalArgumentException("Incorrect number of elements")
        }
    }
}