package functionalities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import playgroundkmp.composeapp.generated.resources.Res
import playgroundkmp.composeapp.generated.resources._01_lemon_cheesecake
import playgroundkmp.composeapp.generated.resources._02_chocolate_cake_1
import playgroundkmp.composeapp.generated.resources._03_chocolate_donuts
import playgroundkmp.composeapp.generated.resources._04_fluffy_cake
import playgroundkmp.composeapp.generated.resources._05_macaroons
import playgroundkmp.composeapp.generated.resources._06_white_cream_cake
import playgroundkmp.composeapp.generated.resources._07_honey_cake
import playgroundkmp.composeapp.generated.resources._08_cream_cupcakes
import playgroundkmp.composeapp.generated.resources._09_fruit_plate
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurvedScroll() {
    val items = listOf(
        "Lemon Cheesecake",
        "Chocolate Cake",
        "Chocolate Donuts",
        "Fluffy Cake",
        "Macaroons",
        "White Cream Cake",
        "Honey Cake",
        "Cream Cup Cake",
        "Fruit Plate"
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xff343434))
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            CurvedScrollItem(items.size) { index ->
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(
                        when (index) {
                            0 -> Res.drawable._01_lemon_cheesecake
                            1 -> Res.drawable._02_chocolate_cake_1
                            2 -> Res.drawable._03_chocolate_donuts
                            3 -> Res.drawable._04_fluffy_cake
                            4 -> Res.drawable._05_macaroons
                            5 -> Res.drawable._06_white_cream_cake
                            6 -> Res.drawable._07_honey_cake
                            7 -> Res.drawable._08_cream_cupcakes
                            else -> Res.drawable._09_fruit_plate
                        }
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = items[index],
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CurvedScrollItem(
    itemCount: Int,
    item: @Composable (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    var size = remember { mutableStateOf(IntSize.Zero) }
    val scope = rememberCoroutineScope()
    val indices = remember { IntArray(itemCount) { 0 } }

    val flingBehaviour = object : FlingBehavior {
        override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
            val value = scrollState.value
            indices.minByOrNull { abs(it - value) }?.let {
                scope.launch {
                    scrollState.animateScrollTo(it)
                }
            }
            return initialVelocity
        }
    }

    Box(
        modifier = Modifier
            .onSizeChanged {
                size.value = it
            }
    ) {
        Layout(
            content = {
                repeat(itemCount) {
                    item(it)
                }
            },
            modifier = Modifier.verticalScroll(
                scrollState, flingBehavior = flingBehaviour
            )
        ) { measurables, constraints ->
            val itemSpacing = 16.dp.roundToPx()
            var contentHeight = (itemCount - 1) * itemSpacing

            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints = constraints)
                contentHeight += if (index == 0 || index == measurables.lastIndex) {
                    placeable.height / 2
                } else {
                    placeable.height
                }
                placeable
            }

            layout(constraints.maxWidth, size.value.height + contentHeight) {
                val startOffset = size.value.height / 2 - placeables[0].height / 2
                var yPosition = startOffset

                val scrollPercent = scrollState.value.toFloat() / scrollState.maxValue

                placeables.forEachIndexed { index, placeable ->
                    val elementRatio = index.toFloat() / placeables.lastIndex
                    val interpolatedValue = cos((scrollPercent - elementRatio) * PI)
                    val indent = interpolatedValue * size.value.width / 2

                    placeable.placeRelativeWithLayer(x = indent.toInt(), y = yPosition) {
                        alpha = interpolatedValue.toFloat()
                    }
                    indices[index] = yPosition - startOffset
                    yPosition += placeable.height + itemSpacing
                }
            }
        }
    }
}