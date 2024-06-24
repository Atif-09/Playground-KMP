package functionalities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun OpenUrl() {
    val url = LocalUriHandler.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = {
            url.openUri("https://www.youtube.com/channel/UCvYndnOOXzFk86Z3fNGfetw")

        }){
            Text("Open Url")
        }
    }

}