package image

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun image(imageViewModel: ImageViewModel) = when (val path = imageViewModel.path) {
    null -> {}
    else -> File(path).inputStream().use { fileInputStream ->
        val image = try {
            loadImageBitmap(fileInputStream)
        } catch (e: IllegalArgumentException) {
            null
        }

        when (image) {
            null -> errorMessageWindow(
                "Incorrect file format",
                "Selected file is not an image. Choose another file"
            )
            else -> Image(
                bitmap = image,
                modifier = Modifier
                    .width(100.dp)
                    .offset(
                        x = imageViewModel.imagePosition.x.dp,
                        y = imageViewModel.imagePosition.y.dp
                    )
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consumeAllChanges()
                                imageViewModel.move(dragAmount)
                            }
                        )
                    },
                contentDescription = "Selected Image"
            )
        }
    }
}