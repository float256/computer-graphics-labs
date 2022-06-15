package scene

import primitives.Vector

interface Camera {
    fun transform(x: Int, y: Int): Vector
}