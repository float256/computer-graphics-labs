import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL


abstract class BaseWindow(
    width: Int,
    height: Int,
    title: String
) : AutoCloseable {
    private var window: Long = glfwCreateWindow(width, height, title, NULL, NULL)

    init {
        if (window == NULL) {
            throw RuntimeException("Failed to create window")
        }
    }

    protected abstract fun draw(frameBufferWidth: Int, frameBufferHeight: Int)

    fun run() {
        glfwMakeContextCurrent(window)
        while (!glfwWindowShouldClose(window)) {
            createCapabilities()
            val widthInBuffer = BufferUtils.createIntBuffer(1)
            val heightInBuffer = BufferUtils.createIntBuffer(1)
            glfwGetFramebufferSize(window, widthInBuffer, heightInBuffer)
            val width = widthInBuffer.get()
            val height = heightInBuffer.get()

            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            fixAspectRatio(width, height)
            draw(width, height)
            glfwSwapBuffers(window)
            glfwPollEvents()
        }
    }

    private fun fixAspectRatio(width: Int, height: Int) {
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()

        val aspect: Double = width.toDouble() / height.toDouble()
        if (width >= height) {
            glOrtho(-aspect, aspect, -1.0, 1.0, -1.0, 1.0)
        } else {
            glOrtho(-1.0, 1.0, -1 / aspect, 1 / aspect, -1.0, 1.0)
        }
    }

    final override fun close() {
        glfwDestroyWindow(window)
        window = NULL
    }
}