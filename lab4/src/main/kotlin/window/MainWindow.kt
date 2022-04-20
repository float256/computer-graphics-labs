package window

import figure.Octahedron
import graphics.DirectLight
import org.lwjgl.opengl.GL11.*
import primitive.RGBA
import primitive.Vector3D

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val figure = Octahedron()
    private val mouseMoveEvent = MouseMove(this)
    private val camera = Camera(this)

    init {
        mouseMoveEvent.doOnMouseMove { offset ->
            camera.addAngle(offset.x, offset.y)
        }
    }

    override fun onDraw(frameBufferWidth: Int, frameBufferHeight: Int) {
        glEnable(GL_COLOR_MATERIAL)
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE)
        figure.draw()
    }

    override fun setupCamera() {
        camera.rotate()
    }

    override fun onInit() {
        val light = DirectLight(
            direction = Vector3D(1.0, 1.0, 3.0),
            diffuseIntensity = RGBA(0.5, 0.5, 0.5, 1.0),
            ambientIntensity = RGBA(0.2, 0.2, 0.2, 1.0),
            specularIntensity = RGBA(0.3, 0.3, 0.3, 1.0)
        )

        light.specifyLightParameters(GL_LIGHT0)

        glEnable(GL_LIGHTING)
        glEnable(GL_LIGHT0)
        glEnable(GL_DEPTH_TEST)
    }
}