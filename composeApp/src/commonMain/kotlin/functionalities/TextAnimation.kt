import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    millisPerSymbol: Int = 100,
    authorDelay: Int = 300,
) {
    val quote = "The magic you are looking for is in the work you are avoiding"
    val author = "Unknown"
    val textDuration = (quote.length * millisPerSymbol)
    val authorDuration = (author.length * millisPerSymbol)

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xff343434)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Preoccupy Space",
                style = quoteTextStyle(color = Color.White),
                textAlign = TextAlign.Start
            )
            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    TypewriteText(
                        text = quote,
                        style = quoteTextStyle(),
                        spec = tween(
                            durationMillis = textDuration,
                            easing = LinearEasing
                        )
                    )
                    TypewriteText(
                        text = "- $author",
                        style = quoteTextStyle(),
                        spec = tween(
                            durationMillis = authorDuration,
                            delayMillis = textDuration + authorDelay,
                            easing = LinearEasing
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )
                }

            }

            Spacer(modifier= Modifier.height(64.dp))

            Text(
                text = "No Preoccupy Space",
                style = quoteTextStyle(color = Color.White),
                textAlign = TextAlign.Start
            )

            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    TypewriteText(
                        text = quote,
                        style = quoteTextStyle(),
                        spec = tween(
                            durationMillis = textDuration,
                            easing = LinearEasing
                        ),
                        preoccupySpace = false
                    )
                    TypewriteText(
                        text = "- $author",
                        style = quoteTextStyle(),
                        spec = tween(
                            durationMillis = authorDuration,
                            delayMillis = textDuration + authorDelay,
                            easing = LinearEasing
                        ),
                        preoccupySpace = false,
                        modifier = Modifier.align(Alignment.End)
                    )
                }

            }
        }
    }
}

@Composable
fun quoteTextStyle(color: Color = Color.Black) = TextStyle(
    fontSize = 16.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily.Serif,
    fontStyle = FontStyle.Italic,
    color = color
)


@Composable
fun TypewriteText(
    text: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    spec: AnimationSpec<Int> = tween(durationMillis = text.length * 100, easing = LinearEasing),
    style: TextStyle = LocalTextStyle.current,
    preoccupySpace: Boolean = true
) {
    var textToAnimate by remember { mutableStateOf("") }
    val index = remember { Animatable(initialValue = 0, typeConverter = Int.VectorConverter) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            textToAnimate = text
            index.animateTo(text.length, spec)
        } else {
            index.snapTo(0)
        }
    }

    LaunchedEffect(text) {
        if (isVisible) {
            index.snapTo(0)
            textToAnimate = text
            index.animateTo(text.length, spec)
        }
    }

    Box(modifier = modifier) {
        if (preoccupySpace && index.isRunning) {
            Text(
                text = text,
                style = style,
                modifier = Modifier.alpha(0f)
            )
        }
        Text(
            text = textToAnimate.substring(0, index.value),
            style = style
        )
    }
}