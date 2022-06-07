package window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWKeyCallback

class KeyboardEventHandler(
    private val window: BaseWindow
) {
    fun doOnKeyPressed(callback: (key: Int) -> Unit) {
        val glfwCallback = object : GLFWKeyCallback() {
            override fun invoke(handle: Long, key: Int, scancode: Int, action: Int, mods: Int) {
                if ((handle == window.handle) && (action != GLFW_RELEASE)) {
                    callback(key)
                }
            }
        }
        glfwSetKeyCallback(window.handle, glfwCallback)
    }
}