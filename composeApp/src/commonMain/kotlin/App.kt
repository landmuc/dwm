import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.landmuc.dwm.Navigation.NavigationScreen
import com.landmuc.dwm.authentication.presentation.sign_in.SignInScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {

    MaterialTheme {
      NavigationScreen()
    }
}