import window.GLFWInitializer
import window.MainWindow

fun main() {
    GLFWInitializer.init()
    val window = MainWindow(500, 500, "Hello")
    window.run()
}