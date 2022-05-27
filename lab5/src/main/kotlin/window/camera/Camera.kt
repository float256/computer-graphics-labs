package window.camera

import org.joml.Matrix4d
import org.joml.Matrix4dc
import org.joml.Vector3d

class Camera(
    private val distanceToOrigin: Double
) {
    private val cameraMatrix: Matrix4d = Matrix4d()
        .lookAt(
            Vector3d(0.0, 0.0, 1.0),
            Vector3d(0.0, 0.0, 0.0),
            Vector3d(0.0, 1.0, 0.0),
        )
        .scale(distanceToOrigin)

    fun rotate(xAngle: Double, yAngle: Double) {
        val xAxis = Vector3d(
            cameraMatrix.get(0, 0),
            cameraMatrix.get(1, 0),
            cameraMatrix.get(2, 0)
        )
        val yAxis = Vector3d(
            cameraMatrix.get(0, 1),
            cameraMatrix.get(1, 1),
            cameraMatrix.get(2, 1)
        )
        cameraMatrix
            .rotate(xAngle, xAxis)
            .rotate(yAngle, yAxis)
            .normalize3x3()
            .scale(distanceToOrigin)
    }

    fun getCameraMatrix(): Matrix4dc {
        return cameraMatrix
    }
}
