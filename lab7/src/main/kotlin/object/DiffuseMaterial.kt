package `object`

import primitives.*
import kotlin.math.*
import kotlin.random.Random

class DiffuseMaterial : Material {
    companion object {
        const val LightAbsorptionCoefficient = 0.1
    }

    override fun computeReflection(ray: Ray, intersection: Intersection): Ray {
        val hitPoint = ray.origin + ray.direction * intersection.distance;
        val normal = intersection.obj.normal(hitPoint);

        val (rotX, rotY) = generateOrthogonalSystem(normal)
        val sampleDirection = uniformSampleHemisphere(
            Random.nextDouble(0.0, 1.0),
            Random.nextDouble(0.0, 1.0)
        )
        val rayDirection = Vector(
            Vector(rotX.x, rotY.x, normal.x).dot(sampleDirection),
            Vector(rotX.y, rotY.y, normal.y).dot(sampleDirection),
            Vector(rotX.z, rotY.z, normal.z).dot(sampleDirection)
        )
        return Ray(hitPoint, rayDirection)
    }

    override fun computeColor(nextColor: Vector, intersection: Intersection): Vector {
        return nextColor * intersection.obj.color * LightAbsorptionCoefficient
    }

    private fun generateOrthogonalSystem(v1: Vector): Pair<Vector, Vector> {
        val v2 = if (abs(v1.x) > abs(v1.y)) {
            val invLen = 1 / sqrt(v1.x * v1.x + v1.z * v1.z);
            Vector(-v1.z * invLen, 0.0, v1.x * invLen);
        } else {
            val invLen = 1 / sqrt(v1.y * v1.y + v1.z * v1.z);
            Vector(0.0, v1.z * invLen, -v1.y * invLen);
        }
        val v3 = v1.cross(v2)
        return Pair(v2, v3)
    }

    private fun uniformSampleHemisphere(u1: Double, u2: Double): Vector {
        val r = sqrt(1.0 - u1 * u1)
        val phi = 2 * PI * u2

        return Vector(cos(phi) * r, sin(phi) * r, u1)
    }
}