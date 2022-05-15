package window

import org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback
import org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback
import org.lwjgl.glfw.GLFWCursorEnterCallback
import org.lwjgl.glfw.GLFWCursorPosCallback
import primitive.Point2D

class MouseMoveEventHandler(
    val window: BaseWindow
) {
    private var previousPosition: Point2D? = null

    init {
        val glfwCallback = object : GLFWCursorEnterCallback() {
            override fun invoke(handle: Long, entered: Boolean) {
                if (!entered && (window.handle == handle)) {
                    previousPosition = null
                }
            }
        }
        glfwSetCursorEnterCallback(window.handle, glfwCallback)
    }

    fun doOnMouseMove(callback: (offset: Point2D) -> Unit) {
        val glfwCallback = object : GLFWCursorPosCallback() {
            override fun invoke(handle: Long, xpos: Double, ypos: Double) {
                if (handle == window.handle) {
                    val newPosition = Point2D(xpos, ypos)
                    previousPosition = previousPosition ?: newPosition
                    callback(
                        Point2D(
                            newPosition.x - previousPosition!!.x,
                            newPosition.y - previousPosition!!.y
                        )
                    )
                    previousPosition = newPosition
                }
            }
        }
        glfwSetCursorPosCallback(window.handle, glfwCallback)
    }
}