package com.landmuc.dwm.task.domain.model

data class Task(
    val taskId: String,
    val taskGroup: TaskGroup,
    val taskTitle: String,
    val taskFurtherInformation: String?,
    val timeCreated: String,
    val timeDue: String?,
    val isDone: Boolean
)