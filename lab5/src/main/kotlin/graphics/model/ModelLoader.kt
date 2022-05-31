package graphics.model

import org.joml.Vector3d
import resourse.InputStreamLoader
import java.io.InputStream
import java.util.Scanner

class ModelLoader(
    private val inputStreamLoader: InputStreamLoader
) {
    private val materialLoader = MaterialLoader()

    private enum class LineType(val linePrefix: String) {
        GeometricVertex("v"),
        TexturedVertex("vt"),
        NormalVertex("vn"),
        Face("f"),
        Material("mtllib")
    }

    fun load(stream: InputStream): Model {
        val scanner = Scanner(stream)

        val vertices = mutableListOf<Vector3d>()
        val faces = mutableListOf<Face>()
        val materials = mutableMapOf<String, Material>()
        while (scanner.hasNext()) {
            val lineParts = scanner.nextLine().split(" ")
            when (lineParts.firstOrNull()) {
                LineType.GeometricVertex.linePrefix -> parseVertex(lineParts, vertices)
                LineType.TexturedVertex.linePrefix -> parseVertex(lineParts, vertices)
                LineType.NormalVertex.linePrefix -> parseVertex(lineParts, vertices)
                LineType.Face.linePrefix -> parseFace(lineParts, vertices, faces)
                LineType.Material.linePrefix -> materials.putAll(parseMaterials(lineParts))
            }
        }
        return Model(faces, materials)
    }

    private fun parseMaterials(lineParts: List<String>): Map<String, Material> {
        if (lineParts.size > 2) {
            throw IllegalArgumentException("Incorrect line format")
        }

        val materialsInputStream = inputStreamLoader.load(lineParts[1])
        return materialLoader.loadMaterials(materialsInputStream)
    }

    private fun parseVertex(lineParts: List<String>, vertices: MutableList<Vector3d>) {
        if (lineParts.size > 4) {
            throw IllegalArgumentException("Incorrect line format")
        }

        vertices.add(
            Vector3d(
                lineParts.getOrNull(1)?.toDouble() ?: 0.0,
                lineParts.getOrNull(2)?.toDouble() ?: 0.0,
                lineParts.getOrNull(3)?.toDouble() ?: 0.0,
            )
        )
    }

    private fun parseFace(lineParts: List<String>, vertices: MutableList<Vector3d>, faces: MutableList<Face>) {
        val linePartsWithoutPrefix = lineParts.subList(1, lineParts.size)
        val faceElements = linePartsWithoutPrefix.map { parseElement(it, vertices) }
        faces.add(Face(faceElements))
    }

    private fun parseElement(str: String, vertices: MutableList<Vector3d>): FaceElement {
        val verticesArrayPositions = str.split("/").map(String::toIntOrNull)

        if (verticesArrayPositions.size > 3) {
            throw IllegalArgumentException("Incorrect line format")
        }

        val geometricVertexPosition = verticesArrayPositions[0]!!
        val textureVertexPosition = verticesArrayPositions.getOrNull(1)
        val normalVertexPosition = verticesArrayPositions.getOrNull(2)

        val geometricVertex = vertices[geometricVertexPosition - 1]
        val textureVertex = if (textureVertexPosition != null) {
            vertices[textureVertexPosition - 1]
        } else {
            null
        }
        val normalVertex = if (normalVertexPosition != null) {
            vertices[normalVertexPosition - 1]
        } else {
            null
        }

        return FaceElement(
            geometricVertex,
            textureVertex,
            normalVertex
        )
    }
}