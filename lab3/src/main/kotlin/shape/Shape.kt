package shape

import primitive.Border
import primitive.Color
import primitive.Point
import primitive.Size
import window.BaseWindow

interface Shape {
    var center: Point
    val size: Size
    val border: Border?
    val fillColor: Color?
    val window: BaseWindow

    fun draw()
}