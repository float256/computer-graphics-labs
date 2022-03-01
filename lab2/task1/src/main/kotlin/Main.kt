import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import image.ImagePath
import image.ImageViewModel
import image.image
import menu.AppMenuViewModel
import menu.appMenu

@Composable
@Preview
fun App() {
    val imagePath = ImagePath()
    val appMenuViewModel = AppMenuViewModel(imagePath)
    val imageViewModel = ImageViewModel(imagePath)

    MaterialTheme {
        Scaffold(
            floatingActionButton = {
                appMenu(appMenuViewModel)
            }
        ) {
            image(imageViewModel)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
