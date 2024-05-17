package com.landmuc.dwm.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.landmuc.dwm.authentication.presentation.sign_in.SignInScreen
import com.landmuc.dwm.authentication.presentation.sign_in.SignInScreenRoot
import com.landmuc.dwm.task.presentation.SecondTestScreen
import com.landmuc.dwm.task.presentation.SingleScreen
import com.landmuc.dwm.task.presentation.TaskScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.SIGNIN
    ) {
        composable(Route.SIGNIN) {
            SignInScreen(
                onSignInClick = { navController.navigate(Route.SECOND) },
                onSignUpClick = { navController.navigate(Route.SIGNUP) }
            )
        }
        composable(Route.SIGNUP) {}
        composable(Route.TASK) {
            TaskScreen(navController = navController)
        }
        composable(Route.SINGLESCREEN) {
            SingleScreen()
        }
        composable(Route.SECOND) {
            SecondTestScreen()
        }
    }
}