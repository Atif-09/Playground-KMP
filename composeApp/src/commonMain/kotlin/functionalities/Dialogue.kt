package functionalities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun FullWidthDialogue() {
    var showDefaultDialog by remember { mutableStateOf(false) }
    var showFullDialog by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { showDefaultDialog = true }) { Text("Default Dialogue") }
            Button(onClick = {showFullDialog = true}) { Text("Full Width Dialogue") }
        }

        if (showDefaultDialog) {
            DefaultDialogue {
                showDefaultDialog = false
            }
        }

        if (showFullDialog) {
            FullWidthDialogue {
                showFullDialog = false
            }
        }
    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultDialogue(onDismissRequest: () -> Unit) {

    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(9.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xD9000000),
                disabledContainerColor = Color(0xD9000000)
            )
        ) {
            IconButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.align(Alignment.End).padding(top = 12.dp, end = 12.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    null,
                    tint = Color.White
                )
            }

            Text(
                "Default Width \nDialogue",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 18.dp, bottom = 36.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 27.sp
            )


        }


    }
}

@Composable
fun FullWidthDialogue(onDismissRequest: () -> Unit) {

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(9.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xD9000000),
                disabledContainerColor = Color(0xD9000000)
            )
        ) {
            IconButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.align(Alignment.End).padding(top = 12.dp, end = 12.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    null,
                    tint = Color.White
                )
            }

            Text(
                "Full Width \nDialogue",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 18.dp, bottom = 36.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 27.sp
            )


        }


    }
}