package graphics

import org.lwjgl.opengl.GL20.*

class Shader(
    private val programId: Int,
    private val shaderCode: String,
    private val shaderType: ShaderType
): AutoCloseable {
    private var shaderId = 0

    fun createShader(): Int {
        val shaderId: Int = glCreateShader(shaderType.openglId)
        if (shaderId == 0) {
            throw Exception("Error creating shader. Type: $shaderType")
        }
        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)
        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024))
        }
        glAttachShader(programId, shaderId)
        return shaderId
    }

    override fun close() {
        if (shaderId != 0) {
            glDeleteShader(shaderId)
            glDetachShader(programId, shaderId)
        }
    }
}