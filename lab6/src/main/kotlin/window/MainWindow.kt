package window

import com.google.common.io.Resources
import graphics.Shader
import graphics.ShaderProgram
import graphics.ShaderType
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL20.*
import primitive.Size

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val keyboardEventHandler = KeyboardEventHandler(this)
    private val fragmentShaderProgram = readResourceAsText("shader/mandelbrot.fsh")
    private var program: ShaderProgram? = null
    private var position = Position(0.0, 0.0, 2.0)

    companion object {
        const val MoveSpeed = 0.02
        const val ScaleSpeed = 1.02
        const val MaxIterations = 1000
    }

    init {
        keyboardEventHandler.doOnKeyPressed {
            when (it) {
                GLFW_KEY_W -> position.centerY += MoveSpeed * position.displayedRegionSize
                GLFW_KEY_S -> position.centerY -= MoveSpeed * position.displayedRegionSize
                GLFW_KEY_A -> position.centerX -= MoveSpeed * position.displayedRegionSize
                GLFW_KEY_D -> position.centerX += MoveSpeed * position.displayedRegionSize
                GLFW_KEY_PAGE_UP -> position.displayedRegionSize /= ScaleSpeed
                GLFW_KEY_PAGE_DOWN -> position.displayedRegionSize *= ScaleSpeed
            }
        }
    }

    override fun onDraw(frameBufferSize: Size<Int>) {
        program!!.drawWithShaders {
            setShaderUniformParameters(program!!)
            drawPlane()
        }
    }

    override fun setupCamera() {}

    override fun onInit() {
        program = ShaderProgram()
        setupShaders()
    }

    private fun readResourceAsText(resourcePath: String): String {
        return Resources
            .getResource(resourcePath)
            .openStream()
            .bufferedReader()
            .use { it.readText() }
    }

    override fun close() {
        super.close()
        program!!.close()
    }

    private fun setupShaders() {
        val fragmentShader = Shader(program!!.id, fragmentShaderProgram, ShaderType.Fragment)
        fragmentShader.createShader()
        program!!.link()
        fragmentShader.close()
        program!!.validate()
    }

    private fun setShaderUniformParameters(program: ShaderProgram) {
        glUniform2f(program.getUniformLocation("resolution"), width.toFloat(), height.toFloat())
        glUniform2f(program.getUniformLocation("zoomCenter"), position.centerX.toFloat(), position.centerY.toFloat())
        glUniform1f(program.getUniformLocation("displayedRegionSize"), position.displayedRegionSize.toFloat())
        glUniform1i(program.getUniformLocation("maxIterations"), MaxIterations)
    }

    private fun drawPlane() {
        glBegin(GL_QUADS)
        glTexCoord2d(0.0, 0.0)
        glVertex2d(-1.0, -1.0)

        glTexCoord2d(4.0, 0.0)
        glVertex2d(1.0, -1.0)

        glTexCoord2d(4.0, 4.0)
        glVertex2d(1.0, 1.0)

        glTexCoord2d(0.0, 4.0)
        glVertex2d(-1.0, 1.0)
        glEnd()
    }
}