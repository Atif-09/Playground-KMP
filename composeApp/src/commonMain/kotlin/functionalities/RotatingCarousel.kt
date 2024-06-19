package functionalities

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import playgroundkmp.composeapp.generated.resources.Res
import playgroundkmp.composeapp.generated.resources.dragon_1
import playgroundkmp.composeapp.generated.resources.dragon_10
import playgroundkmp.composeapp.generated.resources.dragon_2
import playgroundkmp.composeapp.generated.resources.dragon_3
import playgroundkmp.composeapp.generated.resources.dragon_4
import playgroundkmp.composeapp.generated.resources.dragon_5
import playgroundkmp.composeapp.generated.resources.dragon_6
import playgroundkmp.composeapp.generated.resources.dragon_7
import playgroundkmp.composeapp.generated.resources.dragon_8
import playgroundkmp.composeapp.generated.resources.dragon_9
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RotatingCarousel() {
    val images = listOf(
        Res.drawable.dragon_1,
        Res.drawable.dragon_2,
        Res.drawable.dragon_3,
        Res.drawable.dragon_4,
        Res.drawable.dragon_5,
        Res.drawable.dragon_6,
        Res.drawable.dragon_7,
        Res.drawable.dragon_8,
        Res.drawable.dragon_9,
        Res.drawable.dragon_10
    )

    val quantity = images.size
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        images.forEachIndexed { index, imageRes ->
            val angle = index * (360f / quantity)
            val rotationZ = rotation + angle

            val radius = 600.dp
            val angleRad = rotationZ.toRadians()
            val xOffset = (radius.value * sin(angleRad)).toFloat()

            // Adjusting camera distance to simulate 3D perspective
            val scaleFactor = calculateScaleFactor(rotationZ)
            val scale = 1 / scaleFactor

            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        rotationY = angle // Apply rotation based on the image's position in the carousel
                        translationX = xOffset
                        this.scaleX = scale
                        this.scaleY = scale
                    }
            )
        }
    }
}

// Extension function to convert degrees to radians
fun Float.toRadians(): Float = (this / 180f * PI).toFloat()

// Function to calculate scale factor based on rotation angle
fun calculateScaleFactor(rotation: Float): Float {
    val absoluteRotation = rotation.absoluteValue
    return (1 - absoluteRotation / 360f).coerceIn(0.5f, 1f)
}
