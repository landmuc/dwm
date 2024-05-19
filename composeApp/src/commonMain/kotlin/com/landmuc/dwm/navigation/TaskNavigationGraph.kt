package com.landmuc.dwm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.landmuc.dwm.task.presentation.task_tabs.day_tab.DayTab
import com.landmuc.dwm.task.presentation.task_tabs.month_tab.MonthTab
import com.landmuc.dwm.task.presentation.task_tabs.week_tab.WeekTab

@Composable
fun TaskNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Route.TASKGRAPH,
        startDestination = BottomNavigationRoute.Day.route
    ) {
        composable(BottomNavigationRoute.Day.route) {
           DayTab(viewModel = it.sharedViewModel(navController))
        }

        composable(BottomNavigationRoute.Week.route) {
            WeekTab(viewModel = it.sharedViewModel(navController))
        }

        composable(BottomNavigationRoute.Month.route) {
            MonthTab(viewModel = it.sharedViewModel(navController))
        }
    }
}