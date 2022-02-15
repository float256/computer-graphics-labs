import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawIsoscelesTriangle(offset: Offset, size: Size, color: Color) {
    val path = Path()
    path.moveTo(offset.x, size.height + offset.y)
    path.lineTo(size.width + offset.x, size.height + offset.y)
    path.lineTo(size.width / 2 + offset.x, offset.y)
    path.lineTo(offset.x, size.height + offset.y)

    drawPath(
        path = path,
        color = color
    )
}