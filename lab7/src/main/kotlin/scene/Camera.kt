package scene

import common.Vector

interface Camera {
    fun transform(x: Int, y: Int): Vector
}