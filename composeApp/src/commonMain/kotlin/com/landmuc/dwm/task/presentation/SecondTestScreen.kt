package com.landmuc.dwm.task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.presentation.components.ExpandingFab
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun SecondTestScreen() {
    KoinContext {
        SecondTestScreenRoot()
    }
}

@Composable
fun SecondTestScreenRoot(
    viewModel: SecondTestScreenModel = koinInject()
) {
    val controller = LocalSoftwareKeyboardController.current

    val taskList by viewModel.taskList.collectAsState()
    val taskTitle by viewModel.taskTitle.collectAsState()
    val taskFurtherInformation by viewModel.taskFurtherInformation.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()
    val action by viewModel.action.collectAsState()

    LaunchedEffect(Unit) {
        //viewModel.getTasks()
        //viewModel.connectToRealTime(this)
        viewModel.getFlow()
        viewModel.subscribeToChannel()
    }

    Scaffold(
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
        floatingActionButtonPosition = FabPosition.End
    ) {paddingValues ->
        Inhalt(
            modifier = Modifier.padding(paddingValues),
            taskList = taskList,
            onCheckedChange = {task ->
                viewModel.updateTask(task = task, isDone = !task.isDone)
            },
            deleteTask = {task ->
                viewModel.deleteTask(taskId = task.taskId)
            }
        )
    }

}


@Composable
fun Inhalt(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    onCheckedChange: (Task) -> Unit,
    deleteTask: (Task) -> Unit
) {
    Column() {
        TaskLazyColumn(
            taskList = taskList,
            onCheckedChange = onCheckedChange,
            deleteTask = deleteTask
        )
    }
}