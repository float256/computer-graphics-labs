package shape

import org.lwjgl.opengl.GL11.*
import primitive.Border
import primitive.Color
import primitive.Point
import primitive.Size
import window.BaseWindow

class Triangle(
    override var center: Point,
    override val size: Size,
    override val border: Border? = null,
    override val fillColor: Color = Color.White,
    override val window: BaseWindow,
    private val direction: Direction = Direction.Top
) : Shape {
    enum class Direction {
        Top,
        Bottom,
        Right,
        Left
    }

    override fun draw() {
        if (border != null) {
            fillTriangle(
                Size(
                    size.width + border.width,
                    size.height + border.width
                ),
                border.color
            )
        }
        fillTriangle(size, fillColor)
        glTranslatef(0.5f, 0.5f, 0f)
    }

    private fun fillTriangle(size: Size, color: Color) {
        glColor3f(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat())

        glBegin(GL_TRIANGLES)

        val (firstPoint, secondPoint, thirdPoint) = when (direction) {
            Direction.Top -> Triple(
                Point(center.x, center.y + size.height / 2),
                Point(center.x + size.width / 2, center.y - size.height / 2),
                Point(center.x - size.width / 2, center.y - size.height / 2)
            )
            Direction.Bottom -> Triple(
                Point(center.x, center.y - size.height / 2),
                Point(center.x + size.width / 2, center.y + size.height / 2),
                Point(center.x - size.width / 2, center.y + size.height / 2)
            )
            Direction.Right -> Triple(
                Point(center.x + size.width / 2, center.y),
                Point(center.x - size.width / 2, center.y + size.height / 2),
                Point(center.x - size.width / 2, center.y - size.height / 2)
            )
            Direction.Left -> Triple(
                Point(center.x - size.width / 2, center.y),
                Point(center.x + size.width / 2, center.y + size.height / 2),
                Point(center.x + size.width / 2, center.y - size.height / 2)
            )
        }

        glVertex2d(firstPoint.x, firstPoint.y)
        glVertex2d(secondPoint.x, secondPoint.y)
        glVertex2d(thirdPoint.x, thirdPoint.y)

        glEnd()
    }
}