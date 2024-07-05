package functionalities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import expect.getTextToSpeech

@Composable
fun TextToSpeech() {
    val tts = getTextToSpeech()
    val text = "This a demonstration of Text To Speech in Kotlin and Compose Multiplatform"

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text)

            Button(onClick = {
                tts.speak(text)
            }) {
                Text("Start")
            }
        }
    }

}