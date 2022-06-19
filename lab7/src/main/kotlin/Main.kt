import file.PPMImageSaver
import `object`.DiffuseMaterial
import `object`.Plane
import `object`.SpecularMaterial
import `object`.Sphere
import common.Ray
import common.Vector
import scene.PerspectiveCamera
import scene.Scene
import kotlin.math.PI
import kotlin.random.Random

const val StopProbability = 0.1
const val Width = 500
const val Height = 500
const val FOV = PI / 4
const val NumberOfSamples = 50

fun isNeedToStop(): Boolean {
    return Random.nextDouble(0.0, 1.0) < StopProbability
}

fun getRandomRayShift(): Vector {
    return Vector(
        Random.nextDouble(-1.0, 1.0) / 500,
        Random.nextDouble(-1.0, 1.0) / 500,
        0.0
    )
}

fun trace(prevRay: Ray, scene: Scene, color: Vector, depth: Int = 0): Pair<Ray, Vector> {
    if ((depth > 5) && isNeedToStop()) {
        return Pair(prevRay, color)
    }

    val intersection = scene.intersect(prevRay) ?: return Pair(prevRay, color)
    val obj = intersection.obj
    val colorWithEmission = color + Vector(obj.emission, obj.emission, obj.emission)
    val currRay = obj.material.computeReflection(prevRay, intersection)
    val (nextRay, nextColor) = trace(currRay, scene, colorWithEmission, depth + 1)
    val currColor = colorWithEmission + obj.material.computeColor(nextColor, intersection)
    return Pair(nextRay, currColor)
}

fun generateScene(): Scene {
    return Scene(
        listOf(
            Plane(
                2.5,
                Vector(0.0, 1.0, 0.0),
                Vector(2.0, 2.0, 2.0),
                0.0,
                DiffuseMaterial()
            ),
            Plane(
                5.5,
                Vector(0.0, 0.0, 1.0),
                Vector(6.0, 6.0, 6.0),
                0.0,
                DiffuseMaterial()
            ),
            Plane(
                2.75,
                Vector(1.0, 0.0, 0.0),
                Vector(10.0, 10.0, 2.0),
                0.0,
                DiffuseMaterial()
            ),
            Plane(
                2.75,
                Vector(-1.0, 0.0, 0.0),
                Vector(2.0, 10.0, 2.0),
                0.0,
                DiffuseMaterial()
            ),
            Plane(
                3.0,
                Vector(0.0, -1.0, 0.0),
                Vector(10.0, 2.0, 2.0),
                0.0,
                DiffuseMaterial()
            ),
            Plane(
                0.5,
                Vector(0.0, 0.0, -1.0),
                Vector(6.0, 6.0, 6.0),
                0.0,
                DiffuseMaterial()
            ),

            Sphere(
                Vector(-1.75,-1.95,-3.1),
                0.75,
                Vector(8.0, 4.0, 8.0),
                0.0,
                SpecularMaterial()
            ),
            Sphere(
                Vector(2.0,-2.0,-3.7),
                1.5,
                Vector(4.0, 4.0, 8.0),
                0.0,
                DiffuseMaterial()
            ),
            Sphere(
                Vector(0.0,1.9,-3.0),
                0.5,
                Vector(),
                10000.0,
                DiffuseMaterial()
            ),
        )
    )
}

fun main() {
    val scene = generateScene()
    val camera = PerspectiveCamera(FOV, Width, Height)
    val imageSaver = PPMImageSaver()
    val pixels = Array(Height) {
        Array(Width) { Vector() }
    }

    for (colIndex in (0 until Height)) {
        for (rowIndex in (0 until Width)) {
            repeat(NumberOfSamples) {
                val origin = Vector()
                val direction = (camera.transform(rowIndex, colIndex) + getRandomRayShift()).normalize()
                val ray = Ray(origin, direction)
                val (_, newColor) = trace(ray, scene, Vector())
                pixels[colIndex][rowIndex] = pixels[colIndex][rowIndex] + newColor / NumberOfSamples.toDouble()
            }
        }
    }

    imageSaver.save(pixels, "image.ppm")
}