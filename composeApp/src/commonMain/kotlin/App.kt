import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.landmuc.dwm.core.theme.DWMTheme
import com.landmuc.dwm.task.presentation.sign_in.SignInScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    // before MaterialTheme {}
    DWMTheme {
       Navigator(SignInScreen) { navigator ->
           SlideTransition(navigator)
       }
    }
}