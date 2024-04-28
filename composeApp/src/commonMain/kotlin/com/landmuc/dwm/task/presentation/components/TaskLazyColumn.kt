package com.landmuc.dwm.task.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.landmuc.dwm.task.domain.model.Task

@Composable
fun TaskLazyColumn(
    taskList: List<Task>
) {
    LazyColumn(
        modifier = Modifier.padding(top= 20.dp, bottom = 20.dp)
    ) {
        items(items = taskList) {task ->
            taskCard(
                taskTitle = task.taskTitle,
                taskFurtherInformation = task.taskFurtherInformation,
                dateDue = task.dateDue,
                isDone = task.isDone
            )
        }
    }
}