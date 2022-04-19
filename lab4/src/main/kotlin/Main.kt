import primitive.Angle
import window.GLFWInitializer
import window.MainWindow
import window.MouseMove
import kotlin.math.PI

fun main() {
    GLFWInitializer.init()
    val mainWindow = MainWindow(500, 500, "Main window")

    mainWindow.use { window ->
        window.run()
    }
}