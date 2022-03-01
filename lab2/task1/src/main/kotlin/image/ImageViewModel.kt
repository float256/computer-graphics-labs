package image

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

class ImageViewModel(
    imagePath: ImagePath
) {
    private var pathState = mutableStateOf<String?>(null)
    private var imagePositionState = mutableStateOf(Offset.Zero)

    val path: String?
        get() = pathState.value
    val imagePosition: Offset
        get() = imagePositionState.value

    init {
        imagePath.doOnPathChanged { newPath ->
            pathState.value = newPath
            imagePositionState.value = Offset.Zero
        }
    }

    fun move(offset: Offset) {
        val previousPosition = imagePositionState.value
        imagePositionState.value = previousPosition + offset
    }
}