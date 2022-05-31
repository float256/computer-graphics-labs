package graphics.model

import org.lwjgl.opengl.GL11.*

class ModelDrawer {
    fun draw(model: Model) {
        model.faceList.forEach { face ->
            glColor3f(0.4f, 0.27f, 0.17f)
            drawFace(face)
        }
    }

    private fun drawFace(face: Face) {
        val firstVertex = face.elements.first()
        for(triangleStartIndex in (1..(face.elements.size - 2))) {
            val secondVertex = face.elements[triangleStartIndex]
            val thirdVertex = face.elements[triangleStartIndex + 1]
            glBegin(GL_TRIANGLES)
            drawVertex(firstVertex)
            drawVertex(secondVertex)
            drawVertex(thirdVertex)
            glEnd()
        }
    }

    private fun drawVertex(vertex: FaceElement) {
        val normalVertex = vertex.normalVertex
        val textureVertex = vertex.textureVertex
        val geometricVertex = vertex.geometricVertex
        if (normalVertex != null) {
            glNormal3d(normalVertex.x, normalVertex.y, normalVertex.z)
        }
        if (textureVertex != null) {
            glTexCoord2d(textureVertex.x, textureVertex.y)
        }
        glVertex3d(geometricVertex.x, geometricVertex.y, geometricVertex.z)
    }
}