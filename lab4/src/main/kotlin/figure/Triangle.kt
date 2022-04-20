package figure

import org.lwjgl.opengl.GL11.*
import primitive.RGB
import primitive.Point3D
import primitive.Vector3D

class Triangle(
    private val color: RGB,
    private val vertices: Set<Point3D>
) {
    init {
        if (vertices.size != 3) {
            throw IllegalArgumentException("Incorrect number of vertices")
        }
    }

    fun draw() {
        glColor3d(color.red, color.green, color.blue)
        glBegin(GL_TRIANGLES)
        vertices.forEach { vertex -> glVertex3d(vertex.x, vertex.y, vertex.z) }
        specifyNormalVector()
        glEnd()
    }

    private fun specifyNormalVector() {
        val verticesList = vertices.toList()
        val firstVector = verticesList[0].toVector() - verticesList[1].toVector()
        val secondVector = verticesList[0].toVector() - verticesList[2].toVector()
        val normalVector = Vector3D.cross(firstVector, secondVector).normalize()
        glNormal3d(normalVector.x, normalVector.y, normalVector.z)
    }
}