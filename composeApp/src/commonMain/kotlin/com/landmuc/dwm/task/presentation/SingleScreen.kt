package com.landmuc.dwm.task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.landmuc.dwm.task.presentation.components.ExpandingFab
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun SingleScreen() {
    KoinContext {
        SingleScreenRoot()
    }
}

@Composable
fun SingleScreenRoot(
    screenModel: TaskViewModel = koinInject()
) {
    val controller = LocalSoftwareKeyboardController.current

    val taskList by screenModel.taskList.collectAsState()
    val taskTitle by screenModel.taskTitle.collectAsState()
    val taskFurtherInformation by screenModel.taskFurtherInformation.collectAsState()
    val isExpanded by screenModel.isExpanded.collectAsState()

    LaunchedEffect(Unit) {
        screenModel.getFlow()
        screenModel.subscribeToChannel()
    }

    Scaffold(
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
    ) {
        Column() {
            Text("SINGLE SCREEN")
            TaskLazyColumn(
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