package graphics.model

import primitive.RGB
import java.io.InputStream
import java.util.*

class MaterialLoader {
    private enum class LineType(val linePrefix: String) {
        NewMaterial("newmtl"),
        AmbientColor("Ka"),
        AmbientTexturePath("map_Ka"),
        DiffuseColor("Kd"),
        DiffuseTexturePath("map_Kd"),
        SpecularColor("Ks"),
        SpecularTexturePath("map_Ks"),
    }

    fun loadMaterials(stream: InputStream): Map<String, Material> {
        val scanner = Scanner(stream)
        val materials = mutableMapOf<String, Material>()

        var currMaterialName = ""
        while (scanner.hasNext()) {
            val lineParts = scanner.nextLine().split(" ")

            if (lineParts.firstOrNull() == LineType.NewMaterial.linePrefix) {
                currMaterialName = initMaterial(lineParts, materials)
            } else {
                materials[currMaterialName] = when (lineParts.firstOrNull()) {
                    LineType.AmbientColor.linePrefix -> materials[currMaterialName]!!
                        .copy(ambientColor = retrieveColor(lineParts))
                    LineType.DiffuseColor.linePrefix -> materials[currMaterialName]!!
                        .copy(diffuseColor = retrieveColor(lineParts))
                    LineType.SpecularColor.linePrefix -> materials[currMaterialName]!!
                        .copy(specularColor = retrieveColor(lineParts))

                    LineType.AmbientTexturePath.linePrefix -> materials[currMaterialName]!!
                        .copy(ambientTexturePath = retrievePath(lineParts))
                    LineType.DiffuseTexturePath.linePrefix -> materials[currMaterialName]!!
                        .copy(diffuseTexturePath = retrievePath(lineParts))
                    LineType.SpecularTexturePath.linePrefix -> materials[currMaterialName]!!
                        .copy(specularTexturePath = retrievePath(lineParts))
                    else -> materials[currMaterialName]!!
                }
            }
        }
        return materials
    }

    private fun initMaterial(lineParts: List<String>, allMaterials: MutableMap<String, Material>): String {
        if (lineParts.size > 2) {
            throw IllegalArgumentException("Incorrect line format")
        }

        val materialName = lineParts[1]
        allMaterials[materialName] = Material()
        return materialName
    }

    private fun retrieveColor(lineParts: List<String>): RGB {
        if (lineParts.size > 4) {
            throw IllegalArgumentException("Incorrect line format")
        }

        return RGB(lineParts[1].toDouble(), lineParts[2].toDouble(), lineParts[3].toDouble())
    }

    private fun retrievePath(lineParts: List<String>): String {
        if (lineParts.size > 2) {
            throw IllegalArgumentException("Incorrect line format")
        }

        return lineParts[1]
    }
}