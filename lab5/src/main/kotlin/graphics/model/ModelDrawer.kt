package graphics.model

import org.lwjgl.opengl.GL11.*

class ModelDrawer {
    fun draw(model: Model) {
        model.faceList.forEach { face ->
            glColor3f(0.4f, 0.27f, 0.17f)
            glBegin(GL_TRIANGLE_STRIP)
            face.elements.forEach { element ->
                val normalVertex = element.normalVertex
                val geometricVertex = element.geometricVertex

                if (normalVertex != null) {
                    glNormal3d(normalVertex.x, normalVertex.y, normalVertex.z)
                }
                glVertex3d(geometricVertex.x, geometricVertex.y, geometricVertex.z)
            }
            glEnd()
        }
    }
}