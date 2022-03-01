package image

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun errorMessageWindow(title: String, description: String) {
    val isDialogOpen = remember { mutableStateOf(true) }

    if (isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(title)
            },
            text = {
                Text(description)
            },
            buttons = {
                TextButton(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text("Close")
                }
            },
            modifier = Modifier.width(400.dp)
        )
    }
}