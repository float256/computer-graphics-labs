package window

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWFramebufferSizeCallback
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.opengl.GL11.GL_DEPTH_TEST


abstract class BaseWindow(
    width: Int,
    height: Int,
    title: String
) : AutoCloseable {
    var width = width
        private set
    var height = height
        private set
    var handle: Long = glfwCreateWindow(width, height, title, NULL, NULL)
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

    protected abstract fun draw(frameBufferWidth: Int, frameBufferHeight: Int)

    fun run() {
        glfwMakeContextCurrent(handle)
        createCapabilities()
        glEnable(GL_DEPTH_TEST)
        glDepthMask(true)
        glDepthFunc(GL_LEQUAL)
        glDepthRange(0.0, 1.0)
        while (!glfwWindowShouldClose(handle)) {
            val widthInBuffer = BufferUtils.createIntBuffer(1)
            val heightInBuffer = BufferUtils.createIntBuffer(1)
            glfwGetFramebufferSize(handle, widthInBuffer, heightInBuffer)
            val frameBufferWidth = widthInBuffer.get()
            val frameBufferHeight = heightInBuffer.get()

            glClearColor(1f, 1f, 1f, 1f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            glClearDepth(1.0)

            draw(frameBufferWidth, frameBufferHeight)
            fixAspectRatio(frameBufferWidth, frameBufferHeight)

            glViewport(0, 0, frameBufferWidth, frameBufferHeight)
            glfwSwapBuffers(handle)
            glfwPollEvents()
        }
    }

    fun isBackground(xInPixels: Int, yInPixels: Int): Boolean {
        val yInPixelsFromBottomLeft = width - yInPixels

        val depthInBuffer = BufferUtils.createFloatBuffer(1)
        glReadPixels(xInPixels, yInPixelsFromBottomLeft, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, depthInBuffer)
        val depth = depthInBuffer.get()

        return (depth == 1f)
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
        glfwDestroyWindow(handle)
        handle = NULL
    }
}