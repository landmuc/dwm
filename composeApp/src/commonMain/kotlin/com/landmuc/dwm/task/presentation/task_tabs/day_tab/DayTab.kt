package com.landmuc.dwm.task.presentation.task_tabs.day_tab

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.day_tab
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun DayTab(
    action: String,
    taskList: List<Task>,
    onCheckedChange: (Task) -> Unit,
    deleteTask: (Task) -> Unit
) {
    Column() {
        Text(action)
        TaskLazyColumn(
            taskList = taskList,
            onCheckedChange = onCheckedChange,
            deleteTask = deleteTask
        )
    }
}