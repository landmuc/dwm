import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.landmuc.dwm.navigation.NavigationGraph
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {

    MaterialTheme {
        KoinContext {
            NavigationGraph()
        }
    }
}