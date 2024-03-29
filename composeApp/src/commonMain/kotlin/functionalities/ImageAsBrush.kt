package functionalities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import getPlatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
import playgroundkmp.composeapp.generated.resources.Res
import playgroundkmp.composeapp.generated.resources.dog

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageAsBrush() {
    val image = imageResource(Res.drawable.dog)
    val imageBrush =
        ShaderBrush(ImageShader(image))

    Box(Modifier.fillMaxSize().background(Color.Black)){
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally){

            Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Hello ${getPlatform().name}",
                style = TextStyle(
                    brush = imageBrush,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp
                )
            )
        }
    }
}