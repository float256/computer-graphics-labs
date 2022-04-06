package window

import primitive.*
import shape.Ellipse
import shape.Rectangle
import shape.Shape
import window.mouse.DragAndDrop
import kotlin.math.PI

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val shapes = listOf(
        // Ears
        Ellipse(
            center = Point(0.5, 0.5),
            size = Size(0.1, 0.1),
            border = Border(Color.DarkGray, 0.03),
            fillColor = Color.LightGreen,
            window = this
        ),
        Ellipse(
            center = Point(-0.5, 0.5),
            size = Size(0.1, 0.1),
            border = Border(Color.DarkGray, 0.03),
            fillColor = Color.Red,
            window = this
        ),

        // Left hand
        Ellipse(
            center = Point(-0.71, -0.34),
            size = Size(0.1, 0.1),
            border = Border(Color.DarkGray, 0.025),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(-0.81, -0.4),
            size = Size(0.06, 0.06),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(-0.91, -0.435),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(-0.91, -0.34),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(-0.84, -0.5),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),

        // Right hand
        Ellipse(
            center = Point(0.71, -0.34),
            size = Size(0.1, 0.1),
            border = Border(Color.DarkGray, 0.025),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(0.81, -0.4),
            size = Size(0.06, 0.06),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(0.91, -0.435),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(0.91, -0.34),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),
        Ellipse(
            center = Point(0.84, -0.5),
            size = Size(0.03, 0.03),
            border = Border(Color.DarkGray, 0.02),
            fillColor = Color.LightGray,
            window = this
        ),

        // Body
        Ellipse(
            center = Point(0.0, 0.0),
            size = Size(0.7, 0.7),
            border = Border(Color.DarkGray, 0.05),
            fillColor = Color.Yellow,
            window = this
        ),

        // Top part
        Ellipse(
            center = Point(0.0, 0.0),
            size = Size(0.7, 0.7),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(0.05 * PI),
            endAngle = Angle(0.95 * PI),
            window = this
        ),
        Ellipse(
            center = Point(0.0, 0.47),
            size = Size(0.3, 0.235),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.045),
            startAngle = Angle(0.0),
            endAngle = Angle(PI),
            window = this
        ),
        Ellipse(
            center = Point(0.0, 0.21),
            size = Size(0.66, 0.25),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(0.0),
            endAngle = Angle(PI),
            window = this
        ),
        Ellipse(
            center = Point(0.0, 0.0),
            size = Size(0.7, 0.25),
            fillColor = Color.Yellow,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(0.0),
            endAngle = Angle(PI),
            window = this
        ),
        Ellipse(
            center = Point(0.0, 0.38),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),
        Ellipse(
            center = Point(0.4, 0.33),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),
        Ellipse(
            center = Point(-0.4, 0.33),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),

        // Left eye
        Ellipse(
            center = Point(-0.24, 0.0),
            size = Size(0.22, 0.2),
            fillColor = Color.White,
            border = Border(Color.DarkGray, 0.05),
            window = this
        ),
        Ellipse(
            center = Point(-0.2, -0.01),
            size = Size(0.18, 0.18),
            fillColor = Color.DarkGreen,
            window = this
        ),
        Ellipse(
            center = Point(-0.14, -0.01),
            size = Size(0.12, 0.1),
            fillColor = Color.White,
            window = this
        ),
        Ellipse(
            center = Point(-0.131, 0.0),
            size = Size(0.05, 0.07),
            fillColor = Color.Black,
            window = this
        ),
        Ellipse(
            center = Point(-0.24, 0.0),
            size = Size(0.22, 0.2),
            fillColor = Color.White,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(0.2 * PI),
            endAngle = Angle(0.8 * PI),
            window = this
        ),
        Rectangle(
            center = Point(-0.24, 0.13),
            size = Size(0.4, 0.04),
            fillColor = Color.DarkGray,
            window = this
        ),

        // Right eye
        Ellipse(
            center = Point(0.24, 0.0),
            size = Size(0.22, 0.2),
            fillColor = Color.White,
            border = Border(Color.DarkGray, 0.05),
            window = this
        ),
        Ellipse(
            center = Point(0.2, -0.01),
            size = Size(0.18, 0.18),
            fillColor = Color.DarkGreen,
            window = this
        ),
        Ellipse(
            center = Point(0.14, -0.01),
            size = Size(0.123, 0.1),
            fillColor = Color.White,
            window = this
        ),
        Ellipse(
            center = Point(0.131, 0.0),
            size = Size(0.05, 0.07),
            fillColor = Color.Black,
            window = this
        ),
        Ellipse(
            center = Point(0.24, 0.0),
            size = Size(0.22, 0.2),
            fillColor = Color.White,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(0.2 * PI),
            endAngle = Angle(0.8 * PI),
            window = this
        ),
        Rectangle(
            center = Point(0.24, 0.13),
            size = Size(0.4, 0.04),
            fillColor = Color.DarkGray,
            window = this
        ),

        // Left leg
        Rectangle(
            center = Point(-0.3, -0.6),
            size = Size(0.2, 0.5),
            fillColor = Color.Black,
            window = this
        ),
        Ellipse(
            center = Point(-0.3, -0.85),
            size = Size(0.1, 0.1),
            fillColor = Color.Black,
            window = this
        ),

        // Right leg
        Rectangle(
            center = Point(0.3, -0.6),
            size = Size(0.2, 0.5),
            fillColor = Color.Black,
            window = this
        ),
        Ellipse(
            center = Point(0.3, -0.85),
            size = Size(0.1, 0.1),
            fillColor = Color.Black,
            window = this
        ),

        // Bottom part
        Ellipse(
            center = Point(0.0, 0.0),
            size = Size(0.75, 0.75),
            fillColor = Color.LightGray,
            startAngle = Angle(1.1 * PI),
            endAngle = Angle(1.9 * PI),
            border = Border(Color.DarkGray, 0.05),
            window = this
        ),
        Rectangle(
            center = Point(0.0, -0.23),
            size = Size(1.52, 0.05),
            fillColor = Color.DarkGray,
            window = this
        ),
        Rectangle(
            center = Point(0.0, -0.45),
            size = Size(1.25, 0.05),
            fillColor = Color.DarkGray,
            window = this
        ),
        Ellipse(
            center = Point(0.0, -0.34),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),
        Ellipse(
            center = Point(0.4, -0.34),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),
        Ellipse(
            center = Point(-0.4, -0.34),
            size = Size(0.025, 0.03),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.03),
            window = this
        ),
        Ellipse(
            center = Point(0.0, -0.475),
            size = Size(0.3, 0.275),
            fillColor = Color.LightGray,
            border = Border(Color.DarkGray, 0.05),
            startAngle = Angle(PI),
            endAngle = Angle(2 * PI),
            window = this
        ),
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