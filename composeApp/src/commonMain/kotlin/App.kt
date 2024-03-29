import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.runtime.Composable
import functionalities.DockedSearchBarSample
import functionalities.ExposedDropdownMenu
import functionalities.FlashlightEffect
import functionalities.ImageAsBrush
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        ImageAsBrush()
        //FlashlightEffect()
        //DockedSearchBarSample()
        //ExposedDropdownMenu()


    }
}