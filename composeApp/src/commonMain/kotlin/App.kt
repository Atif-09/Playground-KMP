import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import functionalities.AccordionLazyRow
import functionalities.RotatingCarousel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        //ImageAsBrush()
        //FlashlightEffect()
        //DockedSearchBarSample()
        //ExposedDropdownMenu()
        //BottomSheet()
        //ParallaxCarousel()
        //FullWidthDialogue()
        //SlideToUnlockUI()
        //ListAnimation()
        //RadarWaves()
       /* Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            AccordionLazyRow()
        }*/
        RotatingCarousel()

    }
}