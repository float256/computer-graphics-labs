package window

import org.lwjgl.glfw.GLFW.glfwInit


object GLFWInitializer {
    fun init() {
        if (!glfwInit())
        {
            throw RuntimeException("Failed to initialize GLFW")
        }
    }
}