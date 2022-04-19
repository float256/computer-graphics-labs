package window

import figure.Octahedron
import org.lwjgl.opengl.GL11.*

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val figure = Octahedron()

    override fun onDraw(frameBufferWidth: Int, frameBufferHeight: Int) {
        figure.draw()
    }
}