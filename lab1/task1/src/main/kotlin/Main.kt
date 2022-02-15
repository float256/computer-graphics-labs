import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun app() {
    MaterialTheme {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(Color.LightGray, Offset.Zero, Size(50f, 150f))
            drawRect(Color.LightGray, Offset(50f, 50f), Size(50f, 50f))
            drawRect(Color.LightGray, Offset(100f, 0f), Size(50f, 150f))

            drawRect(Color.LightGray, Offset(200f, 0f), Size(50f, 100f))
            drawRect(Color.LightGray, Offset(250f, 50f), Size(50f, 50f))
            drawRect(Color.LightGray, Offset(300f, 0f), Size(50f, 150f))
            
            drawRect(Color.LightGray, Offset(400f, 0f), Size(50f, 150f))
            drawRect(Color.LightGray, Offset(450f, 0f), Size(50f, 25f))
            drawRect(Color.LightGray, Offset(450f, 75f), Size(50f, 25f))
            drawRect(Color.LightGray, Offset(500f, 0f), Size(50f, 150f))
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}
