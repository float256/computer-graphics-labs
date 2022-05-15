package window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWFramebufferSizeCallback
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import primitive.Size

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

    protected abstract fun onDraw(frameBufferSize: Size<Int>)
    protected abstract fun setupCamera()
    protected abstract fun onInit()

    private fun fixAspectRatio(frameBufferSize: Size<Int>) {
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()

        val width = frameBufferSize.width
        val height = frameBufferSize.height

        val aspect: Double = width.toDouble() / height.toDouble()
        if (width >= height) {
            glOrtho(-aspect, aspect, -1.0, 1.0, -1.0, 100.0)
        } else {
            glOrtho(-1.0, 1.0, -1 / aspect, 1 / aspect, -1.0, 100.0)
        }
    }

    fun run() {
        glfwMakeContextCurrent(handle)
        createCapabilities()
        onInit()
        while (!glfwWindowShouldClose(handle)) {
            glClearColor(0f, 0f, 0f, 0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)


            val frameBufferSize = getFrameBufferSize()

            fixAspectRatio(frameBufferSize)
            onDraw(frameBufferSize)
            setupCamera()

            glViewport(0, 0, frameBufferSize.width, frameBufferSize.height)
            glfwSwapBuffers(handle)
            glfwPollEvents()
        }
    }

    private fun getFrameBufferSize(): Size<Int> {
        val frameBufferWidth: Int?
        val frameBufferHeight: Int?
        stackPush().use { memoryStack ->
            val widthInBuffer = memoryStack.mallocInt(1)
            val heightInBuffer = memoryStack.mallocInt(1)
            glfwGetFramebufferSize(handle, widthInBuffer, heightInBuffer)
            frameBufferWidth = widthInBuffer.get()
            frameBufferHeight = heightInBuffer.get()
        }
        return Size(frameBufferWidth!!, frameBufferHeight!!)
    }

    final override fun close() {
        glfwDestroyWindow(handle)
        handle = NULL
    }
}