package window

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWFramebufferSizeCallback
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL


abstract class BaseWindow(
    val initialWidth: Int,
    val initialHeight: Int,
    title: String
) : AutoCloseable {
    var width = initialWidth
        private set
    var height = initialHeight
        private set
    var handle: Long = glfwCreateWindow(initialWidth, initialHeight, title, NULL, NULL)
        private set

    init {
        if (handle == NULL) {
            throw RuntimeException("Failed to create window")
        }

        val frameBufferSizeCallback = object : GLFWFramebufferSizeCallback() {
            override fun invoke(handle: Long, width: Int, height: Int) {
                if (this@BaseWindow.handle == handle) {
                    this@BaseWindow.width = width
                    this@BaseWindow.height = height
                }
            }
        }
        glfwSetFramebufferSizeCallback(handle, frameBufferSizeCallback)
    }

    protected abstract fun onDraw(frameBufferWidth: Int, frameBufferHeight: Int)
    protected abstract fun setupCamera()
    protected abstract fun onInit()

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

    fun run() {
        glfwMakeContextCurrent(handle)
        createCapabilities()
        onInit()
        while (!glfwWindowShouldClose(handle)) {
            val widthInBuffer = BufferUtils.createIntBuffer(1)
            val heightInBuffer = BufferUtils.createIntBuffer(1)
            glfwGetFramebufferSize(handle, widthInBuffer, heightInBuffer)
            val frameBufferWidth = widthInBuffer.get()
            val frameBufferHeight = heightInBuffer.get()

            glClearColor(1f, 1f, 1f, 1f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

            setupCamera()

            onDraw(frameBufferWidth, frameBufferHeight)
            fixAspectRatio(frameBufferWidth, frameBufferHeight)

            glViewport(0, 0, frameBufferWidth, frameBufferHeight)
            glfwSwapBuffers(handle)
            glfwPollEvents()
        }
    }

    final override fun close() {
        glfwDestroyWindow(handle)
        handle = NULL
    }
}