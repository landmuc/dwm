package com.landmuc.dwm.task.presentation.task_tabs

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.landmuc.dwm.core.theme.DarkGrey
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.model.TaskGroup
import com.landmuc.dwm.task.presentation.task_tabs.day_tab.DayTab
import com.landmuc.dwm.task.presentation.task_tabs.month_tab.MonthTab
import com.landmuc.dwm.task.presentation.task_tabs.week_tab.WeekTab

object TaskScreen: Screen {
    @Composable
    override fun Content() {
        TabNavigator(DayTab(testList)) {
            Scaffold(
                content = { CurrentTab() },
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(50)),
                    ) {
                        TabNavigationItem(DayTab(testList))
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
        selectedContentColor = Color.Green,
        unselectedContentColor = Color.White,
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

val testList: List<Task> = listOf(
    Task(
        taskId = "1",
        taskGroup = TaskGroup.DAY,
        taskTitle = "First Task",
        taskFurtherInformation = null,
        timeCreated = "09:30 am",
        timeDue = "09:30 am",
        isDone = false
    ),
    Task(
        taskId = "2",
        taskGroup = TaskGroup.DAY,
        taskTitle = "Second Task",
        taskFurtherInformation = null,
        timeCreated = "09:30 am",
        timeDue = "09:30 am",
        isDone = false
    ),
    Task(
        taskId = "3",
        taskGroup = TaskGroup.DAY,
        taskTitle = "Third Task",
        taskFurtherInformation = null,
        timeCreated = "09:30 am",
        timeDue = "09:30 am",
        isDone = true
    )

)