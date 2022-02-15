// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw ground
            drawRect(Color.Green, Offset(0f, 550f), Size(700f, 50f))

            // Draw house frame
            drawRect(Color.Red, Offset(450f, 50f), Size(50f, 100f))
            drawIsoscelesTriangle(Offset(50f, 50f), Size(600f, 200f), Color.LightGray)
            drawRect(Color.Gray, Offset(150f, 250f), Size(400f, 300f))

            // Draw window
            drawRect(Color.White, Offset(200f, 300f), Size(100f, 150f))
            drawRect(Color.Cyan, Offset(210f, 310f), Size(35f, 130f))
            drawRect(Color.Cyan, Offset(255f, 310f), Size(35f, 60f))
            drawRect(Color.Cyan, Offset(255f, 380f), Size(35f, 60f))

            // Draw door
            drawRect(Color.Yellow, Offset(400f, 350f), Size(100f, 200f))
            drawCircle(Color.Black, 10f, Offset(480f, 440f))

            // Draw fence
            drawRect(Color.LightGray, Offset(100f, 450f), Size(500f, 10f))
            for (currXOffset in 100..600 step 50) {
                drawRect(Color.LightGray, Offset(currXOffset.toFloat(), 450f), Size(10f, 100f))
            }
            drawRect(Color.LightGray, Offset(100f, 500f), Size(500f, 10f))
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
