import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.landmuc.dwm.task.presentation.sign_in.SignInScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {

    MaterialTheme {
       Navigator(SignInScreen) { navigator ->
           SlideTransition(navigator)
       }
    }
}