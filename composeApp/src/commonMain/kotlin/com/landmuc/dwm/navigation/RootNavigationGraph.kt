package com.landmuc.dwm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.landmuc.dwm.authentication.presentation.sign_in.SignInScreen
import com.landmuc.dwm.authentication.presentation.sign_up.SignUpScreen
import com.landmuc.dwm.task.presentation.TaskScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.ROOTGRAPH
    ) {
        navigation(
            startDestination = Route.SIGNIN,
            route = Route.ROOTGRAPH
        ) {

            composable(Route.SIGNIN) {
                SignInScreen(
                    onSignInClick = {
                        navController.navigate(Route.TASKGRAPH) {
                            popUpTo(Route.ROOTGRAPH) { inclusive = true }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(Route.SIGNUP)
                    }
                )
            }
            composable(Route.SIGNUP) {
                SignUpScreen(
                    onBackClick = { navController.popBackStack()}
                )
            }
        }

        navigation(
            startDestination = Route.TASK,
            route = Route.TASKGRAPH
        ) {
            composable(Route.TASK) {
                TaskScreen()
            }
        }
    }
}