package com.landmuc.dwm.task.presentation.task_tabs.week_tab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.landmuc.dwm.core.koin.koinViewModel
import com.landmuc.dwm.task.presentation.TaskViewModel
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn

@Composable
fun WeekTab(
    viewModel: TaskViewModel = koinViewModel<TaskViewModel>()
) {
    val controller = LocalSoftwareKeyboardController.current

    val taskList by viewModel.taskList.collectAsState()
    val taskTitle by viewModel.taskTitle.collectAsState()
    val taskFurtherInformation by viewModel.taskFurtherInformation.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()

//    LaunchedEffect(Unit) {
//        viewModel.getFlow()
//        viewModel.subscribeToChannel()
//    }

    Column() {
        TaskLazyColumn(
            taskList = taskList,
            onCheckedChange = { task ->
                viewModel.updateTask(task, isDone = !task.isDone)
            },
            deleteTask = { task ->
                viewModel.deleteTask(taskId = task.taskId)
            }
        )
    }
}