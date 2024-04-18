import androidx.compose.ui.window.ComposeUIViewController
import com.landmuc.dwm.di.sharedModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}