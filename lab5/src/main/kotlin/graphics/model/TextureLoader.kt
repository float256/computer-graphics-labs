package graphics.model

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load
import java.nio.ByteBuffer
import java.nio.IntBuffer

class TextureLoader {
    fun load(path: String): Int {
        val texID = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, texID)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

        val width: IntBuffer = BufferUtils.createIntBuffer(1)
        val height: IntBuffer = BufferUtils.createIntBuffer(1)
        val channels: IntBuffer = BufferUtils.createIntBuffer(1)
        val image: ByteBuffer? = stbi_load(path, width, height, channels, 0)

        if (image != null) {
            if (channels.get(0) == 3) {
                glTexImage2D(
                    GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0),
                    0, GL_RGB, GL_UNSIGNED_BYTE, image
                )
            } else if (channels.get(0) == 4) {
                glTexImage2D(
                    GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0),
                    0, GL_RGBA, GL_UNSIGNED_BYTE, image
                )
            } else {
                throw IllegalArgumentException("Error: (Texture) Unknown number of channesl '" + channels.get(0) + "'")
            }
        } else {
            throw IllegalArgumentException("Error: (Texture) Could not load image '$path'")
        }

        stbi_image_free(image)
        return texID
    }
}