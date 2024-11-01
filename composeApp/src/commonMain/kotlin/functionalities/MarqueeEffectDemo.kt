package functionalities

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import getPlatform

@Composable
fun MarqueeEffectDemo(modifier: Modifier = Modifier) {
    val text = mutableStateOf("")

    if (getPlatform().name.contains("Android")){
        text.value = "Compose Multiplatform has finally added support for Marquee! It's soo easy to implement!"
    } else if (getPlatform().name.contains("Desktop")) {
        text.value = "Compose Multiplatform(Desktop) has finally added support for Marquee! It's soo easy to implement!"
    } else{
        text.value = "Compose Multiplatform(IOS) has finally added support for Marquee! It's soo easy to implement!"
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.basicMarquee(),
            text = text.value,
            color = Color.White
        )
    }
}