package shape

import org.lwjgl.opengl.GL11.*
import primitive.Border
import primitive.Color
import primitive.Point
import primitive.Size
import window.BaseWindow

class Rectangle(
    override var center: Point,
    override val size: Size,
    override val border: Border? = null,
    override val fillColor: Color,
    override val window: BaseWindow
) : Shape {
    override fun draw() {
        if (border != null) {
            fillRectangle(
                Size(
                    size.width + border.width,
                    size.height + border.width
                ),
                border.color
            )
        }
        fillRectangle(size, fillColor)
    }

    private fun fillRectangle(
        size: Size,
        color: Color
    ) {
        glColor3f(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat())

        glBegin(GL_TRIANGLE_FAN)

        glVertex2d(center.x - size.width / 2, center.y - size.height / 2)
        glVertex2d(center.x - size.width / 2, center.y + size.height / 2)
        glVertex2d(center.x + size.width / 2, center.y + size.height / 2)
        glVertex2d(center.x + size.width / 2, center.y - size.height / 2)

        glEnd()
    }
}