package window

import com.google.common.io.Resources
import graphics.Shader
import graphics.ShaderProgram
import graphics.ShaderType
import org.lwjgl.opengl.GL20.*
import primitive.Size

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val fragmentShaderProgram = readResourceAsText("shader/mandelbrot.fsh")
    private var program: ShaderProgram? = null

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
        // val vertexShader = Shader(program!!.id, vertexShaderProgram, ShaderType.Vertex)
        val fragmentShader = Shader(program!!.id, fragmentShaderProgram, ShaderType.Fragment)

        // vertexShader.createShader()
        fragmentShader.createShader()

        program!!.link()

        // vertexShader.close()
        fragmentShader.close()

        program!!.validate()
    }

    private fun setShaderUniformParameters(program: ShaderProgram) {
        glUniform2f(program.getUniformLocation("u_resolution"), width.toFloat(), height.toFloat())
        glUniform2f(program.getUniformLocation("u_zoomCenter"), 0f, 0f)
        glUniform1f(program.getUniformLocation("u_zoomSize"), 2f)
        glUniform1i(program.getUniformLocation("u_maxIterations"), 1000)
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