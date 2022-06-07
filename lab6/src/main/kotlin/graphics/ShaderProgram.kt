package graphics

import org.lwjgl.opengl.GL20.*

class ShaderProgram : AutoCloseable {
    val id = glCreateProgram()

    init {
        if (id == 0) {
            throw Exception("Could not create Shader")
        }
        glUseProgram(id)
    }

    fun link() {
        glLinkProgram(id)
        if (glGetProgrami(id, GL_LINK_STATUS) == 0) {
            throw Exception("Error linking Shader code: " + glGetProgramInfoLog(id, 1024))
        }
    }

    fun validate() {
        glValidateProgram(id)
        if (glGetProgrami(id, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(id, 1024))
        }
    }

    fun getUniformLocation(name: String): Int {
        return glGetUniformLocation(id, name)
    }

    fun drawWithShaders(function: () -> Unit) {
        glUseProgram(id)
        function()
        glUseProgram(0)
    }

    override fun close() {
        glUseProgram(0)
        if (id != 0) {
            glDeleteProgram(id)
        }
    }
}