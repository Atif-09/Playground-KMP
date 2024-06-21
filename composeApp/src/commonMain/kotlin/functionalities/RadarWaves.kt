package functionalities

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun RadarWaves() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        SearchWaves()
    }
}

@Composable
fun SearchWaves(modifier: Modifier = Modifier) {

    val isActivated by remember {
        mutableStateOf(true)
    }


    // Animations setup
    val numberOfWaves = 4
    val animationDelay = 2_500

    // Animation lists for scale, alpha, and strokeWidth
    val scalesAnimation = List(numberOfWaves) { remember { Animatable(1f) } }
    val alphasAnimation = List(numberOfWaves) { remember { Animatable(1f) } }
    val strokesAnimation = List(numberOfWaves) { remember { Animatable(2f) } }

    // Animation for each property based on activation
    if (isActivated) {
        scalesAnimation.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = index) {
                delay((animationDelay / numberOfWaves.toLong()) * index)
                animatable.animateTo(
                    targetValue = 5f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(animationDelay, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        alphasAnimation.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = index) {
                delay((animationDelay / numberOfWaves.toLong()) * index)
                animatable.animateTo(
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(animationDelay, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        strokesAnimation.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = index) {
                delay((animationDelay / numberOfWaves.toLong()) * index)
                animatable.animateTo(
                    targetValue = 0.1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(animationDelay, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }
    } else {
        scalesAnimation.forEach { animatable ->
            LaunchedEffect(Unit) { animatable.snapTo(1f) }
        }
        alphasAnimation.forEach { animatable ->
            LaunchedEffect(Unit) { animatable.snapTo(1f) }
        }
        strokesAnimation.forEach { animatable ->
            LaunchedEffect(Unit) { animatable.snapTo(2f) }
        }
    }


    Box(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .requiredHeight(768.dp)
        ) {
            NonAnimatedCircle(
                modifier = Modifier.align(Alignment.Center),
                isActivated = isActivated
            )

            scalesAnimation.forEachIndexed { index, scaleAnimatable ->
                val alpha = alphasAnimation[index].value
                val color = Color.White
                Box(
                    modifier = Modifier
                        .scale(scale = scaleAnimatable.value)
                        .size(size = 192.dp)
                        .border(
                            width = strokesAnimation[index].value.dp,
                            color = color.copy(alpha = alpha),
                            shape = CircleShape
                        )
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun NonAnimatedCircle(modifier: Modifier = Modifier, isActivated: Boolean) {
    val inactiveSizes = listOf(192, 208, 224, 240)
    val inactiveCircleAlphas = listOf(1f, 0.5f, 0.2f, 0.1f)
    val strokeWidths = listOf(2f, 0.1f, 0.5f, 1f)
    if (isActivated) {
        Box(
            modifier = modifier
                .size(192.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                )
        )
    } else {
        inactiveSizes.forEach {
            Box(
                modifier = modifier
                    .size(it.dp)
                    .border(
                        width = strokeWidths[inactiveSizes.indexOf(it)].dp,
                        color = Color(0xff343b3d).copy(
                            alpha = inactiveCircleAlphas[inactiveSizes.indexOf(
                                it
                            )]
                        ),
                        shape = CircleShape
                    )
            )
        }
    }
}


