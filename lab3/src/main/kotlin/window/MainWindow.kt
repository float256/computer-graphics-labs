package window

import primitive.Border
import primitive.Color
import primitive.Point
import primitive.Size
import shape.Shape
import shape.Triangle
import window.mouse.DragAndDrop

class MainWindow(
    width: Int,
    height: Int,
    title: String
): BaseWindow(width, height, title) {
    private val shapes = listOf(
        Triangle(
            Point(0.0, 0.0),
            Size(0.7, 0.7),
            Border(Color.Red, 0.2),
            direction = Triangle.Direction.Right,
            window = this
        )
    )

    init {
        DragAndDrop(this).doOnDrag { offset ->
            shapes.forEach { shape ->
                shape.center = Point(
                    shape.center.x + offset.x,
                    shape.center.y + offset.y
                )
            }
        }
    }

    override fun draw(frameBufferWidth: Int, frameBufferHeight: Int) {
        shapes.forEach(Shape::draw)
    }
}