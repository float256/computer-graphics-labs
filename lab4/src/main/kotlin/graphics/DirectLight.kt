package graphics

import org.lwjgl.opengl.GL11.*
import primitive.RGBA
import primitive.Vector3D

class DirectLight(
    private val direction: Vector3D,
    private val diffuseIntensity: RGBA,
    private val ambientIntensity: RGBA,
    private val specularIntensity: RGBA
) {
    fun specifyLightParameters(lightType: Int) {
        glLightfv(lightType, GL_POSITION, vector3dToFloatArray(direction))
        glLightfv(lightType, GL_DIFFUSE, diffuseIntensity.toFloatArray())
        glLightfv(lightType, GL_AMBIENT, ambientIntensity.toFloatArray())
        glLightfv(lightType, GL_SPECULAR, specularIntensity.toFloatArray())
    }

    private fun vector3dToFloatArray(vector3D: Vector3D): FloatArray {
        val array = FloatArray(4)
        array[0] = vector3D.x.toFloat()
        array[1] = vector3D.y.toFloat()
        array[2] = vector3D.z.toFloat()
        array[3] = 0f
        return array
    }
}