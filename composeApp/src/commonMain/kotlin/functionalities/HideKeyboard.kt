package functionalities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun HideKeyboardAndFocus() {
    val text = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        Modifier.fillMaxSize().clickable(
            MutableInteractionSource(),
            indication = null,
            enabled = true,
            role = null,
            onClickLabel = null,
            onClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        contentAlignment = Alignment.Center
    ) {
        Column {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                singleLine = true
            )
        }
    }
}