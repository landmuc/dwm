package com.landmuc.dwm.task.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.model.TaskGroup
import com.landmuc.dwm.task.presentation.components.ExpandingFab
import com.landmuc.dwm.task.presentation.task_tabs.day_tab.DayTab
import com.landmuc.dwm.task.presentation.task_tabs.month_tab.MonthTab
import com.landmuc.dwm.task.presentation.task_tabs.week_tab.WeekTab
import org.koin.mp.KoinPlatform

object TaskScreen: Screen {
    @Composable
    override fun Content() {
        val taskScreenModel: TaskScreenModel = KoinPlatform.getKoin().get()
        val screenModel = rememberScreenModel { taskScreenModel }

        TaskScreenRoot(screenModel = screenModel)
    }
}

@Composable
private fun TaskScreenRoot(
    screenModel: TaskScreenModel
) {
    val controller = LocalSoftwareKeyboardController.current

    val taskList by screenModel.taskList.collectAsState()
    val taskTitle by screenModel.taskTitle.collectAsState()
    val taskFurtherInformation by screenModel.taskFurtherInformation.collectAsState()
    val isExpanded by screenModel.isExpanded.collectAsState()

   // LaunchedEffect(isExpanded) { screenModel.getTaskList() }

    TabNavigator(DayTab(taskList)) {
        Scaffold(
            content = { CurrentTab() },
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50)),
                ) {
                    TabNavigationItem(DayTab(taskList))
                    TabNavigationItem(WeekTab)
                    TabNavigationItem(MonthTab)
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
            floatingActionButtonPosition = FabPosition.End
        )
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
