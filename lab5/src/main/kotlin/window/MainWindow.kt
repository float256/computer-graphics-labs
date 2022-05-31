package window

import com.google.common.io.Resources
import graphics.light.DirectLight
import graphics.model.Model
import graphics.model.ModelDrawer
import graphics.model.ModelLoader
import org.joml.Matrix4dc
import org.joml.Vector3d
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import primitive.RGBA
import primitive.Size
import resourse.ResourceStreamLoader
import window.camera.Camera
import kotlin.math.PI

class MainWindow(
    width: Int,
    height: Int,
    title: String
) : BaseWindow(width, height, title) {
    private val mouseMoveEvent = MouseMoveEventHandler(this)
    private val camera = Camera(0.25)
    private val modelLoader = ModelLoader(ResourceStreamLoader())
    private val modelDrawer = ModelDrawer()
    private val model: Model

    init {
        Resources.getResource("untitled.obj").openStream().use { modelFileStream ->
            model = modelLoader.load(modelFileStream!!)
        }
        subscribeOnCameraEvents()
    }

    override fun onDraw(frameBufferSize: Size<Int>) {
        glEnable(GL_COLOR_MATERIAL)
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE)
        modelDrawer.draw(model)
    }

    override fun setupCamera() {
        setModelViewMatrix(camera.getCameraMatrix())
    }

    override fun onInit() {
        val light = DirectLight(
            direction = Vector3d(1.0, 1.0, 3.0),
            diffuseIntensity = RGBA(0.05, 0.05, 0.05, 1.0),
            ambientIntensity = RGBA(0.05, 0.05, 0.05, 1.0),
            specularIntensity = RGBA(0.03, 0.03, 0.03, 1.0)
        )

        glEnable(GL_LIGHTING)
        glEnable(GL_LIGHT0)
        glEnable(GL_DEPTH_TEST)
        glEnable(GL_CULL_FACE or GL_CULL_FACE_MODE)
        glColorMaterial(GL_FRONT, GL_DIFFUSE)
        //glfwSetInputMode(handle, GLFW_CURSOR, GLFW_CURSOR_DISABLED)
        light.specifyLightParameters(GL_LIGHT0)
    }

    private fun subscribeOnCameraEvents() {
        mouseMoveEvent.doOnMouseMove { offset ->
            val widthToInitialValueRatio = width / initialWidth
            val heightToInitialValueRatio = height / initialHeight
            val xAngle = offset.y * PI / width * widthToInitialValueRatio
            val yAngle = offset.x * PI / height * heightToInitialValueRatio
            camera.rotate(xAngle, yAngle)
        }
    }

    private fun setModelViewMatrix(matrix: Matrix4dc) {
        stackPush().use { stack ->
            glMatrixMode(GL_MODELVIEW)
            glLoadMatrixd(matrix.get(stack.mallocDouble(16)))
        }
    }
}