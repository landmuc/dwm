package com.landmuc.dwm.task.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.landmuc.dwm.Navigation.BottomNavigationRoute
import com.landmuc.dwm.Navigation.bottomNavigationRoutes
import com.landmuc.dwm.core.theme.LightBlue
import com.landmuc.dwm.core.theme.TextBlack
import com.landmuc.dwm.task.presentation.components.ExpandingFab
import com.landmuc.dwm.task.presentation.task_tabs.day_tab.DayTab
import com.landmuc.dwm.task.presentation.task_tabs.month_tab.MonthTab
import com.landmuc.dwm.task.presentation.task_tabs.week_tab.WeekTab
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun TaskScreen(
    navController: NavHostController
) {
    KoinContext {
        TaskScreenRoot(navController = navController)
    }
}

@Composable
private fun TaskScreenRoot(
   navController: NavHostController,
    screenModel: TaskViewModel = koinInject()
) {
    val controller = LocalSoftwareKeyboardController.current

    val taskList by screenModel.taskList.collectAsState()
    val taskTitle by screenModel.taskTitle.collectAsState()
    val taskFurtherInformation by screenModel.taskFurtherInformation.collectAsState()
    val isExpanded by screenModel.isExpanded.collectAsState()
    val action by screenModel.action.collectAsState()

    LaunchedEffect(Unit) {
        //screenModel.getTasks()
        //screenModel.connectToRealTime(this)
        screenModel.getFlow()
        screenModel.subscribeToChannel()
    }


    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(50)),
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavigationRoutes.forEach { route ->
                    BottomNavigationItem(
                        modifier = Modifier.background(MaterialTheme.colors.primary),
                        selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                        onClick = {
                                  navController.navigate(route.route) {
                                      // Pop up to the start destination of the graph to
                                      // avoid building up a large stack of destinations
                                      // on the back stack as users select items
                                      popUpTo(navController.graph.findStartDestination().toString()) { saveState = true }

                                      // Avoid multiple copies of the same destination when
                                      // reselecting the same item
                                      launchSingleTop = true
                                      // Restore state when reselecting a previously selected item
                                      restoreState = true
                                  }
                        },
                        label = { Text(route.route)},
                        icon = {},
                        selectedContentColor = TextBlack,
                        unselectedContentColor = LightBlue
                    )
                }
            }
        },
        floatingActionButton = {
            ExpandingFab(
                isExpanded = isExpanded,
                onExpand = screenModel::onExpand,
                addTask = {
                    screenModel.addTask()
                    controller?.hide()
                },
                taskTitle = taskTitle,
                onTaskTitleChange = screenModel::updateTaskTitle,
                taskFurtherInformation = taskFurtherInformation,
                onTaskFurtherInformationChange = screenModel::updateTaskFurtherInformation
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Text("TASK SCREEN")
            NavHost(
                navController = navController,
                startDestination = BottomNavigationRoute.Day.route
            ) {
                composable(BottomNavigationRoute.Day.route) {
                    DayTab(
                        action = action,
                        taskList = taskList,
                        onCheckedChange = { task ->
                            screenModel.updateTask(task = task, isDone = !task.isDone)
                        },
                       deleteTask = { task ->
                           screenModel.deleteTask(taskId = task.taskId)
                       }
                    )
                }
            }
        }
    )
}



//@Composable
//private fun RowScope.TabNavigationItem(tab: Tab) {
//    val tabNavigator = LocalTabNavigator.current
//
//    BottomNavigationItem(
//        modifier = Modifier.background(MaterialTheme.colors.primary),
//        selected = tabNavigator.current == tab,
//        onClick = { tabNavigator.current = tab},
//        label = { Text(tab.options.title)},
//        selectedContentColor = TextBlack,
//        unselectedContentColor = LightBlue,
//        icon = {
//            tab.options.icon?.let {
//                Icon(
//                    painter = it,
//                    contentDescription = tab.options.title
//                )
//            }
//        }
//    )
//}

//TabNavigator(
//DayTab(
//action = action,
//taskList,
//onCheckedChange = {task ->
//    screenModel.updateTask(task = task, isDone = !task.isDone)
//},
//deleteTask = {task ->
//    screenModel.deleteTask(taskId = task.taskId)
//}
//)
//)