package menu

import androidx.compose.runtime.mutableStateOf
import image.ImagePath

class AppMenuViewModel(
    private var imagePath: ImagePath
) {
    private val isFileDialogOpen = mutableStateOf(false)

    fun onSelectImageButtonClicked() {
        isFileDialogOpen.value = true
    }

    fun onFileSelected(pathToFile: String?) {
        isFileDialogOpen.value = false
        imagePath.path = pathToFile
    }

    fun isFileDialogOpen(): Boolean {
        return isFileDialogOpen.value
    }
}