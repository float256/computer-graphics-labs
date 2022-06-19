package file

import common.Vector
import java.io.File
import kotlin.math.min

class PPMImageSaver {
    fun save(image: Array<Array<Vector>>, path: String) {
        val height = image.size
        val width = image[0].size

        File(path).printWriter().use {writer ->
            writer.println("P3")
            writer.println("$width $height")
            writer.println("255")
            for (colIndex in (0 until height)) {
                for (rowIndex in (0 until width)) {
                    val color = image[colIndex][rowIndex]
                    val normalizedRedComponent = min(color.x.toInt(), 255)
                    val normalizedGreenComponent = min(color.y.toInt(), 255)
                    val normalizedBlueComponent = min(color.z.toInt(), 255)
                    writer.println("$normalizedRedComponent $normalizedGreenComponent $normalizedBlueComponent")
                }
            }
        }
    }
}