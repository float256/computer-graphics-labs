package shape

import org.lwjgl.opengl.GL11.*
import primitive.*
import window.BaseWindow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Ellipse(
    override var center: Point,
    override val size: Size,
    override val border: Border? = null,
    override val fillColor: Color = Color.White,
    override val window: BaseWindow,
    private val startAngle: Angle = Angle(0.0),
    private val endAngle: Angle = Angle(2 * PI),
    private val numberOfPoints: Int = 30
) : Shape {
    init {
        if (numberOfPoints < 0) {
            throw IllegalArgumentException("Incorrect number of points")
        }
        if (startAngle.value >= endAngle.value) {
            throw IllegalArgumentException("Incorrect angles")
        }
    }

    override fun draw() {
        if (border != null) {
            fillEllipse(
                Size(
                    size.width + border.width,
                    size.height + border.width
                ),
                border.color
            )
        }
        fillEllipse(size, fillColor)
    }

    private fun fillEllipse(
        size: Size,
        color: Color
    ) {
        glColor3f(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat())

        glBegin(GL_POLYGON)

        (0..numberOfPoints).map { numberOfOperation ->
            val currAngle = startAngle.value + (endAngle.value - startAngle.value) * numberOfOperation / numberOfPoints
            val dx = size.width * cos(currAngle)
            val dy = size.height * sin(currAngle)
            glVertex2d(dx + center.x, dy + center.y)
        }

        glEnd()
    }
}