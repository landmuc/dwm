package com.landmuc.dwm.task.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.landmuc.dwm.navigation.BottomNavigationRoute
import com.landmuc.dwm.navigation.bottomNavigationRoutes
import com.landmuc.dwm.core.koin.koinViewModel
import com.landmuc.dwm.core.theme.LightBlue
import com.landmuc.dwm.navigation.TaskNavGraph
import com.landmuc.dwm.task.presentation.components.ExpandingFab

@Composable
fun TaskScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: TaskViewModel = koinViewModel(),
) {

    val controller = LocalSoftwareKeyboardController.current

    val taskTitle by viewModel.taskTitle.collectAsState()
    val taskFurtherInformation by viewModel.taskFurtherInformation.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()
    
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = {
            ExpandingFab(
                isExpanded = isExpanded,
                onExpand = viewModel::onExpand,
                addTask = {
                    viewModel.addTask()
                    controller?.hide()
                },
                taskTitle = taskTitle,
                onTaskTitleChange = viewModel::updateTaskTitle,
                taskFurtherInformation = taskFurtherInformation,
                onTaskFurtherInformationChange = viewModel::updateTaskFurtherInformation
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        TaskNavGraph(navController = navController)
    }

}

@Composable
fun BottomBar(navController: NavController) {

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationRoutes.forEach { route ->
            AddItem(
                screen = route,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationRoute,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {},
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().route.toString()) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    )
}