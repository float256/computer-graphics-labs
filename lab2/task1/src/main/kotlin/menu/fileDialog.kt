package menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame

@Composable
fun fileDialog(
    parent: Frame? = null,
    visible: Boolean = true,
    onFileSelected: (result: String?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    val path: String? = if ((directory != null) && (file != null)) {
                        directory + file
                    } else {
                        null
                    }
                    onFileSelected(path)
                }
            }
        }
    },
    visible = visible,
    dispose = {
        it.dispose()
    }
)