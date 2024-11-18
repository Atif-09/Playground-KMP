import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import functionalities.CurvedScroll
import functionalities.FadingWaves
import functionalities.HideKeyboardAndFocus
import functionalities.MarqueeEffectDemo
import functionalities.OpenUrl
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import playgroundkmp.composeapp.generated.resources.Res
import playgroundkmp.composeapp.generated.resources.image_1
import playgroundkmp.composeapp.generated.resources.image_2
import playgroundkmp.composeapp.generated.resources.image_3
import playgroundkmp.composeapp.generated.resources.image_4
import playgroundkmp.composeapp.generated.resources.image_5
import playgroundkmp.composeapp.generated.resources.image_6
import playgroundkmp.composeapp.generated.resources.image_7
import playgroundkmp.composeapp.generated.resources.image_8
import kotlin.math.sin

@Composable
@Preview
fun App() {
    MaterialTheme {
        HideKeyboardAndFocus()
    }
}



























@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UncatinedCarousal() {

    val items = listOf(
        Res.drawable.image_1,
        Res.drawable.image_2,
        Res.drawable.image_3,
        Res.drawable.image_4,
        Res.drawable.image_5,
        Res.drawable.image_6,
        Res.drawable.image_7,
        Res.drawable.image_8,
    )
    HorizontalUncontainedCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier.fillMaxWidth().height(221.dp),
        itemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier.height(205.dp).maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(item),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}