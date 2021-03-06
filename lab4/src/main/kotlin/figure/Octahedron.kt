package figure

import primitive.RGB
import primitive.Point3D

class Octahedron {
    private val sides: List<Triangle> = listOf(
        Triangle(RGB.LightGray, setOf(
            Point3D(-1.0, 0.0, 0.0),
            Point3D(0.0, 1.0, 0.0),
            Point3D(0.0, 0.0, 1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(-1.0, 0.0, 0.0),
            Point3D(0.0, 1.0, 0.0),
            Point3D(0.0, 0.0, -1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(-1.0, 0.0, 0.0),
            Point3D(0.0, -1.0, 0.0),
            Point3D(0.0, 0.0, 1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(-1.0, 0.0, 0.0),
            Point3D(0.0, -1.0, 0.0),
            Point3D(0.0, 0.0, -1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(1.0, 0.0, 0.0),
            Point3D(0.0, 1.0, 0.0),
            Point3D(0.0, 0.0, 1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(1.0, 0.0, 0.0),
            Point3D(0.0, 1.0, 0.0),
            Point3D(0.0, 0.0, -1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(1.0, 0.0, 0.0),
            Point3D(0.0, -1.0, 0.0),
            Point3D(0.0, 0.0, 1.0),
        )),
        Triangle(RGB.LightGray, setOf(
            Point3D(1.0, 0.0, 0.0),
            Point3D(0.0, -1.0, 0.0),
            Point3D(0.0, 0.0, -1.0),
        ))
    )

    fun draw() {
        sides.forEach(Triangle::draw)
    }
}