import window.GLFWInitializer
import window.MainWindow

fun main() {
    GLFWInitializer.init()
    val mainWindow = MainWindow(800, 600, "Main window")

    mainWindow.use { window ->
        window.run()
    }
}