package graphics.model

import primitive.RGB

data class Material(
    val ambientColor: RGB? = null,
    val ambientTexturePath: String? = null,
    val diffuseColor: RGB? = null,
    val diffuseTexturePath: String? = null,
    val specularColor: RGB? = null,
    val specularTexturePath: String? = null,
)

