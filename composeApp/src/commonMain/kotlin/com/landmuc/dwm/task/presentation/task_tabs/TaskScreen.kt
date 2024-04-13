package com.landmuc.dwm.task.presentation.task_tabs

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.landmuc.dwm.core.theme.AccentViolet
import com.landmuc.dwm.core.theme.DarkGrey
import com.landmuc.dwm.core.theme.LightBlue
import com.landmuc.dwm.core.theme.LightBlueGrey
import com.landmuc.dwm.task.presentation.task_tabs.day_tab.DayTab
import com.landmuc.dwm.task.presentation.task_tabs.month_tab.MonthTab
import com.landmuc.dwm.task.presentation.task_tabs.week_tab.WeekTab

object TaskScreen: Screen {
    @Composable
    override fun Content() {
        TabNavigator(DayTab) {
            Scaffold(
                content = { CurrentTab() },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(DayTab)
                        TabNavigationItem(WeekTab)
                        TabNavigationItem(MonthTab)
                    }
                }
            )
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab},
        label = { Text(tab.options.title)},
        unselectedContentColor = DarkGrey,
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title
                )
            }
        }
    )
}