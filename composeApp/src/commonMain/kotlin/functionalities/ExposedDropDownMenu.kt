package functionalities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu() {
    var isExpand by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        ExposedDropdownMenuBox(
            expanded = isExpand,
            onExpandedChange = { isExpand = !isExpand }
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpand) },
                colors = TextFieldDefaults.colors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = isExpand, onDismissRequest = { isExpand = false }) {
                DropdownMenuItem(
                    text = { Text("Male") },
                    onClick = {
                        gender = "Male"
                        isExpand = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Female") },
                    onClick = {
                        gender = "Female"
                        isExpand = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Other") },
                    onClick = {
                        gender = "Other"
                        isExpand = false
                    }
                )
            }
        }
    }
}