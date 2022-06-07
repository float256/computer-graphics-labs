package graphics

import org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER
import org.lwjgl.opengl.GL20.GL_VERTEX_SHADER

enum class ShaderType(
    val openglId: Int
) {
    Vertex(GL_VERTEX_SHADER),
    Fragment(GL_FRAGMENT_SHADER);
}