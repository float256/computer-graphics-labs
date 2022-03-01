package menu

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import compose.icons.FeatherIcons
import compose.icons.feathericons.File

@Composable
fun appMenu(viewModel: AppMenuViewModel) {
    ExtendedFloatingActionButton(
        onClick = viewModel::onSelectImageButtonClicked,
        icon = {
            Icon(
                FeatherIcons.File,
                contentDescription = "File"
            )
        },
        text = { Text("Select Image") }
    )
    fileDialog(null, viewModel.isFileDialogOpen(), viewModel::onFileSelected)
}