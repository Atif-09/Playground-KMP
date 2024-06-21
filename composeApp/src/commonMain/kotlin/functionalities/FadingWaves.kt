package functionalities

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import playgroundkmp.composeapp.generated.resources.Res
import playgroundkmp.composeapp.generated.resources.android


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FadingWaves(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {

        val waves = listOf(
            remember { Animatable(0f) },
            remember { Animatable(0f) },
            remember { Animatable(0f) },
            remember { Animatable(0f) },
        )

        val animationSpec = infiniteRepeatable<Float>(
            animation = tween(4000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart,
        )

        waves.forEachIndexed { index, animatable ->
            LaunchedEffect(animatable) {
                delay(index * 500L)
                animatable.animateTo(
                    targetValue = 1f, animationSpec = animationSpec
                )
            }
        }

        val dys = waves.map { it.value }

        Box(
            modifier = modifier,
            contentAlignment = Center
        ) {
            // Waves
            dys.forEach { dy ->
                Box(
                    Modifier
                        .size(81.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            scaleX = dy * 4 + 1
                            scaleY = dy * 4 + 1
                            alpha = 1 - dy
                        },
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color = Color.Green, shape = CircleShape)
                    )
                }
            }

            Box(
                Modifier
                    .size(136.dp)
                    .align(Alignment.Center)
                    .background(color = Color.White, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.android),
                    "",
                    modifier = Modifier.size(136.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}