package window

import org.lwjgl.opengl.GL11.*
import primitive.Angle
import primitive.Vector3D
import kotlin.math.PI

class Camera(
    private val window: BaseWindow
) {
    private var sumXAngle: Angle = Angle(0.0)
    private var sumYAngle: Angle = Angle(0.0)

    fun addAngle(deltaX: Double, deltaY: Double) {
        val widthToInitialValueRatio = window.width / window.initialWidth
        val heightToInitialValueRatio = window.height / window.initialHeight
        val xAngle = Angle(deltaY * PI / window.height * heightToInitialValueRatio)
        val yAngle = Angle(deltaX * PI / window.width * widthToInitialValueRatio)

        sumXAngle = Angle(sumXAngle.radianValue + xAngle.radianValue)
        sumYAngle = Angle(sumYAngle.radianValue + yAngle.radianValue)
    }

    fun rotate() {
        val modelViewMatrix = DoubleArray(16)
        glGetDoublev(GL_MODELVIEW_MATRIX, modelViewMatrix)

        val xAxis = Vector3D(modelViewMatrix[0], modelViewMatrix[4], modelViewMatrix[8])
        val yAxis = Vector3D(modelViewMatrix[1], modelViewMatrix[5], modelViewMatrix[9])

        glRotated(sumXAngle.degreeValue, xAxis.x, xAxis.y, xAxis.z)
        glRotated(sumYAngle.degreeValue, yAxis.x, yAxis.y, yAxis.z)
    }
}