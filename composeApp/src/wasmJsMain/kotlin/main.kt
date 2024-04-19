import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.landmuc.dwm.di.sharedModule
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
}

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}