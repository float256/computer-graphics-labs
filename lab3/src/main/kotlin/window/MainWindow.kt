package window

import primitive.Border
import primitive.Color
import primitive.Point
import primitive.Size
import shape.Ellipse
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
            center = Point(0.0, 0.0),
            size = Size(0.7, 0.7),
            border = Border(Color.Red, 0.2),
            direction = Triangle.Direction.Right,
            window = this
        ),
        Ellipse(
            center = Point(0.3, 0.5),
            size = Size(0.2, 0.1),
            fillColor = Color.Blue,
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