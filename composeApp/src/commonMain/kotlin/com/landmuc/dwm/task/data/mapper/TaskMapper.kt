package com.landmuc.dwm.task.data.mapper

import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.model.TaskGroup

fun TaskDto.toTask(): Task {
    val taskGroup = when(taskGroup) {
        "DAY" -> TaskGroup.DAY
        "WEEK" -> TaskGroup.WEEK
        "MONTH" -> TaskGroup.MONTH
        else -> TaskGroup.DAY
    }

    return Task(
        taskId = taskId,
        taskGroup = taskGroup,
        taskTitle = taskTitle,
        taskFurtherInformation = taskFurtherInformation,
        dateCreated = dateCreated,
        dateDue = dateDue,
        isDone = isDone
    )
}