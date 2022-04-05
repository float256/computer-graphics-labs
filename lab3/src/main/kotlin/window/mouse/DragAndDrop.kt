package window.mouse

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWMouseButtonCallback
import primitive.Point
import window.BaseWindow

class DragAndDrop(
    val window: BaseWindow
) {
    private var isLeftMouseDown = false
    private var previousPosition: Point? = null

    init {
        val glfwMouseClickCallback = object : GLFWMouseButtonCallback() {
            override fun invoke(handle: Long, button: Int, action: Int, mods: Int) {
                val mousePositionXInBuffer = BufferUtils.createDoubleBuffer(1)
                val mousePositionYInBuffer = BufferUtils.createDoubleBuffer(1)
                glfwGetCursorPos(window.handle, mousePositionXInBuffer, mousePositionYInBuffer)

                val mouseX = mousePositionXInBuffer.get().toInt()
                val mouseY = mousePositionYInBuffer.get().toInt()

                previousPosition = positionToRelativeCoordinates(Point(mouseX.toDouble(), mouseY.toDouble()))
                isLeftMouseDown = (handle == window.handle) && !window.isBackground(mouseX, mouseY)
                        && (button == GLFW_MOUSE_BUTTON_LEFT)
                        && (action == GLFW_PRESS)
            }
        }
        glfwSetMouseButtonCallback(window.handle, glfwMouseClickCallback)
    }

    fun doOnDrag(callback: (offset: Point) -> Unit) {
        val glfwCallback = object : GLFWCursorPosCallback() {
            override fun invoke(handle: Long, xpos: Double, ypos: Double) {
                if (isLeftMouseDown && (handle == window.handle)) {
                    val xPosInRelativeCoordinates = 2 * xpos / window.width - 1
                    val yPosInRelativeCoordinates = - 2 * ypos / window.height + 1
                    val newPosition = Point(xPosInRelativeCoordinates, yPosInRelativeCoordinates)

                    previousPosition = previousPosition ?: newPosition
                    callback(
                        Point(
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

    private fun positionToRelativeCoordinates(positionInAbsoluteCoordinates: Point): Point {
        val xPosInRelativeCoordinates = 2 * positionInAbsoluteCoordinates.x / window.width - 1
        val yPosInRelativeCoordinates = - 2 * positionInAbsoluteCoordinates.y / window.height + 1
        return Point(xPosInRelativeCoordinates, yPosInRelativeCoordinates)
    }
}