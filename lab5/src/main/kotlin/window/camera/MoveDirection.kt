package window.camera

import org.joml.Vector3d
import org.joml.Vector3dc

enum class MoveDirection(
    val moveVector3d: Vector3dc
) {
    Forward(Vector3d(0.0, 0.0, 1.0)),
    Backward(Vector3d(0.0, 0.0, 1.0)),
    Left(Vector3d(0.0, 0.0, 0.0)),
    Right(Vector3d(0.0, 0.0, 0.0)),
    Up(Vector3d(0.0, 0.0, 0.0)),
    Down(Vector3d(0.0, 0.0, 0.0))
}