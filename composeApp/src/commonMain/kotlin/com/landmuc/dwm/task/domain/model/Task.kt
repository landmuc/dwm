package com.landmuc.dwm.task.domain.model

data class Task(
    val taskId: Int,
    val taskGroup: TaskGroup,
    val taskTitle: String,
    val taskFurtherInformation: String?,
    val dateCreated: String,
    val dateDue: String?,
    val isDone: Boolean
)